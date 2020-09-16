;; Problem 1
;; Define a variable `year`
(def year 2020)
;; please uncomment the following line to test the line above 
;; (println year)

;; Problem 5
;; Define a function `add-up`
(defn add-up [x y] (+ x y))
;; please uncomment the following line to test the line above 
;; (println (add-up 1 2))

;; Problem 6
;; Define a function `is-it-four?`
(defn is-it-four? [x]
  (if (= x 4) true, false))
;; please uncomment the following 2 lines to test the line above 
;; (def answer (is-it-four? 5))
;; (println answer)

;; Problem 7
; add your code in the indicated space below,
; (so that the symbol `problem-7` evaluates to `true`)
(def problem-7 ;<-- do not edit this
  (= (quote    ;<-- do not edit this
      platypus) 'platypus) ;<-- do not edit this
)              ;<-- do not edit this
;; please uncomment the following 2 lines to test the line above 
;; (def answer problem-7)
;; (println answer)


;; Problem 8
;; Define a function `func` and an expression `expr` 
(defn func [x y z]
  (if (> x y z) 3 0))
(def expr (conj `(2 1) 3))
;; please uncomment the following 2 lines to test the line above 
;; (println (= 3 (apply func expr)))

;; Problem 9
;; Define a function `both-same-type?`
(defn both-same-type? [x y]
  (if (= (type x) (type y)) true false))
;; please uncomment the following 2 lines to test the line above  
;; (def ans (both-same-type? 3 "hi"))
;; (println ans)

;; Problem 10
;; Define a function `list-longer-than`
(defn list-longer-than [n lst]
  (def leng (count lst))
  (if (< n leng) true false))
;; please uncomment the following 2 lines to test the line above  
;; (println (list-longer-than 3 (list 1 2 3)))
;; (println (list-longer-than 2 `(1 2 3)))

;; Problem 11
;; Define a function `dot-product`
(defn dot-product [lstA lstB]
  (def mapped (map * lstA lstB))
  (apply + mapped))
;; please uncomment the following 2 lines to test the line above 
;; (println (dot-product `(0 2 4 5) `(1 3 5 1)))

;; Problem 12
;; Define a function `swap-arg-order`
(defn swap-arg-order [fun]
  (fn ([x y] (fun y x))))
;; please uncomment the following 2 lines to test the line above 
;; (println ((swap-arg-order -) 3 5))

;; Problem 13
;; Define a higher order function `g`
;; g is a higher order function that takes in a function 'fun' as its argument 
;; and returns a value
(defn g [fun]
  (fun 10))
;; please uncomment the following 2 lines to test the line above 
;; (println (= 100 (g (fn [n] (* n n)))))