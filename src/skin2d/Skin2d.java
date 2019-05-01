package skin2d;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PShape;
import processing.event.MouseEvent;
import processing.event.KeyEvent;

public class Skin2D {
	PApplet p5;

	ArrayList<SkinShape> skinShapes;
	int selectedShape = 0;

	private enum mode {
		ADD_PIVOT, EDIT_WEIGHTS
	}; // TODO FOR LATER

	public Skin2D(PApplet parent) {
		p5 = parent;
		skinShapes = new ArrayList<SkinShape>();

		p5.registerMethod("mouseEvent", this);
		p5.registerMethod("keyEvent", this);

	}

	public void addSkinShape(PShape shape, String name) {
		// TODO REMEMBER THE NAME OF THE SHAPE
		SkinShape newSkinShape = new SkinShape(p5, shape);
		skinShapes.add(newSkinShape);
	}

	public void update() {
		for (SkinShape sShape : skinShapes) {
			sShape.update();
		}
	}

	public void drawDebug() {
		for (SkinShape sShape : skinShapes) {
			sShape.drawDebug();
		}
	}

	public SkinShape getSkinShape(int i) {
		return skinShapes.get(i);
	}

	public SkinShape getSkinShape(String name) {
		// TODO GET BY NAME
		return skinShapes.get(0);
	}

	public void mouseEvent(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		int button = event.getButton();

		switch (event.getAction()) {
		case MouseEvent.PRESS:
				p5.println("Mouse Pressed");
			break;
		case MouseEvent.RELEASE:
			// do something for mouse released
			break;
		case MouseEvent.CLICK:
			// do something for mouse clicked
			break;
		case MouseEvent.DRAG:
			// do something for mouse dragged
			break;
		case MouseEvent.MOVE:
			// do something for mouse moved
			break;
		}
	}
	
	public void keyEvent(KeyEvent event){
		
		char key = event.getKey();
		
		// TODO IF IN DEBUG MODE ONLY
		
		  switch (event.getAction()) {
		    case KeyEvent.PRESS:
				p5.println("-| KeyPressed -> " + key);
				
				skinShapes.get(selectedShape).onKeyPressed(key);
				
				if(event.getKeyCode() == p5.LEFT){
					
				}
				
		      break;
		    case KeyEvent.RELEASE:
				p5.println("-| KeyReleased -> " + key);
		      break;

		  }
	}

}
