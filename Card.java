public class Card
{
    boolean faceUp;
    int numValue; //numerical value of card (0-12 in computer bc)
    String name;
    
    public Card (int InnumValue, String invalue, String insuit) //constructor class
    {
        name = invalue + " of " + insuit;
        faceUp = false; //assume facedown position
    }

    public int getNumValue () //getter classes
    {
        return numValue;
    }

    public String getName ()
    {
        return name;
    }

    public boolean getFaceUp ()
    {
        return faceUp;
    }

    public void flipCard()
    {
        if (faceUp)
            faceUp = false;
        else
            faceUp = true;
        return;
    }
}