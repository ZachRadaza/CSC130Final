package Main;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color darkGreen = new Color(0, 100, 50);
	public static stopWatchX timer = new stopWatchX(1);
	public static stopWatchX timerFrames = new stopWatchX(50);
	
	public static Queue<Vector2D> vecs1 = new LinkedList<>();
	public static Queue<Vector2D> vecs2 = new LinkedList<>();
	
	public static Vector2D currentVec = new Vector2D(-100, -100);
	public static boolean vecs1InUse = true;
	public static int frameUsingN = 0; //decides which frame to use, needs frameUsing Method
	public static String frameUsingHolder = "Ex0";
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		currentVec.setY(300);
		for(int n = currentVec.getX(); n < 1350; n += 10){
			vecs1.add(new Vector2D(n,300));
		}
	}
		
	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.addSpriteToFrontBuffer(1060, 570, "ChelseySouthBase");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)		
		
		if(timerFrames.isTimeUp()){
			frameUsingHolder = frameUsing();
			timerFrames.resetWatch();
		}
		
		ctrl.drawString(1000, 710, "Zachary Juls Aballe Radaza", darkGreen);		// Test drawing text on screen where you want (Remove later! Test only!)
		
		ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), frameUsingHolder);
		
		
		if(timer.isTimeUp() && vecs1InUse){
			currentVec.setX(vecs1.peek().getX());
			currentVec.setY(vecs1.peek().getY());
			vecs2.add(vecs1.remove());
			timer.resetWatch();
			if(vecs1.isEmpty()) vecs1InUse = false;
		}
		if(timer.isTimeUp() && !vecs1InUse){
			currentVec.setX(vecs2.peek().getX());
			currentVec.setY(vecs2.peek().getY());
			vecs1.add(vecs2.remove());
			timer.resetWatch();
			if(vecs2.isEmpty()) vecs1InUse = true;
		}
		
		
	}
	
	// Additional Static methods below...(if needed)
	//determine which frame to use
	public static String frameUsing(){
		frameUsingN++;
		if(frameUsingN == 6) frameUsingN = 1;
		switch(frameUsingN - 1){
			case 0:
				return "Ex0";
			case 1:
				return "Ex1";
			case 2:
				return "Ex2";
			case 3:
				return "Ex3";
			case 4:
				return "Ex2";
			case 5:
				return "Ex1";
			default:
				return "ChelseySouthBase";
		}
	}

}
