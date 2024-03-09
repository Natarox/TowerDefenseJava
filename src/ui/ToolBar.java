package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import helpz.LoadSave;
import objects.Tile;
import scenes.Editing;

public class ToolBar extends Bar{

	private MyButton bMenu, bSave;
	private Editing editing;
	private MyButton bPathStart, bPathEnd;
	private BufferedImage pathStart, pathEnd;

//	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();
	
	private MyButton bGrass, bWater, bRoadS, bRoadC, bCorner, bBeach, bIsland;
	private MyButton currentButton;
	
	private int currentIndex = 0;
	private int lvlNumber = 1;
	
	private Tile selectedTile;
	
	public ToolBar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		initPathImgs();
		initButtons();

	}
	
	private void initPathImgs() {
		pathStart = LoadSave.getSpriteAtlas().getSubimage(7*32, 2*32, 32, 32);
		pathEnd = LoadSave.getSpriteAtlas().getSubimage(8*32, 2*32, 32, 32);
		
	}

	private void initButtons() {
		
		bMenu = new MyButton("Menu", 555, 765, 75, 75 / 3);
		bSave = new MyButton("Save", 555, 725, 75, 75 / 3);
		
		int w = 50;
		int h = 50;
		int xStart = 15;
		int y = 665;
		int xOffset = (int) (w * 1.2f);		
		int i = 0;
		
		bGrass = new MyButton("Grass", xStart, y, w, h, i++);
		bWater = new MyButton("Water", xStart + xOffset, y, w, h, i++);
		
		initMapButton(bRoadS, editing.getGame().getTileManager().getRoadsS(), xStart, y, xOffset, w, h, i++);
		initMapButton(bRoadC, editing.getGame().getTileManager().getRoadsC(), xStart, y, xOffset, w, h, i++);
		initMapButton(bCorner, editing.getGame().getTileManager().getCorners(), xStart, y, xOffset, w, h, i++);
		initMapButton(bBeach, editing.getGame().getTileManager().getBeaches(), xStart, y, xOffset, w, h, i++);
		initMapButton(bIsland, editing.getGame().getTileManager().getIslands(), xStart, y, xOffset, w, h, i++);
		
		bPathStart = new MyButton("PathStart", xStart, y + xOffset, w, h, i++);
		bPathEnd = new MyButton("PathStart", xStart + xOffset, y + xOffset, w, h, i++);

	}

	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
		
		b = new MyButton("", x + xOff * id, y, w, h, id);
		map.put(b, list);
		
	}
	
	public void rotateSprite() {
		
		currentIndex++;
		if(currentIndex >= map.get(currentButton).size()) {
			currentIndex = 0;
		}
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);
	}
	
	public void draw(Graphics g) {
		
		// Background
		g.setColor(new Color(138, 109, 70));
		g.fillRect(x, y, width, height);

		
		// Buttons
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);
		
		drawPathButton(g, bPathStart, pathStart);
		drawPathButton(g, bPathEnd, pathEnd);

		drawNormalButton(g, bGrass);
		drawNormalButton(g, bWater);
		
		drawSelectedTile(g);
		
		drawMapButtons(g);
		
		
	}
	
	private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
		
		g.drawImage(img, b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g, b);
		
	}

	private void drawNormalButton(Graphics g, MyButton b) {
		g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
		
		drawButtonFeedback(g, b);
	}

	private void drawMapButtons(Graphics g) {
		for(Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();
			
			g.drawImage(img, b.x, b.y, b.width, b.height, null);
			
			drawButtonFeedback(g, b);
			
		}
		
	}
	
	

	private void drawSelectedTile(Graphics g) {

		if(selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 475, 665, 50, 50, null);
			g.setColor(Color.black);
			g.drawRect(475, 665, 50, 50);
			g.drawString("Selected Tile", 465, 660);
		}
		
	}

	public BufferedImage getButtImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}
	
	private void saveLevel() {
		askLevelNumber();
		
		editing.saveLevel(lvlNumber);
        System.out.println("Lvl number : " + lvlNumber);
		
	}
	
	private void askLevelNumber() {
		boolean numberGived = false;
		int line = 1;
		
		try (Scanner scanner = new Scanner(System.in)) {
			try {
			    while (numberGived == false) {
			        System.out.println("Input lvl number : ");
			        line = scanner.nextInt();
			        System.out.printf("User input was: %s%n", line);
			        numberGived = true;
			    }
			} catch(IllegalStateException | NoSuchElementException e) {
			    // System.in has been closed
			    System.out.println("System.in was closed; exiting");
			}
		}
		
		lvlNumber = line;

	}

	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		} else if(bSave.getBounds().contains(x, y)) {
			saveLevel();
		
		} else if(bGrass.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if(bWater.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
			editing.setSelectedTile(selectedTile);
			return;
			
		} else if(bPathStart.getBounds().contains(x, y)) {
			selectedTile = new Tile(pathStart, -1, -1);
			editing.setSelectedTile(selectedTile);
			
		} else if(bPathEnd.getBounds().contains(x, y)) {
			selectedTile = new Tile(pathEnd, -2, -2);
			editing.setSelectedTile(selectedTile);
			
			
		} else {
			
			for (MyButton b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
					selectedTile = map.get(b).get(0);
					editing.setSelectedTile(selectedTile);
					currentButton = b;
					currentIndex = 0;
					return;
				}
			}	
		}
	}

	

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		bGrass.setMouseOver(false);
		bWater.setMouseOver(false);
		bPathStart.setMouseOver(false);
		bPathEnd.setMouseOver(false);
		
		for (MyButton b : map.keySet()) {
			b.setMouseOver(false);
		}	
			
		
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		} else if(bSave.getBounds().contains(x, y)) {
			bSave.setMouseOver(true);
		} else if(bPathStart.getBounds().contains(x, y)) {
			bPathStart.setMouseOver(true);
		} else if(bPathEnd.getBounds().contains(x, y)) {
			bPathEnd.setMouseOver(true);
		} else if(bGrass.getBounds().contains(x, y)) {
			bGrass.setMouseOver(true);
		} else if(bWater.getBounds().contains(x, y)) {
			bWater.setMouseOver(true);
		} else {
			for (MyButton b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}	
		}
		
	}

	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		} else if(bSave.getBounds().contains(x, y)) {
			bSave.setMousePressed(true);
		} else if(bPathStart.getBounds().contains(x, y)) {
			bPathStart.setMousePressed(true);
		} else if(bPathEnd.getBounds().contains(x, y)) {
			bPathEnd.setMousePressed(true);
		} else if(bGrass.getBounds().contains(x, y)) {
			bGrass.setMousePressed(true);
		} else if(bWater.getBounds().contains(x, y)) {
			bWater.setMousePressed(true);
		} else {
			for (MyButton b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}	
		}
		
	}

	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		bGrass.resetBooleans();
		bWater.resetBooleans();
		bPathStart.resetBooleans();
		bPathEnd.resetBooleans();
		
		for (MyButton b : map.keySet()) {
			b.resetBooleans();
		}	

		
	}


	public BufferedImage getStartPathImg() {
		return pathStart;
	}
	
	public BufferedImage getEndPathImg() {
		return pathEnd;
	}
	
}
