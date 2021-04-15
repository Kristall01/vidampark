package hu.g14de.gamestate;

import static hu.g14de.Utils.checkNull;

public class Map
{
    private final GameState gameState;
    private final int width;
	private final int height;
	private Cell[] cellmatrix;

    public Map (int width, int height, GameState gameState)
    {
        checkNull(gameState);
        if(width < 1 || height < 1)
        	throw new IllegalArgumentException("illegal map size ("+width+", "+height+")");
        
        this.width = width;
        this.height = height;
        this.cellmatrix = new Cell[width*height];
        this.gameState = gameState;
    }

    //Getters
    public GameState getGameState()
    {
        return this.gameState;
    }
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
    //Methods
    /*public Cell getCell(Coordinate c)
    {
    	checkNull(c);
    	
        if(InBounds(c))
        {
        	return cellmatrix.get(c);
            return searchCellByCoordinates(x,y);
        }
        else throw new TranslatedException("error.map.invalid-ID", x, y);
    }*/
	
	private boolean outOfBounds(int x, int y) {
		return	x < 0 || x >= width || y < 0 || y >= height;
	}
	
	private boolean outOfBounds(Coordinate c) {
		return outOfBounds(c.getX(), c.getY());
	}
	
	/*public void placeBuilding(Coordinate c, BuildingTemplate template) throws BadCoordinateExcception{
		checkNull(c, template);
		
		try {
			getGameState().getBalance().removeMoney(BigInteger.valueOf(template.getBuildCost()));
		}
		catch (Balance.NegativeMoneyException e) {
			throw new TranslatedException("error.balance.too-few-money");
		}
		Cell cell = getCell(c.getX(), c.getY());
		if(cell == null) {
			throw new BadCoordinateExcception();
		}
		//TODO do the actual placement
	}*/
	
	private Cell getCell(int x, int y) {
		if(outOfBounds(x, y)) {
			return null;
		}
		return cellmatrix[x*y + x];
	}

    /*private Cell searchCellByCoordinates(int x, int y) // [TODO] Redundancy: can be swapped to a lambda function
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

    private ArrayList<Coordinate> generateBuildingCoords(int startX, int startY, int endX, int endY) //[FIXME] where is coordinates checked for validity
    {
        if(startX < 0 || startY < 0 || endX < 0 ||endY < 0)
                throw new TranslatedException("error.map.negative-coordinate");
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

    public void placeBuilding(BuildingTemplate content, int x, int y)
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

    }*/
	
	public static class BadCoordinateExcception extends Exception {}
	
}
