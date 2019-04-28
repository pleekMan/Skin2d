package skin2d;

import java.util.ArrayList;
import processing.core.PVector;

class PivotData{
	SkinPivot pivot;
	float weight;
}
// KINDA LIKE A STRUCT

public class SkinPoint {
	
	private PVector posePosition;//, linkedVertex;
	private int pointId;
		
	private ArrayList<PivotData> pivots;
	
	public SkinPoint(float _x, float _y, int _id) {
		
		setPosePosition( new PVector(_x, _y) );
		//linkedVertex = _linkedVertex; // POINTER
		setId(_id);
		
		pivots = new ArrayList<PivotData>();
				
	}
	
	private void setPosePosition(PVector poseVertex){
		posePosition = poseVertex;
	}
	
	private void setId(int _id){
		pointId = _id;
	}
	
	public void attachToPivot(SkinPivot pivot, float weight){
		
		PivotData piDat = isAlreadyAttached(pivot);
		
		if (piDat != null) {
			updatePivot(piDat, weight);
		} else {
			addPivot(pivot, weight);
		}
		
	}
	
	private void addPivot(SkinPivot pivot, float weight){
		PivotData newPiDat = new PivotData();
		newPiDat.pivot = pivot;
		newPiDat.weight = weight;
		pivots.add(newPiDat);
	}
	
	private void updatePivot(PivotData pivot, float weight){
		pivot.weight = weight;
	}
	
	private PivotData isAlreadyAttached(SkinPivot pivot){
		for (int i = 0; i < pivots.size(); i++) {
			if (pivots.get(i).pivot.getId() == pivot.getId()) {
				return pivots.get(i);
			}
		}
		return null;
		
	}
	
	/*
	public void setPosition(PVector newPos){
		linkedVertex.set(newPos);
	}
	*/
	
	public PVector getPosePosition(){return posePosition;}
	//public PVector getPosition(){return linkedVertex;}
	public ArrayList<PivotData> getPivotData(){return pivots;}
	
	
}
