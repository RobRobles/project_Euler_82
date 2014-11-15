import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class p82_Matrix {

	//auto filled to static, done know why 
	private static int[][] Matrix; 
	private static int infin; 
	private static Integer[][] minPath; 

	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {

		//this is for problem 82 
		File file = new File("matrix.txt");
		Scanner input = new Scanner(file);

		int row = 80; 
		int col = 80;
		int size = row*col; 
		int i = 0;

		//a very large number
		infin = Integer.MAX_VALUE / 2; 
		//Final Matrix
		Matrix = new int[row][col]; 
		//temp arry for getting the numbers in text file
		Integer[] temp = new Integer[size];
		//keeping track of the cost of the minPath
		minPath = new Integer[row][col]; 

		//putting the numbers from text file into array
		while(input.hasNext())
		{	
			temp[i] = input.nextInt();
			i++;
		}


		int a = 0;

		//putting the numbers from array into Matrix
		for(int b = 0; b < row; b++)
		{
			for(int c = 0; c < col; c++)
			{
				Matrix[b][c] = temp[a];
				a++;
			}
		}

		//printing to see if it works. 
		for(int b = 0; b < row; b++)
		{
			for(int c = 0; c < col; c++)
			{
				System.out.print(Matrix[b][c] + " ");

			}
			System.out.println("");
		}
		
		//moving left, down, and right and finding the minPath
		
		for(int x = 0; x < col; x++)
		{
			for(int y = 0; y < row; y++)
			{
				minPath[y][x] = Matrix[y][x] + Math.min(getMinValue(x - 1, y), getMinValue(x, y -1));
			}
			for(int s = col -1; s >= 0; s--)
			{
				minPath[s][x] = Math.min(Matrix[s][x] + getMinValue(x, s + 1), minPath[s][x]);
			}
		}
		
		//getting the minimum of the right most column, Backman class example : hope it works 
		int min = infin; 
		for(int n = 0; n < col; n++)
		{
			min = Math.min(minPath[n][col - 1], min);
		}
		System.out.println("I should be min: " + min);

		
		
	}

	private static int getMinValue(int i, int y) {
		// TODO Auto-generated method stub

		if(i < 0)
		{
			return 0; 
		}
		else if (y < 0 || y >= minPath.length || i >= minPath[y].length)
		{
			return infin;
		}
		else 
			return minPath[y][i];
	}
}
