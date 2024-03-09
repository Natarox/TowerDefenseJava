package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpz.LoadSave;
import helpz.MusicManager;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{
	
	private MyButton bPlaying, bSelectLvl, bSettings, bQuit;
//	private MyButton bEdit;
	private BufferedImage background, titlePlane;

	public Menu(Game game) {
		super(game);

		initButtons();
		
		playMusic();
		
	}

	private void playMusic() {
		MusicManager.titleMusicStart();
		
	}
	private void changeMusic() {
		MusicManager.stopMusic();
		MusicManager.playingMusicStart();
		
	}

	private void initButtons() {
		
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 230;
		int yOffset = 100;
		
		
		bPlaying = new MyButton("Play", x, y, w, h);
		bSelectLvl = new MyButton("Select Level", x, y + yOffset, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset*2, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
//		bEdit = new MyButton("Editor", x, y+ yOffset*4, w, h);
				
		
	}

	@Override
	public void render(Graphics g) {

		drawBackgroung(g);
		
		drawButtons(g);
		
		drawTitle(g);
		
	}
	
	private void drawTitle(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("LucidaSans", Font.BOLD, 60));
		g.drawString("Tower Defense", 110, 115);
		g.setFont(new Font("LucidaSans", Font.BOLD, 25));
		g.drawString("Or something like that ...", 185, 165);
	}

	private void drawBackgroung(Graphics g) {
		background = LoadSave.getMenuBackground();
		titlePlane = LoadSave.getMenuTitle();
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(titlePlane, 70, 40, 500, 175, null);
		
	}

	private void drawButtons(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 15));
		bPlaying.draw(g);
		bSelectLvl.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
//		bEdit.draw(g);
		
	}

	@Override
	public void mouseClicked(int x, int y) {

		if(bPlaying.getBounds().contains(x, y)) {
			if(LoadSave.getSaveNumber() == 0) {
				SetGameState(TUTORIAL);
			}else {
				SetGameState(PLAYING);
				changeMusic();
			}
		}else if(bSelectLvl.getBounds().contains(x, y)) {
			SetGameState(LEVELSELECT);
		}else if(bSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		}else if(bQuit.getBounds().contains(x, y)) {
			System.exit(0);
//		}else if(bEdit.getBounds().contains(x, y)) {
//			SetGameState(EDIT);
		}
		
	}

	

	@Override
	public void mouseMoved(int x, int y) {

		bPlaying.setMouseOver(false);
		bSelectLvl.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);
//		bEdit.setMouseOver(false);
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		}else if(bSelectLvl.getBounds().contains(x, y)) {
			bSelectLvl.setMouseOver(true);
		}else if(bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		}else if(bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
//		}else if(bEdit.getBounds().contains(x, y)) {
//			bEdit.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		}else if(bSelectLvl.getBounds().contains(x, y)) {
			bSelectLvl.setMousePressed(true);
		}else if(bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		}else if(bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
//		}else if(bEdit.getBounds().contains(x, y)) {
//			bEdit.setMousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSelectLvl.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
//		bEdit.resetBooleans();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
