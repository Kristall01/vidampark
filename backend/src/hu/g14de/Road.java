package hu.g14de;

import static hu.g14de.Utils.checkNull;

public class Road
{
    private Cell occuppiedCell;

    public Road(Cell cell)
    {
        checkNull(cell);
        this.occuppiedCell = cell;
    }

    public Cell getCell()
    {
        return occuppiedCell;
    }
}
