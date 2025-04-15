/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(5);
	
	public static int direction = 1; //direction character is going
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
			Main.spriteChelsey.getCoords().adjustY(-5);
			break;
			
		case 'a':
			direction = 3;
			Main.currentlyMoving = true;
			Main.spriteChelsey.getCoords().adjustX(-5);
			break;
			
		case 's':
			direction = 1;
			Main.currentlyMoving = true;
			Main.spriteChelsey.getCoords().adjustY(5);
			break;
			
		case 'd':
			direction = 2;
			Main.currentlyMoving = true;
			Main.spriteChelsey.getCoords().adjustX(5);
			break; 
			
		case '$':
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}