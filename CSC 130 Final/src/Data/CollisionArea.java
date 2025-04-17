package Data;

//object class to make sprite objects interactable through use of collision buffer class
public class CollisionArea extends CollisionBuffer{
	
	private String onScreenText;
	private boolean canInteract;
	private int level;
	private boolean[] directions; //directions you can interact from, index 0 is up, 1 is down, 2 is right, 3 is left
	
	int widthAdditional;
	int heightAdditional;
	
	public CollisionArea(spriteInfo sprite, int width, int height, int widthAdditional, int heightAdditional, String onScreenText, int level, boolean up, boolean down, boolean right, boolean left){
		super(sprite, width, height);
		
		this.onScreenText = onScreenText;
		this.canInteract = false;
		this.level = level;
		
		this.widthAdditional = widthAdditional;
		this.heightAdditional = heightAdditional;
		
		this.directions = new boolean[4];
		setDirections(up, down, right, left);

	}
	
	//override to remove moving and adjust with the additional buffer zones
	@Override
	public boolean collisionDetection(spriteInfo sprite, CollisionBuffer object){
		adjustCoords(sprite);
		if((this.getX1() - widthAdditional< object.getX2()) &&
		   (this.getX2() + widthAdditional> object.getX1()) &&
		   (this.getY1() - heightAdditional < object.getY2()) &&
		   (this.getY2() + heightAdditional > object.getY1())) {
			
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
	
	//checks if you can interact with object depending on level you are on
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
	//for initialization
	private void setDirections(boolean up, boolean down, boolean right, boolean left){
		boolean[] temp = {up, down, right, left};
		for(int i = 0; i < directions.length; i++){
			this.directions[i] = temp[i];
		}
	}
	
	public boolean[] getDirections(){
		return this.directions;
	}
}