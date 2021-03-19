package hu.g14de;

public class Map
{
    //Variables
    private GameState gamestateReference;
    private Cell[][] cellmatrix;
    private int xSize;
    private int ySize;
    //Getters
    public GameState getGamestateReference()
    {
        return this.gamestateReference;
    }
    public Cell[][] getCellmatrix()
    {
        return  this.cellmatrix;
    }
    public int getxSize()
    {
        return this.xSize;
    }
    public int getySize()
    {
        return this.ySize;
    }
    //Setters
    //Methods
    public Cell getCellByID(int x, int y)
    {
        if(InBounds(x,y))
        {
            return cellmatrix[x][y];
        }
        else return null;
    }

    private boolean InBounds(int x,int y)
    {
        return x >= 0 &&x < xSize && y >= 0 && y < ySize;
    }



    public void setGamestateReference(GameState gamestateReference) {
        this.gamestateReference = gamestateReference;
    }
}
