package hu.g14de;

import java.util.ArrayList;
import java.util.Collection;

public class BuildingInstance implements  Placeable
{

    private int height;
    private int width;
    private String content; //[FIXME] Ambiguous: may be changed for enum or whatever

    @Override
    public ArrayList<Cell> cellsOccuppied() //[FIXME]
    {
        return null;
    }
}
