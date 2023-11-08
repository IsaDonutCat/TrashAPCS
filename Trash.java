import java.util.Scanner;

public class Trash //so can be combined with other game
{
    ArrayList<Card> trashStack;
    Deck freshStack = new Deck();
    Grid player1 = new Grid(10, freshStack)
    Grid player2 = new Grid(10, freshStack);
    
    public void trashShort ()//create short game
    {
        Scanner input = new Scanner(System.in);
        int decide;
        
    }

    public void turn() //create to act based on what turn
    {
        //is drawing from grid an option? print differently based on answer
        //is there a card in the trashStack? determine answer based on that;
        //draw from deck directly
        //place card if did not choose drawing from grid
        //check for winner
        //print finally to show all changes
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
