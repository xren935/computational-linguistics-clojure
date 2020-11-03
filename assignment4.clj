(ns ps4-ren-xingya (:refer-clojure :exclude [repeat]))
;;change my name to your name

(comment "Make sure that you follow the instructions carefully and use the right procedure names, inputs, and outputs")

;;Problem 1
(def vocabulary '(call me ishmael))

(def theta1 (list (/ 1 2 ) (/ 1 4 ) (/ 1 4 )))
(def theta2 (list (/ 1 4 ) (/ 1 2 ) (/ 1 4 )))

(def thetas (list theta1 theta2))

(def theta-prior (list (/ 1 2) (/ 1 2)))
(defn score-categorical [outcome outcomes params]
	(if (empty? params)
		(print "no matching outcome")
		(if (= outcome (first outcomes))
			(first params)
			(score-categorical outcome (rest outcomes) (rest params)))))

(defn list-foldr [f base lst]
	(if (empty? lst)
		base
		(f (first lst)
			(list-foldr f base (rest lst)))))

(defn log2 [n]
  (/ (Math/log n) (Math/log 2)))

(defn score-BOW-sentence [sen probabilities]
	(list-foldr
		(fn [word rest-score]
			(+ (log2 (score-categorical word vocabulary probabilities))
				rest-score)) 
		0
		sen))

(defn score-corpus [corpus probabilities]
	(list-foldr
		(fn [sen rst]
			(+ (score-BOW-sentence sen probabilities) rst))
		0
		corpus))

(defn logsumexp [log-vals]
	(let [mx (apply max log-vals)]
		(+ mx
			(log2
				(apply +
					(map (fn [z] (Math/pow 2 z))
						(map (fn [x] (- x mx)) log-vals)))))))

(def my-corpus '((call me)
				(call ishmael)))

;; (defn theta-corpus-joint [theta corpus theta-probs]
;; 	(+ (score-corpus corpus theta) 
;; 		(log2 (score-categorical theta thetas theta-probs))))

(defn index-in-list [w lst index]
  (if (empty? lst)
    -1
    (if (= w (first lst))
      index
      (index-in-list w (rest lst) (+ index 1)))))

(defn theta-corpus-joint [theta corpus theta-probs]
  (+ (score-corpus corpus theta)
     (log2 (nth theta-probs (index-in-list theta thetas 0)))))
;; (println (theta-corpus-joint theta1 my-corpus theta-prior))
;; (println (theta-corpus-joint theta2 my-corpus theta-prior))

;; Problem 2
;; marginalization refers to the process of summing over all of the values of some of 
;; the random variables in a joint distribution
;; return the log of the marginal likelihood
(defn compute-marginal [corpus theta-probs]
	(logsumexp (list (theta-corpus-joint theta1 corpus theta-probs) 
	(theta-corpus-joint theta2 corpus theta-probs))))
;; (println (compute-marginal my-corpus theta-prior))
;; (println (Math/pow 2 (compute-marginal my-corpus theta-prior)))


;; Problem 3
(defn compute-conditional-prob [theta corpus theta-probs]
  (- (theta-corpus-joint theta corpus theta-probs)
    (compute-marginal corpus theta-probs)))
;; (println (compute-conditional-prob theta1 my-corpus theta-prior))

;; Problem 4 
(defn compute-conditional-dist [corpus theta-probs]
  (map (fn [th] (compute-conditional-prob th corpus theta-probs))
		thetas)
)
;; (def conditional-dist (compute-conditional-dist my-corpus theta-prior))
;; (println conditional-dist)

;; Problem 5 
;; (println (compute-conditional-dist my-corpus theta-prior))
;; 

; ;Problem 6
; (defn compute-posterior-predictive [observed-corpus new-corpus theta-probs]
; 	(let [conditional-dist ... 
; 		(compute-marginal ...

; ;Problem 7
(defn normalize [params]
  (let [sum (apply + params)]
    (map (fn [x] (/ x sum)) params)))

(defn flip [weight]
  (if (< (rand 1) weight)
      true
      false))

(defn sample-categorical [outcomes params]
  (if (flip (first params))
      (first outcomes)
      (sample-categorical (rest outcomes) 
                          (normalize (rest params)))))

(defn repeat [f n]
  (if (= n 0)
      '()
      (cons (f) (repeat f (- n 1)))))

(defn sample-BOW-sentence [len probabilities]
		(if (= len 0)
		  '()
		  (cons (sample-categorical vocabulary probabilities)
		    (sample-BOW-sentence (- len 1) probabilities))))

; ;Problem 8
; (defn sample-theta-corpus [sent-len corpus-len theta-probs]
;   (let [theta ...
;     (list theta ...

; ;Problem 9
(defn get-theta [theta-corpus]
  (first theta-corpus))

(defn get-corpus [theta-corpus]
  (first (rest theta-corpus)))

; ;uncomment the following after you have defined `sample-theta-corpus` above
; (defn sample-thetas-corpora [sample-size sent-len corpus-len theta-probs]
;   (repeat (fn [] (sample-theta-corpus sent-len corpus-len theta-probs)) sample-size))

; ;Problem 11
(defn get-count [obs observation-list count]
  (if (empty? observation-list)
      count
      (if (= obs (first observation-list))
          (get-count obs (rest observation-list) (+ 1 count))
          (get-count obs (rest observation-list) count))))


(defn get-counts [outcomes observation-list]
  (let [count-obs (fn [obs] (get-count obs observation-list 0))]
    (map count-obs outcomes)))

; (defn rejection-sampler 
;   [theta observed-corpus sample-size sent-len corpus-len theta-probs]
;   ...)