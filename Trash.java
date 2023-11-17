import java.util.ArrayList;
import java.util.Scanner;

public class Trash //so can be combined with other game
{
    public static ArrayList<Card> trashStack = new ArrayList<Card>();
    public static Deck freshStack = new Deck();
    Grid player1;
    Grid player2;
    int gameCount = 1; //human counting
    int winner; 

    public void trashShort ()//create short game
    {   
        freshStack.resetDeck();
        trashStack.clear();
        player1 = new Grid(10);
        player2 = new Grid(10);
        winner = game();
        if (winner == -1)
            System.out.println("Draw!");
        else
            System.out.println("Player " + winner + " wins!");

        return;
    }

    public void trashLong (int play1Num, int play2Num) //create long game
    {
        freshStack.resetDeck();
        trashStack.clear();
        player1 = new Grid(play1Num);
        player2 = new Grid(play2Num);

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
            gameCount++;
            if (winner == 1)
            {
                System.out.println("Player 1 wins round " + gameCount + "!");
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
        boolean allowDraw = false;
        int ans = 0;
        player.printGrid();

        if (player.anyUndrawn())
        {
            System.out.println("You can draw from your grid. Enter 1 to do so.");
            allowGrid = true;
        }

        if (trashStack.size() == 0)
        {
            System.out.println("There are no cards in the trash that you can draw from.");
        }
        else
        {
            System.out.println("The card on top of the trash is " + trashStack.get(trashStack.size()-1).getName());
            if (trashStack.get(trashStack.size()-1).getNumValue() < player1.getCardCt())
            {
                System.out.println("You can choose this card by entering 2.");
                allowTrash = true;
            }
        }
        
        if (freshStack.cardCtRemain() != 0 )
        {
            System.out.println("Enter 3 to draw from the deck.");
            allowDraw = true;
        }
        else
            System.out.println("The deck is empty.");

            String temp;
        
        do
        {
            System.out.print("Please enter a number for an option\n");
            System.out.print("waiting\n");
                temp = input.nextLine();
                System.out.print("finished" + ".");
            try
            {
                ans = Integer.parseInt(temp);
            }
            catch (NumberFormatException e){
                
            }
        }
        while(!(ans == 3 && allowDraw) && !(ans == 2 && allowTrash) && !(ans == 1 && allowGrid));

        String buffClear = input.next();
        input.close();
        

        if (ans == 2)
        {
            player.placeCard(trashStack.get(trashStack.size()-1));
            trashStack.remove(trashStack.size()-1);
        }
        else if (ans == 3)
        {
            player.placeCard(freshStack.draw());
        }
        else
        {
            player.selectCard();
        }
        
    }

    public int game()//game return swinner or -1 if deck runs out and trash latest is not valid
    {
        while (true)
        {
            if (freshStack.cardCtRemain() == 0 
            && trashStack.get(trashStack.size()-1).getNumValue() >= player1.getCardCt()
            && !player1.anyUndrawn()) //no cards in deck, && can't draw from stack && cant flip from grid
                return -1;
            turn(player1);
            if (player1.winnerWinner())
                return 1;

            if (freshStack.cardCtRemain() == 0 
            && trashStack.get(trashStack.size()-1).getNumValue() >= player2.getCardCt()
            && !player2.anyUndrawn())
                return -1;
            turn(player2);
            if (player2.winnerWinner())
                return 2;
        }
    }

}
