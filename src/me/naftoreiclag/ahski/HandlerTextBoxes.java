package me.naftoreiclag.ahski;

public class HandlerTextBoxes
{

	private String myText = "null";
	
	public void drawBox(GraphicsArrayDraw gObject)
	{
		if(myText != "null")
		{
			String lines[] = new String[16];
			String stringInput = myText;
			
			for(int i = 0;i < 16;i++)
			{
				lines[i] = "null";
			}
			
			int numEntries = 0;
			String tempMem;
			
			stringInput += "\n";
			
			tempMem = "";
			for(int i = 0; i < stringInput.length(); i++)
			{
				if(stringInput.charAt(i) != '\n')
				{
					tempMem += stringInput.charAt(i);
				}
				else
				{
					if(numEntries < 16)
					{
						lines[numEntries] = tempMem;
						numEntries++;
						tempMem = "";
					}
					else
					{
						break;
					}
				}
			}
			
			int s = -(int)Math.ceil(numEntries / 2) + -1;
			int a = (int)Math.floor(numEntries / 2);
			
			if(numEntries % 2 == 1)
			{
				a ++;
			}
			
			gObject.drawRectangleFilledChar(5, 11 + s, 45, 13 + a,' ');
			gObject.drawRectangleThick(5, 11 + s, 45, 13 + a);
			
			for(int i = 0;i < numEntries;i ++)
			{
				gObject.drawString(lines[i], 7, 13 + s + i);
			}
			myText = "null";
		}
	}
	
	public void drawBox(GraphicsArrayDraw gObject, String text)
	{
		String lines[] = new String[16];
		String stringInput = text;
		
		for(int i = 0;i < 16;i++)
		{
			lines[i] = "null";
		}
		
		int numEntries = 0;
		String tempMem;
		
		stringInput += "\n";
		
		tempMem = "";
		for(int i = 0; i < stringInput.length(); i++)
		{
			if(stringInput.charAt(i) != '\n')
			{
				tempMem += stringInput.charAt(i);
			}
			else
			{
				if(numEntries < 16)
				{
					lines[numEntries] = tempMem;
					numEntries++;
					tempMem = "";
				}
				else
				{
					break;
				}
			}
		}
		
		int s = -(int)Math.ceil(numEntries / 2) + -1;
		int a = (int)Math.floor(numEntries / 2);
		
		if(numEntries % 2 == 1)
		{
			a ++;
		}
		
		gObject.drawRectangleFilledChar(5, 11 + s, 45, 13 + a,' ');
		gObject.drawRectangleThick(5, 11 + s, 45, 13 + a);
		
		for(int i = 0;i < numEntries;i ++)
		{
			gObject.drawString(lines[i], 7, 13 + s + i);
		}
		myText = "null";
	}

	public void addBox(String text)
	{
		myText = text;
	}
	
}
