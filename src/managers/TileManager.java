package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.ImgFix;
import helpz.LoadSave;
import static helpz.Constants.Tiles.*;
import objects.Tile;

public class TileManager {

	public Tile GRASS, WATER, ROAD, UP_ROAD, 
				T_WATER, R_WATER, B_WATER,L_WATER,
				BL_ROAD_CORNER, TL_ROAD_CORNER, TR_ROAD_CORNER, BR_ROAD_CORNER, 
				BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER,
				BL_WATER_POINT, TL_WATER_POINT, TR_WATER_POINT, BR_WATER_POINT;
	public BufferedImage atlas;
	
	public ArrayList<Tile> tiles = new ArrayList<>();
	
	public ArrayList<Tile> roadsS = new ArrayList<>();
	public ArrayList<Tile> roadsC = new ArrayList<>();
	public ArrayList<Tile> corners = new ArrayList<>();
	public ArrayList<Tile> beaches = new ArrayList<>();
	public ArrayList<Tile> islands = new ArrayList<>();
	
	public TileManager() {
		
		loadAtlas();
		creatTiles();
		
	}

	private void creatTiles() {
		
		int id = 0;
		tiles.add(GRASS = new Tile(getSprite(9, 0), id++, GRASS_TILE));	
		tiles.add(WATER = new Tile(getAniSprites(0, 0), id++, WATER_TILE));
		
		roadsS.add(ROAD = new Tile(getSprite(8, 0), id++, ROAD_TILE));
		roadsS.add(UP_ROAD = new Tile(ImgFix.getRotImg(getSprite(8, 0), 90), id++, ROAD_TILE));
		
		roadsC.add(BL_ROAD_CORNER = new Tile(ImgFix.getRotImg(getSprite(7, 0), 270), id++, CORNER_ROAD_TILE));
		roadsC.add(TL_ROAD_CORNER = new Tile(getSprite(7, 0), id++, CORNER_ROAD_TILE));
		roadsC.add(TR_ROAD_CORNER = new Tile(ImgFix.getRotImg(getSprite(7, 0), 90), id++, CORNER_ROAD_TILE));
		roadsC.add(BR_ROAD_CORNER = new Tile(ImgFix.getRotImg(getSprite(7, 0), 180), id++, CORNER_ROAD_TILE));
		
		beaches.add(B_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 180), id++, WATER_TILE));
		beaches.add(L_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 270), id++, WATER_TILE));
		beaches.add(T_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 0), id++, WATER_TILE));
		beaches.add(R_WATER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 90), id++, WATER_TILE));
		
		corners.add(BL_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 0), id++, WATER_TILE));
		corners.add(TL_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 90), id++, WATER_TILE));
		corners.add(TR_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 180), id++, WATER_TILE));
		corners.add(BR_WATER_CORNER = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 270), id++, WATER_TILE));

		islands.add(BL_WATER_POINT = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 270), id++, WATER_TILE));
		islands.add(TL_WATER_POINT = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 0), id++, WATER_TILE));
		islands.add(TR_WATER_POINT = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 90), id++, WATER_TILE));
		islands.add(BR_WATER_POINT = new Tile(ImgFix.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 180), id++, WATER_TILE));
		
		tiles.addAll(roadsS);
		tiles.addAll(roadsC);
		tiles.addAll(beaches);
		tiles.addAll(corners);
		tiles.addAll(islands);
		
	}
	
//	private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY){
//		return new BufferedImage[] {getSprite(firstX, firstY), getSprite(secondX, secondY)};
//	}

	private void loadAtlas() {

		atlas = LoadSave.getSpriteAtlas();
		
	}
	
	public boolean isSpriteAnimation(int spriteID) {
		return tiles.get(spriteID).isAnimation();
	}
	
	public Tile getTile(int id) {
		return tiles.get(id);
	}
	
	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	public BufferedImage getAniSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex);
	}
	
	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord*32, yCord*32, 32, 32);
	}
	
	private BufferedImage[] getAniSprites(int xCord, int yCord) {
		
		BufferedImage[] arr = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			arr[i] = getSprite(xCord + i, yCord);
		}
		
		return arr;
	}

	public int[][] getTypeArr(){
		int[][] idArr = LoadSave.GetLevelData();
		int[][] typeArr = new int[idArr.length][idArr[0].length];
		
		for(int j = 0; j < idArr.length; j++) {
			for(int i = 0; i < idArr[j].length; i++) {
				int id = idArr[j][i];
				typeArr[j][i] = tiles.get(id).getTileType();
			}
		}
		
		return typeArr;
		
	}
	
	public ArrayList<Tile> getRoadsS() {
		return roadsS;
	}

	public ArrayList<Tile> getRoadsC() {
		return roadsC;
	}

	public ArrayList<Tile> getCorners() {
		return corners;
	}

	public ArrayList<Tile> getBeaches() {
		return beaches;
	}

	public ArrayList<Tile> getIslands() {
		return islands;
	}

	

	
}
