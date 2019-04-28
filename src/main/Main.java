package main;

import processing.core.*;
import skin2d.Skin2d;

public class Main extends PApplet {

	Skin2d skin;
	PShape circleShape;

	public void settings() {
		size(500, 500, P2D);
	}

	public void setup() {

		
		
		createCircle();
		
		skin = new Skin2d(this, circleShape);

	}

	public void draw() {
		background(0);
		
		skin.setPivot(0, new PVector(mouseX, mouseY));
		skin.update();
		
		//circleShape.setVertex(0, mouseX, mouseY);
		shape(circleShape);
		
		skin.drawDebug();
		
		drawMouseCoordinates();

	}
	
	//----------------------------
	
	public void createCircle(){
		
	    PVector center = new PVector(width * 0.5f, height * 0.5f);
	    int res = 10;
	    float rad = 200;
	    
	    //circleShape = createShape(ELLIPSE, center.x, center.y,200,200);
	    circleShape = createShape();
	    circleShape.setFill(color(random(255)));
	    
	    circleShape.beginShape();
	    for (int i=0; i<res; i++) {
	        float x = rad * (cos((TWO_PI / res) * i)) + center.x;
	        float y = rad * (sin((TWO_PI / res) * i)) + center.y;
	        
	        circleShape.vertex(x,y);
	    }
	    circleShape.endShape(CLOSE);
	    
		
	}

	private void drawMouseCoordinates() {
		// MOUSE POSITION
		fill(255, 0, 0);
		text("FR: " + frameRate, 20, 20);
		text("X: " + mouseX + " / Y: " + mouseY, mouseX, mouseY);
	}

	public void keyPressed() {

		// AFTER FORWARDING ALL KEYS, OVERRIDE ESCAPE (USED TO CANCEL SOME PROCEDURES) TO NOT EXIT DE APP
		/*
		if (key == ESC) {
			key = 0;
		}
		*/
	}

	public void mousePressed() {
	}

	public void mouseReleased() {
	}

	public void mouseClicked() {
	}

	public void mouseDragged() {

	}

	public void mouseMoved() {

	}

	public static void main(String args[]) {
		/*
		 * if (args.length > 0) { String memorySize = args[0]; }
		 */

		PApplet.main(new String[] { Main.class.getName() });
		// PApplet.main(new String[] {
		// "--present","--hide-stop",Main.class.getName() }); //
		// PRESENT MODE
	}

}
