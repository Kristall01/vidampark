package hu.g14de;

import java.math.BigInteger;

public class Balance
{
    private BigInteger money;
    private GameState gameState;

    public BigInteger getMoney()
    {
        return money;
    }

    public void setMoney(BigInteger amount)
    {
        this.money = amount;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void addMoney(BigInteger amount)
    {
        this.money = this.money.add(amount);
    }

    public boolean removeMoney(BigInteger amount)
    {
        if(this.money.compareTo(amount) < 0)
        {
            return false;
        }
        else
        {
            this.money = this.money.subtract(amount);
            return true;
        }
    }

}
