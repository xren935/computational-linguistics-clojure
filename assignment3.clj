(comment "Make sure that you follow the instructions carefully and use the right procedure names, inputs, and outputs")

;; Here are the word tokes from Moby Dick
(def moby-word-tokens '(CALL me Ishmael . Some years ago never mind
     how long precisely having little or no money in my purse , and
     nothing particular to interest me on shore , I thought I would
     sail about a little and see the watery part of the world .  It is
     a way I have of driving off the spleen , and regulating the
     circulation . Whenever I find myself growing grim about the mouth
     whenever it is a damp , drizzly November in my soul whenever I
     find myself involuntarily pausing before coffin warehouses , and
     bringing up the rear of every funeral I meet and especially
     whenever my hypos get such an upper hand of me , that it requires
     a strong moral principle to prevent me from deliberately stepping
     into the street , and methodically knocking people's hats off
     then , I account it high time to get to sea as soon as I can .
     This is my substitute for pistol and ball . With a philosophical
     flourish Cato throws himself upon his sword I quietly take to the
     ship .  There is nothing surprising in this . If they but knew it
     , almost all men in their degree , some time or other , cherish
     very nearly the same feelings toward the ocean with me .))

;see if w is in l
(defn member-of-list? [w l]
  (if (empty? l)
    false
    (if (= w (first l))
      true
      (member-of-list? w (rest l)))))

;; helper function, my-member-of-list? 
;; cast Symbol to a String to help compare the words
;; Because without the name funciton a Symbol type doesn't equal to a String type even if they have the same values :/ 
(defn my-member-of-list? [w l]
  (if (empty? l)
    false
    (if (= (name w) (name (first l)))
      true
      (my-member-of-list? w (rest l)))))

;; Q1
(defn get-vocabulary [word-tokens vocab]
 (if (empty? word-tokens)
     vocab
     (if (member-of-list? (first word-tokens) vocab)
       (get-vocabulary (rest word-tokens) vocab)
       (get-vocabulary (rest word-tokens) (conj vocab (first word-tokens)))
     )
 )
)  

(def moby-vocab (get-vocabulary moby-word-tokens `()))
;; moby-vocab contains all unique words in moby-word-tokens in backwards order
;; (println moby-vocab) 

;; Q2
(defn get-count-of-word [w word-tokens count]
 (if (member-of-list? w word-tokens)
   (if (= (compare w (first word-tokens)) 0) 
     (+ 1 (get-count-of-word w (rest word-tokens) count)) 
     (get-count-of-word w (rest word-tokens) count))
   0
  )
)
;; (def vo (list "man" "the" "is"))
;; (def word-to (list "man" "the" "is" "is"))
;; (print (get-count-of-word "is" word-to vo))

(defn get-word-counts [vocab word-tokens]
  (let [count-word (fn [w] 
                     (get-count-of-word w word-tokens 0))]
    (map count-word vocab)))
;; (def word-tokens (list 'the 'man 'is 'is))
;; (def vocab (list 'man 'the 'is))
;; (print (get-word-counts vocab word-tokens))

(defn flip [p]
  (if (< (rand 1) p)
    true
    false))

(defn normalize [params]
  (let [sum (apply + params)]
    (map (fn [x] (/ x sum)) params)))

(defn sample-categorical [outcomes params]
  (if (flip (first params))
    (first outcomes)
    (sample-categorical (rest outcomes) 
			(normalize (rest params)))))

(defn create-uniform-distribution [outcomes]
  (let [num-outcomes (count outcomes)]
    (map (fn [x] (/ 1 num-outcomes))
	 outcomes)))

;; get-word-counts takes a word and a wordList, 
;; and returns the number of times each word in vocab occurs in the list
(def moby-word-frequencies (get-word-counts moby-vocab moby-word-tokens))
;; (println moby-word-frequencies)
;; (println moby-vocab)

;; create-uniform-distribution takes a list of length n and returns a list containing the number 1/n repeated n times
;; sample-categorical samples a categorical distribution 
(defn sample-uniform-BOW-setence [n vocab]
  (def uniform-distrib (create-uniform-distribution vocab))
  ;; get one word 
  (if (= n 0)
      '()
       (cons (sample-categorical vocab uniform-distrib) (sample-uniform-BOW-setence (- n 1) vocab))
  )
)
;; (println (sample-uniform-BOW-setence 6 (list "a" "b" "c")))

;; compute-uniform-BOW-prob takes a list of all vocabs in a language and a sentence
;; and returns the probability of that sentence being formed
;; helper function, returns true if all words in sentence are in vocab; fale if otherwise
(defn check-if-word-in-lang [vocab sentence]
  (if (= (count sentence) 0) 
    true 
    (if (member-of-list? (first sentence) vocab)
        (check-if-word-in-lang vocab (rest sentence))
        false
    ) 
  )
)

(defn exp [x n]
  (reduce * (repeat n x)))

;; Q5
(defn compute-uniform-BOW-prob [vocab sentence] 
   (def oneW (/ 1 (count vocab)))
   (def wordCount (count sentence))
   (exp oneW wordCount)
)
;; (def voca (list "the" "a" "very"))
;; (def sent (list "every" "every"))
;; (println (compute-uniform-BOW-prob voca sent))
;; (print (compute-uniform-BOW-prob (list "a" "b" "c" "d") (list "a" "b" "c")))

;; Question 6
;; (def sampleA (sample-uniform-BOW-setence 3 moby-vocab))
;; (def sampleB (sample-uniform-BOW-setence 3 moby-vocab))
;; (def sampleC (sample-uniform-BOW-setence 3 moby-vocab))
;; (def proA (compute-uniform-BOW-prob moby-vocab sampleA))
;; (def proB (compute-uniform-BOW-prob moby-vocab sampleB))
;; (def proC (compute-uniform-BOW-prob moby-vocab sampleC))
;; (println proA)
;; (println proB)
;; (println proC)

;; sample-uniform-BOW-setence takes a number n and a list of vocabs 
;; and returns a sentence of length n madeup using vocabs
;; (def sample1 (sample-uniform-BOW-setence 3 moby-vocab))
(defn sample-BOW-sentence [len vocabulary probabilities]
  (if (= len 0)
    '()
    (cons (sample-categorical vocabulary probabilities)
	  (sample-BOW-sentence (- len 1) vocabulary probabilities))))


;; the probability of a word is itsFrequency / totalNoOfWords 
;; moby-word-probabilities is a list of prob. of every word in moby-words
;; (println moby-vocab)
;; (println (count moby-vocab))
;; (println moby-word-frequencies)
;; (println (count moby-word-tokens))

;; Q7
(def moby-word-probabilities (normalize moby-word-frequencies))

 

;; sample from moby-vocab using moby-word-probabilities
;; Q8 
(def sentence (sample-BOW-sentence 3 moby-vocab moby-word-probabilities))
;; (println moby-vocab)
;; (println moby-word-probabilities)
;; (println sentence)

;; Q9
;; outcomes is a list of words 
;; probs represents a probability distribution over the elements of outcomes
;; return the prob of word w in outcomes
(defn lookup-probability [w outcomes probs]
  (if (my-member-of-list? w outcomes) 
      (if (= (name (first outcomes)) w) 
          (first probs) 
          (lookup-probability w (rest outcomes) (rest probs))
      )
      0
  )
)
;; (println (lookup-probability "same" moby-vocab moby-word-probabilities))
;; (def vv (list "with" "ocean" "toward" "feelings" "same" "nearly" "very" "cherish" "other" "some" "degree" "their" "men" "all" "almost" "knew" "but" "they" "If" "this" "surprising" "There" "ship" "take" "quietly" "sword" "his" "upon" "himself" "throws" "Cato" "flourish" "philosophical" "With" "ball" "pistol" "for" "substitute" "This" "can" "soon" "as" "sea" "time" "high" "account" "then" "hats" "people's" "knocking" "methodically" "street" "into" "stepping" "deliberately" "from" "prevent" "principle" "moral" "strong" "requires" "that" "hand" "upper" "an" "such" "get" "hypos" "especially" "meet" "funeral" "every" "rear" "up" "bringing" "warehouses" "coffin" "before" "pausing" "involuntarily" "soul" "November" "drizzly" "damp" "it" "whenever" "mouth" "grim" "growing" "myself" "find" "Whenever" "circulation" "regulating" "spleen" "off" "driving" "have" "way" "is" "It" "world" "of" "part" "watery" "the" "see" "a" "about" "sail" "would" "thought" "I" "shore" "on" "interest" "to" "particular" "nothing" "and" "purse" "my" "in" "money" "no" "or" "little" "having" "precisely" "long" "how" "mind" "never" "ago" "years" "Some" "." "Ishmael" "me" "CALL" ))
;; (def pp (list 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 2/209 1/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 4/209 3/209 1/209 1/209 1/209 2/209 2/209 1/209 1/209 1/209 1/209 2/209 1/209 1/209 1/209 4/209 1/209 1/209 4/209 1/209 1/209 10/209 1/209 5/209 2/209 1/209 1/209 1/209 9/209 1/209 1/209 1/209 5/209 1/209 2/209 7/209 1/209 4/209 4/209 1/209 1/209 2/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 8/209 1/209 5/209 1/209))


(defn product [l]
  (apply * l))
;; Q10 
;; compute-uniform-BOW-prob takes a list of all vocabs in a language and a sentence
;; and returns the probability of that sentence being formed
(defn compute-BOW-prob [sentence vocabulary probabilities]
  (if (empty? sentence) 1
      (product (list (lookup-probability (first sentence) vocabulary probabilities) (compute-BOW-prob (rest sentence) vocabulary probabilities)))
  )
)
;(println (compute-BOW-prob (list "a" "b" "a") (list "a" "b" "c" "d") (list 0.1 0.3 0.4 0.2)))

;; Q11 
;; (def sentenceOne (list "throws" "I" "sword"))
;; (def sentenceTwo (list "their" "the" "I"))
(def sentenceThree (list "get" "the" "but"))
;; (def probOne (compute-BOW-prob sentenceOne moby-vocab moby-word-probabilities))
;; (println probOne)
;; (def probTwo (compute-BOW-prob sentenceTwo moby-vocab moby-word-probabilities))
;; (println probTwo)
(def probThree (compute-BOW-prob sentenceThree moby-vocab moby-word-probabilities))
;; (println probThree)
;; (def vv (list "with" "ocean" "toward" "feelings" "same" "nearly" "very" "cherish" "other" "some" "degree" "their" "men" "all" "almost" "knew" "but" "they" "If" "this" "surprising" "There" "ship" "take" "quietly" "sword" "his" "upon" "himself" "throws" "Cato" "flourish" "philosophical" "With" "ball" "pistol" "for" "substitute" "This" "can" "soon" "as" "sea" "time" "high" "account" "then" "hats" "people's" "knocking" "methodically" "street" "into" "stepping" "deliberately" "from" "prevent" "principle" "moral" "strong" "requires" "that" "hand" "upper" "an" "such" "get" "hypos" "especially" "meet" "funeral" "every" "rear" "up" "bringing" "warehouses" "coffin" "before" "pausing" "involuntarily" "soul" "November" "drizzly" "damp" "it" "whenever" "mouth" "grim" "growing" "myself" "find" "Whenever" "circulation" "regulating" "spleen" "off" "driving" "have" "way" "is" "It" "world" "of" "part" "watery" "the" "see" "a" "about" "sail" "would" "thought" "I" "shore" "on" "interest" "to" "particular" "nothing" "and" "purse" "my" "in" "money" "no" "or" "little" "having" "precisely" "long" "how" "mind" "never" "ago" "years" "Some" "." "Ishmael" "me" "CALL" ))
;; (def pp (list 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 2/209 1/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 4/209 3/209 1/209 1/209 1/209 2/209 2/209 1/209 1/209 1/209 1/209 2/209 1/209 1/209 1/209 4/209 1/209 1/209 4/209 1/209 1/209 10/209 1/209 5/209 2/209 1/209 1/209 1/209 9/209 1/209 1/209 1/209 5/209 1/209 2/209 7/209 1/209 4/209 4/209 1/209 1/209 2/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 8/209 1/209 5/209 1/209))

