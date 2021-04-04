package hu.g14de;

import static hu.g14de.Utils.checkNull;

public class Road implements Placeable
{
    private Cell occupiedCell;

    public Road(Cell cell)
    {
        checkNull(cell);
        
        this.occupiedCell = cell;
    }

    public Cell getCell()
    {
        return this.occupiedCell;
    }
	
	@Override
	public Cell getOccupiedCell() {
		return occupiedCell;
	}
}
