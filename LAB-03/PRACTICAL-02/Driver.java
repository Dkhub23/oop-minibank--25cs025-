public class Driver {
    public static void main(String[] args) {
        Card[] cards = new Card[6];
        int count = 0;

        Card[] newCards = {
            new Card("King", "Spades"),
            new Card("Queen", "Heart"),
            new Card("King", "Spades"),
            new Card("Ace", "Spades"),
            new Card("Queen", "Spades"),
            new Card("Ace", "Spades")
        };

        for (Card card : newCards) {
            boolean duplicate = false;

            for (int i = 0; i < count; i++) {
                if (card.equals(cards[i])) {
                    System.out.println("Duplicate found: " + card);
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                cards[count] = card;
                count++;
            }
        }
    }
}