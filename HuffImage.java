import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class HuffImage{
	static int[] pixels;
	static int[] uniqueRgb;
	static int[] temp;
	static int[] freq;
	static ArrayList<Integer> unqRgb = new ArrayList<>();
	static int size = 0, ctr = 0;
	static boolean flag;
	
	static int[][] data;
	static File file;
	
	public HuffImage(String string){
		render(string);
		checkFreq();
		size = unqRgb.size();
		
		System.out.print("\nNumber of PIXELS: " + pixels.length);
		System.out.print("\nNumber of UNIQUE RGB: " + uniqueRgb.length);
	}
	
	public void render(String string){
		BufferedImage image;
		String img = string;
		System.out.println(string);
		
		try{
			image = ImageIO.read(new File(img));
			
			pixels = new int[image.getHeight() * image.getWidth()];
			temp = new int[pixels.length];
			
			for(int i = 0; i < image.getHeight(); i++){
				for(int j = 0; j < image.getWidth(); j++){
					int rgb = image.getRGB(j,i);
					pixels[ctr++] = rgb;
					enqueue(rgb);
				}
			}
			System.out.print("UNIQUE RGB: " + unqRgb.size());
			
			uniqueRgb = new int[unqRgb.size()];
			for(int i = 0; i < unqRgb.size(); i++){
				uniqueRgb[i] = unqRgb.get(i).intValue();
			}
			
		}catch(IOException io){
		
		}catch(NullPointerException np){
		
		}catch(IllegalArgumentException ia){
			
		}
	}
	public static void enqueue(int rgb){
		if(!unqRgb.contains(rgb)){
			unqRgb.add(rgb);
		}
	}
	
	public static void checkFreq(){
		int ctr1 = 0, tmp, c;
		freq = new int[unqRgb.size()];
		System.out.println();
		for(int i = 0; i < unqRgb.size(); i++){
			for(int j = 0; j < pixels.length; j++){
				if(uniqueRgb[i] == pixels[j]){
					freq[i]++;
				}
			}
		}
		
		for(int i = 0; i < (freq.length)-1; i++){
			for(int j = i+1; j < freq.length; j++){
				if(freq[j] < freq[i]){
					tmp = uniqueRgb[j];
					c = freq[j];
					
					uniqueRgb[j] = uniqueRgb[i];
					freq[j] = freq[i];
					
					freq[i] = c;
					uniqueRgb[i] = tmp;
	
				}
			}
		}
		
		try{
			PrintWriter writer = new PrintWriter("sample.txt");
			for(int i = 0; i < uniqueRgb.length; i++){
				//System.out.println("FREQ[" + i + "]: " + freq[i] + "		UNIQUE RGB: " + uniqueRgb[i]);
				writer.println(uniqueRgb[i] + " " + freq[i]);
			}
			writer.close();
		}catch(IOException io){ }
		
	}
	
	public static void createTree(){
		flag = false;
		data = new int[2][uniqueRgb.length];
		int a = 0, b = 0;
		String str;
		
		try{
			file = new File("sample.txt");
			Scanner scan = new Scanner(file);
			
			while(scan.hasNext()){
				if(a % 2 == 0){
					scan.next();
				}else{
					str = scan.next();
					if(str != null){
						data[1][b++] = Integer.parseInt(str);
					}
				}
				a++;
			}
			
			Tree tree = new Tree(uniqueRgb, data[1], b, pixels);
			System.out.println("\nGenerated Huffman Tree: ");
			
			for(int i = 0; i < b-1; i++){
				tree.addNode();
			}
			tree.printBitString(flag);
			scan.close();
		}catch(Exception e){ 
			e.printStackTrace();
		}
	}
}