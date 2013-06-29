package com.m1enkrafftman.rogue.entity;

import com.m1enkrafftman.rogue.external.Collideable;
import com.m1enkrafftman.rogue.external.World;
import com.m1enkrafftman.rogue.external.WorldStore;
import com.m1enkrafftman.rogue.misc.Location;
import com.m1enkrafftman.rogue.misc.MathHelper;

public class EntityEnemy extends EntityLiving {

	private float rotation;
	
	public EntityEnemy(int minX, int minY, int width, int color) {
		super(minX, minY, width, color);
		this.rotation = 0;
	}
	
	public void onUpdate(EntityPlayer p, World w) {
		if(this.getHealth() < 1) {
			this.setLifeStatus(true);
			//System.err.printf("Setting to : %s\n", this.getIsLiving() + "");
		}
		if(this.canSeePlayer(p) && this.getIsLiving()){
			this.lookToPlayer(p, w);
			this.damage(p);
		}else {
			//add in basic looking stuff here, for searching for player
		}
	}
	
	public boolean isThisWalkable() {
		return (this instanceof EntityGuard || this instanceof EntityBoss);
	}
	
	public void damage(EntityPlayer p) {
		if(this.getAttackInt() > 14 && (MathHelper.getDistance(new Location(this.getMinX(), this.getMinY()), new Location(p.getPlayerPosX() + 32, p.getPlayerPosY() + 32)) < 35)) {
			p.setHealth(p.getHealth() - 1);
			this.setAttackInt(0);
		}
	}
	
	public float getRotation() {
		return this.rotation;
	}
	
	public void lookToPlayer(EntityPlayer p, World w) {
		this.setAngles(p);
		if((MathHelper.getDistance(new Location(this.getMinX(), this.getMinY()), new Location(p.getPlayerPosX() + 16, p.getPlayerPosY() + 16)) > 20)) {
			this.move(w);
			if(this.isThisWalkable()) {
				if(this instanceof EntityGuard)((EntityGuard)this).animationTime += 1;
				if(this instanceof EntityBoss)((EntityBoss)this).animationTime += 1;
			}
		}
	}
	
	public void move(World w) {
		int speed = this.getSpeed();
		double xDelta = Math.sin(Math.toRadians(this.rotation)) * speed;
		double yDelta = Math.cos(Math.toRadians(this.rotation)) * speed;
		int xInt = (int)xDelta;
		int yInt = (int)yDelta;
		
		Location toMove = new Location(this.getMinX() + xInt, this.getMinY() + yInt);
		Location negMove = new Location(this.getMinX() - xInt, this.getMinY() - yInt);
		
		if(this.canMove(w, toMove)) {
			this.setLocation(toMove);
		}else {
			this.setLocation(negMove);
		}
	}
	
	public boolean canSeePlayer(EntityPlayer p) {
		return (MathHelper.getDistance(new Location(this.getMinX(), this.getMinY()), new Location(p.getPlayerPosX(), p.getPlayerPosY())) < 500);
	}
	
	public boolean canMove(World w, Location toMove) {
		for(WorldStore s : w.getStorage()) {
			if(s.getWall() instanceof Collideable) {
				Location you = this.getMid();
				Location it = s.getWall().getMid();
				double distance = MathHelper.getDistance(you.getX() + 384, you.getY() + 284, it.getX(), it.getY());
				if(distance <= 32) {
					return true;
					//TODO fix
				}
			}
		}
		return true;
	}
	
	public void renderEnemy(EntityPlayer ep) {
		
	}
	
	private void setAngles(EntityPlayer var1)
    {
        double var2 = var1.getPlayerPosX() - this.getMinX() + 32;
        double var6 = var1.getPlayerPosY() - this.getMinY() + 32;
        float var10 = (float)(Math.atan2(var6, var2) * 180.0D / Math.PI) - 90.0F;
        this.rotation = -var10;
    }

}
