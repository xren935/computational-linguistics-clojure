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
;; (println moby-vocab)

(defn get-count-of-word [w word-tokens count]
 (if (member-of-list? w word-tokens)
   (if (= (compare w (first word-tokens)) 0) 
     (+ 1 (get-count-of-word w (rest word-tokens) count)) 
     (get-count-of-word w (rest word-tokens) count))
   0
  )
)
;; (def tokens (list "he" "is" "him" "him" "her"))
;; (get-count-of-word "her" tokens 0)
;; (print (get-count-of-word "her" tokens 0))

;;fill this in

;; (defn get-word-counts [vocab word-tokens]
;;   (let [count-word (fn [w] 
;;                      (get-count-of-word w word-tokens 0))]
;;     (map count-word vocab)))
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
;; (println "moby-vocab")
;; (println moby-vocab)
;; (println "moby-word-tokens")
;; (println moby-word-tokens)

;; create-uniform-distribution takes a list of length n and returns a list containing the number 1/n repeated n times
;; sample-categorical samples a categorical distribution 
(defn sample-uniform-BOW-setence [n vocab]
  (def uniform-distrib (create-uniform-distribution vocab))
  ;; get one word 
  ;; (sample-categorical vocab uniform-distrib)
  (if (= n 0)
      '()
       (cons (sample-categorical vocab uniform-distrib) (sample-uniform-BOW-setence (- n 1) vocab))
  )
)
;; (println (sample-uniform-BOW-setence 6 (list "a" "b" "c" "dd")))
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

;; (defn compute-uniform-BOW-prob [vocab sentence] 
;;   (if (empty? sentence) 1
;;       ;; if the sentence contains words that do not exist in the language, return 0
;;       (if (check-if-word-in-lang vocab sentence)
;;       ;; all words in sentence are in the lang 
;;       ;; probability = prob. of every word 
;;           ((def curr-uniform-distribution (create-uniform-distribution vocab)) 
           
;;           )
;;       )
;;   )
;; )
(defn exp [x n]
  (reduce * (repeat n x)))

;; (defn compute-uniform-BOW-prob [vocab sentence] 
;;   (if (empty? sentence) 1
;;       ;; if the sentence contains words that do not exist in the language, return 0
;;       (if (check-if-word-in-lang vocab sentence)
;;       ;; all words in sentence are in the lang 
;;       ;; probability = prob. of word1 * prob of word2... 
;;           ((exp (/ 1 (count vocab)) (count sentence)))
;;           0  ;; not in the lang
;;       )
;;   )
;; )

;; Question 6
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
(def moby-word-probabilities ) 
 
(defn product [l]
  (apply * l))