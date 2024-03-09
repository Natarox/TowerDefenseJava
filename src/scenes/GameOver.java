package scenes;

import static main.GameStates.*;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpz.LoadSave;
import helpz.MusicManager;
import main.Game;
import ui.MyButton;

public class GameOver extends GameScene implements SceneMethods {

	private MyButton bRetry, bMenu;
	private BufferedImage background, titlePlane;
	
	public GameOver(Game game) {
		super(game);
		
		initButtons();
		
	}

	private void initButtons() {
		
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 350;
		int yOffset = 100;
		
		bRetry = new MyButton("Retry", x, y, w, h);
		bMenu = new MyButton("Menu", x, y + yOffset, w, h);
		
	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		
		// Game Over text
		g.setColor(new Color(163, 27, 0));
		g.setFont(new Font("LucidaSans", Font.BOLD, 65));
		g.drawString("GAME", 225, 200);
		g.drawString("OVER", 230, 275);
		
		// Buttons
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 15));
		bRetry.draw(g);
		bMenu.draw(g);
		
	}
	
	private void drawBackground(Graphics g) {
		background = LoadSave.getGameOverBackground();
		titlePlane = LoadSave.getMenuTitle();
		g.drawImage(background, 0, 0, null);
		g.drawImage(titlePlane, 200, 130, 240, 170, null);
		
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
		}else if(bRetry.getBounds().contains(x, y)) {
			resetGame();
			SetGameState(PLAYING);
			MusicManager.stopMusic();
			MusicManager.playingMusicStart();
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bRetry.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}else if(bRetry.getBounds().contains(x, y)) {
			bRetry.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}else if(bRetry.getBounds().contains(x, y)) {
			bRetry.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bRetry.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}

}
