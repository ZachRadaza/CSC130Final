package Data;

import Main.KeyProcessor;

public class CollisionBuffer{
	
	private boolean collision;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width;
	private int height;
	private spriteInfo sprite;
	
	public CollisionBuffer(spriteInfo sprite, int width, int height){
		this.collision = false;
		this.x1 = sprite.getCoords().getX();
		this.x2 = sprite.getCoords().getX() + width;
		this.y1 = sprite.getCoords().getY();
		this.y2 = sprite.getCoords().getY() + height;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public int getX1(){
		return this.x1;
	}
	
	public int getX2(){
		return this.x2;
	}
	
	public int getY1(){
		return this.y1;
	}
	
	public int getY2(){
		return this.y2;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public boolean getCollision(){
		return this.collision;
	}
	
	public spriteInfo getSprite(){
		return this.sprite;
	}
	
	public void setCollision(boolean collision){
		this.collision = collision;
	}
	
	public void setX1(int x1){
		this.x1 = x1;
	}
	
	public void setY1(int y1){
		this.y1 = y1;
	}
	
	public void setX2(int x2){
		this.x2 = x2 + this.width;
	}
	
	public void setY2(int y2){
		this.y2 = y2 + this.height;
	}
	
	public void adjustCoords(spriteInfo sprite){
		setX1(sprite.getCoords().getX());
		setX2(sprite.getCoords().getX());
		setY1(sprite.getCoords().getY());
		setY2(sprite.getCoords().getY());
	}
	
	//checks if then can move through the object or not, if not, bounces them back depending on last input
	public boolean collisionDetection(spriteInfo sprite, CollisionBuffer object){
		adjustCoords(sprite);
		if((this.getX1() + 30 < object.getX2()) &&
		   (this.getX2() - 30 > object.getX1()) &&
		   (this.getY1() + 90 < object.getY2()) &&
		   (this.getY2() - 18 > object.getY1())) {
			
				this.setCollision(true);
				object.setCollision(true);
						
				if(KeyProcessor.getLastBeforeCollision() == 'w'){
					sprite.getCoords().adjustY(3);
				}
					
				if(KeyProcessor.getLastBeforeCollision() == 'a'){
					sprite.getCoords().adjustX(3);
				}
							
				if(KeyProcessor.getLastBeforeCollision() == 's'){
					sprite.getCoords().adjustY(-3);
				}
					
				if(KeyProcessor.getLastBeforeCollision() == 'd'){
					sprite.getCoords().adjustX(-3);
				}
				
				return true;
							
			} else {
				this.setCollision(false);
				object.setCollision(false);
				return false;
		
			}
		}
}