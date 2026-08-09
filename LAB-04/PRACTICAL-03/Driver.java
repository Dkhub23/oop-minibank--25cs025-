public class Driver{
    public static void main(String[] args) {
         String template = "Dear {name}, order {id} ships {date}.";

        String[] names = {"name", "id"};
        String[] values = {"Dhruvi", "A07"};

        TemplateFiller.fillTemplate(template, names, values);
    }
}