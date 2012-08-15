package me.naftoreiclag.ahski;

//version 6
public class GraphicsArrayDraw {
	
	//new gArray
	private char gArray[][] = new char[79][24];
	
	//the letters correspond to positions on this chart:
	//QWE
	//ASD
	//ZXC
	
	//single-thick
	private char charBoxW = 196;
	private char charBoxA = 179;
	private char charBoxX = 196;//Obsolete
	private char charBoxD = 179;//Obsolete
	private char charBoxQ = 218;
	private char charBoxZ = 192;
	private char charBoxE = 191;
	private char charBoxC = 217;
	//X intersection
	private char charBoxI = 197;
	
	//bold
	private char charFill = 219;
	
	//double-thick
	private char charBox2W = 205;
	private char charBox2A = 186;
	private char charBox2X = 205;//Obsolete
	private char charBox2D = 186;//Obsolete
	private char charBox2Q = 201;
	private char charBox2Z = 200;
	private char charBox2E = 187;
	private char charBox2C = 188;
	
	//constructor
	public GraphicsArrayDraw()
	{
		initializeGArray();
	}
	
	//Fill array with blank stuff
	public void initializeGArray()
	{
		for(int x = 0;x < 79;x += 1)
		{
			for(int y = 0;y < 24;y += 1)
			{
				gArray[x][y] = ' ';
			}
		}
	}
	
	//clears array
	public void clearGArray()
	{
		//alias
		initializeGArray();
	}
	
	//draw rectangle with single-thick lines; auto-creates X-intersections
	public void drawRectangle(int ax,int ay, int bx, int by)
	{
		//top & bottom
		for(int i = ax;i <= bx;i++)
		{
			//("\\u " + Integer.toHexString('─' | 0x1000000)).charAt(0);
			//W
			if(getGArrayUnit(i,ay) == charBoxA)
			{
				setGArrayUnit(charBoxI, i, ay);
			}
			else if(getGArrayUnit(i,ay) == charBoxD)
			{
				setGArrayUnit(charBoxI, i, ay);
			}
			else if(getGArrayUnit(i,ay) == charBoxI)
			{
				setGArrayUnit(charBoxI, i, ay);
			}
			else
			{
				setGArrayUnit(charBoxW, i, ay);
			}
			//X
			if(getGArrayUnit(i,by) == charBoxA)
			{
				setGArrayUnit(charBoxI, i, by);
			}
			else if(getGArrayUnit(i,by) == charBoxD)
			{
				setGArrayUnit(charBoxI, i, by);
			}
			else if(getGArrayUnit(i,by) == charBoxI)
			{
				setGArrayUnit(charBoxI, i, by);
			}
			else
			{
				setGArrayUnit(charBoxX, i, by);
			}
		}
		//left & right
		for(int i = ay;i <= by;i++)
		{
			//A
			if(getGArrayUnit(ax, i) == charBoxW)
			{
				setGArrayUnit(charBoxI, ax, i);
			}
			else if(getGArrayUnit(ax, i) == charBoxX)
			{
				setGArrayUnit(charBoxI, ax, i);
			}
			else if(getGArrayUnit(ax, i) == charBoxI)
			{
				setGArrayUnit(charBoxI, ax, i);
			}
			else
			{
				setGArrayUnit(charBoxA, ax, i);
			}
			//D
			if(getGArrayUnit(bx, i) == charBoxW)
			{
				setGArrayUnit(charBoxI, bx, i);
			}
			else if(getGArrayUnit(bx, i) == charBoxX)
			{
				setGArrayUnit(charBoxI, bx, i);
			}
			else if(getGArrayUnit(bx, i) == charBoxI)
			{
				setGArrayUnit(charBoxI, bx, i);
			}
			else
			{
				setGArrayUnit(charBoxD, bx, i);
			}
		}
		
		setGArrayUnit(charBoxQ, ax, ay);
		setGArrayUnit(charBoxZ, ax, by);
		setGArrayUnit(charBoxE, bx, ay);
		setGArrayUnit(charBoxC, bx, by);
	}
	
	//draw rectangle with double-thick lines
	public void drawRectangleThick(int ax,int ay, int bx, int by)
	{
		//top & bottom
		for(int i = ax;i <= bx;i++)
		{
			//("\\u " + Integer.toHexString('─' | 0x1000000)).charAt(0);
			setGArrayUnit(charBox2W, i, ay);
			setGArrayUnit(charBox2X, i, by);
		}
		//left & right
		for(int i = ay;i <= by;i++)
		{
			setGArrayUnit(charBox2A, ax, i);
			setGArrayUnit(charBox2D, bx, i);
		}
		//corners
		setGArrayUnit(charBox2Q, ax, ay);
		setGArrayUnit(charBox2Z, ax, by);
		setGArrayUnit(charBox2E, bx, ay);
		setGArrayUnit(charBox2C, bx, by);
	}
	
	//draw rectangle with bold lines; no intersection interpretation necessary
	public void drawRectangleBold(int ax,int ay, int bx, int by)
	{
		//top & bottom
		for(int i = ax;i <= bx;i++)
		{
			//("\\u " + Integer.toHexString('─' | 0x1000000)).charAt(0);
			setGArrayUnit(charFill, i, ay);
			setGArrayUnit(charFill, i, by);
		}
		//left & right
		for(int i = ay;i <= by;i++)
		{
			setGArrayUnit(charFill, ax, i);
			setGArrayUnit(charFill, bx, i);
		}
	}
	
	//draw rectangle filled in
	public void drawRectangleFilled(int ax,int ay, int bx, int by)
	{
		for(int i = ax;i <= bx;i++)
		{
			for(int j = ay;j <= by;j++)
			{
				setGArrayUnit(charFill, i, j);
				setGArrayUnit(charFill, i, j);
			}
		}
		
	}
	
	//draw rectangle filled in with given char
	public void drawRectangleFilledChar(int ax,int ay, int bx, int by, char c)
	{
		for(int i = ax;i <= bx;i++)
		{
			for(int j = ay;j <= by;j++)
			{
				setGArrayUnit(c, i, j);
				setGArrayUnit(c, i, j);
			}
		}
		
	}
	
	//draw string at x,y
	public void drawString(String text, int x, int y)
	{
		for(int i = 0;i < text.length();i++)
		{
			setGArrayUnit(text.charAt(i),x + i,y);
		}
	}
	
	//draw string at x,y; do not add spaces;
	public void drawStringIgnoreSpace(String text, int x, int y)
	{
		for(int i = 0;i < text.length();i++)
		{
			if(text.charAt(i) != ' ')
			{
				setGArrayUnit(text.charAt(i),x + i,y);
			}
		}
	}
	
	//draw char at x,y
	public void drawChar(char newCharacter, int x, int y)
	{
		//alias
		setGArrayUnit(newCharacter, x, y);
	}
	
	//set unit at x,y
	public void setGArrayUnit(char newCharacter, int x, int y)
	{
		//checks if the given values are legal
		if((x <= 78 && y <= 23) && (x >= 0 && y >= 0))
		{
			//sets character at x,y to newCharacter
			gArray[x][y] = newCharacter;
		}
	}
	
	//returns the char at x,y
	public char getGArrayUnit(int x, int y)
	{
		return gArray[x][y];
	}
	
	//prints gArray onto screen
	public void printGArray()
	{
		//char nullChar = 0;
		String printText = "";
		for(int y = 0;y < 24;y += 1)
		{
			for(int x = 0;x < 79;x += 1)
			{
				printText = printText + gArray[x][y];
			}
			if(y < 23)
			{
				printText = printText + '\n';
			}
		}
		
		printText += '\n';
		
		//print out that
		System.out.print(printText);
	}
}
