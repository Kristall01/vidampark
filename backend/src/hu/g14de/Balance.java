package hu.g14de;

import java.math.BigInteger;
import static hu.g14de.Utils.checkNull;

public class Balance
{
    private final GameState gameState;
    private BigInteger money;

    Balance(GameState gameState, BigInteger money)
    {
        checkNull(gameState,money);
        if(money.compareTo(new BigInteger("0")) == -1)
            throw new NegativeMoneyException();

        this.gameState = gameState;
        this.money = money;
    }

    Balance(GameState gameState, int money)
    {
        checkNull(gameState);
        if(money < 0)
            throw new NegativeMoneyException();

        this.gameState = gameState;
        this.money = new BigInteger(Integer.toString(money));
    }

    public BigInteger getMoney()
    {
        return money;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setMoney(BigInteger amount)
    {
        this.money = amount;
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

    public static class NegativeMoneyException extends TranslatedException
    {
        public NegativeMoneyException()
        {
            super("error.balance.negative-money");
        }
    }
}
