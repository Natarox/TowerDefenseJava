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

public class LvlWin extends GameScene implements SceneMethods {


	private MyButton bNextLvl, bMenu;
	private BufferedImage background, titlePlane;
	
	public LvlWin(Game game) {
		super(game);

		initButtons();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 350;
		int yOffset = 100;
		
		bNextLvl = new MyButton("Next Level", x, y, w, h);
		bMenu = new MyButton("Menu", x, y + yOffset, w, h);
		
	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		
		// Level Win text
		g.setColor(new Color(5, 48, 117));
		g.setFont(new Font("LucidaSans", Font.BOLD, 65));
		g.drawString("LEVEL", 225, 200);
		g.drawString("WINNED", 200, 275);
		
		// Buttons
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 15));
		bNextLvl.draw(g);
		bMenu.draw(g);
		
	}

	private void drawBackground(Graphics g) {

		background = LoadSave.getLevelWinBackground();
		titlePlane = LoadSave.getMenuTitle();
		g.drawImage(background, 0, 0, null);
		g.drawImage(titlePlane, 175, 130, 310, 170, null);
		
	}

	private void resetGame() {
		game.getPlaying().resetEverything();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			resetGame();
			SetGameState(MENU);	
			MusicManager.stopMusic();
			MusicManager.titleMusicStart();
		}else if(bNextLvl.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bNextLvl.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}else if(bNextLvl.getBounds().contains(x, y)) {
			bNextLvl.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}else if(bNextLvl.getBounds().contains(x, y)) {
			bNextLvl.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bNextLvl.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}

}
