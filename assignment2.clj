;; Xingya Ren 260784116 
;; Problem 1. Write a procedure called abs that takes in a number, 
;; and computes the absolute value of the number by finding the square root of the square of the argument
(defn abs [n] 
  (def nsquare (* n n))
  (Math/sqrt nsquare)
)

;; Problem 2. fix the following procedures 
;; (defn take-square
;;   (* x x))
;; (defn sum-of-squares [(take-square x) (take-square y)]
;;   (+ (take-square x) (take-square y)))
(defn take-square [x]
  (* x x))

;; (defn sum-of-squares [(take-square x)]
;;   (+ (take-square x) (take-square x)))

(defn sum-of-squares [take-square x y]
  (+ (take-square x) (take-square y)))

;; Problem 3. Write four other different Clojure expressions whose values are also the number 13.
(def exp-13-1 (+ 10 3))
(def exp-13-2 (+ (* 3 1) 10))
(def exp-13-3 (/ 26 2))
(def exp-13-4 (- 23 10))
;; (print exp-13-1)
;; (print exp-13-2)
;; (print exp-13-3)
;; (print exp-13-4)

;; Problem 4. Write a procedure, called third, that selects the third element of a list
;; i.e. input: `(4,5,6) 
;; output: 6 
(defn third [l]
 (nth l 2)  
)

;; Problem 5. Write a procedure, called compose, that takes two one-place functions f and g as arguments. 
;; It should return a new function, the composition of its input functions, which computes f (g (x)) when passed the argument x
(defn compose [f g]
  (comp f g)
)
;; (defn sqrt [x] (Math/sqrt x))
;; (defn abs [x] (Math/abs x))
;; (println (compose sqrt abs) -36)

;; Problem 6. Write a procedure first-two that takes a list as its argument, 
;; returning a two element list containing the first two elements of the argument. 
(defn first-two [l] 
  (take 2 l)
)
;; (println (first-two '(4 5 6)))

;; Problem 7. Write a procedure remove-second that takes a list, and returns the same list with the second value removed. 
(defn remove-second [l] 
  (def first-half (subvec l 1 2 ))
  (def second-half (subvec 2 (count l) ))
  (concat first-half second-half)
)