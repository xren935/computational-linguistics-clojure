;; Xingya Ren 260784116 
;; Problem 1. Write a procedure called abs that takes in a number, 
;; and computes the absolute value of the number by finding the square root of the square of the argument
(defn abs [n] 
  (def nsquare (* n n))
  (Math/sqrt nsquare)
)
(abs -3)

;; Problem 2. fix the following procedures 
;; (defn take-square
;;   (* x x))
;; (defn sum-of-squares [(take-square x) (take-square y)]
;;   (+ (take-square x) (take-square y)))
(defn take-square [x]
  (* x x))

(defn sum-of-squares [take-square x take-square y]
  (+ (take-square x) (take-square y)))
(sum-of-squares take-square 3 take-square 5)

;; Problem 3. Write four other different Clojure expressions whose values are also the number 13.
(def exp-13-1 (+ 10 3))
(def exp-13-2 (+ (* 3 1) 10))
(def exp-13-3 (/ 26 2))
(def exp-13-4 (- 23 10))
(print exp-13-1)
(print exp-13-2)
(print exp-13-3)
(print exp-13-4)

;; Problem 4. Write a procedure, called third, that selects the third element of a list
;; i.e. input: `(4,5,6) 
;; output: 6 
;; return 0 if list has less than 3 members 
(defn third [l]
 (if (< (count l) 3) 0)
 (nth l 2)  
)
(third (list 4 5 6))

;; Problem 5. Write a procedure, called compose, that takes two one-place functions f and g as arguments. 
;; It should return a new function, the composition of its input functions, which computes f (g (x)) when passed the argument x
(defn compose [f g]
  (fn  ([x y] (f (g x y))))
)
(defn sqrt [x] (Math/sqrt x))
(defn abs [x] (Math/abs x))
(println (compose sqrt abs) -36)

;; Problem 6. Write a procedure first-two that takes a list as its argument, 
;; returning a two element list containing the first two elements of the argument. 
(defn first-two [l]
  (def sec (first (rest l)))
  (def fst (first l))
  (def seclst (cons sec (list)))
  (def fstlst (cons fst (list)))
  (concat fstlst seclst))
(first-two (list 1 2 3))

;; Problem 7. Write a procedure remove-second that takes a list, and returns the same list with the second value removed. 
(defn remove-second [l] 
  (def second-half (drop 2 l)) ;; drops the first two elements 
  (def first-half (first l)) ;; gets the first element 
  (cons first-half second-half)
)
(remove-second (list 1 2 3 4))

;; Problem 8. Write a procedure add-to-end that takes in two arguments:
;; a list l and a value x. It should return a new list which is the same as l, except that it has x as its final element
(defn add-to-end [l x] 
  (def newlis (list x))
  (concat l newlis)
)
(add-to-end (list 1 2 3) 4)


;; Problem 9. Write a procedure, called reverse, that takes in a list, and returns the reverse
(defn reverse [coll]
  (if (empty? coll)
    coll
    (concat
     (reverse (rest coll))
     (list (first coll))))
)
(reverse (list 1 2 3))

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
  (def toOne (count-to-1 n))
  (reverse toOne)
)
(count-to-n 5)

;; Probelm 12. Write a procedure, called get-max, that takes a list of numbers, and 
;; returns the maximum value.
(defn compare [a b]
  (if (> a b) a b)
)
(defn get-max [l]
  (reduce compare l)
)
(get-max (list 1 2 3))


;; Problem 13. Write a procedure, called greater-than-five?, that takes a list of numbers, and 
;; replaces each number with true if the number is greater than 5, and false otherwise.
(defn greater-than-five? [l]
  ;; (for [n l] (if(> n 5) (conj myList true) (conj myList false)))
  ;; use map
  (map (defn myfunc [n] (if (> n 5) true false)) l)
)
(def input (list 1 5 4 7))
(greater-than-five? input)

;; Problem 14. Write a procedure, called concat-three, that takes three sequences (represented as lists), x
;; y, and z, and returns the concatenation of the three sequences.
(defn concat-three [x y z]
  (concat (concat x y) z)
)

;; Problem 15. 
;; sequence-to-power takes a list x and a positive int n 
;; returns the sequence of n concatenations of the x string
;; concat x to itself for n number of times 
(defn sequence-to-power [x n]
  (if (= n 0)
    '()
    (concat x (sequence-to-power x (- n 1)))
  )
)
(sequence-to-power (list 1 2) 3)

;; Problem 16. 
;; Define L as a language containing a single sequence, L = {a}. 
;; in-L? that takes a sequence x, and decides if it is a member of the language L
;; helper function prefix? 
(defn prefix? [x L]
  (if (> (count x) (count L))
    false
    (if (empty? x)
      true
      (if (= (first x) (first L))
        (prefix? (rest x) (rest L))
        false))))
;; DEFINING THE LANGUAGE L HERE AS L=(list 1 2 3)
(def L (list 1 2 3 4 5))

(defn in-L? [x]
  (if (empty? x)
    true
    (if (prefix? x L)
      (in-L?(rest (rest x)))
      false)))
(in-L? '(1 2))

;; 
;; Problem 17. 
;; 