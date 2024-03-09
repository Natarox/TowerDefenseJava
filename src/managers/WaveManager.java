package managers;

import java.util.ArrayList;
import java.util.Arrays;

import event.Wave;
import helpz.LoadSave;
import scenes.Playing;

public class WaveManager {
	
//	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();
	private int enemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private boolean waveStartTimer;
	private boolean waveTickTimerOver;
	private int waveTickLimit = 60 * 10;
	private int waveTick = 0;
	private int currWave = 1;
	
	public WaveManager(Playing playing) {
//		this.playing = playing;
		createWaves();
	}
	
	public void update() {		
		if(enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++;
		
		if(waveStartTimer) {
			waveTick++;
			if(waveTick >= waveTickLimit) {
				
				waveTickTimerOver = true;
				currWave++;
				helpz.Constants.Enemies.setCurrWave(currWave);
			}
		}
	}
	
	public void increaseWaveIndex() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}
	
	public void startWaveTimer() {
		waveStartTimer = true;
		
	}
	
	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}
	
	private void createWaveOne() {

		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1, 1, 0, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 0, 0, 3, 0, 1, 0, 3, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 3, 3, 1, 3, 1, 1, 1, 3, 1, 3, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 3, 3, 3, 2, 1, 0, 0, 1, 0, 2, 3))));
	}
	private void createWaveTwo() {

		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 3, 1, 0, 1, 3, 3, 1, 0, 1, 3, 3, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 2, 0, 1, 3, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 2, 3, 1, 2, 3, 0, 1, 0, 0, 1, 2, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 3, 2, 1, 0, 0, 1, 0, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 2, 1, 0, 3, 3, 0, 2, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 0, 1, 0, 1, 2, 3, 2, 2, 1, 3, 2, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 2, 1, 0, 2, 1, 3, 2, 1, 2, 1, 0, 3, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 0, 1, 0, 2, 3, 0, 2, 1, 3, 1, 0, 0, 1, 0, 3, 3, 3, 1, 0 , 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 0, 0, 1, 1, 2, 2, 3, 3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3))));
	}
	private void createWaveThree() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 3, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 3, 3, 1, 0, 0, 1, 0, 3, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 2, 1, 3, 0, 1, 0, 1, 3, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 2, 1, 3, 2, 1, 0, 1, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 3, 0, 1, 0, 1, 3, 0, 1, 0, 0, 3, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 1, 3, 1, 1, 0, 3, 3, 0, 1, 0, 2, 3, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 2, 1, 3, 1, 0, 1, 3, 3, 0, 2, 2, 0, 2, 1, 2, 0, 3, 1, 0, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 2, 2, 0, 3, 2, 0, 2, 1, 2, 3, 3, 2, 2, 1, 3, 2, 1, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 0, 1, 2, 1, 0, 3, 2, 1, 0, 0, 2, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 0, 2, 1, 3, 2, 0, 1, 0, 1, 0, 2, 1, 3, 3, 0, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 0, 1, 0, 2, 1, 3, 3, 3, 1, 1, 1, 3, 3, 3, 2, 2, 0, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 0, 2, 2, 0, 1, 3, 3, 1, 2, 0, 2, 1, 2, 3, 3, 1, 0, 2, 2, 0, 1, 3, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 2, 2, 0, 1, 0, 1, 3, 3, 2, 3, 2, 2, 3, 1, 1, 2, 0, 1, 2, 2, 1, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 0, 2, 0, 1, 3, 1, 2, 2, 2, 2, 0, 0, 1, 3, 3, 1, 2, 0, 1, 0, 2, 2, 0, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 0, 2, 1, 0, 2, 1, 2, 2, 3, 1, 1, 0, 3, 3, 3, 1, 3, 1, 0, 1, 3, 2, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 2, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 2, 1, 3, 1, 2, 0, 2, 1, 3, 2, 1, 2, 3, 2, 1, 3, 2, 0))));

	}
	private void createWaveFour() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 3, 3, 1, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 2, 2, 1, 2, 1, 3, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 1, 3, 3, 3, 1, 1, 1, 0, 0, 0, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 0, 2, 1, 2, 0, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 0, 1, 0, 1, 3, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 3, 1, 1, 3, 2, 2, 2, 1, 2, 3, 3, 0, 2, 1, 0, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 2, 0, 1, 3, 3, 1, 0, 2, 1, 3, 3, 2, 1, 2, 1, 3, 3, 1, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 3, 1, 0, 3, 3, 1, 1, 0, 0, 1, 3, 1, 3, 2, 1, 2, 0, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 3, 1, 0, 2, 1, 3, 3, 1, 0, 2, 1, 3, 2, 1, 2, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 2, 1, 0, 3, 1, 2, 3, 3, 3, 1, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 0, 1, 3, 1, 2, 0, 1, 0, 1, 3, 1, 0, 2, 1, 3, 3, 0, 1, 3, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 0, 1, 2, 3, 1, 3, 3, 2, 1, 2, 3, 3, 3, 1, 3, 1, 2, 2, 1, 0, 0, 1, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 2, 1, 1, 3, 3, 3, 1, 0, 2, 1, 2, 2, 2, 3, 3, 3, 2, 1, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 1, 3, 2, 2, 1, 3, 1, 2, 2, 2, 3, 2, 3, 3, 2, 1, 3, 1, 2, 2, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 3, 3, 1, 2, 3, 1, 2, 2, 1, 3, 2, 1, 2, 3, 1, 2, 1, 3, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 2, 2, 3, 3, 3, 1, 2, 2, 2, 1, 2, 3, 1, 2, 1, 3, 1, 2, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 3, 3, 1, 3, 1, 2, 1, 2, 1, 3, 1, 2, 2, 1, 3, 1, 2, 2, 2, 1, 3, 1, 3, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 3, 3, 1, 3, 3, 3, 2, 1, 3, 1, 2, 2, 2, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 1, 2, 2, 2, 2, 2, 1, 3, 3, 3, 2, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 3, 1, 0, 1, 3, 1, 2, 0, 1, 0, 2, 3, 1, 0, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 1, 2, 3, 0))));

	}
	private void createWaveFive() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 0, 1, 3, 1, 1, 3, 3, 3, 0, 0, 1, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 2, 0, 1, 0, 3, 3, 1, 2, 0, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 0, 1, 3, 1, 0, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 1, 0, 1, 3, 0, 1, 3, 1, 0, 2, 0, 1, 0, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 0, 2, 0, 2, 1, 3, 1, 3, 1, 0, 3, 3, 1, 3, 1, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 2, 2, 2, 1, 3, 1, 0, 1, 3, 0, 1, 0, 1, 3, 2, 0, 2, 1, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 0, 1, 3, 1, 0, 2, 0, 2, 2, 3, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 2, 2, 1, 2, 3, 3, 2, 1, 1, 1, 3, 2, 2, 2, 1, 3, 3, 1, 3, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 3, 3, 3, 2, 2, 2, 1, 3, 3, 3, 1, 0, 0, 0, 2, 2, 2, 0, 1, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 3, 1, 0, 1, 3, 1, 2, 2, 2, 1, 1, 3, 3, 3, 0, 0, 1, 2, 1, 3, 3, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 1, 3, 2, 2, 1, 3, 1, 0, 2, 1, 3, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 3, 3, 3, 1, 1, 1, 1, 3, 3, 3, 3, 1, 2, 2, 2, 2, 1, 3, 1, 2, 2, 1, 3, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 2, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 2, 1, 1, 1, 3, 3, 3, 2, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 3, 1, 0, 0, 2, 2, 2, 1, 3, 3, 3, 1, 1, 1, 0, 0, 0, 2, 2, 2, 1, 3, 1, 3, 1, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 3, 3, 3, 3, 1, 0, 0, 0, 1, 3, 3, 3, 1, 1, 2, 2, 2, 3, 3, 3, 1, 3, 1, 2, 2, 2, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 2, 2, 2, 1, 1, 1, 3, 3, 3, 1, 1, 1, 3, 3, 3, 2, 2, 2, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 1, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 2, 0, 0, 1, 1, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 2, 2, 1, 1, 3, 3, 2, 2, 0, 0, 2, 1, 2, 3, 2, 0, 2, 1, 2, 3, 3, 2, 2, 1, 1, 2, 3, 3, 3, 1, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 3, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 2, 1, 3, 1, 3, 2, 2, 1, 3, 3, 3, 1, 1, 1, 2, 2, 2, 1, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 0, 0, 0, 1, 1, 1, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 0, 0, 3, 3, 3, 2, 2, 2, 1, 3, 1, 0, 1, 3, 2, 1, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 2, 2, 0, 2, 0, 0, 1, 0, 1, 1, 3, 1, 3, 3, 2, 3, 2, 2, 1, 2, 1, 1, 3, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 1, 3, 1, 3, 1, 2, 2, 2, 3, 2, 1, 2, 3, 2, 1, 2, 1, 2, 1, 2, 3, 2, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 2, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 2, 1, 0, 1, 3, 1, 2, 2, 1, 3, 3, 2, 2, 1, 1, 3, 3, 3, 0, 0, 2, 2, 1, 3, 1, 2, 1, 3, 1, 2, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 2, 2, 1, 3, 3, 1, 3, 1, 3, 1, 2, 0, 0, 2, 2, 1, 3, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 2, 2, 3, 3, 3, 1, 1, 0, 1, 0, 1, 3, 3, 3, 1, 2, 1, 3, 1, 2, 2, 1, 0, 3, 3, 1, 2, 1, 2, 3, 2, 1, 2, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 2, 0, 1, 3, 1, 3, 1, 2, 2, 2, 1, 3, 1, 0, 1, 3, 1, 2, 2, 1, 3, 1, 0, 1, 3, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 0))));

	}
	private void createWaveSix() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 3, 3, 1, 0, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 3, 1, 0, 0, 0, 1, 0, 1, 3, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 2, 2, 2, 1, 3, 3, 0, 1, 0, 3, 1, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 1, 3, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 2, 0, 1, 1, 3, 1, 2, 1, 3, 1, 2, 1, 2, 1, 3, 3, 1, 2, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 0, 1, 3, 2, 2, 2, 1, 3, 1, 3, 1, 2, 2, 2, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 2, 2, 1, 2, 3, 3, 3, 0, 1, 2, 1, 3, 3, 1, 0, 2, 2, 1, 2, 0, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 3, 3, 3, 0, 0, 0, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 3, 3, 2, 2, 1, 0, 1, 3, 1, 0, 1, 2, 1, 3, 1, 2, 0, 2, 1, 2, 3, 2, 0, 2, 1, 2, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 2, 1, 2, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 3, 1, 2, 2, 1, 2, 0, 1, 2, 3, 1, 2, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 2, 2, 3, 2, 2, 3, 3, 3, 1, 1, 0, 0, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0, 2, 1, 3, 1, 2, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 2, 1, 2, 0, 1, 3, 1, 3, 3, 1, 0, 1, 2, 2, 2, 3, 1, 2, 1, 0, 2, 1, 2, 1, 3, 3, 1, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 3, 3, 1, 1, 0, 2, 1, 3, 1, 2, 2, 0, 1, 3, 1, 0, 2, 2, 1, 1, 3, 3, 1, 0, 2, 1, 2, 3, 1, 2, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 3, 3, 3, 0, 1, 2, 0, 0, 2, 1, 3, 1, 2, 2, 0, 1, 3, 1, 0, 1, 3, 2, 2, 1, 1, 3, 1, 3, 1, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 2, 0, 1, 0, 2, 3, 1, 0, 2, 0, 1, 2, 3, 1, 3, 2, 1, 0, 2, 0, 1, 3, 2, 1, 0, 2, 1, 0, 1, 3, 2, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 2, 0, 1, 3, 1, 0, 1, 3, 2, 2, 2, 1, 3, 1, 2, 2, 2, 2, 2, 2, 2, 3, 1, 0, 1, 3, 1, 0, 1, 0, 1, 3, 3, 2, 2, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 3, 2, 0, 1, 0, 1, 3, 3, 3, 2, 2, 2, 2, 1, 0, 1, 0, 1, 3, 3, 3, 2, 2, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 2, 2, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 3, 3, 3, 1, 2, 1, 3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 1, 3, 3, 2, 2, 2, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 3, 1, 3, 1, 0, 1, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 3, 1, 2, 2, 0, 1, 2, 2, 3, 1, 2, 2, 0, 1, 3, 3, 1, 1, 0, 0, 1, 1, 3, 3, 1, 0, 1, 3, 2, 2, 0, 1, 3, 1, 0, 2, 2, 1, 3, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 3, 3, 1, 1, 0, 0, 2, 0, 1, 3, 2, 1, 2, 0, 1, 3, 1, 2, 2, 0, 1, 3, 1, 0, 1, 2, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 3, 3, 2, 2, 1, 1, 3, 3, 1, 1, 0, 0, 2, 2, 1, 3, 3, 1, 0, 0, 1, 1, 3, 3, 2, 2, 0, 1, 0, 2, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 2, 2, 1, 3, 3, 1, 2, 1, 3, 1, 2, 1, 0, 1, 3, 3, 1, 2, 2, 0, 1, 3, 2, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 1, 0, 3, 1, 0, 1, 3, 3, 2, 1, 3, 1, 2, 1, 3, 2, 3, 1, 2, 1, 0, 1, 3, 1, 0, 3, 2, 2, 2, 2, 1, 1, 3, 3, 3, 3, 3, 1, 1, 2, 2, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 1, 1, 3, 3, 3, 0, 0, 0, 2, 2, 2, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 1, 2, 2, 2, 3, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 2, 1, 0, 1, 3, 2, 2, 1, 1, 3, 1, 3, 1, 2, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 3, 1, 1, 2, 2, 2, 1, 3, 1, 2, 1, 3, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 3, 2, 2, 1, 3, 3, 2, 2, 1, 3, 3, 1, 2, 1, 3, 1, 3, 1, 0, 0, 2, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0, 1, 3, 1, 0, 1, 2, 1, 0, 0, 1, 3, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 2, 1, 3, 1, 2, 3, 1, 0, 1, 3, 2, 1, 3, 1, 3, 1, 2, 2, 2, 1, 3, 1, 0, 1, 3, 0, 1, 3, 1, 3, 1, 2, 2, 2, 3, 3, 1, 2, 1, 2, 1, 3, 3, 1, 2, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 2, 1, 3, 1, 2, 2, 3, 1, 3, 1, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 1, 0, 1, 3, 1, 3, 1, 2, 2, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 3, 1, 1, 2, 2, 2, 0, 1, 0, 2, 2, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 0, 1, 3, 1, 0, 2, 1, 3, 1, 3, 1, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 1, 3, 3, 3, 3, 2, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 3, 0, 1, 3, 1, 0, 2, 2, 0))));

	}
	private void createWaveSeven() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 2, 2, 1, 3, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 0, 1, 3, 1, 2, 2, 0, 1, 3, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 2, 2, 1, 2, 1, 3, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0, 1, 2, 1, 3, 1, 0, 0, 2, 1, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 0, 1, 3, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 3, 1, 0, 1, 2, 1, 3, 1, 3, 1, 0, 1, 0, 1, 3, 1, 2, 2, 0, 1, 3, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 3, 1, 0, 1, 2, 1, 3, 1, 1, 3, 1, 0, 3, 1, 0, 1, 3, 0, 2, 1, 2, 1, 3, 1, 3, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 3, 1, 1, 0, 0, 1, 3, 3, 2, 2, 1, 2, 1, 3, 1, 0, 1, 3, 1, 3, 2, 0, 1, 3, 1, 3, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 1, 2, 2, 0, 1, 3, 1, 3, 1, 0, 2, 0, 2, 1, 3, 1, 0, 1, 3, 2, 2, 1, 3, 1, 0, 1, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 0, 2, 2, 1, 3, 1, 2, 0, 1, 3, 1, 2, 1, 2, 1, 3, 1, 2, 1, 3, 1, 0, 1, 2, 3, 1, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 0, 1, 0, 1, 3, 1, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 2, 1, 3, 1, 2, 0, 1, 3, 1, 2, 1, 0, 1, 1, 3, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 2, 1, 3, 1, 0, 1, 3, 1, 0, 2, 1, 2, 3, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 0, 1, 2, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 1, 3, 1, 0, 1, 2, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 0, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 2, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 3, 1, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 2, 3, 1, 2, 0, 1, 0, 1, 3, 1, 3, 1, 0, 2, 0, 1, 0, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 3, 1, 1, 2, 2, 0, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 3, 1, 2, 1, 3, 1, 3, 1, 0, 1, 2, 1, 3, 1, 0, 1, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 3, 2, 2, 1, 0, 2, 0, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 2, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 1, 0, 1, 2, 1, 0, 1, 3, 1, 3, 1, 2, 2, 1, 3, 3, 2, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0, 3, 1, 2, 1, 0, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 1, 3, 1, 0, 1, 3, 2, 2, 1, 3, 1, 3, 1, 2, 2, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0, 3, 1, 2, 1, 3, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 0, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 2, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 3, 1, 3, 1, 2, 1, 0, 3, 1, 3, 1, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 0, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 0, 2, 0, 1, 2, 0, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 2, 2, 2, 1, 3, 3, 3, 1, 1, 1, 0, 0, 2, 2, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 1, 3, 2, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 2, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 1, 2, 2, 2, 0, 0, 1, 1, 3, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 2, 1, 1, 3, 3, 1, 0, 2, 1, 3, 1, 0, 1, 3, 0, 3, 1, 2, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 2, 1, 2, 3, 3, 1, 1, 0, 0, 1, 2, 1, 3, 1, 0, 2, 1, 3, 1, 0, 3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 0, 1, 3, 1, 0, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 1, 3, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 3, 2, 3, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 2, 3, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 3, 2, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 2, 0, 3, 1, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 3, 2, 2, 1, 0, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 0, 1, 3, 2, 1, 3, 1, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 3, 3, 2, 2, 2, 0, 0, 0, 1, 1, 1, 3, 3, 3, 2, 2, 2, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1, 2, 2, 1, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 3, 1, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 1, 3, 1, 3, 1, 2, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 1, 3, 1, 2, 3, 1, 2, 1, 3, 1, 0, 1, 3, 2, 1, 3, 1, 3, 1, 2, 3, 1, 3, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 3, 1, 2, 0, 1, 3, 2, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 0, 1, 3, 2, 2, 0, 1, 2, 2, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 2, 2, 0, 1, 3, 1, 0, 2, 1, 0, 2, 0, 1, 3, 3, 1, 0, 2, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 1, 3, 1, 2, 0, 1, 0, 2, 1, 3, 1, 2, 0, 1, 3, 1, 2, 3, 1, 3, 1, 0, 1, 3, 1, 3, 1, 2, 3, 3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 3, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 1, 2, 2, 2, 0, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 3, 1, 2, 0, 3, 1, 0, 2, 0))));

	}
	private void createWaveEight() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 1, 3, 1, 0, 1, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 2, 0, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 0, 2, 2, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 2, 0, 2, 1, 3, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 1, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 2, 0, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 2, 0, 1, 3, 1, 0, 2, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 3, 1, 0, 1, 0, 2, 0, 1, 3, 1, 0, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 1, 2, 2, 0, 1, 3, 3, 1, 0, 1, 2, 2, 1, 3, 1, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 2, 2, 1, 2, 0, 1, 3, 2, 1, 2, 0, 2, 1, 2, 3, 1, 2, 1, 0, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 2, 3, 1, 2, 1, 3, 3, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 2, 2, 1, 1, 3, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 2, 0, 1, 3, 1, 3, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 2, 0, 1, 2, 3, 1, 2, 1, 2, 3, 1, 2, 0, 2, 1, 2, 1, 3, 2, 3, 1, 3, 1, 0, 1, 3, 1, 0, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 0, 3, 1, 0, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 1, 3, 2, 2, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 0, 1, 3, 1, 0, 1, 3, 2, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 0, 3, 1, 0, 2, 0, 1, 3, 1, 0, 3, 1, 0, 1, 2, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 3, 1, 2, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 2, 0, 1, 0, 2, 2, 1, 3, 1, 2, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 2, 0, 2, 0, 1, 1, 3, 1, 2, 0, 1, 3, 1, 2, 2, 0, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 0, 1, 3, 1, 3, 1, 0, 1, 3, 1, 0, 2, 0, 1, 0, 3, 1, 0, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 2, 0, 1, 1, 3, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 2, 1, 3, 1, 0, 1, 3, 1, 0, 1, 3, 1, 2, 1, 3, 0, 1, 1, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 0, 2, 1, 3, 1, 3, 2, 1, 0, 0, 1, 3, 3, 3, 1, 1, 0, 0, 0, 2, 2, 2, 1, 1, 2, 1, 0, 2, 1, 3, 2, 1, 3, 1, 2, 2, 0, 2, 1, 3, 1, 3, 2, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 0, 1, 3, 1, 2, 0, 2, 1, 2, 1, 2, 3, 3, 3, 1, 2, 0, 1, 1, 1, 3, 3, 1, 2, 1, 3, 1, 2, 1, 2, 1, 3, 1, 2, 2, 1, 3, 1, 2, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 1, 1, 1, 1, 1, 2, 0, 1, 3, 1, 0, 2, 1, 1, 3, 1, 3, 1, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 1, 3, 1, 2, 0, 1, 2, 1, 3, 1, 3, 1, 0, 2, 1, 3, 1, 2, 1, 3, 1, 0, 2, 1, 2, 0, 2, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 2, 1, 3, 1, 0, 2, 2, 3, 1, 2, 0, 0, 1, 3, 1, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 3, 1, 2, 1, 0, 2, 2, 1, 3, 1, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 0, 1, 3, 3, 1, 2, 0, 1, 0, 3, 1, 0, 2, 1, 3, 1, 0, 2, 0, 2, 0, 1, 3, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 2, 0, 1, 3, 2, 0, 2, 1, 3, 1, 0, 2, 1, 3, 1, 0, 2, 0, 2, 1, 3, 3, 1, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 1, 3, 1, 3, 1, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 0, 2, 2, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 3, 2, 1, 0, 2, 0, 1, 3, 1, 3, 1, 2, 2, 0, 1, 3, 1, 3, 1, 0, 2, 0, 1, 3, 1, 2, 3, 1, 2, 0, 1, 3, 1, 2, 0, 1, 3, 1, 0, 2, 0, 1, 3, 1, 0, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 2, 2, 0, 3, 0, 3, 1, 2, 1, 2, 1, 1, 0, 0, 2, 0, 0, 1, 1, 3, 1, 0, 0, 1, 1, 2, 1, 0, 0, 1, 1, 3, 0, 1, 3, 0, 1, 0, 3, 2, 2, 0, 0, 3, 1, 2, 0, 1, 3, 1, 1, 1, 1, 3, 1, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 3, 1, 2, 3, 3, 2, 3, 3, 2, 1, 3, 2, 2, 3, 2, 3, 1, 3, 3, 2, 1, 3, 2, 3, 2, 1, 2, 1, 1, 3, 1, 1, 1, 3, 3, 1, 2, 3, 3, 3, 3, 3, 1, 2, 3, 3, 1, 3, 3, 1, 2, 3, 1, 0, 1, 3, 1, 0, 0)))); 
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1, 2, 2, 3, 2, 2, 3, 1, 2, 2, 2, 1, 2, 3, 2, 1, 2, 2, 2, 2, 2, 1, 1, 3, 3, 1, 2, 1, 3, 3, 1, 3, 2, 3, 3, 3, 2, 2, 3, 3, 3, 1, 1, 2, 1, 3, 3, 2, 1, 1, 0)))); 
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 3, 2, 2, 1, 2, 1, 1, 1, 1, 1, 3, 2, 2, 3, 1, 2, 3, 3, 3, 1, 2, 1, 3, 1, 2, 1, 3, 3, 1, 2, 1, 1, 3, 2, 1, 2, 3, 1, 2, 3, 2, 2, 1, 1, 1, 2, 3, 1, 1, 2, 2, 3, 3, 2, 3, 1, 2, 0)))); 
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 3, 2, 3, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 3, 3, 1, 2, 1, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 2, 3, 3, 2, 1, 3, 2, 1, 2, 2, 1, 3, 1, 2, 1, 1, 1, 3, 2, 3, 1, 1, 1, 1, 1, 1, 3, 2, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 3, 3, 1, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 3, 1, 2, 1, 2, 2, 1, 2, 1, 1, 2, 3, 1, 1, 3, 3, 3, 1, 3, 2, 1, 3, 2, 1, 3, 1, 1, 3, 3, 3, 3, 3, 1, 2, 3, 2, 3, 1, 3, 3, 2, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 2, 3, 2, 2, 2, 2, 2, 3, 2, 1, 2, 2, 2, 1, 1, 3, 1, 1, 3, 1, 1, 3, 2, 3, 2, 3, 1, 2, 3, 3, 3, 1, 3, 2, 2, 2, 3, 1, 2, 1, 1, 3, 3, 2, 1, 3, 2, 2, 3, 1, 2, 3, 3, 3, 3, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 2, 2, 2, 2, 3, 3, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 3, 2, 2, 3, 2, 2, 3, 2, 3, 2, 1, 1, 2, 2, 2, 1, 3, 1, 3, 2, 2, 1, 1, 2, 3, 3, 3, 3, 2, 1, 1, 1, 3, 3, 1, 1, 1, 1, 3, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 2, 2, 3, 1, 3, 2, 3, 2, 2, 2, 1, 1, 2, 2, 1, 3, 2, 1, 3, 1, 2, 1, 2, 2, 1, 2, 3, 2, 3, 3, 3, 1, 1, 2, 2, 1, 1, 2, 2, 2, 3, 3, 2, 2, 1, 3, 2, 2, 1, 1, 2, 2, 2, 1, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 3, 1, 1, 1, 1, 1, 2, 1, 3, 2, 2, 2, 1, 2, 1, 3, 2, 3, 3, 1, 3, 1, 2, 1, 3, 1, 3, 1, 1, 2, 3, 1, 2, 3, 1, 2, 1, 3, 3, 3, 1, 2, 2, 2, 3, 3, 1, 2, 2, 3, 2, 1, 1, 1, 2, 3, 2, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 3, 3, 3, 3, 2, 2, 2, 3, 1, 3, 3, 2, 3, 2, 2, 3, 3, 2, 3, 3, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 2, 1, 3, 1, 3, 2, 2, 1, 1, 2, 3, 3, 2, 1, 2, 2, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 1, 1, 2, 3, 1, 3, 3, 2, 1, 1, 2, 2, 3, 3, 3, 1, 1, 3, 2, 1, 2, 3, 3, 1, 1, 3, 1, 1, 1, 3, 1, 2, 1, 2, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 3, 3, 1, 3, 2, 3, 1, 1, 2, 1, 1, 3, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 3, 3, 2, 1, 2, 1, 1, 3, 3, 1, 1, 1, 1, 3, 2, 3, 1, 3, 2, 2, 1, 1, 2, 1, 2, 3, 2, 2, 1, 1, 3, 3, 1, 2, 1, 3, 3, 1, 2, 2, 2, 2, 3, 2, 1, 2, 2, 2, 1, 1, 3, 2, 1, 1, 3, 2, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 3, 3, 2, 1, 2, 3, 3, 1, 3, 1, 1, 1, 3, 3, 2, 1, 1, 2, 1, 3, 3, 1, 3, 1, 1, 2, 1, 3, 3, 3, 3, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 1, 2, 3, 2, 2, 2, 3, 1, 1, 1, 1, 2, 2, 2, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 2, 2, 2, 3, 3, 3, 1, 1, 1, 3, 1, 1, 3, 1, 2, 2, 1, 1, 3, 2, 3, 1, 3, 2, 3, 1, 3, 1, 2, 3, 1, 1, 1, 3, 3, 1, 3, 1, 2, 3, 3, 2, 3, 2, 3, 2, 1, 3, 2, 3, 1, 2, 1, 1, 1, 3, 1, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 3, 3, 2, 1, 3, 2, 2, 2, 3, 3, 3, 1, 3, 2, 2, 1, 3, 1, 1, 1, 1, 3, 1, 1, 3, 1, 3, 2, 1, 1, 2, 1, 3, 3, 1, 3, 1, 2, 2, 3, 1, 1, 1, 1, 2, 3, 1, 2, 3, 2, 3, 1, 2, 3, 1, 1, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 1, 3, 1, 2, 3, 3, 1, 1, 2, 1, 3, 1, 3, 2, 1, 3, 1, 2, 1, 3, 2, 3, 1, 3, 3, 3, 3, 1, 3, 2, 2, 3, 2, 3, 2, 2, 2, 2, 2, 1, 3, 1, 2, 2, 3, 2, 1, 3, 1, 2, 2, 3, 1, 3, 2, 3, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 2, 2, 3, 1, 3, 3, 3, 2, 3, 2, 3, 2, 2, 1, 1, 2, 3, 2, 1, 2, 3, 2, 1, 2, 2, 2, 1, 3, 3, 3, 1, 2, 2, 3, 2, 1, 3, 1, 3, 3, 2, 3, 1, 1, 3, 2, 2, 3, 3, 3, 1, 3, 1, 2, 2, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 0, 1, 2, 0, 1, 0, 3, 2, 2, 3, 1, 2, 2, 2, 1, 3, 3, 0, 2, 1, 1, 3, 0, 2, 0, 3, 2, 2, 1, 2, 1, 1, 3, 3, 2, 1, 3, 1, 0, 2, 1, 1, 1, 3, 3, 2, 3, 3, 3, 1, 0, 1, 1, 1, 3, 1, 1, 2, 0))));

	}
	private void createWaveNine() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 3, 1, 1, 0, 1, 3, 1, 0, 1, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 3, 3, 3, 1, 3, 1, 1, 1, 0, 0, 0, 1, 3, 1, 3, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 3, 3, 1, 3, 3, 3, 0, 0, 0, 3, 0, 3, 3, 3, 0, 1, 3, 1, 0, 1, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 3, 1, 3, 1, 1, 0, 0, 1, 3, 1, 3, 1, 1, 0, 0, 0, 0, 1, 0, 1, 3, 1, 3, 1, 1, 0, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 3, 1, 1, 1, 0, 1, 0, 1, 3, 1, 3, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 3, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 3, 3, 3, 3, 1, 3, 1, 0, 1, 0, 0, 3, 3, 3, 1, 1, 1, 0, 0, 0, 1, 1, 1, 3, 3, 3, 0, 1, 3, 3, 1, 1, 0, 0, 0, 1, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 0, 1, 1, 0, 3, 0, 0, 3, 0, 1, 1, 0, 1, 3, 1, 1, 2, 0, 0, 3, 2, 0, 0, 2, 3, 3, 3, 0, 0, 3, 3, 1, 1, 0, 1, 0, 0, 1, 3, 1, 2, 1, 2, 0, 1, 0, 1, 3, 0, 2, 2, 2, 3, 3, 1, 2, 2, 0, 3, 3, 3, 2, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 1, 2, 2, 1, 1, 2, 2, 1, 0, 3, 0, 0, 0, 0, 1, 2, 2, 0, 2, 2, 2, 2, 2, 1, 1, 0, 3, 1, 3, 0, 2, 1, 3, 0, 2, 0, 2, 0, 2, 2, 0, 2, 3, 0, 3, 0, 3, 1, 0, 2, 0, 3, 0, 3, 1, 1, 3, 0, 3, 3, 1, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 2, 0, 1, 0, 3, 1, 0, 2, 3, 0, 0, 0, 0, 2, 3, 3, 1, 1, 2, 2, 3, 2, 0, 2, 3, 1, 0, 3, 1, 1, 3, 3, 1, 0, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1, 3, 2, 1, 0, 3, 3, 3, 2, 2, 0, 1, 1, 1, 1, 0, 3, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 3, 2, 2, 2, 3, 1, 3, 1, 2, 2, 1, 3, 1, 3, 1, 1, 3, 1, 3, 2, 3, 3, 2, 2, 3, 1, 3, 1, 2, 1, 3, 2, 3, 2, 3, 2, 2, 3, 2, 1, 2, 3, 3, 2, 3, 1, 3, 2, 1, 2, 1, 3, 2, 3, 2, 2, 3, 1, 3, 2, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 1, 2, 1, 3, 2, 3, 2, 3, 2, 2, 1, 2, 3, 2, 3, 1, 1, 1, 3, 2, 2, 3, 1, 1, 1, 1, 1, 3, 2, 1, 1, 2, 3, 2, 2, 2, 1, 3, 1, 3, 2, 1, 2, 1, 3, 1, 2, 3, 2, 1, 2, 3, 1, 2, 3, 1, 3, 1, 3, 1, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 3, 1, 1, 1, 2, 1, 3, 1, 3, 3, 1, 1, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 3, 3, 3, 2, 3, 3, 3, 2, 1, 3, 2, 1, 1, 1, 2, 1, 1, 2, 3, 2, 3, 1, 3, 2, 3, 3, 3, 1, 1, 3, 2, 3, 3, 2, 2, 2, 1, 2, 3, 2, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 3, 3, 1, 3, 2, 1, 3, 2, 3, 1, 1, 2, 3, 1, 3, 2, 1, 3, 3, 3, 2, 1, 1, 3, 2, 3, 2, 1, 1, 2, 2, 1, 2, 2, 3, 3, 3, 3, 2, 2, 3, 2, 3, 3, 2, 3, 1, 3, 2, 3, 3, 1, 1, 3, 3, 2, 2, 1, 3, 3, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 3, 3, 0, 3, 1, 2, 3, 0, 1, 3, 3, 0, 0, 0, 1, 2, 1, 3, 1, 0, 0, 2, 0, 1, 1, 0, 1, 1, 1, 3, 0, 0, 3, 1, 0, 0, 3, 3, 1, 1, 3, 0, 1, 3, 1, 2, 1, 3, 2, 3, 0, 2, 1, 0, 0, 3, 1, 2, 0, 2, 1, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 3, 3, 0, 3, 1, 2, 3, 0, 1, 3, 3, 0, 0, 0, 1, 2, 1, 3, 1, 0, 0, 2, 0, 1, 1, 0, 1, 1, 1, 3, 0, 0, 3, 1, 0, 0, 3, 3, 1, 1, 3, 0, 1, 3, 1, 2, 1, 3, 2, 3, 0, 2, 1, 0, 0, 3, 1, 2, 0, 2, 1, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 2, 3, 0, 3, 1, 1, 0, 2, 0, 2, 2, 3, 1, 2, 0, 0, 0, 2, 1, 2, 0, 2, 1, 3, 1, 0, 1, 2, 2, 2, 2, 0, 2, 2, 3, 3, 0, 0, 1, 1, 3, 0, 0, 0, 2, 1, 1, 3, 3, 2, 1, 0, 0, 2, 0, 2, 3, 1, 0, 1, 0, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 1, 0, 2, 0, 1, 0, 3, 0, 2, 2, 2, 0, 0, 3, 2, 1, 3, 3, 2, 0, 2, 1, 2, 3, 0, 1, 2, 1, 2, 1, 1, 0, 1, 3, 1, 3, 0, 1, 0, 2, 0, 2, 2, 1, 1, 1, 2, 1, 0, 1, 1, 2, 3, 3, 2, 3, 1, 1, 1, 3, 2, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 3, 1, 1, 2, 2, 2, 0, 3, 2, 0, 2, 0, 1, 0, 3, 3, 2, 0, 1, 2, 2, 3, 0, 0, 2, 0, 2, 1, 3, 3, 3, 2, 1, 0, 1, 3, 2, 1, 2, 1, 1, 0, 1, 3, 1, 1, 3, 3, 1, 1, 2, 0, 0, 3, 0, 3, 3, 2, 1, 2, 2, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 3, 3, 0, 2, 0, 0, 3, 0, 0, 1, 1, 3, 3, 3, 1, 3, 1, 2, 2, 2, 1, 2, 3, 3, 3, 3, 1, 0, 1, 0, 1, 0, 0, 2, 2, 0, 2, 2, 0, 2, 3, 2, 2, 3, 1, 0, 3, 3, 3, 1, 2, 1, 0, 1, 1, 3, 1, 2, 0, 2, 2, 1, 3, 3, 3, 2, 3, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 3, 0, 2, 2, 0, 3, 3, 2, 2, 3, 0, 0, 2, 2, 2, 1, 1, 0, 1, 3, 3, 1, 2, 1, 0, 1, 3, 0, 0, 2, 1, 0, 2, 0, 2, 2, 3, 1, 1, 1, 1, 2, 2, 3, 0, 0, 1, 1, 1, 3, 1, 0, 0, 3, 1, 0, 0, 1, 1, 1, 3, 3, 1, 2, 3, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 0, 3, 2, 2, 3, 0, 0, 1, 1, 1, 1, 1, 3, 0, 0, 3, 0, 3, 3, 0, 1, 1, 0, 2, 0, 3, 3, 2, 3, 1, 2, 0, 1, 0, 2, 1, 1, 2, 3, 2, 0, 0, 0, 0, 0, 0, 3, 1, 0, 3, 2, 1, 1, 2, 3, 2, 1, 3, 1, 0, 0, 3, 0, 2, 3, 3, 3, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 0, 1, 0, 0, 0, 0, 3, 0, 2, 1, 3, 0, 3, 2, 3, 1, 3, 3, 0, 2, 3, 0, 0, 3, 3, 0, 3, 2, 1, 1, 3, 1, 3, 3, 2, 3, 0, 1, 0, 3, 3, 3, 0, 3, 1, 0, 0, 0, 3, 1, 3, 3, 2, 3, 2, 1, 0, 1, 3, 1, 3, 3, 3, 2, 3, 1, 2, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 1, 0, 0, 2, 1, 3, 3, 2, 0, 3, 0, 0, 2, 3, 0, 2, 2, 1, 2, 3, 2, 3, 0, 3, 0, 1, 3, 2, 3, 1, 1, 0, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 0, 2, 3, 3, 0, 1, 1, 0, 3, 0, 1, 3, 0, 2, 3, 3, 1, 0, 1, 2, 2, 3, 1, 2, 2, 0, 0, 0, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 2, 2, 2, 3, 3, 0, 2, 2, 0, 2, 2, 0, 1, 2, 3, 3, 1, 2, 0, 0, 2, 2, 3, 1, 3, 1, 0, 3, 2, 3, 3, 0, 2, 1, 0, 1, 1, 2, 3, 2, 0, 2, 2, 2, 1, 2, 1, 2, 0, 2, 3, 3, 0, 2, 0, 0, 0, 0, 3, 2, 0, 2, 3, 2, 3, 3, 3, 1, 2, 3, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 0, 1, 2, 2, 3, 2, 0, 3, 1, 3, 1, 0, 1, 2, 2, 2, 0, 1, 0, 0, 1, 1, 3, 1, 1, 3, 1, 0, 1, 1, 0, 2, 3, 2, 0, 2, 1, 0, 2, 2, 0, 3, 2, 2, 2, 0, 1, 2, 2, 2, 3, 3, 3, 3, 0, 1, 3, 2, 1, 0, 3, 2, 2, 3, 1, 3, 2, 2, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 0, 2, 3, 1, 1, 0, 1, 0, 2, 2, 3, 2, 3, 1, 0, 0, 3, 0, 3, 1, 1, 1, 2, 0, 2, 1, 3, 2, 2, 1, 2, 2, 0, 1, 0, 1, 1, 2, 1, 0, 0, 1, 3, 3, 2, 3, 0, 2, 0, 0, 0, 1, 1, 3, 2, 3, 1, 1, 2, 3, 3, 0, 2, 3, 2, 2, 1, 0, 0, 3, 1, 1, 1, 0, 1, 0, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 0, 2, 1, 1, 1, 2, 0, 1, 3, 2, 3, 0, 3, 3, 3, 1, 1, 0, 1, 2, 3, 3, 0, 2, 0, 2, 1, 1, 1, 2, 1, 2, 0, 3, 0, 2, 1, 0, 0, 1, 3, 3, 1, 2, 3, 1, 0, 0, 2, 1, 3, 2, 0, 0, 1, 1, 2, 2, 3, 0, 0, 1, 2, 0, 0, 3, 2, 2, 0, 2, 3, 3, 2, 1, 2, 1, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 3, 1, 0, 3, 1, 3, 2, 2, 3, 0, 3, 2, 3, 2, 3, 3, 0, 0, 3, 0, 1, 0, 0, 3, 2, 0, 1, 2, 1, 1, 0, 1, 3, 1, 0, 0, 1, 2, 3, 0, 3, 2, 2, 2, 0, 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 0, 2, 2, 3, 3, 0, 0, 0, 3, 1, 1, 0, 2, 3, 0, 2, 2, 3, 1, 1, 0, 0, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 3, 3, 3, 3, 3, 0, 2, 1, 2, 0, 3, 1, 1, 0, 0, 0, 0, 3, 0, 3, 0, 1, 0, 0, 2, 1, 2, 3, 3, 2, 3, 2, 2, 2, 2, 1, 3, 0, 3, 1, 1, 3, 3, 2, 3, 3, 3, 0, 3, 3, 3, 0, 0, 3, 0, 3, 1, 3, 3, 1, 2, 3, 0, 0, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 0, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 0, 3, 3, 2, 1, 3, 0, 3, 2, 1, 0, 3, 0, 2, 2, 2, 3, 0, 2, 2, 2, 0, 3, 1, 0, 0, 1, 1, 1, 1, 3, 2, 0, 3, 1, 2, 2, 2, 1, 0, 3, 1, 0, 3, 0, 2, 2, 0, 3, 0, 2, 1, 1, 2, 2, 1, 3, 3, 2, 1, 1, 2, 0, 1, 1, 1, 3, 3, 3, 0, 0, 3, 2, 1, 2, 3, 3, 0, 0, 3, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 1, 0, 1, 3, 2, 0, 1, 3, 1, 1, 0, 1, 3, 1, 0, 1, 0, 3, 0, 1, 1, 3, 3, 3, 3, 1, 3, 1, 0, 0, 3, 1, 1, 0, 1, 3, 0, 2, 3, 2, 0, 0, 3, 0, 2, 0, 1, 2, 3, 1, 0, 1, 1, 3, 0, 1, 1, 0, 2, 1, 1, 2, 3, 2, 2, 0, 2, 3, 3, 2, 1, 0, 3, 1, 0, 1, 1, 2, 0, 1, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 2, 1, 0, 1, 1, 2, 1, 2, 2, 3, 1, 1, 1, 3, 3, 1, 0, 2, 0, 2, 3, 0, 0, 3, 2, 2, 3, 0, 0, 2, 0, 3, 0, 1, 0, 1, 3, 2, 0, 1, 1, 1, 2, 2, 1, 2, 0, 1, 0, 0, 0, 3, 0, 0, 3, 3, 2, 2, 3, 3, 1, 0, 3, 2, 1, 0, 0, 1, 2, 0, 2, 0, 1, 1, 2, 2, 3, 3, 0, 3, 3, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 2, 2, 3, 2, 3, 2, 3, 3, 2, 1, 1, 0, 1, 3, 0, 0, 2, 2, 2, 2, 1, 3, 3, 2, 1, 1, 0, 2, 1, 1, 1, 0, 0, 0, 2, 3, 2, 1, 3, 1, 3, 1, 1, 0, 0, 2, 1, 3, 0, 3, 1, 1, 2, 0, 3, 3, 2, 0, 2, 3, 2, 3, 3, 2, 3, 2, 2, 2, 3, 1, 3, 3, 2, 1, 3, 3, 3, 0, 2, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 1, 1, 3, 2, 3, 3, 3, 0, 0, 3, 2, 1, 0, 2, 3, 0, 0, 2, 3, 3, 1, 3, 2, 3, 2, 2, 2, 3, 0, 2, 1, 2, 3, 1, 1, 3, 1, 0, 0, 2, 0, 2, 3, 0, 1, 3, 1, 1, 1, 1, 0, 3, 0, 3, 3, 3, 2, 1, 2, 2, 3, 0, 0, 3, 2, 1, 2, 3, 2, 2, 1, 2, 1, 1, 3, 2, 1, 1, 0, 2, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 2, 3, 2, 3, 0, 1, 1, 0, 3, 3, 0, 1, 0, 0, 3, 3, 0, 2, 3, 2, 2, 2, 3, 1, 3, 0, 3, 1, 2, 3, 2, 1, 2, 0, 0, 3, 2, 1, 0, 1, 1, 0, 3, 1, 0, 2, 1, 1, 1, 0, 3, 3, 2, 3, 1, 2, 0, 0, 0, 2, 0, 2, 3, 3, 2, 0, 2, 0, 2, 2, 2, 3, 3, 1, 1, 3, 3, 2, 0, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 3, 3, 3, 1, 1, 1, 0, 3, 1, 1, 0, 0, 2, 2, 0, 0, 3, 0, 0, 0, 0, 3, 1, 0, 3, 1, 1, 1, 3, 1, 1, 3, 0, 0, 0, 2, 2, 1, 1, 0, 3, 2, 1, 2, 0, 1, 2, 0, 1, 2, 1, 1, 2, 2, 2, 1, 0, 0, 2, 0, 2, 0, 3, 3, 0, 3, 1, 3, 1, 2, 3, 0, 0, 1, 3, 1, 2, 1, 2, 0, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 3, 0, 3, 2, 3, 1, 1, 0, 1, 1, 1, 3, 2, 1, 2, 2, 3, 2, 0, 1, 1, 3, 1, 0, 3, 2, 1, 3, 3, 3, 1, 3, 0, 1, 1, 3, 3, 3, 0, 0, 0, 2, 3, 3, 3, 2, 2, 2, 1, 2, 0, 2, 2, 2, 3, 1, 0, 0, 1, 0, 0, 2, 0, 0, 3, 1, 0, 2, 1, 1, 2, 1, 3, 2, 1, 3, 1, 3, 2, 2, 2, 2, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 2, 0, 0, 1, 1, 3, 2, 2, 3, 3, 2, 0, 3, 0, 0, 2, 3, 2, 3, 3, 0, 0, 3, 0, 2, 1, 2, 3, 2, 3, 3, 1, 0, 3, 3, 1, 2, 1, 0, 2, 1, 2, 3, 1, 2, 3, 0, 2, 1, 3, 1, 1, 0, 1, 2, 2, 3, 1, 1, 1, 0, 1, 2, 0, 1, 1, 2, 0, 3, 2, 2, 0, 1, 2, 2, 0, 1, 2, 2, 3, 1, 0, 1, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 2, 2, 0, 3, 3, 2, 3, 1, 1, 3, 1, 1, 0, 2, 3, 1, 0, 3, 2, 0, 2, 0, 3, 3, 3, 3, 2, 0, 2, 1, 3, 0, 0, 0, 0, 0, 3, 3, 2, 1, 3, 3, 2, 0, 2, 2, 2, 3, 1, 0, 3, 3, 1, 3, 3, 0, 2, 1, 0, 1, 3, 0, 1, 3, 0, 2, 3, 3, 1, 2, 0, 1, 1, 3, 1, 0, 1, 0, 3, 2, 0, 2, 1, 1, 1, 1, 2, 0, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 2, 2, 0, 2, 2, 0, 0, 0, 3, 3, 0, 3, 1, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 2, 3, 0, 1, 2, 2, 0, 2, 0, 2, 2, 3, 1, 2, 0, 1, 2, 3, 2, 1, 1, 3, 0, 3, 1, 0, 3, 0, 1, 0, 2, 0, 0, 2, 1, 3, 0, 2, 1, 1, 1, 3, 0, 2, 1, 0, 3, 0, 0, 2, 1, 1, 2, 2, 0, 3, 2, 0, 0, 0, 1, 2, 0, 1, 1, 2, 0, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 2, 0, 0, 3, 0, 1, 1, 2, 1, 0, 3, 2, 2, 2, 3, 2, 3, 0, 1, 0, 0, 2, 1, 2, 0, 3, 0, 3, 3, 3, 2, 2, 0, 3, 1, 0, 0, 3, 3, 1, 3, 3, 3, 2, 1, 2, 0, 1, 3, 1, 1, 3, 3, 3, 1, 0, 3, 1, 3, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 3, 2, 1, 2, 0, 3, 1, 0, 0, 0, 2, 2, 0, 2, 1, 1, 2, 0, 3, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 3, 0, 1, 2, 1, 0, 2, 3, 3, 2, 2, 0, 3, 0, 0, 1, 0, 3, 2, 3, 0, 3, 0, 0, 0, 3, 2, 0, 1, 3, 1, 0, 0, 2, 2, 1, 2, 1, 0, 2, 0, 3, 2, 3, 3, 3, 1, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 2, 1, 0, 2, 2, 0, 2, 0, 3, 0, 3, 0, 1, 1, 1, 1, 3, 0, 1, 3, 2, 1, 2, 2, 0, 2, 1, 0, 2, 2, 0, 1, 3, 0, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 3, 0, 1, 1, 1, 3, 3, 1, 1, 3, 3, 3, 2, 2, 2, 3, 3, 1, 3, 1, 2, 2, 1, 2, 2, 2, 3, 2, 3, 0, 2, 2, 1, 0, 3, 2, 3, 3, 3, 1, 3, 3, 1, 1, 1, 3, 0, 0, 1, 0, 2, 0, 2, 3, 2, 1, 1, 1, 3, 0, 2, 2, 3, 3, 3, 3, 2, 3, 3, 1, 0, 2, 1, 1, 0, 2, 1, 1, 3, 3, 1, 2, 3, 3, 1, 1, 1, 3, 3, 0, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 3, 3, 2, 3, 3, 2, 3, 3, 1, 1, 3, 2, 3, 2, 1, 1, 2, 2, 1, 0, 3, 3, 3, 3, 0, 0, 1, 0, 3, 3, 2, 2, 3, 0, 2, 3, 2, 2, 2, 3, 3, 2, 2, 2, 0, 3, 0, 3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 3, 2, 2, 3, 0, 1, 3, 1, 3, 2, 3, 1, 0, 3, 0, 3, 2, 3, 3, 2, 3, 2, 0, 1, 2, 3, 3, 1, 1, 1, 0, 0, 3, 2, 3, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 3, 0, 0, 1, 1, 2, 2, 2, 3, 3, 0, 0, 3, 2, 2, 1, 2, 2, 0, 2, 3, 3, 0, 2, 2, 2, 1, 1, 0, 1, 1, 3, 2, 2, 0, 3, 1, 3, 2, 0, 3, 2, 0, 3, 1, 3, 0, 3, 1, 1, 2, 0, 1, 2, 3, 3, 1, 0, 0, 1, 2, 2, 1, 0, 0, 1, 0, 2, 1, 2, 2, 1, 0, 3, 0, 2, 2, 1, 1, 3, 2, 3, 0, 3, 2, 3, 3, 3, 0, 0, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 3, 3, 2, 0, 1, 2, 1, 3, 1, 0, 3, 0, 1, 2, 2, 3, 3, 1, 2, 0, 0, 1, 1, 0, 2, 3, 1, 2, 1, 0, 0, 1, 3, 2, 1, 0, 1, 3, 0, 3, 0, 0, 3, 1, 2, 1, 0, 2, 3, 3, 3, 0, 0, 0, 0, 2, 3, 2, 0, 1, 2, 1, 2, 2, 0, 0, 3, 2, 3, 0, 1, 0, 0, 0, 1, 3, 2, 1, 1, 2, 1, 0, 2, 1, 0, 3, 0, 0, 2, 1, 1, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 3, 2, 2, 2, 1, 3, 2, 2, 1, 1, 2, 1, 3, 3, 1, 1, 1, 0, 0, 3, 1, 0, 3, 0, 3, 0, 0, 3, 2, 3, 1, 2, 2, 0, 0, 3, 0, 2, 2, 1, 0, 0, 2, 3, 0, 0, 1, 1, 1, 1, 1, 2, 2, 0, 2, 1, 1, 3, 0, 3, 2, 2, 2, 1, 3, 2, 0, 3, 1, 3, 0, 2, 0, 3, 1, 0, 3, 2, 2, 0, 0, 3, 3, 2, 3, 2, 3, 1, 1, 0, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 2, 0, 1, 1, 3, 3, 3, 1, 1, 0, 1, 2, 3, 2, 1, 0, 2, 1, 2, 1, 3, 1, 3, 1, 2, 2, 3, 1, 3, 0, 2, 1, 0, 3, 3, 3, 3, 2, 1, 1, 3, 2, 3, 2, 1, 1, 2, 0, 2, 1, 1, 2, 2, 1, 0, 0, 0, 3, 2, 3, 3, 0, 1, 0, 0, 1, 3, 3, 0, 0, 0, 3, 3, 0, 1, 1, 1, 0, 2, 0, 1, 3, 3, 0, 1, 3, 1, 1, 2, 3, 1, 2, 0, 2, 2, 1, 0))));

	}
	
	private void createWaves() {
		int lvlNumber = LoadSave.getLvlNumber();
		
		if(lvlNumber == 1) {
			createWaveOne();
		}else if(lvlNumber == 2) {
			createWaveTwo();
		}else if(lvlNumber == 3) {
			createWaveThree();
		}else if(lvlNumber == 4) {
			createWaveFour();
		}else if(lvlNumber == 5) {
			createWaveFive();
		}else if(lvlNumber == 6) {
			createWaveSix();
		}else if(lvlNumber == 7) {
			createWaveSeven();
		}else if(lvlNumber == 8) {
			createWaveEight();
		}else if(lvlNumber == 9) {
			createWaveNine();
		}
	}
	
	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	}

	public ArrayList<Wave> getWaves(){
		return waves;
	}
	
	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex +1 < waves.size();
	}

	public boolean isWaveTimerOver() {
		return waveTickTimerOver;
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
		
	}

	public int getWaveIndex() {
		return waveIndex;
	}
	
	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}
	
	public int getNumberOfEnnemies(int currentWave) {
		return waves.get(currentWave).getEnemyList().size();
	}

	public void resetEverything() {
		waves.clear();
		createWaves();
		
		enemyIndex = 0;
		waveIndex = 0;
		
		waveStartTimer = false;
		waveTickTimerOver = false;
		
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
		currWave = 1;
		helpz.Constants.Enemies.setCurrWave(currWave);
	}

}
