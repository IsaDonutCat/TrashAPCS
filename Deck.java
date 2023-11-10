import java.util.ArrayList;

public class Deck
{
    String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    Card temp;
    int index;  //INDEX
    ArrayList<Card> deck; //need to declare outsid for it to be used throughout the class

    public Deck () //constructor
    {
        deck = new ArrayList<Card>();

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 13; j++) //cycle thru the values and j can be used as numValue
                deck.add(new Card(j, values[j], suits[i]));
        }
    }

    public Card deal() //deal random card (remain faceDown)
    {
        index = (int) (Math.random() * deck.size());
        temp = deck.get(index);
        deck.remove(index);
        return temp;

    }

    public Card draw() // draw random card (Flip and print out value so player knows what it is)
    {
        index = (int) (Math.random() * deck.size());
        deck.get(index).flipCard();
        temp = deck.get(index);
        System.out.println("You have drawn " + temp.getName());
        deck.remove(index);
        return temp;
    }

    public int cardCtRemain()
    {
        return deck.size();
    }
}
