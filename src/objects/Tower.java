package objects;

import static helpz.Constants.Towers.*;

public class Tower {

	private int x, y, id, towerType, cdTick, dmg;
	private float range, cooldown;
	private int tier;
	private String name;

	
	public Tower(int x, int y, int id, int towerType, String name) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		this.name = name;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}
	
	public void update() {
		cdTick++;
	}
	
	public void upgradeTower() {
		tier++;
		
		switch(towerType) {
		case CANNON:
			dmg += 5;
			range += 10;
			cooldown -= 15;
			break;
		case ARCHER:
			dmg += 2;
			range += 20;
			cooldown -= 3;
			break;
		case WIZARD:
			range += 30;
			cooldown -= 10;
			break;
		}
	}
	
	public void evolveTower(int choice) {
		tier++;
		
		dmg = helpz.Constants.EvolvedTowers.GetStartDmg(choice);
		range = helpz.Constants.EvolvedTowers.GetStartRange(choice);
		cooldown = helpz.Constants.EvolvedTowers.GetStartCooldown(choice);
		
		name = helpz.Constants.EvolvedTowers.GetName(choice);
		
	}
	
	public boolean isCooldownOver() {
		return cdTick >= cooldown;
	}

	public void resetCooldown() {
		cdTick = 0;
	}

	private void setDefaultCooldown() {
		cooldown = helpz.Constants.Towers.GetStartCooldown(towerType);
		
	}

	private void setDefaultRange() {
		range = helpz.Constants.Towers.GetStartRange(towerType);
		
	}

	private void setDefaultDmg() {
		dmg = helpz.Constants.Towers.GetStartDmg(towerType);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTowerType() {
		return towerType;
	}

	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getTier() {
		return tier;
	}
	
	public String getName() {
		return name;
	}

}
