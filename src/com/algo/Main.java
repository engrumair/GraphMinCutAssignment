package com.algo;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	/**
	 * @param args
	 */
	static  long alocSeed = 4234249l;
	static int[] vertex_Merged = new int[200]; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<200; i++){
			vertex_Merged[i] =1;
			
		}
		
		/*
		In in= new In("G:/data/1.txt");
		
			int[] fileResults =in.readAllInts();
		
		
			
		
		//Random aRandomValue = new Random();
		
				
		g.setEdges_Array(1, new int[]{2,3});
		
		g.setEdges_Array(2, new int[]{1,3,4});
		
		g.setEdges_Array(3, new int[]{1,2,4});
		
		g.setEdges_Array(4, new int[]{3,2});
		*/
		
		SGraph g = new SGraph(200);  
		for(int i =1; i <=200; i++){
			
			String str="G:/data/"+ String.valueOf(i)+".txt";
			
			g.setEdges_Array(i,  new In(str).readAllInts());
			
			
		}
		
		
		
		
		// Get vertex Randomly
		while(totalVerticesInGraph() > 2){
			
		int vertexNum= GetNextInt();
		//System.out.println(vertexNum);
		// Randomnly choose the edge
		
		ArrayList<Integer> edgesArray = g.getEdges(vertexNum);
		int sizeOfSubArray = edgesArray.size();
		
		// Randomnly Select the subArray Element...
		int edgeNum = GetNextIntEdges(sizeOfSubArray) ;  //  new Random().nextInt(sizeOfSubArray);
		//System.out.println(edgeNum);
		//Element at subArray index
		int edgeElement = edgesArray.get(edgeNum);
			
		
		ContractEdgeInGraph(g, vertexNum, edgeNum, edgeElement );
		
		// Print Graph G
		
		//PrintGraph( g);
		
		// get the available vertex and count its total edges..
		
	
		//System.out.prinln()
	
		}
		
		int resultVertexIndex =0; 
		for(int m=0; m< vertex_Merged.length; m++){
			
			if(vertex_Merged[m]==1) 
				{
				resultVertexIndex = m; 
				
				
				break;
				}
			
		}
		System.out.print("Min Cut is: ");
		System.out.println(g.getEdges(resultVertexIndex+1).size());

	}
	public static void PrintGraph(SGraph g){
		
		System.out.println();
		
		for(int i=1; i <=vertex_Merged.length;i++  ){
			System.out.print(i); System.out.print(" | ");
			
			ArrayList<Integer> edges = g.getEdges(i);
			for(int k=0; k< edges.size();k++){
				System.out.print(edges.get(k)); System.out.print(" ");
				
			}
			System.out.println();
				
			
		}
		System.out.print("=======================================================================================");
	}
	
	public static void ContractEdgeInGraph(SGraph g, int v, int edgeNum, int EdgeElement){
		
		// 	remove the EdgeElement from 	v th Row .. We can even remove on the basis of edgeElement index
		g.RemoveEdge(v, EdgeElement);
		g.RemoveEdge(EdgeElement, v);
		
		// Union them now 
			// vth Row
			ArrayList<Integer> vthRow= g.getEdges(v);
			
			ArrayList<Integer> EdgeElementRow = g.getEdges(EdgeElement);
			
			for(int i =0; i<EdgeElementRow.size();i++){
				
				vthRow.add(EdgeElementRow.get(i));
			}
			
			g.setEdges(v, vthRow);
		
		
		// replace them (EdgeElment) now with v 
		
			// get the edge Row of the EdgeElment
		
		 	ArrayList<Integer> edgeArray=  g.getEdges(EdgeElement);
		 	
		 	for(int i =0; i< edgeArray.size();i++){
		 		int locElement = edgeArray.get(i);
		 		
		 		g.ReplaceElement(locElement, EdgeElement, v); // if 3 | 1 2 4 // here 1 is removed and 2,3 is replaced by 2,1
		 			
		 	}
		 	
		 	// now remove all elements inside the vTh element
		 	while(edgeArray.size() !=0) edgeArray.remove(0);
		 	
		 // Remove the self loop
		 //	ArrayList<Integer> allEdgesIndexV = g.getEdges(v);
		 //	int selfLoopRemoved = allEdgesIndexV.remove(v);
		 	g.RemoveEdge(v,v);
		 	
		 	// Mark the array for loop deletion..
		 		vertex_Merged[EdgeElement-1] =0;
		 		
		 	// Print Graph
		
		
	}
	public static void AddArrayToArrayList(ArrayList<Integer> row1, int[] arr){
		for(int i =0; i<arr.length;i++){
			row1.add(arr[i]);
			
		}
		
	}
	// Randomly Get next Vertex
	static int _temp1=0;
	static int[] array = {1,1};
	
	public static int GetNextInt(){
		
		//return array[_temp1++];
		
		if( totalVerticesInGraph() ==1){
			
			for(int i=0; i< vertex_Merged.length;i++){
				if(vertex_Merged[i] == 1) return i+1;
				
			}
			
		}
		int value=0;
		do{
			
		Random aRandomValue = new Random();
		//aRandomValue.setSeed(alocSeed);
		 value = aRandomValue.nextInt(vertex_Merged.length);
		
		} while(vertex_Merged[value] !=1);
		return value+1;
	}
	
	static int _temp2=0;
	static int[] array2 = {1,1};
	
	public static int GetNextIntEdges(int sizeOfEdges){
		
	//	return array2[_temp2++];
		
		Random aRandom = new Random();
		//aRandom.setSeed(alocSeed);
		return aRandom.nextInt(sizeOfEdges);
		
		
	}

	public static int totalVerticesInGraph(){
		
		int size =0;
		for(int i=0 ;i< vertex_Merged.length;i++){
			
			size = size + vertex_Merged[i];
		}
		return size;
	}	

}
