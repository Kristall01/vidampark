package hu.g14de;

import java.math.BigInteger;
import static hu.g14de.Utils.checkNull;

public class Balance
{
    private final GameState gameState;
    private BigInteger money;

    public Balance(GameState gameState, BigInteger money)
    {
        checkNull(gameState,money);
        
        if(money.compareTo(new BigInteger("0")) < 0) {
        	throw new IllegalArgumentException("negative money");
		}
        
        this.gameState = gameState;
        this.money = money;
    }

    public Balance(GameState gameState, int money)
    {
    	this(gameState, BigInteger.valueOf(money));
    }

    public BigInteger getMoney()
    {
        return money;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void addMoney(BigInteger amount)
    {
        this.money = this.money.add(amount);
    }

    public void removeMoney(BigInteger amount) throws NegativeMoneyException
    {
        if(this.money.compareTo(amount) < 0)
        {
        	throw new NegativeMoneyException();
        }
		this.money = this.money.subtract(amount);
    }
    
    public static class NegativeMoneyException extends Exception {}

}
