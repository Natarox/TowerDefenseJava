package helpz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import objects.PathPoint;

public class LoadSave {

	public static int lvlNumber = 1;
	public static int saveNumber = 1;
	
	public static String folderPath = System.getProperty("user.dir");
	public static String saveFolder = "levels";
	public static String levelFile = "level_"+lvlNumber+".txt";
	public static String filePath = folderPath + File.separator + saveFolder + File.separator + levelFile;
	public static String saveFileName = "save_file.txt";
	public static String savePath = folderPath + File.separator + saveFolder + File.separator + saveFileName;
	
	private static File lvlFile = new File(filePath);
	private static File saveFile = new File(savePath);
	
	public static void CreateFolder() {
		File folder = new File(folderPath + File.separator + saveFolder);
		if(!folder.exists())
			folder.mkdir();
	}
	
	public static void GetSaveProgress() {
		saveNumber = ReadSave();
	}
	
	
	
	public static BufferedImage getSpriteAtlas() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static BufferedImage getBlackHole() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("black_hole.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static BufferedImage getMenuBackground() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("bg_menu.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static BufferedImage getMenuTitle() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("menu_title.jpg");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static BufferedImage getGameOverBackground() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("bg_gameover.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static BufferedImage getGameEndBackground() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("bg_gameend.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static BufferedImage getLevelWinBackground() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("bg_levelwin.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return img;
		
	}
	
	public static void CreateLevel(int [] idArr) {
		
		if(lvlFile.exists()) {
			System.out.println("File:"+ lvlFile+ " already exists");
			return;
		} else {
			try {
				lvlFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			WriteToFile(idArr, new PathPoint(0, 0), new PathPoint(0, 0), lvlFile);
			
		}	
	}
	
	public static void CreateSave(int saveNumber) {
		if(saveFile.exists()) {
			System.out.println("File:"+ saveFile+ " already exists");
			return;
		} else {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			WriteToFile(0, saveFile);
			
		}	
	}
	
	private static void WriteToFile(int[] idArr, PathPoint start, PathPoint end, File lvlFile) {
				
		try {
			PrintWriter pw = new PrintWriter(lvlFile);

			for(Integer i : idArr)
				pw.println(i);
			
			pw.println(start.getxCord());
			pw.println(start.getyCord());
			pw.println(end.getxCord());
			pw.println(end.getyCord());
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private static void WriteToFile(int saveNumber, File lvlFile) {
		
		try {
			PrintWriter pw = new PrintWriter(lvlFile);
			
			pw.println(saveNumber);
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void SaveLevel(int[][] idArr, PathPoint start, PathPoint end, int lvlNumber) {
		String levelFileSave = "level_"+lvlNumber+".txt";
		String filePathSave = folderPath + File.separator + saveFolder + File.separator + levelFileSave;
		
		File lvlFileSave = new File(filePathSave);
		
		
		if(!lvlFileSave.exists()) {
			try {
				lvlFileSave.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		WriteToFile(Utilz.TwoDto1DintArr(idArr), start, end, lvlFileSave);	
			
	}
	
	public static void SaveProgress(int saveNumber) {
		if(!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		WriteToFile(saveNumber, saveFile);
	}
	
	private static ArrayList<Integer> ReadFromFile() {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			Scanner sc = new Scanner(lvlFile);
			
			while(sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	private static int ReadSave() {
		int saveNumber = 1;
		
		try {
			Scanner sc = new Scanner(saveFile);
			
			saveNumber = sc.nextInt();
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return saveNumber;
		
	}
	
	public static ArrayList<PathPoint> GetLevelPathPoint(){
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile();
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400), list.get(401)));
			points.add(new PathPoint(list.get(402), list.get(403)));
			
			return points;
		}else {
			System.out.println("File: "+lvlFile+" don't exists");
			return null;
		}
	}
	
	public static int[][] GetLevelData() {
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile();
			return Utilz.ArrayListTo2Dint(list, 20, 20);
		}else {
			System.out.println("File: "+lvlFile+" don't exists");
			return null;
		}
		
	}
	
	public static int GetSaveData() {
		if(saveFile.exists()) {
			int saveNumber = ReadSave();
			return saveNumber;
		}else {
			System.out.println("File: "+saveFile+" don't exists");
			return 0;
		}
	}
	
	public static void nextLevel() {
		lvlNumber++;

		resetFilePath(lvlNumber);
		
	}
	
	public static void SetCurrentLevel(int currentLevel) {
		lvlNumber = currentLevel;
		resetFilePath(lvlNumber);
	}
	
	private static void resetFilePath(int lvlNumber) {
		levelFile = "level_"+lvlNumber+".txt";
		filePath = folderPath + File.separator + saveFolder + File.separator + levelFile;
		
		lvlFile = new File(filePath);
	}
	
	public static int getLvlNumber() {
		return lvlNumber;
	}
	
	public static File getCurrLevel() {
		return lvlFile;
	}
	
	public static int getSaveNumber() {
		return saveNumber;
	}
	
}
