package enemies;

import java.awt.Rectangle;

import managers.EnemyManager;

import static helpz.Constants.Direction.*;

public abstract class Enemy {

	protected float x, y;
	protected Rectangle bounds;
	protected int health;
	protected int maxHealth;
	protected int ID;
	protected int enemyType;
	protected int lastDir;
	protected int slowDuration = 60 * 2;
	protected int slowTick = slowDuration;
	protected float slowPower = 0.5f;
	protected int timeStopDuration = 60 * 2;
	protected int timeStopTick = timeStopDuration;
	protected boolean alive = true;
	protected EnemyManager enemyManager;
	private int icePoisonDuration = 60 * 2;
	private int icePoisonTick = icePoisonDuration;
	private int icePoisonTickDmg = 0;
	
	public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		this.enemyManager = enemyManager;
		bounds = new Rectangle((int)x, (int)y, 32, 32);
		lastDir = -1;
		setStartHealth();
	}
	
	public Enemy(float x, float y, int ID, int enemyType, int health, EnemyManager enemyManager) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		this.enemyManager = enemyManager;
		bounds = new Rectangle((int)x, (int)y, 32, 32);
		lastDir = -1;
		setStartHealth();
		this.health = health;
	}
	
	protected void setStartHealth() {
		health = helpz.Constants.Enemies.GetStartHealth(enemyType);
		maxHealth = health;
	}
	
	public void hurt(int dmg) {
		this.health -= dmg;
		if(health <= 0) {
			alive = false;
			enemyManager.rewardPlayer(enemyType);
		}
	}
	
	public void kill() {
		health = 0;
		alive = false;
	}
	
	public void timeStop() {
		timeStopTick = 0;
	}
	
	public void slow() {
		slowTick = 0;
	}
	
	public void icePoisoned() {
		icePoisonTick = 0;
		
	}
	
	public void move(float speed, int dir) {
		lastDir = dir;
		
		if(slowTick < slowDuration) {
			slowTick++;
			speed *= slowPower;
		}
		
		if(timeStopTick < timeStopDuration) {
			timeStopTick++;
			speed = 0;
		}
		
		if(icePoisonTick < icePoisonDuration) {
			icePoisonTick++;
			icePoisonTickDmg++;
			
			if(icePoisonTickDmg >= 30) {
				hurt(10);
				icePoisonTickDmg = 0;
			}
		}
		
		switch(dir) {
		case LEFT:
			this.x -= speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		}
		
		updateHitbox();
	}
	
	private void updateHitbox() {
		bounds.x = (int)x;
		bounds.y = (int)y;
		
	}

	public float getHealthBarFloat() {
		return health / (float)maxHealth;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}
	
	public int getLastDir() {
		return lastDir;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isSlowed() {
		return slowTick < slowDuration;
	}

	public boolean isTimeStoped() {
		return timeStopTick < timeStopDuration;
	}
	
	public void setLastDir(int newDir) {
		this.lastDir = newDir;
		
	}

	
}
