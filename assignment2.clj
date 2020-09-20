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
  (def second-half (drop 2 l)) ;; drops the first two elements 
  (def first-half (nth l 0)) ;; gets the first element 
  (cons first-half second-half)
)

;; Problem 8. Write a procedure add-to-end that takes in two arguments:
;; a list l and a value x. It should return a new list which is the same as l, except that it has x as its final element
(defn add-to-end [l x] 
  (def newlis (list x))
  (concat l newlis)
)
;; (add-to-end (list 1 2 3) 4)


;; Problem 9. Write a procedure, called reverse, that takes in a list, and returns the reverse
(defn reverse [l] 
  (reverse l)
)

;; Problem 10. Write a procedure, called count-to-1, that takes a positive integer n, and 
;; returns a list of the integers counting down from n to 1.
(defn count-to-1 [n]
  (def startIndex (+ n 1))
  (def inorder (range 1 startIndex))
  (reverse inorder)
)
(count-to-1 5)

;; Problem 11. Write a procedure, called count-to-n, that takes a positive integer n, and 
;; returns a list of the integers from 1 to n.
(defn count-to-n [n]
 (range 1 (+ n 1))
)

;; Probelm 12. Write a procedure, called get-max, that takes a list of numbers, and 
;; returns the maximum value.
(defn get-max [l]
  (apply max l)
)
(get-max (list 1 2 3 5 9))

;; Problem 13. Write a procedure, called greater-than-five?, that takes a list of numbers, and 
;; replaces each number with true if the number is greater than 5, and false otherwise.
(defn greater-than-five? [l]
  ;; (for [n l] (if(> n 5) (conj myList true) (conj myList false)))
  ;; use map
  (map (defn myfunc [n] (if (> n 5) true false)) l)
)
;; CHECK IF THE WORD LIST NEEDS TO BE INCLUDED !!!!
;; 

;; Problem 14. Write a procedure, called concat-three, that takes three sequences (represented as lists), x
;; y, and z, and returns the concatenation of the three sequences.
