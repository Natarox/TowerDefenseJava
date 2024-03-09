package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import helpz.MusicManager;
import main.Game;
import ui.MyButton;

import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods{

	private MyButton bMenu;
	private MyButton volumeUp, volumeDown; 
	
	
	public Settings(Game game) {
		super(game);
		
		initButtons();

	}

	private void initButtons() {
		int w = 75;
		int h = w / 3;
		int x = 545;
		int y = 15;
		
		
		bMenu = new MyButton("Menu", x, y, w, h);
		volumeDown = new MyButton("-", 150, 350, 25, 25);
		volumeUp = new MyButton("+", 465, 350, 25, 25);
		
	}

	@Override
	public void render(Graphics g) {

		g.setColor(new Color(219, 188, 123));
		g.fillRect(0, 0, 640, 800);
		
		g.setColor(Color.white);
		g.fillRect(200, 357, 240, 10);
		
		g.setColor(Color.blue);
		g.fillRect(200, 357, (int)(240*MusicManager.getVolume()), 10);
		
		g.setColor(Color.black);
		g.drawRect(200, 357, 240, 10);
		g.setFont(new Font("LucidaSans", Font.BOLD, 40));
		g.drawString("Volume", 250, 325);
			
		g.setFont(new Font("LucidaSans", Font.BOLD, 25));
		g.drawString(""+(int)(MusicManager.getVolume() * 100)+" %", 300, 400);
		
		g.setFont(new Font("LucidaSans", Font.BOLD, 12));
		drawButtons(g);
		
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		

		g.setFont(new Font("LucidaSans", Font.BOLD, 24));
		volumeDown.draw(g);
		volumeUp.draw(g);
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		} else if(volumeDown.getBounds().contains(x, y)) {
			if(MusicManager.getVolume() > 0.06) {
				MusicManager.changeVolume(MusicManager.getVolume() - 0.05);
				MusicManager.setVolume();
			}else {
				MusicManager.changeVolume(0);
				MusicManager.setVolume();
			}
			
		} else if(volumeUp.getBounds().contains(x, y)) {
			if(MusicManager.getVolume() < 0.95) {
				MusicManager.changeVolume(MusicManager.getVolume() + 0.05);
				MusicManager.setVolume();
			}else {
				MusicManager.changeVolume(1);
				MusicManager.setVolume();
			}
		}
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		volumeDown.setMouseOver(false);
		volumeUp.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else if(volumeDown.getBounds().contains(x, y)) {
			volumeDown.setMouseOver(true);
		} else if(volumeUp.getBounds().contains(x, y)) {
			volumeUp.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if(volumeDown.getBounds().contains(x, y)) {
			volumeDown.setMousePressed(true);
		} else if(volumeUp.getBounds().contains(x, y)) {
			volumeUp.setMousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bMenu.resetBooleans();
		volumeDown.resetBooleans();;
		volumeUp.resetBooleans();;
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
