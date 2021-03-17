package hu.g14de;

import hu.g14de.GameState;

public class Guest
{
    private int money;
    private int thirst;
    private int hunger;
    private Action action;
    private GameState gameState;

    public GameState getGameState()
    {
        return gameState;
    }

    public void notifyTick()
    {

    }
}
