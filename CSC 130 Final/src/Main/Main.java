package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;
import Data.spriteInfo;
import FileIO.EZFileRead;

public class Main{
	// Fields (Static) below...
	public static Color darkGreen = new Color(0, 100, 50);
	public static stopWatchX timer = new stopWatchX(50);
	public static stopWatchX timerDialogue = new stopWatchX(5000);
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static int currentSpriteIndex = 0;
	
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
		String[] frameInUse = {"ChelseyEast1", "ChelseyEast2", "ChelseyEast2", "ChelseyEast2", "ChelseyEast1", "ChelseyEast3", "ChelseyEast3", "ChelseyEast3"};
		int i = 0;
		
		for(int n = -100; n < 1350; n += 10){
			sprites.add(new spriteInfo(new Vector2D(n, 300), frameInUse[i]));
			i++;
			if(i == 8) i = 0;
		}
		
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
		ctrl.addSpriteToFrontBuffer(1060, 570, "ChelseySouth1");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)		

		ctrl.drawString(1000, 710, "Zachary Juls Radaza", darkGreen);		// Test drawing text on screen where you want (Remove later! Test only!)
		
		ctrl.addSpriteToFrontBuffer(sprites.get(currentSpriteIndex).getCoords().getX(), sprites.get(currentSpriteIndex).getCoords().getY(), sprites.get(currentSpriteIndex).getTag());
		
		ctrl.drawString(100, 250, dialogue.get(dialogueKey[currentDialogueKey]), Color.WHITE);

		while(timer.isTimeUp()){
			currentSpriteIndex++;
			if(currentSpriteIndex >= sprites.size() - 1) currentSpriteIndex = 0;
			timer.resetWatch();
		}

		while(timerDialogue.isTimeUp()){
			currentDialogueKey++;
			if(currentDialogueKey >= dialogueKey.length) currentDialogueKey = 0;
			timerDialogue.resetWatch();
		}
		
	}
	
	// Additional Static methods below...(if needed)

}
