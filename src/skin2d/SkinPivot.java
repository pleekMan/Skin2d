package skin2d;

import processing.core.PVector;

public class SkinPivot {
	
	private int pivotId;
	private PVector posePosition, position;
	private float rotation;
	
	public SkinPivot(PVector pos, int _id) {
		posePosition = pos;
		position = pos.copy();
		rotation = 0;
		pivotId = _id;
		
	}

	public int getId(){return pivotId;}
	public PVector getPosition(){return position;}
	public PVector getPosePosition(){return posePosition;}
	public float getRotation(){return rotation;}
	
	
}
