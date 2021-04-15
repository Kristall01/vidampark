package hu.g14de.unused;

import hu.g14de.gamestate.GameState;

import java.util.Random;

import static hu.g14de.Utils.checkNull;

public class Guest
{
    private int money;
    private int thirst;
    private int hunger;
    private final GameState gameState;

    public Guest(GameState gameState)
    {
        checkNull(gameState);

        this.gameState = gameState;
        Random random = new Random();
        money = random.nextInt(501);
        thirst = random.nextInt(101);
        hunger = random.nextInt(101);

        this.chooseDestination();
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void notifyTick()
    {

    }

    private void chooseDestination()
    {

    }
}
