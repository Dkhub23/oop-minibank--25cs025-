
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFreq {
    public static void main(String[] args) {
        String text = "hello hy how are you in chanAga with largest forest hello hy";
        Map<String, Integer> freq = new HashMap<>();

        for (String w : text.toLowerCase().split("\\W+")) {
            if (!w.isEmpty()) {
                freq.merge(w,1,Integer::sum);
            }
           
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
            .reversed()
            .thenComparing(Map.Entry::getKey));

        for(Map.Entry<String,Integer> e:list){
            System.out.println(e.getKey()+"->"+e.getValue());
        }


    }
}
