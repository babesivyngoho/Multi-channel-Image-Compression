import java.io.*;

public class Tree{
	private Node root;
	Node[] nodes;
	Node temp;
		
	String codedBitString = "";
	String[] codedBit;
	
	int[] leaves;
	static int[] pix;
	static int[] freq;
	String[] chunks;
	int size, ctr;
	//int[][] data = new int[2][26];
	
	public Tree(int[] rgb, int[] c, int size, int[] pixels){
		Tree.pix = pixels;
		Tree.freq = c;
		this.size = size;
		nodes = new Node[size];

		for(int i = 0; i < size; i++){
			nodes[i] = new Node(null, null, c[i], rgb[i]);
		}
	}
	
	public void addNode(){
		int freq = nodes[0].getFrequency() + nodes[1].getFrequency();
		root = new Node(nodes[0], nodes[1], freq, 0);
		nodes[0] = root;
		
		for(int a = 2; a < size; a++){
			nodes[a-1] = nodes[a];
		}
		
		if(size > 1){
			nodes[size-1] = null; 
		}
		
		for(int i = 0; i < size-1; i++){
			for(int j = i+1; j < size; j++){
				if(nodes[j] != null && nodes[i].getFrequency() > nodes[j].getFrequency()){
					temp = nodes[i];
					nodes[i] = nodes[j];
					nodes[j] = temp;
				}
			}
		}
	}
	
	public String getBitCode(){
		Node currentNode = nodes[0];
		Node temp = null;
		String bitCode = "";
		
		while(!currentNode.isLeaf()){
			temp = currentNode;
			if(currentNode.left.isLeaf == false && currentNode.right.isLeaf == false){
				currentNode = currentNode.left;
				bitCode += "0";
			}else{
				if(currentNode.left.isLeaf == true && currentNode.right.isLeaf == true){
					currentNode.isLeaf = true;
					currentNode = nodes[0];
					bitCode = "";
				}else{
					currentNode = currentNode.right;
					bitCode += "1";
				}
			}
		}
		
		currentNode.isLeaf = true;
		leaves[ctr++] = currentNode.getRgb();
		
		if(temp.left.isLeaf == true && temp.right.isLeaf == true){
			temp.isLeaf = true;
		}
		
		return bitCode;
	}
	
	public String getBitString(){
		String bitString = "";
		
		for(int i = 0; i < pix.length; i++){
			for(int j = 0; j < size; j++){
				if(pix[i] == leaves[j]){
					bitString = bitString + codedBit[j];
				}
			}
		}
		return bitString;
	}
	
	public void printBitString(boolean checkFile){
		String codedBitString = "";
		if(!checkFile){
			codedBit = new String[size];
			leaves = new int[size];
			
			//System.out.println("memememe");
			System.out.println("	 RGB 		|	Bit Code");
			System.out.println("	     		|	");
			for(int i = 0; i < size; i++){
				codedBit[i] = getBitCode();
				System.out.println("    " + leaves[i] + "   	|	" + codedBit[i]);
			}
			//System.out.println("\nGenerated Coded Bit String: " + getBitString());
			codedBitString = getBitString();
			breakToChunks(codedBitString);
		}
	}
	
	public void breakToChunks(String chunkMsg){
		int ctr = 0, ctr2 = 31;
		String code = "";
		
		try{
			
			DataOutputStream output = new DataOutputStream(new FileOutputStream("HuffFile.txt"));
			//PrintWriter writer = new PrintWriter("huff.txt");
			
			for(int i = 0; i < chunkMsg.length()/31; i++){	
				while(ctr < ctr2){
					code += chunkMsg.charAt(ctr);
					ctr++;
				}
				ctr = ctr2;
				ctr2 += 31;
				
				output.writeInt(Integer.parseInt(code, 2));
				code = "";
			}
			output.close();
		}catch(IOException io){ }
	}
}