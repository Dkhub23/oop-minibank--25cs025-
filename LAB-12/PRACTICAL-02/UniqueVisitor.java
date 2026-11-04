

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueVisitor {
    public static void main(String[] args) {
        List<String> logins = Arrays.asList("a", "b", "c", "a", "c", "d", "e");
        Set<String> unique=new HashSet<>(logins);
        Set<String> order=new LinkedHashSet<>(logins);

        System.out.println("Unique use:"+ unique.size());
        System.out.println("First three distict");
        int n=0;
        for(String u:order){
            if(n++==3)
                break;

            System.out.println(u+"");
        }
        System.out.println();
        
    }
}
