package hu.g14de.gamestate;

import hu.g14de.VidamparkApp;
import hu.g14de.restapi.signals.out.game.SignalOutGameBalance;

import java.math.BigInteger;

import static hu.g14de.Utils.checkNull;

public class Balance
{
    private final GameState gameState;
    private BigInteger money;
    private boolean changed = false;

    public Balance(GameState gameState, BigInteger money)  {
        checkNull(gameState,money);
        
        this.gameState = gameState;

		this.setMoney(money);
	}

    public Balance(GameState gameState, long money) {
		this(gameState, BigInteger.valueOf(money));
    }
    
    public VidamparkApp getApp() {
    	return gameState.getApp();
	}

    public BigInteger getMoney()
    {
        return money;
    }

    public GameState getGameState()
    {
        return gameState;
    }
    
    public boolean hasMoneyAtLeast(BigInteger amount) {
    	return !isNegative(this.money.subtract(amount));
	}

    public void addMoney(BigInteger amount) throws NegativeMoneyException {
    	setMoney(this.money.add(amount));
    }
    
    private void setMoney(BigInteger i) throws NegativeMoneyException {
    	if(isNegative(i)) {
    		throw new NegativeMoneyException();
		}
    	changed = true;
    	this.money = i;
	}
	
	public void broadcastChanges() {
    	if(changed) {
			gameState.broadcastSignal(new SignalOutGameBalance(this.money));
			changed = false;
		}
	}

    public void removeMoney(BigInteger amount) throws NegativeMoneyException
    {
    	setMoney(this.money.subtract(amount));
    }
    
    private static boolean isNegative(BigInteger i) {
    	return i.compareTo(BigInteger.ZERO) < 0;
	}
    
    public static class NegativeMoneyException extends RuntimeException {}

}
