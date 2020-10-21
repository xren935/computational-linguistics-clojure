import java.util.*; 
public class count {
    public static void main(String[] args){
        int sum = 0;
        String vocabs = "with ocean toward feelings same nearly very cherish other some degree their men all almost knew but they If this surprising There ship take quietly sword his upon himself throws Cato flourish philosophical With ball pistol for substitute This can soon as sea time high account then hats people's knocking methodically street into stepping deliberately from prevent principle moral strong requires that hand upper an such get hypos especially meet funeral every rear up bringing warehouses coffin before pausing involuntarily soul November drizzly damp it whenever mouth grim growing myself find Whenever circulation regulating spleen off driving have way is It world of part watery the see a about sail would thought I shore on interest to particular nothing and purse my in money no or little having precisely long how mind never ago years Some . Ishmael me CALL";
        String[] words = vocabs.split(" "); 
        String result = ""; 
        String input = "1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 2/209 1/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 4/209 3/209 1/209 1/209 1/209 2/209 2/209 1/209 1/209 1/209 1/209 2/209 1/209 1/209 1/209 4/209 1/209 1/209 4/209 1/209 1/209 10/209 1/209 5/209 2/209 1/209 1/209 1/209 9/209 1/209 1/209 1/209 5/209 1/209 2/209 7/209 1/209 4/209 4/209 1/209 1/209 2/209 2/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 1/209 8/209 1/209 5/209 1/209"; 
        String[] nos = input.split(" "); 
        float[] probs = new float[nos.length];
        // for(int i=0; i<words.length; i++){
        //     result = result + '"' + words[i] + '"'+ ' '; 
        // } 
        // System.out.println(result);
        for(int i=0; i<nos.length; i++){
            String curr = nos[i]; 
            System.out.println(curr); 
            String currNum[] = curr.split("/");
            // System.out.println(currNum[0]);
            float fst = Integer.parseInt(currNum[0]);
            System.out.println("first is " + fst);
            float snd = Integer.parseInt(currNum[1]); 
            System.out.println("second is " + snd);
            float curno = fst / snd; 
            System.out.println(curno);
            probs[i] = curno; 
        }
        
        Map<String, Float> map = new HashMap<>(); 
        for(int i=0; i<words.length; i++){
            map.put(words[i], probs[i]); 
        }
        System.out.println(map);
    }
}
