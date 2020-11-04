(defn rejection-sampler [theta observed-corpus sample-size sent-len corpus-len theta-probs]
	(let [pairs (sample-thetas-corpora sample-size sent-len corpus-len theta-probs)]
		(/ (get-count theta (map get-theta (filterv (fn [p] (= observed-corpus (get-corpus p))) pairs)) 0) (count pairs))))