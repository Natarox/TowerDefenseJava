package scenes;

import static main.GameStates.MENU;
import static main.GameStates.PLAYING;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import helpz.LoadSave;
import helpz.MusicManager;
import main.Game;
import ui.MyButton;

public class LevelSelect extends GameScene implements SceneMethods{

	private MyButton bMenu;
	private MyButton[] levelButtons;
	private static int saveProgress;
	
	public LevelSelect(Game game) {
		super(game);
		initButtons();
		getSaveProgress();
	}

	private static void getSaveProgress() {
		saveProgress = LoadSave.GetSaveData();
	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 545, 15, 75, 75 / 3);
		
		levelButtons = new MyButton[9];
		
		int w = 50;
		int h = 50;
		int xStart = 215;
		int y = 275;
		int xOffset = (int) (w * 1.5f);
		int yOffset = (int) (h * 1.5f);
		int xPos = 0;
		int yPos = 0;
		
		for(int i = 0; i < levelButtons.length; i++) {
			
			levelButtons[i] = new MyButton(""+(i+1), xStart + xOffset*xPos, y + yOffset * yPos, w, h, i);
			
			if(xPos >= 2) {
				xPos = 0;
				yPos++;
			} else 	
				xPos++;
		}
		
	}
	
	public static void resetSaveProgress() {
		getSaveProgress();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(219, 188, 123));
		g.fillRect(0, 0, 640, 800);
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		g.drawString("LEVEL", 240, 150);
		g.drawString("SELECT", 220, 200);
		
		
		
		g.setFont(new Font("LucidaSans", Font.BOLD, 12));
		bMenu.draw(g);
		
		for(MyButton b : levelButtons) {
			if(b.getId() + 1 <= saveProgress)
				b.draw(g);
			else
				b.drawDisabled(g);
		}
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);	
		} else {
			for(MyButton b : levelButtons) {
				if(b.getBounds().contains(x, y) && b.getId() + 1 <= saveProgress) {
					game.getPlaying().setCurrentLevel(b.getId() + 1);
					SetGameState(PLAYING);
					MusicManager.stopMusic();
					MusicManager.playingMusicStart();
				}
			}
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for(MyButton b : levelButtons)
			b.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else {
			for(MyButton b : levelButtons) {
				if(b.getBounds().contains(x, y) && b.getId() + 1 <= saveProgress)
					b.setMouseOver(true);
			}
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else {
			for(MyButton b : levelButtons) {
				if(b.getBounds().contains(x, y) && b.getId() + 1 <= saveProgress)
					b.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		
		for(MyButton b : levelButtons)
			b.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		
	}

}
