import java.util.Scanner;

public class Grid
{
    Card[][] cardGrid; // 2 rows 5 columns
    public boolean[][] blankSlot;
    int numCards, tempRow, tempCol;

    public int getCardCt ()
    {
        return numCards;
    }
    public Grid(int size, Deck dealFrom) //constructor class, possible future recursion
    {   
        if (size <= 5) //create according to size, if five or less its only one row
        {   
            cardGrid = new Card[][] {new Card[size]};
            blankSlot = new boolean[][] {new boolean[size]};
        }
        else
        {
            cardGrid = new Card[][] {new Card[5], new Card[size-5]}; //since first row def filled, its at lest 5|1
            blankSlot = new boolean[][] {new boolean[5], new boolean[size-5]};
        }
        
        for (int a = 0; a < cardGrid.length; a++) //array.length returns num rows
        {       
            for (int b = 0; b < cardGrid[a].length; b++)  //gets different length per
            {
                cardGrid[a][b] = dealFrom.deal();
                blankSlot[a][b] = false; //initialize
            }
            System.out.println();
        }

        numCards = size;
    }

    public void printGrid() //print out what cards are known and what cards are unknown
    {
        for (int c = 0; c < cardGrid.length; c++) //array.length returns num rows
        {       
            for (int d = 0; d < cardGrid[c].length; d++) 
            {
                if (blankSlot[c][d])
                    System.out.print(" ");
                else if (cardGrid[c][d].getFaceUp())
                    System.out.print(cardGrid[c][d].getName());
                else
                    System.out.print("?");

                if (d < cardGrid[c].length - 1)
                    System.out.print("|");
            }
            System.out.println();
        }
    }

    public void printLayout() //print layout so user knows wh  
    {
        int cursor = 0;//for continuous counting

        for (int e = 0; e < cardGrid.length; e++) //array.length returns num rows
        {       
            cursor++;
            
            for (int f = 0; f < cardGrid[e].length; f++) 
            {
                System.out.print(cursor);

                if (f < cardGrid[e].length - 1)
                    System.out.print("|");
            }
            System.out.println();
        }
    }

    public boolean anyUndrawn () //checks to see if this is even a valid option to draw from player grid
    {
        for (int h = 0; h < cardGrid.length; h++) //array.length returns num rows
        {       
            for (int i = 0; i < cardGrid[h].length; i++) 
            {
                if(!cardGrid[h][i].getFaceUp())
                    return true;
            }
        }
        return false;
    }

    public void selectCard () //select facedown card in grid 
    {
        printGrid();
        System.out.println();
        printLayout();
        Scanner input = new Scanner(System.in);
        String answer;

        do
        {   
            System.out.println("Enter the corresponding number for the card you want to flip over");
            answer = input.nextLine();
        }
        while (possError(answer)); //run until valid choice
        input.close();
        drawCard(Integer.parseInt(answer)-1);
        return;
    }

    public boolean possError (String inChoice)//find any errors with answer
    {
        char[] arrDigits = inChoice.toCharArray();
        int lenChoice = arrDigits.length;

        for (int g = 0; g < lenChoice; g++)
        {
            if (!Character.isDigit(arrDigits[g]))
            {
                System.out.println("Please enter a number");
                return true;
            }
        }

        if (lenChoice > 2)
        {
            System.out.println("Please choose a number within the range of your cards");
            return true;
        }

        int ans = Integer.parseInt(inChoice) -1; //okay to parseInt since completely integer

        if (ans >= numCards) //error for not a possible slot
        {
            System.out.println("Please choose a number within the range of your cards");
            return true;
        }

        if (cardGrid[ans/5][ans%5].getFaceUp()) //error for already existing card
        {
            System.out.println("You already have a face-up card here!");
            return true;
        }

        if(blankSlot[ans/5][ans%5]) //error for no card to draw
        {
            System.out.println("There's no card here!");
            return true;
        }

        return false;
    }

    public void drawCard (int index) //draw facedown card from grid
    {
        tempRow = index/5;
        tempCol = index%5;
        cardGrid[tempRow][tempCol].flipCard();
        blankSlot[tempRow][tempCol] = true; //now that the card has been "drawn" the slot is blank
        System.out.println("The card is " + cardGrid[tempRow][tempCol].getName());//reveal value
        placeCard(cardGrid[tempRow][tempCol]); //place card will be chosen
        return;
    }

    public boolean placeCard (Card inHand)//place card in grid, return false to end turn
    {   
        int cardVal = inHand.getNumValue();
        if (cardVal >= numCards)
        {
            trashStack.add(inHand);
            System.out.println("Trash!");
            return; //the card is trash
        }
        else if (blankSlot[tempRow][tempCol]) //do this first since im too lazy to remove a drawn card from the cardGrid
        {
            cardGrid[tempRow][tempCol] = inHand;
            blankSlot[tempRow][tempCol] = false;
            return; //
        }
        else if (cardGrid[tempRow][tempCol].getFaceUp())//the player already has this value 
        {
            System.out.println("Trash!");
            trashStack.add(inHand);
            return; //the card is trash
        }
        else //theres a facedown card in this spot
        {
            drawCard(tempRow*5+tempCol);//continyue turn by drawing that card
            return;
        }
    }

    public boolean winnerWinner() //checks for win conditions
    {
        for (int j = 0; j < cardGrid.length; j++) //array.length returns num rows
        {       
            for (int k = 0; k < cardGrid[j].length; k++) 
            {
                if (!cardGrid[j][k].getFaceUp() || blankSlot[j][k] ||cardGrid[j][k].getNumValue() != (j*5+k)) //second part as a safeguard
                    return false;
            }
        }
        return true;
    }
}
