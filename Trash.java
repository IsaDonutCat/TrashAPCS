import java.util.Scanner;

public class Trash //so can be combined with other game
{
    public ArrayList<Card> trashStack;
    Deck freshStack = new Deck();
    Grid player1;
    Grid player2;
    int gameCount = 1; //human counting
    int winner; 

    public void trashShort ()//create short game
    {
        winner = game();
        if (winner == -1)
            System.out.println("Draw!");
        else
            System.out.println("Player " + winner + " wins!");

        return;
    }

    public void trashLong (int play1Num, int play2Num) //create long game
    {
        player1 = new Grid(play1Num, freshStack);
        player2 = new Grid(play2Num, freshStack);

        if (play1Num == 0)
        {
            System.out.println("Player 1 wins!");
            return;
        }
        else if (play2Num == 0)
        {
            System.out.println("Player 2 wins!");
            return;
        }
        else
        {
            winner = game(); //runs 1 game, return 1 if player 1 wins and return 2 if player 2 wins
            if (winner == 1)
            {
                System.out.println("Player 1 wins round " + gameCount + "!");
                gameCount++;
                trashLong(play1Num-1, play2Num);
            }
            else if (winner == -1)
            {
                System.out.println("Draw!");
                trashLong(play1Num-1, play2Num);
            }
            else
            {
                System.out.println("Player 2 wins round " + gameCount + "!");
                gameCount++;
                trashLong(play1Num, play2Num - 1);
            }
            return;
        }

    }

    public void turn(Grid player) //create to act based on what turn
    {
        Scanner input = new Scanner(System.in);
        boolean allowGrid = false;
        boolean allowTrash = false;

        

        input.close();
    }

    public void game()
    {

    }
}