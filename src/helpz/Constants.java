package helpz;

public class Constants {

	public static class Direction{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Tiles{
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
		public static final int CORNER_ROAD_TILE = 3;
	}
	
	public static class Enemies {
		public static int currWave = 1;
		public static int currLvl = LoadSave.getLvlNumber();
		
		public static final int ORC = 0;
		public static final int BAT = 1;
		public static final int KNIGHT = 2;
		public static final int WOLF = 3;
		
		public static float GetSpeed(int enemyType) {
			switch(enemyType) {
			case ORC:
				return 0.5f;
			case BAT:
				return 0.75f;
			case KNIGHT:
				return 0.3f;
			case WOLF:
				return 1f;
			}
			
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			currLvl = LoadSave.getLvlNumber();
						
			switch(enemyType) {
			case ORC:
				return (int)(75 + (75 * (0.2f * (currLvl -1))) + (75 * (0.01f *  (currWave - 1) * (currLvl))));
			case BAT:
				return (int)(40 + (40 * (0.2f * (currLvl -1))) + (40 * (0.01f *  (currWave - 1) * (currLvl))));
			case KNIGHT:
				return (int)(200 + (200 * (0.2f * (currLvl -1))) + (200 * (0.01f *  (currWave - 1) * (currLvl))));
			case WOLF:
				return (int)(55 + (55 * (0.2f * (currLvl -1))) + (55 * (0.01f *  (currWave - 1) * (currLvl))));
			}
			
			return 0;
		}
		
		public static int GetDroppedGold(int enemyType) {
			currLvl = LoadSave.getLvlNumber();
			
			switch(enemyType) {
			case ORC:
				return (int)(8 + (8 * (0.2f * (currLvl -1))));
			case BAT:
				return (int)(5 + (5 * (0.2f * (currLvl -1))));
			case KNIGHT:
				return (int)(25 + (25 * (0.2f * (currLvl -1))));
			case WOLF:
				return (int)(10 + (10 * (0.2f * (currLvl -1))));
			}
			
			return 0;
		}
		
		public static void setCurrWave(int currWaveInput) {
			currWave = currWaveInput;
		}
		
	}
	
	public static class EvolvedTowers {
		public static final int NUKE = 0;
		public static final int ARTILLERIST = 1;
		
		public static final int SNIPER = 2;
		public static final int HEAVYBG = 3;
		
		public static final int TIMETHIEF = 4;
		public static final int ICELORD = 5;
		
		
		public static String GetName(int towerType) {
			switch(towerType) {
			
			case NUKE:
				return "Nuke";
			case ARTILLERIST:
				return "Artillerist";
			case SNIPER:
				return "Sniper";
			case HEAVYBG:
				return "HeavyBowGun";
			case TIMETHIEF:
				return "TimeThief";
			case ICELORD:
				return "IceLord";
			}
			return "";
		}
		
		public static int GetStartDmg(int towerType) {
			switch(towerType) {
			
			case NUKE:
				return 150;
			case ARTILLERIST:
				return 2;
			case SNIPER:
				return 65;
			case HEAVYBG:
				return 10;
			case TIMETHIEF:
				return 0;
			case ICELORD:
				return 1;
			}
			
			return 0;
		}
		
		public static float GetStartRange(int towerType) {
			switch(towerType) {
			
			case NUKE:
				return 140;
			case ARTILLERIST:
				return 80;
			case SNIPER:
				return 300;
			case HEAVYBG:
				return 160;
			case TIMETHIEF:
				return 60;
			case ICELORD:
				return 200;
			}
			
			return 0;
		}
		
		public static float GetStartCooldown(int towerType) {
			switch(towerType) {
			
			case NUKE:
				return 500;
			case ARTILLERIST:
				return 5;
			case SNIPER:
				return 150;
			case HEAVYBG:
				return 10;
			case TIMETHIEF:
				return 250;
			case ICELORD:
				return 75;
			}
			
			return 0;
		}
		
	}
	
		
	public static class Towers {
		public static final int CANNON = 0;
		public static final int ARCHER = 1;
		public static final int WIZARD = 2;

		public static String GetName(int towerType) {
			switch(towerType) {
			case CANNON:
				return "Cannon";
			case ARCHER:
				return "Archer";
			case WIZARD:
				return "Wizard";
			}
			return "";
		}
		
		public static int GetStartDmg(int towerType) {
			switch(towerType) {
			case CANNON:
				return 15;
			case ARCHER:
				return 5;
			case WIZARD:
				return 0;
			}
			
			return 0;
		}
		
		public static float GetStartRange(int towerType) {
			switch(towerType) {
			case CANNON:
				return 100;
			case ARCHER:
				return 150;
			case WIZARD:
				return 125;
			}
			
			return 0;
		}
		
		public static float GetStartCooldown(int towerType) {
			switch(towerType) {
			case CANNON:
				return 100;
			case ARCHER:
				return 25;
			case WIZARD:
				return 65;
			}
			
			return 0;
		}
		
		public static int GetTowerCost(int towerType) {
			switch(towerType) {
			case CANNON:
				return 65;
			case ARCHER:
				return 30;
			case WIZARD:
				return 45;
			}
			
			return 0;
		}
		
	}
	
	public static class Projectiles {
		
		public static final int ARROW = 0;
		public static final int CHAINS = 1;
		public static final int BOMB = 2;
		public static final int SNIPER = 3;
		public static final int TIMESTOP = 4;
		public static final int ICEPOISON = 5;
		public static final int NUKE = 6;
		
		public static float GetSpeed(int type) {
			switch(type) {
			case BOMB:
				return 4f;
			case ARROW:
				return 8f;
			case SNIPER:
				return 15f;
			case CHAINS:
				return 6f;
			case TIMESTOP:
				return 30f;
			case ICEPOISON:
				return 10f;
			case NUKE:
				return 8f;
			}
			return 0f;
		
		}
		
	}
	
}
