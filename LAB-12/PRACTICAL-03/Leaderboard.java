
import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {

        Map<String, Integer> players = new HashMap<>();

        players.put("Dhruvi", 90);
        players.put("Amit", 85);
        players.put("Rahul", 95);
        players.put("Priya", 90);
        players.put("Neha", 80);

        int N = 3;

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(players.entrySet());

        list.sort((a, b) -> {

            // Sort by score in descending order
            int result = b.getValue().compareTo(a.getValue());

            // If scores are equal, sort by name
            if (result == 0) {
                return a.getKey().compareTo(b.getKey());
            }

            return result;
        });

        System.out.println("Top " + N + " Players:");

        for (int i = 0; i < N && i < list.size(); i++) {

            System.out.println(
                list.get(i).getKey() + " : " +
                list.get(i).getValue()
            );
        }
    }
}
