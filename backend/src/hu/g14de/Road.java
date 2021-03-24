package hu.g14de;

import java.util.ArrayList;

public class Road
{
    private Cell occuppiedCell;
    public Road(Cell cell)
    {
        this.occuppiedCell = cell;
    }

    public Cell getCell()
    {
        return occuppiedCell;
    }
}
