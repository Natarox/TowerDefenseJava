package scenes;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpz.LoadSave;
import helpz.MusicManager;
import main.Game;
import ui.MyButton;

public class GameEnd extends GameScene implements SceneMethods{

	private MyButton bMenu;
	private BufferedImage background, titlePlane;
	
	public GameEnd(Game game) {
		super(game);

		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 350;
//		int yOffset = 100;

		bMenu = new MyButton("Menu", x, y, w, h);
		
	}

	@Override
	public void render(Graphics g) {
		drawBackground(g);
		
		// Game Over text
		g.setColor(new Color(219, 207, 37));
		g.setFont(new Font("LucidaSans", Font.BOLD, 65));
		g.drawString("THANKS", 190, 205);
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		g.drawString("FOR PLAYING", 155, 265);
		
		// Buttons
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 15));
		bMenu.draw(g);
		
		
	}

	private void drawBackground(Graphics g) {
		background = LoadSave.getGameEndBackground();
		titlePlane = LoadSave.getMenuTitle();
		g.drawImage(background, 0, 0, null);
		g.drawImage(titlePlane, 120, 130, 400, 170, null);
		
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
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}

}
