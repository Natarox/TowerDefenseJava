package scenes;

import static main.GameStates.MENU;
import static main.GameStates.PLAYING;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpz.LoadSave;
import helpz.MusicManager;
import main.Game;
import ui.MyButton;

public class Tutorial extends GameScene implements SceneMethods{
	
	private MyButton bMenu, bSkip, bReturn;
	private MyButton bTowers, bGeneralInfos, bEvolveTower, bUpgradeTower, bEnemies, bManage;
	private boolean towers, generalInfos, evolveTower, upgradeTower, enemies, manage;
	private boolean menu = true;
	
	private BufferedImage cannon, archer, wizard;
	private BufferedImage orc, bat, knight, wolf;
	private BufferedImage portal, grass;
	

	public Tutorial(Game game) {
		super(game);

		initButtons();
		initImages();
	}

	private void initImages() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();

		orc = atlas.getSubimage(0, 32, 32, 32);
		bat = atlas.getSubimage(32, 32, 32, 32);
		knight = atlas.getSubimage(2 * 32, 32, 32, 32);
		wolf = atlas.getSubimage(3 * 32, 32, 32, 32);
		
		cannon = atlas.getSubimage((4)*32, 32, 32, 32);
		archer = atlas.getSubimage((4+1)*32, 32, 32, 32);
		wizard = atlas.getSubimage((4+2)*32, 32, 32, 32);
		
		portal = LoadSave.getBlackHole();
		grass = atlas.getSubimage(9*32, 0, 32, 32);
		
	}

	private void initButtons() {

		bMenu = new MyButton("Menu", 545, 15, 75, 75 / 3);
		bSkip = new MyButton("Skip", 545, 760, 75, 75 / 3);
		bReturn = new MyButton("Return", 450, 760, 75, 75 / 3);
		
		int w = 175;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
		
		bGeneralInfos = new MyButton("General Informations", x, y, w, h);
		bTowers = new MyButton("Towers", x, y + yOffset, w, h);
		bUpgradeTower = new MyButton("Upgrade Tower", x, y + (yOffset * 2), w, h);
		bEvolveTower = new MyButton("Towers Evolutions", x, y + (yOffset * 3), w, h);
		bEnemies = new MyButton("Enemies", x, y + (yOffset * 4), w, h);
		bManage = new MyButton("Manage Towers", x, y + (yOffset * 5), w, h);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(219, 188, 123));
		g.fillRect(0, 0, 640, 800);
		
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 15));
		
		bMenu.draw(g);
		bSkip.draw(g);
		
		if(!menu)
			bReturn.draw(g);
		
		if(menu) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 50));
			g.drawString("Tutorial", 230, 85);
			

			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			bGeneralInfos.draw(g);
			bTowers.draw(g);
			bUpgradeTower.draw(g);
			bEvolveTower.draw(g);
			bEnemies.draw(g);
			bManage.draw(g);
		}
		
		if(generalInfos) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 40));
			g.drawString("General Informations", 125, 85);
			
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawString("Welcome to my game !", 15, 125);
			g.drawString("If you don't know how a tower defense work, it's really simple :", 15, 150);
			
			g.drawString("Waves of enemies will start to spawn, and your goal is to stop ", 15, 200);
			g.drawString("them from reaching the end, by buying and upgrading towers.", 20, 225);
			
			g.drawString("The enemies will spawn from this portal :", 15, 275);
			g.drawImage(portal, 415, 240, 50, 50, null);
			g.drawString("And will progress through the road to reach the end.", 15, 300);
			g.drawString("If you let an ennemy reach the end, you'll lose some life point, ", 15, 325);
			g.drawString("and if you reach 0 lp, you'll lose.", 20, 350);
			
			g.drawString("To protect you, you can buy and place towers on the field.", 15, 400);
			g.drawString("You can only place a tower on the grass :", 15, 425);
			g.drawImage(grass, 415, 403, 32, 32, null);
			g.drawString("(Because it's been a wile since they touched some.)", 20, 450);
			
			g.drawString("To buy and upgrade your towers, you'll need gold.", 15, 500);
			g.drawString("You'll gain 1 gold every 3 seconds, and you'll gain some if you", 15, 525);
			g.drawString("kill an enemy.", 20, 550);
			
			g.drawString("There is 9 different maps on this game.", 15, 600);
			g.drawString("To win a map, you'll need to clear all waves in it.", 15, 625);
			g.drawString("The more you progress through the maps, the more there will be ", 15, 650);
			g.drawString("enemies and waves (and the enemies will have more health).", 20, 675);
			
			g.drawString("I wish you good luck.", 15, 725);
			
			
		}else if(towers) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 40));
			g.drawString("Towers", 250, 85);

			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawString("There is 3 types of towers :", 15, 125);
			
			g.drawString("Archer :", 15, 175);
			g.drawImage(archer, 100, 150, 32, 32, null);
			g.drawString("Archers have a decent range and attack speed, but can only hit ", 15, 200);
			g.drawString("one enemies at the time.", 20, 225);
			g.drawString("It's the most basic tower, but it's really reliable.", 15, 250);
			
			g.drawString("Cannon : ", 15, 300);
			g.drawImage(cannon, 110, 275, 32, 32, null);
			g.drawString("Cannons have a lot of damage, but a poor attack speed.", 15, 325);
			g.drawString("While hitting the enemies, the cannon ball will explode and ", 15, 350);
			g.drawString("deal damage to a certain area.", 20, 375);
			
			g.drawString("Wizard : ", 15, 425);
			g.drawImage(wizard, 100, 400, 32, 32, null);
			g.drawString("Wizards are the supportive tower.", 15, 450);
			g.drawString("They won't deal any damage, but they'll slow the ennemies.", 15, 475);
			g.drawString("It can be really helpfull if an enemy is running too fast, ", 15, 500);
			g.drawString("or if there is too much enemies and you want to temporise.", 20, 525);
			
			g.drawString("After placing a tower, you'll be able to upgrade it up to 3 times.", 15, 575);
			g.drawString("The 3rd upgrade will be an evolution, but it will be more  ", 15, 600);
			g.drawString("explained on the Evolution chapter.", 20, 625);
			
		}else if(upgradeTower) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 40));
			g.drawString("Upgrade Towers", 165, 85);
			
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawString("Towers are not locked to be a lvl 1 infinitly buyable structure, ", 15, 125);
			g.drawString("they can be upgraded.", 20, 150);
			
			g.drawString("To upgrade a tower, just click on it to show its informations, ", 15, 200);
			g.drawString("and there will be the upgrade button.", 20, 225);
			
			g.drawString("You can buy an upgrade only two times per tower, they start ", 15, 275);
			g.drawString("Tier 1 and you can make them Tier 3.", 20, 300);
			
			g.drawString("Of course, the higher the tier, the more price they'll cost ", 15, 350);
			g.drawString("to be upgraded.", 20, 375);
			
			g.drawString("And don't worry, if you sell an upgraded tower, you'll get back", 15, 425);
			g.drawString("more gold depending of the number of ameliorations ", 20, 450);
			g.drawString("you've purchased.", 20, 475);
			
			g.drawString("Each upgrade will be different depending of the tower type,", 15, 525);
			g.drawString("but generally speaking, they gain damage (not for Wizard tho), ", 20, 550);
			g.drawString("attack speed and range.", 20, 575);
			
			g.drawString("After Tier 3, you can buy an Evolution, more detailed on the ", 15, 625);
			g.drawString("coresponding tutorial.", 20, 650);
			
		}else if(evolveTower) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 40));
			g.drawString("Tower Evolutions", 155, 85);
			
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawString("After reaching Tier 3, your tower will be able to evolve.", 15, 125);
			g.drawString("(Not quite like pokemon tho.)", 15, 150);
			
			g.drawString("For each tower type, two path of evolution will be availabe.", 15, 200);
			
			g.drawString("Each evolutions are differents and offers new ways to play ", 15, 250);
			g.drawString("around your towers.", 20, 275);
			
			g.drawString("Don't worry, each evolutions are described the moment you ", 15, 325);
			g.drawString("want to choose an evolution, so you don't need to remember ", 20, 350);
			g.drawString("them every time.", 20, 375);
			
			g.drawString("After evolving, you won't be able to upgrade them ", 15, 425);
			g.drawString("like the base tower.", 20, 450);
			
			g.drawString("So go try them all, try to find new synergies between them, ", 15, 500);
			g.drawString("it's up to you to do an evolution or not, and to chose ", 20, 525);
			g.drawString("wich one you want after all.", 20, 550);
			
			g.drawString("(And even if they are powerfull, they cost quite a buch of gold, ", 15, 600);
			g.drawString("so you'll have to balance between making one tower strong ", 20, 625);
			g.drawString("and having multiple towers to manage a lot of enemies.)", 20, 650);
			
		}else if(enemies) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 40));
			g.drawString("Enemies", 230, 85);
			
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawString("There is 4 types of ennemies :", 15, 125);
			
			g.drawString("Orc :", 15, 175);
			g.drawImage(orc, 65, 150, 32, 32, null);
			g.drawString("Slow, it just have some health. It's the most basic enemy, ", 15, 200);
			g.drawString("but can quickly become problematic if targeted by your towers ", 20, 225);
			g.drawString("wile other, quicker, enemies rush through your defenses.", 20, 250);
			
			g.drawString("Bat : ", 15, 300);
			g.drawImage(bat, 65, 275, 32, 32, null);
			g.drawString("Really low health, but quicker than the orc.", 15, 325);
			g.drawString("Be carefull of the positions of your towers, if your projectiles", 15, 350);
			g.drawString("are too slow, they might not it the bat.", 20, 375);
			
			g.drawString("Knight : ", 15, 425);
			g.drawImage(knight, 95, 400, 32, 32, null);
			g.drawString("A pure tank, but really slow. ", 15, 450);
			g.drawString("Take care of the other enemies if this one is targeted, ", 15, 475);
			g.drawString("because it'll take some time to bring him down.", 20, 500);
			
			g.drawString("Wolf : ", 15, 550);
			g.drawImage(wolf, 70, 525, 32, 32, null);
			g.drawString("It's the quickest enemy, and it has a little more health than ", 15, 575);
			g.drawString("the bat, so same as the bat, be careful of your projectiles ", 20, 600);
			g.drawString("not hitting him, while making sure to deal enough damage.", 20, 625);
			
			g.drawString("Each enemies will drop different amount of gold if killed.", 15, 675);
			g.drawString("Don't forget that the more you advance in the wave number ", 15, 700);
			g.drawString("and map number, the more the enemies will have health.", 20, 725);
			
		}else if(manage) {
			g.setFont(new Font("LucidaSans", Font.BOLD, 40));
			g.drawString("Manage Towers", 230, 85);
			
			g.setFont(new Font("LucidaSans", Font.BOLD, 20));
			g.drawString("Your towers have terrible placement ? ", 15, 125);
			g.drawString("Don't you worry about that.", 15, 150);
			
			g.drawString("If you realise your tower are too far away from the enemies,", 15, 200);
			g.drawString("or if you just want them to be placed somewere else, ", 20, 225);
			g.drawString("you just have to wait until you clear the current wave.", 20, 250);
			
			g.drawString("After each wave, while waiting for the next, you can press the ", 15, 300);
			g.drawString("manage button if you need.", 20, 325);
			
			g.drawString("If pressed, the game wil pause until you pressed it again.", 15, 375);
			
			g.drawString("In this state, if you click a tower, a new button will appear, ", 15, 425);
			g.drawString("the move button.", 20, 450);
			
			g.drawString("Just click it from the tower you want to move, ", 15, 500);
			g.drawString("click where you want to place it, and that's it.", 20, 525);
			
			g.drawString("Don't forget to repress the manage button if you ", 15, 575);
			g.drawString("have finished to unpause the game.", 20, 600);
		}
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}else if(bSkip.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
			MusicManager.stopMusic();
			MusicManager.playingMusicStart();
		}
		if(menu) {
			if(bGeneralInfos.getBounds().contains(x, y)) {
				menu = false;
				generalInfos = true;
			}else if(bTowers.getBounds().contains(x, y)) {
				menu = false;
				towers = true;
			}else if(bUpgradeTower.getBounds().contains(x, y)) {
				menu = false;
				upgradeTower = true;
			}else if(bEvolveTower.getBounds().contains(x, y)) {
				menu = false;
				evolveTower = true;
			}else if(bEnemies.getBounds().contains(x, y)) {
				menu = false;
				enemies = true;
			}else if(bManage.getBounds().contains(x, y)) {
				menu = false;
				manage = true;
				
			}
		}else {
			if(bReturn.getBounds().contains(x, y)) {
				towers = false;
				generalInfos = false;
				evolveTower = false;
				upgradeTower = false;
				enemies = false;
				manage = false;
				
				menu = true;
			}
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSkip.setMouseOver(false);
		bReturn.setMouseOver(false);
		
		bGeneralInfos.setMouseOver(false);
		bTowers.setMouseOver(false);
		bUpgradeTower.setMouseOver(false);
		bEvolveTower.setMouseOver(false);
		bEnemies.setMouseOver(false);
		bManage.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}else if(bSkip.getBounds().contains(x, y)) {
			bSkip.setMouseOver(true);
		}
		
		if(menu) {
			if(bGeneralInfos.getBounds().contains(x, y)) {
				bGeneralInfos.setMouseOver(true);
			}else if(bTowers.getBounds().contains(x, y)) {
				bTowers.setMouseOver(true);
			}else if(bUpgradeTower.getBounds().contains(x, y)) {
				bUpgradeTower.setMouseOver(true);
			}else if(bEvolveTower.getBounds().contains(x, y)) {
				bEvolveTower.setMouseOver(true);
			}else if(bEnemies.getBounds().contains(x, y)) {
				bEnemies.setMouseOver(true);
			}else if(bManage.getBounds().contains(x, y)) {
				bManage.setMouseOver(true);
			}
		}else {
			if(bReturn.getBounds().contains(x, y)) {
				bReturn.setMouseOver(true);
			}
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}else if(bSkip.getBounds().contains(x, y)) {
			bSkip.setMousePressed(true);
		}
		
		if(menu) {
			if(bGeneralInfos.getBounds().contains(x, y)) {
				bGeneralInfos.setMousePressed(true);
			}else if(bTowers.getBounds().contains(x, y)) {
				bTowers.setMousePressed(true);
			}else if(bUpgradeTower.getBounds().contains(x, y)) {
				bUpgradeTower.setMousePressed(true);
			}else if(bEvolveTower.getBounds().contains(x, y)) {
				bEvolveTower.setMousePressed(true);
			}else if(bEnemies.getBounds().contains(x, y)) {
				bEnemies.setMousePressed(true);
			}else if(bManage.getBounds().contains(x, y)) {
				bManage.setMousePressed(true);
			}
		}else {
			if(bReturn.getBounds().contains(x, y)) {
				bReturn.setMousePressed(true);
			}
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSkip.resetBooleans();
		bReturn.resetBooleans();
		
		bGeneralInfos.resetBooleans();
		bTowers.resetBooleans();
		bUpgradeTower.resetBooleans();
		bEvolveTower.resetBooleans();
		bEnemies.resetBooleans();
		bManage.resetBooleans();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
