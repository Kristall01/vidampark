package hu.g14de.gamestate;

import com.google.gson.JsonElement;
import hu.g14de.Utils;

import java.util.Objects;

public final class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Coordinate add(int x, int y) {
		return new Coordinate(this.x + x, this.y + y);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || !getClass().equals(o.getClass())) {
			return false;
		}
		Coordinate that = (Coordinate) o;
		return getX() == that.getX() && getY() == that.getY();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}
	
	@Override
	public String toString() {
		return String.format("(%d %d)", x,y);
	}
	
	public JsonElement serialize() {
		return Utils.gson().toJsonTree(this);
	}
	
	
}
