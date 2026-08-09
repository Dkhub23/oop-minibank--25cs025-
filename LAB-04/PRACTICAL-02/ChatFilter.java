public class ChatFilter {
    public static void searchLogs(String[] logs, String keyword) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

      for(String line:logs){
        String[] parts=line.split(" ",3);//when space occurs then splite up to 3 words
        if(parts.length<3){
            continue;
        }

          String time = parts[0];
            String user = parts[1];
         String message = parts[2];

         if(message.toLowerCase().contains(keyword.toLowerCase()))
         {
            count++;
            sb.append(time+" "+user+": "+message+"\n");
         }

      }
      
      System.out.println("Matches: " + count);
        System.out.println(sb);
    }

}