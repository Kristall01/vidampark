package hu.g14de;

import java.util.ArrayList;
import java.util.Collection;

public class BuildingInstance implements  Placeable
{

    private int height;
    private int width;
    private String content; //[FIXME] Ambiguous: may be changed for enum or whatever

    private ArrayList<Cell> occuppiedCells;


    //Constructor
    public BuildingInstance(int h, int w)
    {
        this.height = h;
        this.width = w;
        occuppiedCells = new ArrayList<Cell>();
    }


    public void addCellToList(Cell cell)
    {
        occuppiedCells.add(cell);
    }

    @Override
    public ArrayList<Cell> cellsOccuppied()
    {
        return occuppiedCells;
    }
}
