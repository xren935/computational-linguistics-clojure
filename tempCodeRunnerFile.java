   Map<String, Float> map = new HashMap<>(); 
        for(int i=0; i<words.length; i++){
            map.put(words[i], probs[i]); 
        }
        System.out.println(map);