package enemies;

import static helpz.Constants.Enemies.BAT;

import managers.EnemyManager;

public class Bat extends Enemy{

	public Bat(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BAT, em);
	}
	
	public Bat(float x, float y, int ID, int health, EnemyManager em) {
		super(x, y, ID, BAT, health, em);
	}

}
