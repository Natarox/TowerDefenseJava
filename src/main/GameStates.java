package main;

public enum GameStates {
	
	PLAYING, MENU, SETTINGS, EDIT, GAMEOVER, LVLWIN, LEVELSELECT, GAMEEND, TUTORIAL;
	
	public static GameStates gameState = MENU;
	
	public static void SetGameState(GameStates state) {
		gameState = state;
	}
	
}
