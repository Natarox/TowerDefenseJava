package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Game;
import main.GameStates;

public class KeyboardListener implements KeyListener{

//	private Game game;
	
	public KeyboardListener(Game game) {
//		this.game = game;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_M)
			GameStates.gameState = MENU;
		
		else if(e.getKeyCode() == KeyEvent.VK_P)
			GameStates.gameState = PLAYING;

		
		else if(e.getKeyCode() == KeyEvent.VK_S)
			GameStates.gameState = SETTINGS;

		else if(e.getKeyCode() == KeyEvent.VK_E)
			GameStates.gameState = EDIT;
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
