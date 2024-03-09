package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

public class TowerManager {

	private Playing playing;
	private BufferedImage[] towerImgs;
	private Tower movedTower;
	
	private ArrayList<Tower> towers = new ArrayList<>();
	
	private int towerAmount = 0;
	
	public TowerManager(Playing playing) {
		this.playing = playing;
		
		loadTowerImgs();
	}

	private void loadTowerImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[3];
		for(int i = 0; i < 3; i++) {
			towerImgs[i] = atlas.getSubimage((4+i)*32, 32, 32, 32);
		}
		
	}
	
	public void draw(Graphics g) {
		for(Tower t : towers) {
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
		}
	}
	
	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType(), helpz.Constants.Towers.GetName(selectedTower.getTowerType())));
		
	}
	
	public void movedTower(Tower movedTower, int xPos, int yPos) {
		for(Tower t : towers) {
			if(t.getId() == movedTower.getId()) {
				t.setX(xPos);
				t.setY(yPos);
			}
		}
	}
	
	
	public void removeTower(Tower displayedTower) {
		for(int i = 0; i < towers.size(); i++) {
			if(towers.get(i).getId() == displayedTower.getId()) {
				towers.remove(i);
			}
		}
		
	}
	
	public void moveTower(Tower displayedTower) {
		movedTower = displayedTower;
		playing.setSelectedTower(movedTower);
	}
	
	
	public void upgradeTower(Tower displayedTower) {
		for(Tower t : towers) {
			if(t.getId() == displayedTower.getId()) {
				t.upgradeTower();
			}
		}
		
	}
	
	public void evolveTower(Tower displayedTower, int choice) {
		for(Tower t : towers) {
			if(t.getId() == displayedTower.getId()) {
				t.evolveTower(choice);
			}
		}
	}

	public void update() {
		for(Tower t : towers) {
			t.update();
			attackEnemyIfClose(t);
		}
		
	}
	
	private void attackEnemyIfClose(Tower t) {
			for(Enemy e : playing.getEnemyManager().getEnemies()) {
				if(e.isAlive()) {
					if(isEnemyInRange(t, e)) {
						if(!playing.isGameManaged()) {
							if(t.isCooldownOver()) {
								playing.shootEnemy(t, e);
								t.resetCooldown();
							}
						}
					}else {
						// Do nothing
					}
				}
			}
		
		
	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		
		return range < t.getRange();
	}

	public BufferedImage[] getTowerImg() {
		return towerImgs;
	}

	public Tower getTowerAt(int x, int y) {
		
		for(Tower t : towers) {
			if(t.getX() == x && t.getY() == y)
				return t;
		}
		
		return null;
		
	}

	public void resetEverything() {
		towers.clear();
		towerAmount = 0;
		
	}
	
	public Tower getMovedTower() {
		return movedTower;
	}

	
}
