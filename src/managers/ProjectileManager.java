package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;
import static helpz.Constants.Towers.*;
import static helpz.Constants.Projectiles.*;

public class ProjectileManager {
	
	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private BufferedImage[] proj_imgs, explo_imgs;
	private int proj_id = 0;
	private float exploRadius = 60.0f;
	private float timeStopRadius = 120.0f;
	private float nukeRadius = 200.0f;
	
	
	public ProjectileManager(Playing playing) {
		this.playing = playing;
		importImgs();
	}
	
	private void importImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		proj_imgs = new BufferedImage[3];
		
		for(int i = 0; i < 3; i++)
			proj_imgs[i] = atlas.getSubimage((7+i) * 32, 32, 32, 32);
		
		importExplosion(atlas);
	}
	
	private void importExplosion(BufferedImage atlas) {
		explo_imgs = new BufferedImage[7];
		
		for(int i = 0; i < 7; i++)
			explo_imgs[i] = atlas.getSubimage(i * 32, 2 * 32, 32, 32);
	}
	
	public void newProjectile(Tower t, Enemy e) {
		int type = getProjType(t);
		
		if(t.getName() == "TimeThief") {
			type = TIMESTOP;
		}else if(t.getName() == "Nuke") {
			type = NUKE;
		}else if(t.getName() == "IceLord") {
			type = ICEPOISON;
		}else if(t.getName() == "Sniper") {
			type = SNIPER;
		}
		
		int xDist = (int)(t.getX() - e.getX());
		int yDist = (int)(t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);
		float xPer = (float)Math.abs(xDist) / totDist;
		float xSpeed = xPer * helpz.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helpz.Constants.Projectiles.GetSpeed(type) - xSpeed;
		
		if(t.getX() > e.getX())
			xSpeed *= -1;
			
		if(t.getY() > e.getY())
			ySpeed *= -1;
		
		float rotate = 0;
		
		if(type == ARROW || type == SNIPER) {
			float arcValue = (float)Math.atan(yDist / (float)xDist);
			rotate = (float)Math.toDegrees(arcValue);
			
			if(xDist < 0)
				rotate += 180;
		}
		
		for(Projectile p : projectiles)
			if(!p.isActive())
				if(p.getProjectileType() == type) {
					p.reuse(t.getX() +16, t.getY() +16, xSpeed, ySpeed, t.getDmg(), rotate);
					return;
				}
					
		projectiles.add(new Projectile(t.getX() +16, t.getY() +16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));
	}
	
	

	public void update() {
		for(Projectile p : projectiles)
			if(p.isActive()) {
				p.move();
				if(isProjHittingEnemy(p)) {
					p.setActive(false);
					if(p.getProjectileType() == BOMB) {
						explosions.add(new Explosion(p.getPos()));
						explodeOnEnemies(p);
					} else if(p.getProjectileType() == TIMESTOP) {
						timeStopEnemies(p);
					} else if(p.getProjectileType() == NUKE) {
						explosions.add(new Explosion(p.getPos()));
						nukeOnEnemies(p);
					}
				}else if(isProjOutsideBounds(p)) {
					p.setActive(false);
				}
			}
		
		for(Explosion e : explosions)
			if(e.getIndex() < 7)
				e.update();
		
		
	}
	
	

	private void explodeOnEnemies(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive()) {
				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());

				float realDist = (float)Math.hypot(xDist, yDist);
				
				if(realDist <= exploRadius)
					e.hurt(p.getDmg());
				
			}
			
		}
		
	}
	
	private void timeStopEnemies(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive()) {
				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());

				float realDist = (float)Math.hypot(xDist, yDist);
				
				if(realDist <= timeStopRadius)
					e.timeStop();
				
			}
			
		}
		
	}
	
	private void nukeOnEnemies(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive()) {
				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());

				float realDist = (float)Math.hypot(xDist, yDist);
				
				if(realDist <= nukeRadius)
					e.hurt(p.getDmg());
				
			}
			
		}
		
	}
	
	

	private boolean isProjHittingEnemy(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive()) {
				if(e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());
					if(p.getProjectileType() == CHAINS)
						e.slow();
					else if(p.getProjectileType() == TIMESTOP)
						e.timeStop();
					else if(p.getProjectileType() == ICEPOISON) {
						e.icePoisoned();
						e.slow();
					}
					return true;
				}
			}
			
		}
		return false;
	}
	
	private boolean isProjOutsideBounds(Projectile p) {
		if(p.getPos().x >= 0)
			if(p.getPos().x <= 640)
				if(p.getPos().y >= 0)
					if(p.getPos().y <= 640)
						return false;
		return true;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		boolean isNuke = false;
		
		for(Projectile p : projectiles) {
			int pType = p.getProjectileType();
			
			if(pType == TIMESTOP)
				pType = CHAINS;
			else if(pType == ICEPOISON)
				pType = CHAINS;
			else if(pType == SNIPER)
				pType = ARROW;
			else if(pType == NUKE) {
				pType = BOMB;
				isNuke = true;
			}
				
			
				if(p.isActive()) {
					if(pType == ARROW || pType == SNIPER) {
						g2d.translate(p.getPos().x, p.getPos().y);
						g2d.rotate(Math.toRadians(p.getRotation()));
						g2d.drawImage(proj_imgs[pType], -16, -16, null);
						g2d.rotate(-Math.toRadians(p.getRotation()));
						g2d.translate(-p.getPos().x, -p.getPos().y);
					}else {
						g2d.drawImage(proj_imgs[pType], (int)p.getPos().x - 16, (int)p.getPos().y - 16, null);
					}
				}
			
		}
		
		if(isNuke) {
			drawBigExplosions(g2d);
			isNuke = false;
		}else
			drawExplosions(g2d);
		
		
		
	}
	
	private void drawExplosions(Graphics2D g2d) {
		for(Explosion e : explosions) {
			if(e.getIndex() < 7) {
				g2d.drawImage(explo_imgs[e.getIndex()], (int)e.getPos().x - 16, (int)e.getPos().y - 16, 32, 32, null);
			}
		}
		
	}
	
	private void drawBigExplosions(Graphics2D g2d) {
		for(Explosion e : explosions) {
			if(e.getIndex() < 7) {
				g2d.drawImage(explo_imgs[e.getIndex()], (int)e.getPos().x - 66, (int)e.getPos().y - 66, 132, 132, null);
			}
		}
		
	}

	private int getProjType(Tower t) {
		switch(t.getTowerType()) {
		case CANNON:
			return BOMB;
		case ARCHER:
			if(t.getName() == "Sniper")
				return SNIPER;
			return ARROW;
		case WIZARD:
			if(t.getName() == "TimeThief")
				return TIMESTOP;
			return CHAINS;
		}
		return 0;
	}
	
	
	public class Explosion {
		
		private Point2D.Float pos;
		private int exploTick = 0, exploIndex = 0;
		
		public Explosion(Point2D.Float pos) {
			this.pos = pos;
		}
		
		public void update() {
			
				exploTick++;
				if(exploTick >= 12) {
					exploTick = 0;
					exploIndex++;
				}
			
		}
		
		public int getIndex() {
			return exploIndex;
		}
		
		public Point2D.Float getPos(){
			return pos;
		}
	}


	public void resetEverything() {
		projectiles.clear();
		explosions.clear();
		
		proj_id = 0;
		
	}
	
}
