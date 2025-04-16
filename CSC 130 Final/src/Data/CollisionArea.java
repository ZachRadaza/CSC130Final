package Data;

//object class to make sprite objects interactable through use of collision buffer class
public class CollisionArea extends CollisionBuffer{
	
	private String onScreenText;
	private boolean canInteract;
	private int level;
	
	public CollisionArea(spriteInfo sprite, int width, int height, int widthAdditional, int heightAdditional, String onScreenText, int level){
		super(sprite, width, height);
		
		this.onScreenText = onScreenText;
		this.canInteract = false;
		this.level = level;
		
		this.setX1(this.getX1() - widthAdditional);
		this.setX2(this.getX2() + widthAdditional);
		this.setY1(this.getY1() + heightAdditional);
		this.setY2(this.getY2() - heightAdditional);

	}
	
	//override to remove moving
	@Override
	public boolean collisionDetection(spriteInfo sprite, CollisionBuffer object){
		adjustCoords(sprite);
		if((this.getX1() + 30 < object.getX2()) &&
		   (this.getX2() - 30 > object.getX1()) &&
		   (this.getY1() + 90 < object.getY2()) &&
		   (this.getY2() - 18 > object.getY1())) {
			
				this.setCollision(true);
				object.setCollision(true);
				
				return true;
							
			} else {
				this.setCollision(false);
				object.setCollision(false);
				return false;
		
			}
		}
	
	public String getOnScreenText(){
		return onScreenText;
	}
	
	//checks if you can interact with object depending on level
	public boolean canInteract(int level){
		if(this.level == level){
			this.canInteract = true;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getCanInteract(){
		return this.canInteract;
	}
	
	public void setCanInteract(boolean canInteract){
		this.canInteract = canInteract;
	}
}