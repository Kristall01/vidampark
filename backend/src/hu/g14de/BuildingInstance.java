package hu.g14de;

import java.util.ArrayList;
import java.util.List;

public class BuildingInstance implements  Placeable
{

    private int height;
    private int width;
    private String content; //[FIXME] Ambiguous: may be changed for enum or whatever

    private List<Cell> occupiedCells;


    //Constructor
    public BuildingInstance(int h, int w)
    {
        this.height = h;
        this.width = w;
        occupiedCells = new ArrayList<Cell>();
    }


    public void addCellToList(Cell cell)
    {
        occupiedCells.add(cell);
    }

    @Override
    public List<Cell> cellsOccupied()
    {
        return occupiedCells;
    }
}
