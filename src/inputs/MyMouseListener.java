package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private Game game;
	private int currentButton = -1;
	
	public MyMouseListener(Game game) {
		this.game = game;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(currentButton == 1) {
		
			switch(GameStates.gameState) {
			case MENU:
				game.getMenu().mouseDragged(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlaying().mouseDragged(e.getX(), e.getY());
				break;
			case SETTINGS:
				game.getSettings().mouseDragged(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mouseDragged(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mouseDragged(e.getX(), e.getY());
				break;
			case LVLWIN:
				game.getLvlWin().mouseDragged(e.getX(), e.getY());
				break;
			case LEVELSELECT:
				game.getLvlSelect().mouseDragged(e.getX(), e.getY());
				break;
			case GAMEEND:
				game.getGameEnd().mouseDragged(e.getX(), e.getY());
				break;
			case TUTORIAL:
				game.getTutorial().mouseDragged(e.getX(), e.getY());
				break;
			default:
				break;
			
			}
			
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		switch(GameStates.gameState) {
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseMoved(e.getX(), e.getY());
			break;
		case SETTINGS:
			game.getSettings().mouseMoved(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseMoved(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseMoved(e.getX(), e.getY());
			break;
		case LVLWIN:
			game.getLvlWin().mouseMoved(e.getX(), e.getY());
			break;
		case LEVELSELECT:
			game.getLvlSelect().mouseMoved(e.getX(), e.getY());
			break;
		case GAMEEND:
			game.getGameEnd().mouseMoved(e.getX(), e.getY());
			break;
		case TUTORIAL:
			game.getTutorial().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		
			switch(GameStates.gameState) {
			case MENU:
					game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
				if(e.getButton() == MouseEvent.BUTTON1)
					game.getPlaying().mouseClicked(e.getX(), e.getY());
				else if(e.getButton() == MouseEvent.BUTTON3) 
					game.getPlaying().mouseClickedButton(e);
				break;
			case SETTINGS:
				game.getSettings().mouseClicked(e.getX(), e.getY());
				break;
			case EDIT:
				if(e.getButton() == MouseEvent.BUTTON1)
					game.getEditor().mouseClicked(e.getX(), e.getY());
				else if(e.getButton() == MouseEvent.BUTTON3) 
					game.getEditor().mouseClickedButton(e);
				
				break;
			case GAMEOVER:
				game.getGameOver().mouseClicked(e.getX(), e.getY());
				break;
			case LVLWIN:
				game.getLvlWin().mouseClicked(e.getX(), e.getY());
				break;
			case LEVELSELECT:
				game.getLvlSelect().mouseClicked(e.getX(), e.getY());
				break;
			case GAMEEND:
				game.getGameEnd().mouseClicked(e.getX(), e.getY());
				break;
			case TUTORIAL:
				game.getTutorial().mouseClicked(e.getX(), e.getY());
				break;
			default:
				break;
			
			}
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		currentButton = e.getButton();
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			switch(GameStates.gameState) {
			case MENU:
				game.getMenu().mousePressed(e.getX(), e.getY());
				break;
			case PLAYING:
				game.getPlaying().mousePressed(e.getX(), e.getY());
				break;
			case SETTINGS:
				game.getSettings().mousePressed(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mousePressed(e.getX(), e.getY());
				break;
			case GAMEOVER:
				game.getGameOver().mousePressed(e.getX(), e.getY());
				break;
			case LVLWIN:
				game.getLvlWin().mousePressed(e.getX(), e.getY());
				break;
			case LEVELSELECT:
				game.getLvlSelect().mousePressed(e.getX(), e.getY());
				break;
			case GAMEEND:
				game.getGameEnd().mousePressed(e.getX(), e.getY());
				break;
			case TUTORIAL:
				game.getTutorial().mousePressed(e.getX(), e.getY());
				break;
			default:
				break;
			
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		currentButton = -1;
		
		switch(GameStates.gameState) {
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			game.getPlaying().mouseReleased(e.getX(), e.getY());
			break;
		case SETTINGS:
			game.getSettings().mouseReleased(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseReleased(e.getX(), e.getY());
			break;
		case GAMEOVER:
			game.getGameOver().mouseReleased(e.getX(), e.getY());
			break;
		case LVLWIN:
			game.getLvlWin().mouseReleased(e.getX(), e.getY());
			break;
		case LEVELSELECT:
			game.getLvlSelect().mouseReleased(e.getX(), e.getY());
			break;
		case GAMEEND:
			game.getGameEnd().mouseReleased(e.getX(), e.getY());
			break;
		case TUTORIAL:
			game.getTutorial().mouseReleased(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
