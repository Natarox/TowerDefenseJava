package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import static helpz.Constants.Tiles.GRASS_TILE;


public class Playing extends GameScene implements SceneMethods{
	
	private int[][] lvl;
	private int mouseX, mouseY;
	private ActionBar actionBar;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private ProjectileManager projManager;
	private WaveManager waveManager;
	private Tower selectedTower, movedTower;
	private boolean drawSelect;
	private int goldTick = 0;
	private boolean gamePaused, gameManage, movingTower, gameStarted;

	private PathPoint start, end;

	public Playing(Game game) {
		super(game);
		
		loadDefaultLevel();
		
		actionBar = new ActionBar(0, 640, 640, 160, this);
		
//		enemyManager = new EnemyManager(this, start, end);
//		waveManager = new WaveManager(this);
		towerManager = new TowerManager(this);
		projManager = new ProjectileManager(this);

	}
	
	

	private void loadDefaultLevel() {
		resetLevel();
	}
	
	private void loadNextLevel() {
		if(LoadSave.getLvlNumber() <= 8) {
			LoadSave.nextLevel();
			resetEverything();
			resetLevel();
		}
		
	}
	
	public void setCurrentLevel(int currentLevel) {
		LoadSave.SetCurrentLevel(currentLevel);
		resetEverything();
		resetLevel();
	}
	
	private void resetLevel() {
		lvl = LoadSave.GetLevelData();
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoint();
		start = points.get(0);
		end = points.get(1);		
		
		enemyManager = new EnemyManager(this, start, end);
		waveManager = new WaveManager(this);
	}
	
	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}
	
	public void update() {
		
		if(!gamePaused) {
			updateTick();
			waveManager.update();
			if(!gameManage) {
				if(gameStarted) {
					
					goldTick++;
					if(goldTick >= (60*3)) {
						actionBar.addGold(1);
						goldTick = 0;
					}
						
					if(isAllEnemiesDead()) {
						if(isThereMoreWaves()) {
							waveManager.startWaveTimer();
							//Check time
							if(isWaveTimerOver()) {
								waveManager.increaseWaveIndex();
								enemyManager.getEnemies().clear();
								waveManager.resetEnemyIndex();
							}
								
						} else {
							loadNextLevel();
							if(LoadSave.getLvlNumber() > LoadSave.GetSaveData()) {
								saveProgress(LoadSave.getLvlNumber());
								LevelSelect.resetSaveProgress();
							}
							if(LoadSave.getLvlNumber() <= 8) {
								actionBar.setLvlWin();
							}else {
								actionBar.setGameEnd();
							}
						}
					}
						
					if(isTimeForNewEnemy()) 
						spawnEnemy();
						
						
					enemyManager.update();
					projManager.update();
					towerManager.update();
				}
				
			}
		}
	}
	
	private void saveProgress(int lvlNumber) {
		LoadSave.SaveProgress(lvlNumber);
		
	}



	private boolean isWaveTimerOver() {
		return waveManager.isWaveTimerOver();
	}



	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();
	}



	private boolean isAllEnemiesDead() {
		if(waveManager.isThereMoreEnemiesInWave())
			return false;
		
		for(Enemy e : enemyManager.getEnemies())
			if(e.isAlive())
				return false;
		
		return true;
	}



	private void spawnEnemy() {
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
		
	}

	private boolean isTimeForNewEnemy() {
		if(waveManager.isTimeForNewEnemy()) {
			if(waveManager.isThereMoreEnemiesInWave())
				return true;
		}
		return false;
	}
	
	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}


	@Override
	public void render(Graphics g) {

		drawLevel(g);
		
		enemyManager.draw(g);
		towerManager.draw(g);
		projManager.draw(g);

		
		drawSelectedTower(g);
		drawHighlight(g);
		drawWaveInfos(g);

		actionBar.draw(g);
	}
	
	private void drawWaveInfos(Graphics g) {
		
		
	}

	private void drawHighlight(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(mouseX, mouseY, 32, 32);
		
	}

	private void drawSelectedTower(Graphics g) {
		if(selectedTower != null && drawSelect)
			g.drawImage(towerManager.getTowerImg()[selectedTower.getTowerType()], mouseX, mouseY, null);
		
	}

	private void drawLevel(Graphics g) {
		for(int y = 0; y < lvl.length; y++) {
			for(int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if(isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x*32, y*32, null);
				} else
					g.drawImage(getSprite(id), x*32, y*32, null);
			}
		}
		

		if(start != null) {
			g.drawImage(LoadSave.getBlackHole(), start.getxCord() * 32, start.getyCord() * 32, 32, 32, null);
		}
	}
	
	public int getTileType(int x, int y) {
		int xCord = x/32;
		int yCord = y/32;
		
		if(xCord < 0 || xCord > 19)
			return 0;
		
		if(yCord < 0 || yCord > 19)
			return 0;
		
		int id = lvl[y/32][x/32];
		return game.getTileManager().getTile(id).getTileType();
	}
	
	private Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);
	}

	public void removeTower(Tower displayedTower) {
		towerManager.removeTower(displayedTower);
		
	}
	
	public void shootEnemy(Tower t, Enemy e) {
		projManager.newProjectile(t, e);
		
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y/32][x/32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}
	
	public void removeLives(int damage) {
		actionBar.removeLives(damage);
	}
	
	private void removeGold(int towerType) {
		actionBar.payForTower(towerType);
		
	}
	
	public void rewardPlayer(int enemyType) {
		actionBar.addGold(helpz.Constants.Enemies.GetDroppedGold(enemyType));
	}
	
	public void upgradeTower(Tower displayedTower) {
		towerManager.upgradeTower(displayedTower);
		
	}
	
	public void evolveTower(Tower displayedTower, int choice) {
		towerManager.evolveTower(displayedTower, choice);
	}
	
	public void moveTower(Tower displayedTower) {
		if(gameManage) {
			towerManager.moveTower(displayedTower);
			movedTower = towerManager.getMovedTower();
			actionBar.displayTower(null);
			movingTower = true;
		}
	}
	
	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	
	public void setGameManaged(boolean gameManage) {
		this.gameManage = gameManage;
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(y >= 640) {
			actionBar.mouseClicked(x, y);
		} else {
			if(selectedTower != null) {
				if(isTileGrass(mouseX, mouseY)) {
					if(getTowerAt(mouseX, mouseY) == null) {
						if(gameManage && movingTower) {
							towerManager.movedTower(movedTower, mouseX, mouseY);
							movingTower = false;
						}else {
							towerManager.addTower(selectedTower, mouseX, mouseY);
							removeGold(selectedTower.getTowerType());
						}
						
						selectedTower = null;

					}
					
				}
			} else {
				Tower t = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(t);
				
			}
			
			actionBar.setIsEvolvableFalse();
		}
		
	}
	
	public void mouseClickedButton(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3) {
			selectedTower = null;
		}
	}
	
	@Override
	public void mouseMoved(int x, int y) {
		
		if(y >= 640) {
			actionBar.mouseMoved(x, y);
			drawSelect = false;
		} else {
			drawSelect = true;
			mouseX = (x / 32) *32;
			mouseY = (y / 32) *32;
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if(y >= 640) {
			actionBar.mousePressed(x, y);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
			actionBar.mouseReleased(x, y);
		
	}


	@Override
	public void mouseDragged(int x, int y) {
		
		
	}
	
	public TowerManager getTowerManager() {
		return towerManager;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public boolean isGameManaged() {
		return gameManage;
	}
	
	public void setGameStart() {
		this.gameStarted = true;
		
	}
	
	public boolean isGameStarted() {
		return gameStarted;
	}

	public void resetEverything() {
		actionBar.resetEverything();

		enemyManager.resetEverything();
		towerManager.resetEverything();
		projManager.resetEverything();
		waveManager.resetEverything();
		
		mouseX = 0;
		mouseY = 0;
		goldTick = 0;
		selectedTower = null;
		gamePaused = false;
		gameManage = false;
		gameStarted = false;
		
	}

}
