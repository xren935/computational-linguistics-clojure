(defn theta-corpus-joint [theta corpus theta-probs]
  (+ (score-corpus corpus theta)
     (log2 (nth theta-probs (index-in-list theta thetas 0)))))