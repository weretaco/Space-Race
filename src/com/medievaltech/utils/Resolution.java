package com.medievaltech.utils;

public class Resolution
{
	private int actualX, actualY;
	private final int[] NUMERATORS = {3,4,5,5,8,16,17};
	private final int[] DENAMENATORS = {2,3,3,4,5,9,9};
	private int largestMultiple;
	
	public Resolution(int dimX, int dimY) {
		this.actualX = dimX;
		this.actualY = dimY;
		this.largestMultiple = 0;
		calculateAssumedDimensions();
	}
	
	public int getLargestMultiple() {
		return this.largestMultiple;
	}
	
	private void calculateAssumedDimensions() {
		int largeDim = this.actualX;
		int smallDim = this.actualY;
		
		if(this.actualY > largeDim){
			largeDim = this.actualY;
			smallDim = this.actualX;
		}
		
		//Assume the larger dimension is the normal one
		int smallestDifference = 10000000;
		for(int i = 0; i < 7; i++){
			if(largeDim % NUMERATORS[i] == 0 && smallestDifference > Math.abs(smallDim - largeDim/NUMERATORS[i]*DENAMENATORS[i])) {
				smallestDifference = Math.abs(smallDim - largeDim/NUMERATORS[i]*DENAMENATORS[i]);
				largestMultiple = largeDim / NUMERATORS[i];
			}
		}
	}
}
