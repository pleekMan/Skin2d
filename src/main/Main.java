package main;

import processing.core.*;
import skin2d.Skin2D;
import skin2d.SkinShape;

public class Main extends PApplet {

	Skin2D skin2d;
	PShape circleShape;

	public void settings() {
		size(800, 800, P2D);
	}

	public void setup() {

		frameRate(30);
		
		
		createCircle();
		
		skin2d = new Skin2D(this);
		skin2d.addSkinShape(circleShape, "circulo");

	}

	public void draw() {
		background(0);
		
		skin2d.update();
		
		SkinShape circulo = skin2d.getSkinShape(0);
		
		PVector pivotMotion = new PVector(width * 0.5f, (sin(frameCount * 0.05f) * 100) + (height * 0.5f));
		circulo.setPivot(0, pivotMotion);
		
		shape(circleShape);
		
		skin2d.drawDebug();
		
		drawMouseCoordinates();

	}
	
	//----------------------------
	
	public void createCircle(){
		
	    PVector center = new PVector(width * 0.5f, height * 0.5f);
	    int res = 10;
	    float rad = 200;
	    
	    //circleShape = createShape(ELLIPSE, center.x, center.y,200,200);
	    circleShape = createShape();
	    circleShape.setFill(color(0,200,150));
	    
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
		
		if(key == 'p' || key == 'P'){
			SkinShape circulo = skin2d.getSkinShape(0);
			circulo.addPivot(new PVector(mouseX,mouseY));
		}
		
		if(key == 'c' || key == 'C'){
			SkinShape circulo = skin2d.getSkinShape(0);
			circulo.clearBindings();
		}
		
		if(key == 'd' || key == 'D'){
			SkinShape circulo = skin2d.getSkinShape(0);
			circulo.bindByDistance(300);
		}
		
		if(key == 's' || key == 'S'){
			skin2d.setMode(Skin2D.EditMode.SELECT_ELEMENTS);
		}
	}

	public void mousePressed() {
		
	

	}

	public void mouseReleased() {
	}

	public void mouseClicked() {
	}

	public void mouseDragged() {
		println("-|| DRAGGING MOUSE");
		//if (button == 2) {
			SkinShape circulo = skin2d.getSkinShape(0);
			circulo.setPivot(circulo.getSelectedPivotID(), new PVector(mouseX, mouseY) );
		//}
		
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
