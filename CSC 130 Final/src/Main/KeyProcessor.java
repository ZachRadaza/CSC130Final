/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(5);
	private static stopWatchX swSpaceBar = new stopWatchX(2000); //makes sure you can only press space bar space bar once every 2 seconds
	
	public static int direction = 1; //direction character is going
	
	private static char lastBeforeCollision = ' ';
	// Static Method(s)
	public static void processKey(char key){
		/*
		if(key == ' ')	{			
			Main.trigger = "";
			return;
		}
		// Debounce routine below...
		*/
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case ' ':
			Main.currentSpriteIndex = 0;
			Main.currentlyMoving = false;
			break;
			
		case 'w':
			direction = 0;
			Main.currentlyMoving = true;
			if(Main.canMove)
				Main.spriteChelsey.getCoords().adjustY(-4);
			else
				lastBeforeCollision = 'w';
			break;
			
		case 'a':
			direction = 3;
			Main.currentlyMoving = true;
			if(Main.canMove)
				Main.spriteChelsey.getCoords().adjustX(-5);
			else
				lastBeforeCollision = 'a';
			break;
			
		case 's':
			direction = 1;
			Main.currentlyMoving = true;
			if(Main.canMove)
				Main.spriteChelsey.getCoords().adjustY(4);
			else
				lastBeforeCollision = 's';
			break;
			
		case 'd':
			direction = 2;
			Main.currentlyMoving = true;
			if(Main.canMove)
				Main.spriteChelsey.getCoords().adjustX(5);
			else
				lastBeforeCollision = 'd';
			break; 
			
		case '$':
			if(Main.spriteInteracts.size() - 1 > Main.currentLevel){
				if(Main.spriteInteracts.get(Main.currentLevel).getCanInteract()){
					if(swSpaceBar.isTimeUp()){
						if(Main.currentLevel < Main.interactableObjects.length){
							Main.dialogueVisible = true;
							Main.currentLevel += 1;
							swSpaceBar.resetWatch();
						}
					}
				}
			}
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
	
	public static char getLastBeforeCollision(){
		return lastBeforeCollision;
	}
}