package Main;

import java.awt.Color;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.ArrayList;

import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;
import Data.spriteInfo;
import FileIO.EZFileRead;

public class Main{
	// Fields (Static) below...
	public static Color darkGreen = new Color(0, 100, 50);
	public static stopWatchX timer = new stopWatchX(68);
	public static stopWatchX timerDialogue = new stopWatchX(5000);
	
	//fields for Chelsey(character)
	//frames in use, [set of frames][specific frame]
	public static String[][] framesInUse = {
											{"ChelseyNorth1", "ChelseyNorth2", "ChelseyNorth2", "ChelseyNorth2", "ChelseyNorth1", "ChelseyNorth3", "ChelseyNorth3", "ChelseyNorth3"},
											{"ChelseySouth1", "ChelseySouth2", "ChelseySouth2", "ChelseySouth2", "ChelseySouth1", "ChelseySouth3", "ChelseySouth3", "ChelseySouth3"},
											{"ChelseyEast1", "ChelseyEast2", "ChelseyEast2", "ChelseyEast2", "ChelseyEast1", "ChelseyEast3", "ChelseyEast3", "ChelseyEast3"},
											{"ChelseyWest1", "ChelseyWest2", "ChelseyWest2", "ChelseyWest2", "ChelseyWest1", "ChelseyWest3", "ChelseyWest3", "ChelseyWest3"}
											};
	public static int currentSpriteIndex = 0; //current frame we are on
	public static spriteInfo spriteChelsey = new spriteInfo(new Vector2D(610, 350), framesInUse[KeyProcessor.direction][currentSpriteIndex]);
	public static boolean currentlyMoving = false;
	
	//fields for other sprites
	public static spriteInfo spriteFloorL = new spriteInfo(new Vector2D(0, 0), "FloorLeft");
	public static spriteInfo spriteFloorR = new spriteInfo(new Vector2D(640, 0), "FloorRight");
	
	public static ArrayList<spriteInfo> spriteObjects = new ArrayList<>();
	public static Vector2D[] spriteObjectsVectors = {new Vector2D(0, 0), new Vector2D(640, 0), new Vector2D(0, 0), new Vector2D(1240, 0), new Vector2D(0, 546), new Vector2D(640, 546), new Vector2D(850, 40), new Vector2D(900, 120), new Vector2D(50, 40), new Vector2D(700, 180), new Vector2D(150, 400), new Vector2D(500, 90), new Vector2D(610, 200)};
	public static String[] spriteObjectsString = {"WallTop1", "WallTop2", "WallLeft", "WallRight", "WallBottom1", "WallBottom2", "GreenScreen", "Cat", "Tables", "Camera", "Crate1", "Crate2", "Chair"};
	
	//fields for dialogue
	public static HashMap<String, String> dialogue = new HashMap<>();
	public static String[] dialogueKey = new String[5];
	public static int currentDialogueKey = 0;
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		EZFileRead ezr = new EZFileRead("dialogueChelsey.txt");
		String key = "";
		String value = "";
		for(int j = 0; j < ezr.getNumLines(); j++){
			StringTokenizer tokenizer = new StringTokenizer(ezr.getNextLine(), "*");
			key = tokenizer.nextToken();
			value = tokenizer.nextToken();
			dialogue.put(key, value);
			dialogueKey[j] = key;
		}
	}
		
	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		addSprites(ctrl);
		
		ctrl.addSpriteToFrontBuffer(spriteChelsey.getCoords().getX(), spriteChelsey.getCoords().getY(), framesInUse[KeyProcessor.direction][currentSpriteIndex]);
		
		ctrl.drawString(100, 250, dialogue.get(dialogueKey[currentDialogueKey]), Color.WHITE);

		timerChecks();
		
	}
	
	// Additional Static methods below...(if needed)
	//checks timers
	private static void timerChecks(){
		while(timer.isTimeUp() && currentlyMoving){
			currentSpriteIndex++;
			if(currentSpriteIndex >= framesInUse[0].length - 1) currentSpriteIndex = 0;
			timer.resetWatch();
		}

		while(timerDialogue.isTimeUp()){
			currentDialogueKey++;
			if(currentDialogueKey >= dialogueKey.length) currentDialogueKey = 0;
			timerDialogue.resetWatch();
		}
	}
	
	//adds other sprites like bg objects
	private static void addSprites(Control ctrl){
		//floors and walls
		ctrl.addSpriteToFrontBuffer(spriteFloorL.getCoords().getX(), spriteFloorL.getCoords().getY(), spriteFloorL.getTag());
		ctrl.addSpriteToFrontBuffer(spriteFloorR.getCoords().getX(), spriteFloorR.getCoords().getY(), spriteFloorR.getTag());
		
		//objects
		for(int i = 0; i < spriteObjectsString.length; i++){
			spriteObjects.add(new spriteInfo(spriteObjectsVectors[i], spriteObjectsString[i]));
			ctrl.addSpriteToFrontBuffer(spriteObjects.get(i).getCoords().getX(), spriteObjects.get(i).getCoords().getY(), spriteObjects.get(i).getTag());
		}
	}
}
