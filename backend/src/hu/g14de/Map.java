package hu.g14de;
import java.util.ArrayList;
import java.util.Collection;

public class Map
{
    //Constructor
    public Map (int boundX, int boundY, GameState gameState)
    {
        this.xSize = boundX;
        this.ySize = boundY;
        this.cellmatrix = new ArrayList<>();
        this.gamestateReference = gameState;
    }
    //Variables
    private GameState gamestateReference;
    private ArrayList<Cell> cellmatrix;
    private int xSize;
    private int ySize;
    //Getters
    public GameState getGamestateReference()
    {
        return this.gamestateReference;
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

    public void setGamestateReference(GameState gamestateReference)
    {
        this.gamestateReference = gamestateReference;
    }

    //Methods
    public Cell getCellByID(int x, int y)
    {
        if(InBounds(x,y))
        {
            return searchCellByCoordinates(x,y);
        }
        else throw new TranslatedException("error.map.invalid-ID", x, y);
    }

    private Cell searchCellByCoordinates(int x, int y) // [TODO] Redundancy: can be swapped to a lambda function
    {
        for (Cell var : cellmatrix)
        {
            if(var.getX() == x && var.getY() == y)
            {
                return  var;
            }
        }
        return  null;

    }

    private boolean InBounds(int x,int y)
    {
        return x >= 0 && x < xSize && y >= 0 && y < ySize;
    }

    public boolean addCell(Cell cell) //[FIXME] Convention error: not throwing valid exception
    {
        if (InBounds(cell.getX(), cell.getY())) {this.cellmatrix.add(cell); return true;}
        else throw new TranslatedException("error.map.invalid-ID", x, y);
    }

    private ArrayList<Coordinate> generateBuildingCoords(int startX, int startY, int endX, int endY) //[FIXME] 0/negative numbers?
    {
        ArrayList<Coordinate>  temp = new ArrayList<Coordinate>();
        for(int i = startX; i <= endX; i++)
        {
            for(int j = startY; j <= endY; j++)
            {
                temp.add(new Coordinate(i,j));
            }
        }
        return temp;
    }

    public void placeBuilding(Placeable content, int x, int y, int width, int height) //[FIXME] Convention error: not throwing valid exception, not checking for already placed stuff
    {
        if(InBounds(x,y) && InBounds(x+width-1, y+height-1))
        {
            //Valid building placement
            ArrayList<Coordinate> validCoordinates = generateBuildingCoords(x,y,x+width-1, y+height-1);
            for (Coordinate coord : validCoordinates)
            {
                searchCellByCoordinates(coord.getX(), coord.getY()).setContent(content);
            }
        }

    }
}
