package ui;

import static main.GameStates.*;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helpz.LoadSave;
import helpz.MusicManager;

import static helpz.Constants.Towers.*;
import static helpz.Constants.EvolvedTowers.*;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar{

	
	private MyButton bMenu, bPause, bManage, bStart;
	private Playing playing;
	
	private MyButton[] towerButtons;
	private MyButton[] evolveButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	private MyButton bUpgrade, bSell, bEvolve, bMove;
	
	private DecimalFormat formatter;
	
	private int goldStart = 40;
	private int gold = goldStart;
	private int maxLives = 20;
	private int lives = maxLives;
	private boolean showTowerCost;
	private int showEvolveInfos = -1;
	private int towerCostType;
	private int maxTier = 3;
	private boolean isEvolvable;
	
	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		
		this.playing = playing;
		formatter = new DecimalFormat("0.0");
		
		initButtons();
		gold = setGoldStart();
		
	}
	
	private int setGoldStart() {
		int lvlNumber = LoadSave.getLvlNumber();
		
		if(lvlNumber == 1) {
			return 40;
		}else if(lvlNumber == 2) {
			return 50;
		}else if(lvlNumber == 3) {
			return 60;
		}else if(lvlNumber == 4) {
			return 70;
		}else if(lvlNumber == 5) {
			return 80;
		}else if(lvlNumber == 6) {
			return 90;
		}else if(lvlNumber == 7) {
			return 100;
		}else if(lvlNumber == 8) {
			return 110;
		}else if(lvlNumber == 9) {
			return 120;
		}
		
		return 0;
	}

	public void addGold(int getDroppedGold) {
		this.gold += getDroppedGold;
		
	}
	
	public void payForTower(int towerType) {
		this.gold -= helpz.Constants.Towers.GetTowerCost(towerType);
		
	}
	
	private boolean isGoldEnoughForTower(int towerType) {
		return gold >= helpz.Constants.Towers.GetTowerCost(towerType);
	}

	private boolean isTowerCostMoreThanCurrentGold() {
		return getTowerCostCost() > gold;
	}
	
	private int getSellAmount(Tower displayedTower) {
		int upgradeCost = (displayedTower.getTier() -1) * (getUpgradeAmount(displayedTower, displayedTower.getTier()-1));
		upgradeCost *= 0.5f;
		
		return (helpz.Constants.Towers.GetTowerCost(displayedTower.getTowerType()) / 2) + upgradeCost;
	}
	
	private int getUpgradeAmount(Tower displayedTower, int tier) {
		return (int)(helpz.Constants.Towers.GetTowerCost(displayedTower.getTowerType()) * (0.5f * tier));
	}
	
	private void upgradeTower() {
		playing.upgradeTower(displayedTower);
		gold -= getUpgradeAmount(displayedTower, displayedTower.getTier()-1);
		
	}
	
	private void evolveTower(int choice) {
		playing.evolveTower(displayedTower, choice);
		gold -= (helpz.Constants.Towers.GetTowerCost(displayedTower.getTowerType())*3 );
	}

	private void sellTower() {
		
		playing.removeTower(displayedTower);
		gold += getSellAmount(displayedTower);
		displayedTower = null;
		
	}
	
	private void initButtons() {
		
		bMenu = new MyButton("Menu", 555, 765, 75, 75 / 3);
		
		bPause = new MyButton("Pause", 555, 730, 75, 75 / 3);
		
		bManage = new MyButton("Manage", 555, 695, 75, 75 / 3);
		
		bStart = new MyButton("Start", 555, 660, 75, 75/3);
		
		towerButtons = new MyButton[3];
		
		int w = 50;
		int h = 50;
		int xStart = 15;
		int y = 665;
		int xOffset = (int) (w * 1.2f);	
		
		for(int i = 0; i < towerButtons.length; i++) {
			towerButtons[i] = new MyButton("", xStart + xOffset*i, y, w, h, i);
		}

		evolveButtons = new MyButton[6];
		
		int wEvo = 120;
		int hEvo = 30;
		int x1Evo = 240;
		int x2Evo = 400;
		int yEvo = 690;
		
		evolveButtons[0] = new MyButton("Nuke", x1Evo, yEvo, wEvo, hEvo);
		evolveButtons[1] = new MyButton("Artillerist", x2Evo, yEvo, wEvo, hEvo);
		evolveButtons[2] = new MyButton("Sniper", x1Evo, yEvo, wEvo, hEvo);
		evolveButtons[3] = new MyButton("HeavyBowGun", x2Evo, yEvo, wEvo, hEvo);
		evolveButtons[4] = new MyButton("TimeThief", x1Evo, yEvo, wEvo, hEvo);
		evolveButtons[5] = new MyButton("IceLord", x2Evo, yEvo, wEvo, hEvo);
		
		
		bSell = new MyButton("Sell", 235, 715, 50, 20);
		bUpgrade = new MyButton("Upgrade", 450, 715, 75, 20);
		bEvolve = new MyButton("Evolve", 450, 715, 75, 20);
		bMove = new MyButton("Move", 450, 690, 75, 20);
		
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bPause.draw(g);
		
		if(playing.getWaveManager().isWaveTimerStarted())
			bManage.draw(g);
		
		if(playing.isGameStarted() == false)
			bStart.draw(g);
		
		for(MyButton b : towerButtons) {
			g.setColor(Color.gray);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImg()[b.getId()], b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}
		
	}
	

	public void draw(Graphics g) {
		
		// Tower range under action bar
		if(displayedTower != null) {
			drawDisplayedTowerBorder(g);
			drawDisplayedTowerRange(g);
		}
		
		// Background
		g.setColor(new Color(138, 109, 70));
		g.fillRect(x, y, width, height);
		
		// Buttons
		g.setFont(new Font("LucidaSans", Font.BOLD, 12));
		drawButtons(g);
		
		if(playing.isGameStarted() == false) 
			drawGameStartWaiting(g);
			
		
		// Displayed Tower
		drawDisplayedTower(g);
		
		// Wave Info
		if(!playing.isGameManaged())
			drawWaveInfo(g);
		
		// Gold Info
		drawGoldAmount(g);
		
		// Game Pause Text
		if(playing.isGamePaused()) {
			g.setColor(Color.white);
			g.fillRect(232, 295, 175, 50);
			g.setColor(Color.black);
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawRect(232, 295, 175, 50);
			g.drawString("Game Paused", 253, 327);
		}
		
		// Game Manage Text
		if(playing.isGameManaged()) {
			g.setColor(Color.white);
			g.fillRect(325, 750, 200, 40);
			g.setColor(Color.black);
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawRect(325, 750, 200, 40);
			g.drawString("Tower Managed", 350, 775);
		}
		
		// Lives
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 18));
		g.drawString("Lives left : " + lives + " / " + maxLives, 15, 765);
		
		// Evolve Tower
		if(displayedTower != null)
			if(isEvolvable) {
				drawEvolveButtons(g);
				drawEvolveInfos(g);
			}

		// Tower Cost
		if(!playing.isGamePaused())
			drawTowerCost(g);
				
		
	}
	
	private void drawEvolveButtons(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillRect(225, 645, 310, 95);
		g.setColor(Color.black);
		g.drawRect(225, 645, 310, 95);
		g.setFont(new Font("LucidaSans", Font.BOLD, 18));
		g.drawString("Choose your evolution", 285, 675);
		

		g.setFont(new Font("LucidaSans", Font.BOLD, 12));
		switch(displayedTower.getTowerType()) {
		case CANNON:
			evolveButtons[0].draw(g);
			evolveButtons[1].draw(g);
			return;
		case ARCHER:
			evolveButtons[2].draw(g);
			evolveButtons[3].draw(g);
			return;
		case WIZARD:
			evolveButtons[4].draw(g);
			evolveButtons[5].draw(g);
			return;
		default:
			return;
		}
			
	}

	private void drawTowerCost(Graphics g) {
		if(showTowerCost) {
			g.setColor(Color.gray);
			g.fillRect(200, 665, 120, 50);
			g.setColor(Color.black);
			g.drawRect(200, 665, 120, 50);
	
			g.setFont(new Font("LucidaSans", Font.BOLD, 18));
			g.drawString("" + getTowerCostName(), 205, 685);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			if(isTowerCostMoreThanCurrentGold())
				g.setColor(new Color(163, 27, 0));
			g.drawString("Cost : " + getTowerCostCost() +"g", 205, 705);
		}
		
	}


	private int getTowerCostCost() {
		return helpz.Constants.Towers.GetTowerCost(towerCostType);
	}

	private String getTowerCostName() {
		return helpz.Constants.Towers.GetName(towerCostType);
	}

	private void drawGoldAmount(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 18));
		g.drawString("Gold : " + gold +"g", 15, 740);
		
	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);
		
		g.setFont(new Font("LucidaSans", Font.BOLD, 17));
		drawWaveTimerInfo(g);
		
	}
	
	private void drawEnemiesLeftInfo(Graphics g) {
		int enemiesRemaining = playing.getEnemyManager().getAmountOfAliveEnemies(playing.getWaveManager().getWaveIndex());
		g.drawString("Enemies left : " + enemiesRemaining, 348, 765);
		
	}
	
	private void drawWavesLeftInfo(Graphics g) {
		int currentWave = playing.getWaveManager().getWaveIndex() + 1;
		int totalWave = playing.getWaveManager().getWaves().size();
		g.drawString("Wave " + currentWave + " / " + totalWave, 348, 790);
		
	}

	private void drawWaveTimerInfo(Graphics g) {
		if(playing.getWaveManager().isWaveTimerStarted()) {
			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formatedText = formatter.format(timeLeft);
			
			g.drawString("Time before next wave : " + formatedText + " sec", 15, 790);
		}
	}
	
	
	private void drawEvolveInfos(Graphics g) {
		if(showEvolveInfos != -1) {
			g.setColor(Color.gray);
			g.fillRect(25, 645, 180, 125);
			g.setColor(Color.black);
			g.drawRect(25, 645, 180, 125);
	
			g.setFont(new Font("LucidaSans", Font.BOLD, 18));
			g.drawString("" + helpz.Constants.EvolvedTowers.GetName(showEvolveInfos), 35, 670);
			
			g.setFont(new Font("LucidaSans", Font.BOLD, 12));			
			switch(showEvolveInfos) {
			case NUKE:
				g.drawString("Solve all your problems", 35, 685);
				g.drawString("with a lage explosion.", 35, 697);
				
				g.drawString("Gain a lot of dmg and ", 35, 735);
				g.drawString("explosion radius, but lose", 35, 747);
				g.drawString("a lot of atq. speed.", 35, 759);
				return;
			case ARTILLERIST:
				g.drawString("Reloading ? ", 35, 685);
				g.drawString("What is this ?", 35, 697);
				
				g.drawString("Gain a LOT of atq. speed ", 35, 735);
				g.drawString("but lose a lot of atq and", 35, 747);
				g.drawString("explosion radius.", 35, 759);
				return;
			case SNIPER:
				g.drawString("Call me a camper if you want,", 35, 685);
				g.drawString("you'll end up dead anyway.", 35, 697);
				
				g.drawString("Gain a lot of dmg and ", 35, 735);
				g.drawString("range, but lose a lot", 35, 747);
				g.drawString("of atq. speed.", 35, 759);
				return;
			case HEAVYBG:
				g.drawString("Yeah it's a Monster", 35, 685);
				g.drawString("Hunter reference.", 35, 697);
				
				g.drawString("Gain a lot of atq speed ", 35, 735);
				g.drawString("but lose some dmg.", 35, 747);
				return;
			case TIMETHIEF:
				g.drawString("You're easy to hit ", 35, 685);
				g.drawString("if you don't move.", 35, 697);
				
				g.drawString("Stop time around tower", 35, 735);
				g.drawString("to root enemies.", 35, 747);
				g.drawString("(is that a jojo ref? )", 35, 759);
				return;
			case ICELORD:
				g.drawString("Ice magic is so versatile if", 35, 685);
				g.drawString("you know what you're doing.", 35, 697);
				
				g.drawString("Enemies gain a dmg dot", 35, 735);
				g.drawString("when slow by this tower.", 35, 747);
				return;
			default:
				return;
			}
			
		}
	}
	
	private void drawGameStartWaiting(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(225, 645, 310, 95);
		g.setColor(Color.black);
		g.drawRect(225, 645, 310, 95);

		g.setFont(new Font("LucidaSans", Font.BOLD, 18));
		g.drawString("To start the waves,", 300, 685);
		g.drawString("press the start buton", 295, 705);
	}
	

	private void drawDisplayedTower(Graphics g) {
		if(displayedTower != null) {
			g.setColor(Color.gray);
			g.fillRect(225, 645, 310, 95);
			g.setColor(Color.black);
			g.drawRect(225, 645, 310, 95);
			g.drawRect(235, 650, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImg()[displayedTower.getTowerType()], 235, 650, 50, 50, null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			
			g.drawString("" + displayedTower.getName(), 300, 665);
//			g.drawString("ID : " + displayedTower.getId(), 400, 665);
			g.drawString("Tier : " + displayedTower.getTier(), 410, 665);
			g.drawString("Dmg : " + displayedTower.getDmg(), 300, 680);
			g.drawString("Range : " + (int)displayedTower.getRange(), 410, 680);
			g.drawString("Atq Speed : " + (int)displayedTower.getCooldown(), 300, 695);
			
			bSell.draw(g);
			drawButtonFeedback(g, bSell);
			
			if(displayedTower.getTier() < maxTier) {
				bUpgrade.draw(g);
				drawButtonFeedback(g, bUpgrade);
			} else if (displayedTower.getTier() == maxTier) {
				bEvolve.draw(g);
				drawButtonFeedback(g, bEvolve);
			}
			
			if(playing.isGameManaged()) {
				bMove.draw(g);
				drawButtonFeedback(g, bMove);
			}
			
			if(bSell.isMouseOver()) {
				g.setColor(new Color(163, 27, 0));
				g.drawString("Sell for : "+getSellAmount(displayedTower)+"g", 300, 730);
			}else if(bUpgrade.isMouseOver()) {
				if (gold >= getUpgradeAmount(displayedTower, displayedTower.getTier())){
					g.setColor(new Color(5, 48, 117));
				}else {
					g.setColor(new Color(163, 27, 0));
				}
				g.drawString("Upgrade for : "+getUpgradeAmount(displayedTower, displayedTower.getTier())+"g", 300, 730);
			} else if(bEvolve.isMouseOver()) {
				if (gold >= (helpz.Constants.Towers.GetTowerCost(displayedTower.getTowerType())*3 )){
					g.setColor(new Color(5, 48, 117));
				}else {
					g.setColor(new Color(163, 27, 0));
				}
				g.drawString("Evolve for : "+(helpz.Constants.Towers.GetTowerCost(displayedTower.getTowerType())*3 )+"g", 300, 730);
			}
			
		}
		
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.white);
		g.drawOval(displayedTower.getX() + 16 - (int)(displayedTower.getRange() * 2)/2, displayedTower.getY() + 16 - (int)(displayedTower.getRange() * 2)/2, 
				(int)displayedTower.getRange()*2, (int)displayedTower.getRange()*2);
		
	}

	private void drawDisplayedTowerBorder(Graphics g) {
		g.setColor(Color.cyan);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
		
	}

	public void displayTower(Tower t) {
		displayedTower = t;
		
	}
	
	private void togglePause() {
		playing.setGamePaused(!playing.isGamePaused());
		
		if(playing.isGamePaused()) {
			bPause.setText("Resume");
		}else {
			bPause.setText("Pause");
		}
	}
	
	private void toggleManage() {
		playing.setGameManaged(!playing.isGameManaged());
		
	}

	private void toggleStart() {
		playing.setGameStart();
		
	}
	
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);		
			MusicManager.stopMusic();
			MusicManager.titleMusicStart();
		}else if(bPause.getBounds().contains(x, y)) {
			togglePause();
		}else if(bManage.getBounds().contains(x, y)) {
			toggleManage();
		}else if(bStart.getBounds().contains(x, y) && playing.isGameStarted() == false) {
			toggleStart();
		} else {
			if(!playing.isGamePaused()) {
				if(displayedTower != null ) {
					if(!isEvolvable) {
						if(bSell.getBounds().contains(x, y)) {
							sellTower();
							return;
						}else if(bUpgrade.getBounds().contains(x, y) && displayedTower.getTier() < maxTier && gold >= getUpgradeAmount(displayedTower, displayedTower.getTier())) {
							upgradeTower();
							return;
						}else if(bEvolve.getBounds().contains(x, y) && displayedTower.getTier() == maxTier && gold >= (helpz.Constants.Towers.GetTowerCost(displayedTower.getTowerType())*3 )) {
							isEvolvable = true;
							return;
						}else if(bMove.getBounds().contains(x, y)) {
							playing.moveTower(displayedTower);
							return;
						}
					}else {
						switch(displayedTower.getTowerType()) {
						case CANNON:
								if(evolveButtons[0].getBounds().contains(x, y)) {
									evolveTower(NUKE);
									isEvolvable = false;
									return;
								}else if(evolveButtons[1].getBounds().contains(x, y)) {
									evolveTower(ARTILLERIST);
									isEvolvable = false;
									return;
								}
						case ARCHER:
							if(evolveButtons[2].getBounds().contains(x, y)) {
								evolveTower(SNIPER);
								isEvolvable = false;
								return;
							}else if(evolveButtons[3].getBounds().contains(x, y)) {
								evolveTower(HEAVYBG);
								isEvolvable = false;
								return;
							}
						case WIZARD:
							if(evolveButtons[4].getBounds().contains(x, y)) {
								evolveTower(TIMETHIEF);
								isEvolvable = false;
								return;
							}else if(evolveButtons[5].getBounds().contains(x, y)) {
								evolveTower(ICELORD);
								isEvolvable = false;
								return;
							}
						}
					}
					
				}
				
				for(MyButton b : towerButtons) {
					if(b.getBounds().contains(x, y)) {
						if(!isGoldEnoughForTower(b.getId())) {
							return;
						}
						selectedTower = new Tower(0, 0, -1, b.getId(), helpz.Constants.Towers.GetName(b.getId()));
						playing.setSelectedTower(selectedTower);
						return;
						
					}
				}
				
				
			}
		}	
		
	}
	
	public void mouseMoved(int x, int y) {
		showTowerCost = false;
		showEvolveInfos = -1;
		bMenu.setMouseOver(false);
		bPause.setMouseOver(false);
		bManage.setMouseOver(false);
		bStart.setMouseOver(false);
		bSell.setMouseOver(false);
		bUpgrade.setMouseOver(false);
		bEvolve.setMouseOver(false);
		bMove.setMouseOver(false);
		for(MyButton b : towerButtons)
			b.setMouseOver(false);
		
		for(MyButton b : evolveButtons)
			b.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
			
		}else if(bPause.getBounds().contains(x, y)) {
			bPause.setMouseOver(true);
		}else if(bManage.getBounds().contains(x, y)) {
			bManage.setMouseOver(true);
		}else if(bStart.getBounds().contains(x, y) && playing.isGameStarted() == false) {
			bStart.setMouseOver(true);
		} else {
			if(!playing.isGamePaused()) {
				if(displayedTower != null ) {
					if(!isEvolvable) {
						if(bSell.getBounds().contains(x, y)) {
							bSell.setMouseOver(true);
						}else if(bUpgrade.getBounds().contains(x, y) && displayedTower.getTier() < maxTier) {
							bUpgrade.setMouseOver(true);
						}else if(bEvolve.getBounds().contains(x, y) && displayedTower.getTier() == maxTier) {
							bEvolve.setMouseOver(true);
						}else if(bMove.getBounds().contains(x, y)) {
							bMove.setMouseOver(true);
						}
					}else {
						switch(displayedTower.getTowerType()) {
						case CANNON:
							for(int i = 0; i < 2; i++)
								if(evolveButtons[i].getBounds().contains(x, y)) {
									evolveButtons[i].setMouseOver(true);
									showEvolveInfos = i;
									return;
								}
						case ARCHER:
							for(int i = 2; i < 4; i++)
								if(evolveButtons[i].getBounds().contains(x, y)) {
									evolveButtons[i].setMouseOver(true);
									showEvolveInfos = i;
									return;
								}
						case WIZARD:
							for(int i = 4; i < 6; i++)
								if(evolveButtons[i].getBounds().contains(x, y)) {
									evolveButtons[i].setMouseOver(true);
									showEvolveInfos = i;
									return;
								}
						}
					}
				}
				
				for(MyButton b : towerButtons) {
					if(b.getBounds().contains(x, y)) {
						b.setMouseOver(true);
						showTowerCost = true;
						towerCostType = b.id;
						return;
					}
					
					
				}
			}
		}
		
	}
	
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
			
		}else if(bPause.getBounds().contains(x, y)) {
			bPause.setMousePressed(true);
		}else if(bManage.getBounds().contains(x, y)) {
			bManage.setMousePressed(true);
		}else if(bStart.getBounds().contains(x, y) && playing.isGameStarted() == false) {
			bStart.setMousePressed(true);
		} else {
			if(!playing.isGamePaused()) {
				if(displayedTower != null ) {
					if(!isEvolvable) {
						if(bSell.getBounds().contains(x, y)) {
							bSell.setMousePressed(true);
						}else if(bUpgrade.getBounds().contains(x, y) && displayedTower.getTier() < maxTier) {
							bUpgrade.setMousePressed(true);
						}else if(bEvolve.getBounds().contains(x, y) && displayedTower.getTier() == maxTier) {
							bEvolve.setMousePressed(true);
						}else if(bMove.getBounds().contains(x, y)) {
							bMove.setMousePressed(true);
						}
					
					}else {
						
						switch(displayedTower.getTowerType()) {
						case CANNON:
							for(int i = 0; i < 2; i++)
								if(evolveButtons[i].getBounds().contains(x, y)) {
									evolveButtons[i].setMousePressed(true);
									return;
								}
								
						case ARCHER:
							for(int i = 2; i < 4; i++)
								if(evolveButtons[i].getBounds().contains(x, y)) {
									evolveButtons[i].setMousePressed(true);
									return;
								}
						case WIZARD:
							for(int i = 4; i < 6; i++)
								if(evolveButtons[i].getBounds().contains(x, y)) {
									evolveButtons[i].setMousePressed(true);
									return;
								}
						}
					}
				}
				
				for(MyButton b : towerButtons) {
					if(b.getBounds().contains(x, y)) {
						b.setMousePressed(true);
						return;
					}
				}
				
				
			}
		} 
	}
	
	
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bMenu.resetBooleans();
		bPause.resetBooleans();
		bManage.resetBooleans();
		bStart.resetBooleans();
		bSell.resetBooleans();
		bUpgrade.resetBooleans();
		bEvolve.resetBooleans();
		bMove.resetBooleans();
		
		for(MyButton b : towerButtons)
			b.resetBooleans();
		
		for(MyButton b : evolveButtons)
			b.resetBooleans();
	}

	public void removeLives(int damage) {
		lives -= damage;
		if(lives <= 0) {
			SetGameState(GAMEOVER);
			MusicManager.stopMusic();
			MusicManager.gameOverMusicStart();
		}
	}
	
	public void setLvlWin() {
		SetGameState(LVLWIN);
		
	}
	
	public void setGameEnd() {
		SetGameState(GAMEEND);
		MusicManager.stopMusic();
		MusicManager.gameEndMusicStart();
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setIsEvolvableFalse() {
		this.isEvolvable = false;
	}
	
	public void resetEverything() {
		lives = maxLives;
		towerCostType = 0;
		showTowerCost = false;
		showEvolveInfos = -1;
		gold = setGoldStart();
		selectedTower = null;
		displayedTower = null;
	}

	

}
