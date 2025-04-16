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
import Data.CollisionBuffer;
import Data.CollisionArea;

public class Main{
	// Fields (Static) below...
	public static Color darkGreen = new Color(0, 100, 50);
	public static stopWatchX timer = new stopWatchX(68);
	
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
	public static CollisionBuffer collisionBufferChelsey= new CollisionBuffer(spriteChelsey, 128, 128);
	
	//fields for other sprites
	public static spriteInfo spriteFloorL = new spriteInfo(new Vector2D(0, 0), "FloorLeft");
	public static spriteInfo spriteFloorR = new spriteInfo(new Vector2D(640, 0), "FloorRight");
	
	public static ArrayList<spriteInfo> spriteObjects = new ArrayList<>();
	public static ArrayList<CollisionBuffer> spriteBuffers = new ArrayList<>();
	public static Vector2D[] spriteObjectsVectors = {new Vector2D(0, 0), new Vector2D(640, 0), new Vector2D(0, 0), new Vector2D(1240, 0), new Vector2D(0, 546), new Vector2D(640, 546), new Vector2D(850, 40), new Vector2D(960, 120), new Vector2D(50, 40), new Vector2D(700, 180), new Vector2D(150, 400), new Vector2D(500, 90), new Vector2D(610, 200)};
	public static String[] spriteObjectsString = {"WallTop1", "WallTop2", "WallLeft", "WallRight", "WallBottom1", "WallBottom2", "GreenScreen", "Cat", "Tables", "Camera", "Crate1", "Crate2", "Chair"};
	//height and widths of objects in order
	public static int[][] widthHeight = {{640, 174}, {640, 174}, {40, 720}, {40, 720}, {640, 174}, {640, 174}, {384, 384}, {128, 128}, {300, 158}, {67, 106}, {122, 106}, {102, 107}, {65, 85}};
	
	//fields for dialogue
	public static HashMap<String, String> dialogue = new HashMap<>();
	public static String[] dialogueKey = new String[5];
	public static int currentDialogueKey = 0;
	public static boolean dialogueVisible = false;
	
	//fields for interaction
	public static String interact = "";
	public static boolean canInteract = false;
	public static ArrayList<CollisionArea> spriteInteracts = new ArrayList<>();
	public static int[] interactableObjects = {6, 10, 6, 8, 12}; //uses index from sprite objects string
	public static int currentLevel = 0;
	
	//fields for physics
	public static boolean canMove = true; //if Chelsey can move in that direction or not
	
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
		
		//initiallize spriteInfo, Collision Buffer, interactable objects
		for(int i = 0; i < spriteObjectsString.length; i++){
			spriteObjects.add(new spriteInfo(spriteObjectsVectors[i], spriteObjectsString[i]));
			spriteBuffers.add(new CollisionBuffer(spriteObjects.get(i), widthHeight[i][0], widthHeight[i][1]));
		}
		
		for(int i = 0; i < interactableObjects.length; i++){
			spriteInteracts.add(new CollisionArea(spriteObjects.get(interactableObjects[i]), widthHeight[interactableObjects[i]][0], widthHeight[interactableObjects[i]][1], 40, 10, dialogue.get(dialogueKey[i]), i));
		}
	}
		
	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		addSprites(ctrl);
		
		ctrl.addSpriteToFrontBuffer(spriteChelsey.getCoords().getX(), spriteChelsey.getCoords().getY(), framesInUse[KeyProcessor.direction][currentSpriteIndex]);
		
		if(dialogueVisible && (currentLevel < spriteInteracts.size())) {
			ctrl.drawString(150, 650, spriteInteracts.get(currentLevel).getOnScreenText(), Color.WHITE);
		}

		ctrl.drawString(525, 520, interact, Color.WHITE);
		
		ctrl.drawString(20, 700, "Level " + (currentLevel + 1), Color.WHITE);
		
		timerChecks();
		
		setCanMove();
		
		setCanInteract();
	}
	
	// Additional Static methods below...(if needed)
	//checks timers
	private static void timerChecks(){
		while(timer.isTimeUp() && currentlyMoving){
			currentSpriteIndex++;
			if(currentSpriteIndex >= framesInUse[0].length - 1) currentSpriteIndex = 0;
			timer.resetWatch();
		}

	}
	
	//adds other sprites like bg objects
	private static void addSprites(Control ctrl){
		//floors
		ctrl.addSpriteToFrontBuffer(spriteFloorL.getCoords().getX(), spriteFloorL.getCoords().getY(), spriteFloorL.getTag());
		ctrl.addSpriteToFrontBuffer(spriteFloorR.getCoords().getX(), spriteFloorR.getCoords().getY(), spriteFloorR.getTag());
		
		//objects with collision
		for(int i = 0; i < spriteObjectsString.length; i++){
			ctrl.addSpriteToFrontBuffer(spriteObjects.get(i).getCoords().getX(), spriteObjects.get(i).getCoords().getY(), spriteObjects.get(i).getTag());
		}
	}
	
	//checks if then can move through the object or not, if not, bounces them back depending on last input
	private static void setCanMove(){
		
		for(int i = 0; i < spriteBuffers.size(); i++){
			if(collisionBufferChelsey.collisionDetection(spriteChelsey, spriteBuffers.get(i))){
				canMove = false;
				break;
			} else {
				canMove = true;
				
			}
		}
	}
	
	private static void setCanInteract(){
		if(spriteInteracts.size() - 1 > currentLevel){
			if(spriteInteracts.get(currentLevel).collisionDetection(spriteInteracts.get(currentLevel).getSprite(), collisionBufferChelsey)){
				interact = "Press SpaceBar to Interact";
				spriteInteracts.get(currentLevel).setCanInteract(true);
			} else{
				interact = "";
				spriteInteracts.get(currentLevel).setCanInteract(false);
			}
		} else {
			interact = "";
		}
	}
	
	
}
