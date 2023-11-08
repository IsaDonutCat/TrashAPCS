public class Grid
{
    Card[][] cardGrid = new Card[2][5]// 2 rows 5 columns

    ///default constructor class that takes nothing and does nothing already exists and im lazy

    public void printGrid()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (!cardGrid[i][j].getFaceUp())
                    System.out.print("?");
                else
                    System.out.print(cardGrid[i][j].getName());
                if (j < 4) //so only prints in between
                    System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public void printLayout()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (!cardGrid[i][j].getFaceUp())
                    System.out.print(i*j + 1);
                if (j < 4) //so only prints in between
                    System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public Card selectCard (int index)
    {
        printLayout();

    }
}