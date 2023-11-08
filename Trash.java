public class Trash //so can be combined with other game
{
    ArrayList<Card> trashStack;
    Deck freshStack = new Deck();
    Grid player1 = new Grid(10, freshStack)
    Grid player2 = new Grid(10, freshStack);

    public void trashShort ()//create short game
    {
        
    }

    public void trashLong (int play1Num, int play2Num) //create long game
    {
        player1 = new Grid(play1Num, freshStack);
        player2 = new Grid(play2Num, freshStack);


    }

    public boolean chickenDinner (int inCount)
    {
        if (inCount == 1)
            return true;
        else
            return false;
    }
}
