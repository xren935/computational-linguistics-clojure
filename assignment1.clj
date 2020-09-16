;; Problem 1
;; Define a variable `year`
(def year 2020)

;; Problem 5
;; Define a function `add-up`
(defn add-up [x y] (+ x y))
;; (add-up 1 2)

;; Problem 6
;; Define a function `is-it-four?`
(defn is-it-four? [x] 
  (if (= x 4) true, false))
;; (def answer (is-it-four? 5))
;; (println answer)

;; Problem 7
; add your code in the indicated space below,
; (so that the symbol `problem-7` evaluates to `true`)
(def problem-7 ;<-- do not edit this
		(= (quote    ;<-- do not edit this
            platypus
        ) 'platypus) ;<-- do not edit this
)              ;<-- do not edit this
;; (def answer problem-7)
;; (println answer)


;; Problem 8
;; Define a function `func` and an expression `expr` 
(defn func [x] 
  (if (= x 3) 3 3)) ; returns 3 no matter what the input value is
(def expr `(3 3 4 5 35))
;; (= 3 (apply func expr))

;; Problem 9
;; Define a function `both-same-type?`
(defn both-same-type? [x y]
  (if (= (type x) (type y)) true false))
;; uncomment the following two lines to test 
;; (def ans (both-same-type? 3 5))
;; (println ans)

;; Problem 10
;; Define a function `list-longer-than`
(defn list-longer-than [n lst]
  (def leng (count lst))
  (if (< n leng) true false)
)
;; (println (list-longer-than 3 (list 1 2 3)))
;; (println (list-longer-than 2 `(1 2 3)))

;; Problem 11
;; Define a function `dot-product`
(defn dot-product [lstA lstB]
  (def mapped (map * lstA lstB))
  (apply + mapped)
)
;; the following line is for testing the above function
;; (dot-product `(0 2 4) `(1 3 5))

;; Problem 12
;; Define a function `swap-arg-order`
(defn swap-arg-order [fun]
  (fn ([x y] (fun y x)))
)
;; uncomment the following line to test this function
;; (println ((swap-arg-order >) 3 5))

;; Problem 13
;; Define a higher order function `g`
;; g is a higher order function that takes in a function 'fun' as its argument 
;; and returns a value
(defn g [fun]
  (fun 10)
)
;; uncomment the following line to test this function
;; (println (= 100 (g (fn [n] (* n n)))))