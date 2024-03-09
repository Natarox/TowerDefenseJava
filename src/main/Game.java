package main;

import javax.swing.JFrame;

import helpz.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.GameEnd;
import scenes.GameOver;
import scenes.LevelSelect;
import scenes.LvlWin;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;
import scenes.Tutorial;

public class Game extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameScreen gameScreen;
	private Thread gameThread;
	
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	//Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	private Editing editing;
	private GameOver gameOver;
	private LvlWin lvlWin;
	private LevelSelect lvlSelect;
	private GameEnd gameEnd;
	private TileManager tileManager;
	private Tutorial tutorial;
	
	public Game() {
		
		LoadSave.CreateFolder();
		createDefaultLevel();
		
		LoadSave.CreateSave(1);
		LoadSave.GetSaveProgress();
		
		initClasses();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Save the world or some other things");
		add(gameScreen);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
	}

	private void createDefaultLevel() {
		int[] arr = new int[400];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		
		LoadSave.CreateLevel(arr);
		
	}
	
	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
		gameOver = new GameOver(this);
		lvlWin = new LvlWin(this);
		lvlSelect = new LevelSelect(this);
		gameEnd = new GameEnd(this);
		tutorial = new Tutorial(this);
		
	}

	private void start() {
		gameThread = new Thread(this) {};
		
		gameThread.start();
	}
	
	

	private void updateGame() {
		switch(GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
		
		}
		
	}
	
	public static void main(String[] args) {

		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();
		

	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
//		long lastTimeCheck = System.currentTimeMillis();
		
		
//		int frames = 0;
//		int updates = 0;
		
		long now;
		
		while(true) {
			
			now = System.nanoTime();
			
			// Render
			if(now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
//				frames++;
			}
			
			// Update
			if(now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
//				updates++;
			}
			
//			if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
//				System.out.println("FPS: "+ frames + " | UPS: " + updates);
//				frames = 0;
//				updates = 0;
//				lastTimeCheck = System.currentTimeMillis();
//			}
			
		}
		
	}
	
	// Getters and setters:
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

	public Editing getEditor() {
		return editing;
	}
	
	public GameOver getGameOver() {
		return gameOver;
	}
	
	public LvlWin getLvlWin() {
		return lvlWin;
	}
	
	public LevelSelect getLvlSelect() {
		return lvlSelect;
	}
	
	public TileManager getTileManager() {
		return tileManager;
	}

	public GameEnd getGameEnd() {
		return gameEnd;
	}
	
	public Tutorial getTutorial() {
		return tutorial;
	}
	
}
