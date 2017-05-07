public class Node{
	Node left;
	Node right;
	
	int freq;
	int rgb;
	
	boolean isLeaf;
		
	public Node(Node left, Node right, int freq, int rgb){
		this.left = left;
		this.right = right; 
		this.freq = freq;
		this.rgb = rgb;
	}
	
	public int getFrequency(){
		return freq;
	}
	
	public int getRgb(){
		return rgb;
	}
	
	public boolean isLeaf(){
		if(left == null && right == null){
			return true;
		}else{
			return false;
		}
	}
}