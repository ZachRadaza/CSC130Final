package Main;

import java.awt.Color;
import java.util.ArrayList;

import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;
import Data.spriteInfo;

public class Main{
	// Fields (Static) below...
	public static Color darkGreen = new Color(0, 100, 50);
	public static stopWatchX timer = new stopWatchX(50);
	public static stopWatchX timerSquare = new stopWatchX(50);
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static int currentSpriteIndex = 0;
	
	//personal testing
	public static ArrayList<spriteInfo> spritesSquare = new ArrayList<>();
	public static int currentSpriteIndexSquare = 0;
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		String[] frameInUse = {"ChelseyEast1", "ChelseyEast2", "ChelseyEast2", "ChelseyEast2", "ChelseyEast1", "ChelseyEast3", "ChelseyEast3", "ChelseyEast3"};
		int i = 0;
		
		for(int n = -100; n < 1350; n += 10){
			sprites.add(new spriteInfo(new Vector2D(n, 300), frameInUse[i]));
			i++;
			if(i == 8) i = 0;
		}
		
		//personal testing
		//loadCoordsTag(400, 500, "ChelseyEast1", "ChelseyEast2", "ChelseyEast3", false, true);
		//loadCoordsTag(700, 500, "ChelseyNorth1", "ChelseyNorth2", "ChelseyNorth3", false, false);
		//loadCoordsTag(700, 200, "ChelseyWest1", "ChelseyWest2", "ChelseyWest3", true, true);
		//loadCoordsTag(400, 200, "ChelseySouth1", "ChelseySouth2", "ChelseySouth3", true, false);
	}
		
	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.addSpriteToFrontBuffer(1060, 570, "ChelseySouth1");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)		

		ctrl.drawString(1000, 710, "Zachary Juls Radaza", darkGreen);		// Test drawing text on screen where you want (Remove later! Test only!)
		
		ctrl.addSpriteToFrontBuffer(sprites.get(currentSpriteIndex).getCoords().getX(), sprites.get(currentSpriteIndex).getCoords().getY(), sprites.get(currentSpriteIndex).getTag());

		while(timer.isTimeUp()){
			currentSpriteIndex++;
			if(currentSpriteIndex >= sprites.size() - 1) currentSpriteIndex = 0;
			timer.resetWatch();
		}
		
		//personal testing, testing all frames
		/*
		ctrl.addSpriteToFrontBuffer(spritesSquare.get(currentSpriteIndexSquare).getCoords().getX(), spritesSquare.get(currentSpriteIndexSquare).getCoords().getY(), spritesSquare.get(currentSpriteIndexSquare).getTag());
		
		while(timerSquare.isTimeUp()){
			currentSpriteIndexSquare++;
			if(currentSpriteIndexSquare >= spritesSquare.size() - 1) currentSpriteIndexSquare = 0;
			timerSquare.resetWatch();
		}
		*/
	}
	
	// Additional Static methods below...(if needed)
	
	//personal testing
	
	private static void loadCoordsTag(int xStart, int yStart, String tag1, String tag2, String tag3, boolean travellingNegative, boolean usingX){
		String[] frameInUse = {tag1, tag2, tag2, tag2, tag1, tag3, tag3, tag3};
		int i = 0;

		if(usingX){
			if(!travellingNegative){
				for(int j = xStart;j < xStart + 300;j += 10){
					spritesSquare.add(new spriteInfo(new Vector2D(j, yStart), frameInUse[i]));
					i++;
					if(i == 8) i = 0;
				}
			} else {
				for(int j = xStart;j > xStart - 300;j -= 10){
					spritesSquare.add(new spriteInfo(new Vector2D(j, yStart), frameInUse[i]));
					i++;
					if(i == 8) i = 0;
				}
			}
		} else {
			if(!travellingNegative){
				for(int j = yStart;j > yStart - 300;j -= 10){
					spritesSquare.add(new spriteInfo(new Vector2D(xStart, j), frameInUse[i]));
					i++;
					if(i == 8) i = 0;
				}
			} else {
				for(int j = yStart;j < yStart + 300;j += 10){
					spritesSquare.add(new spriteInfo(new Vector2D(xStart, j), frameInUse[i]));
					i++;
					if(i == 8) i = 0;
				}
			}
		}

	}
}
