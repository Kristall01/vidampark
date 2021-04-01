package hu.g14de;

import java.util.List;

import static hu.g14de.Utils.checkNull;

public class Road implements Placeable
{
    private Cell occupiedCell;
    private final int price;

    public Road(Cell cell)
    {
        checkNull(cell);
        this.occupiedCell = cell;
        price = 100;
    }

    public Cell getCell()
    {
        return occupiedCell;
    }

    @Override
    public List<Cell> cellsOccupied()
    {
        return List.of(occupiedCell);
    }
}
