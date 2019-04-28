package skin2d;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Skin2d {
	PApplet p5;
	
	PShape shape;
	
	private ArrayList<SkinPivot> pivots;
	private ArrayList<SkinPoint> points;
	
	private int pivotIdCount = 0;
	private int selectedPivot, selectedPoint = 0; 
	
	public Skin2d(PApplet parent, PShape _shape) {
		p5 = parent;
		
		pivots = new ArrayList<SkinPivot>();
		points = new ArrayList<SkinPoint>();
		
		shape = _shape;
		skin(shape);
	}
	
	public void update(){
		
		for (int i = 0; i < points.size(); i++) {
			
			SkinPoint sPo = points.get(i);
			
			// IF NO PIVOT WAS MOVED, REMAIN AT YOUR POSE POSITION
			PVector finalPos = sPo.getPosePosition().copy();
					
			for (int j = 0; j < sPo.getPivotData().size(); j++) {
				
				SkinPivot sPi = sPo.getPivotData().get(j).pivot;
				
				PVector transformedPoint = PVector.sub(sPo.getPosePosition(), sPi.getPosePosition()); // TRANSLATE TO ORIGIN
				transformedPoint.rotate(sPi.getRotation()); // ROTATE
				transformedPoint.add(sPi.getPosition()); // TRANSLATE FOLLOWING PIVOT
				transformedPoint.add(sPi.getPosePosition()); // TRANSLATE BACK TO PIVOT SPACE
				
				// WEIGHTED INFLUENCE => ADD TO finalPos THE DELTA BETWEEN TRANSFORMED POINT AND POSE POINT, TIMES THE WEIGHT
				// FINAL += (transformedPoint - posePoint) * pivotWeight
				float pivotWeight = sPo.getPivotData().get(j).weight;
				finalPos.add( PVector.mult(PVector.sub(transformedPoint, sPo.getPosePosition() ),pivotWeight));
				//sPo.setPosition(transformedPoint);
				
				// THIS i WORKS CUZ sPO.Id == shape.setVertex(i)... SET AT create SkinPoint
				setShapeVertexPosition(i, finalPos); 
				
			}
		}
	}
	
	public void drawDebug(){
		drawPivots();
		drawPoints();
	}
	
	private void skin(PShape shape){
		// INITIALIZE SKIN
		
		// PIVOT CenterOfMass
		SkinPivot sPi = createPivot(new PVector(p5.width * 0.5f, p5.height * 0.5f));
		
		for (int i = 0; i < shape.getVertexCount(); i++) {
			SkinPoint sPo = createPoint(i, shape.getVertex(i).x, shape.getVertex(i).y);
			bind(sPo, sPi, i / 10.0f);
		}
	}
	
	private void setShapeVertexPosition(int i, PVector pos){
		shape.setVertex(i, pos);
	}
	
	public void bind(SkinPoint point, SkinPivot pivot, float weight){
		point.attachToPivot(pivot, weight);
	}
	
	public SkinPivot createPivot(PVector pos){
		SkinPivot newPivot = new SkinPivot(pos, pivotIdCount);
		pivots.add(newPivot);
		pivotIdCount++;
		return newPivot;
	}
	
	private SkinPoint createPoint(int _id , float _x, float _y){
		SkinPoint newPoint = new SkinPoint(_x, _y, _id);
		points.add(newPoint);
		//pointIdCount++;
		return newPoint;
	}
	
	public void setPivot(int _id, PVector pos){
		pivots.get(_id).getPosition().set(pos);
	}
	
	private void drawPivots(){
		// DRAWING TRANSFORMED POSITIONS
		p5.stroke(255,0,0);
		p5.noFill();
		
		for (SkinPivot pivot : pivots) {
			p5.pushMatrix();
			p5.translate(pivot.getPosition().x, pivot.getPosition().y); 
			p5.rotate(pivot.getRotation());
			
			p5.line(0, 0 - 20, 0, 0 + 8);
	        p5.line(0 - 10, 0, 0 + 10, 0);

			p5.popMatrix();
		}
		
		
	}
	
	private void drawPoints(){
		// DRAWING POSE POSITIONS
		
		p5.fill(0,127,255);
		p5.noStroke();
		
		for (SkinPoint point : points) {
			p5.ellipse(point.getPosePosition().x, point.getPosePosition().y, 5,5);
		}
	}
	

}
