package com.dave.day8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImageValidator {
	public static void main(String[] args) throws IOException {
		String fileName = "./Day_8/image.txt";
		FileReader fileReader = new FileReader(fileName);
	
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String pixelLine = bufferedReader.readLine();
	    bufferedReader.close();
	    ArrayList<Integer[][]> layers = new ArrayList<>();
	    ArrayList<Integer> zeroCounter = new ArrayList<>();
	    int layerSize = 25 * 6;
	    int layerNum = 0;
	    while((layerNum + 1) * layerSize <= pixelLine.length()) {
	    	Integer[][] nextLayer = new Integer[6][25];
	    	Integer numZeros = 0;
	    	Integer startingPoint = layerNum * layerSize;
	    	for(int i = 0; i < 6; i++) {
	    		for(int j = 0; j < 25; j++) {
	    			Integer pixel = Integer.parseInt(pixelLine.substring(startingPoint + i * 25 + j, startingPoint + i * 25 + j + 1));
	    			if(pixel == 0) {
	    				numZeros++;
	    			}
	    			nextLayer[i][j] = pixel;
	    		}
	    	}
	    	zeroCounter.add(numZeros);
	    	layers.add(nextLayer);
	    	layerNum++;
	    }
	    //Part 1
//	    //Get layer with min zeros.
//	    Integer minZeros = null;
//	    Integer minIndex = null;
//	    for(int k = 0; k < zeroCounter.size(); k++) {
//	    	if(minZeros == null) {
//	    		minZeros = zeroCounter.get(k);
//	    		minIndex = k;
//	    	} else if (zeroCounter.get(k) < minZeros) {
//	    		minZeros = zeroCounter.get(k);
//	    		minIndex = k;
//	    	}
//	    }
//	    //Count 1s and 2s
//	    Integer[][] lastLayer = layers.get(minIndex);
//	    Integer numOnes = 0;
//	    Integer numTwos = 0;
//	    for(int i = 0; i < 6; i++) {
//			for(int j = 0; j < 25; j++) {
//				Integer pixel = lastLayer[i][j];
//				if(pixel == 1) {
//					numOnes++;
//				} else if (pixel == 2) {
//					numTwos++;
//				}
//			}
//		}
//	    System.out.println("Ones * Twos = " + numOnes * numTwos);
	    //Part 2
	    Integer[][] decodedImage = new Integer[6][25];
	    for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 25; j++) {
				int index = 0;
				while(layers.get(index)[i][j] == 2) {
					index++;
				}
				decodedImage[i][j] = layers.get(index)[i][j];
			}
		}
	    //print out array
	    for(int x = 0; x < 6; x++) {
	    	for (int y = 0; y < 25; y++) {
	    		System.out.print(decodedImage[x][y]);
	    	}
	    	System.out.println();
	    }
	}
}
