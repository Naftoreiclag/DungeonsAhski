package me.naftoreiclag.ahski;

public class HandlerTextNags
{
	private int maxSize = 6;
	
	private String[] promptLines = new String[maxSize];
	
	//constructor
	public HandlerTextNags()
	{
		this.clear();
	}
	
	//clear prompt
	public void clear()
	{
		//fill up with something other than null
		for(int i = 0;i < maxSize;i += 1)
		{
			promptLines[i] = "";
		}
	}
	
	//clear prompt
	public void fillWith(String fill)
	{
		//fill up with something other than null
		for(int i = 0;i < maxSize;i += 1)
		{
			promptLines[i] = fill;
		}
	}
	
	//add line to prompt
	public void addLine(String addition)
	{
		this.shiftDown();
		promptLines[maxSize - 1] = addition;
	}
	
	//shifts all values down
	private void shiftDown()
	{
		//0 is deleted
		//1 overwrites 0
		//2 overwrites 1
		//3 overwrites 2
		//...
		for(int i = 0;i < maxSize - 1;i += 1)
		{
			promptLines[i] = promptLines[i + 1];
		}
	}
	
	//draw to gObject
	public void drawSelf(GraphicsArrayDraw gObject, int x, int y)
	{
		for(int i = 1;i < maxSize;i += 1)
		{
			gObject.drawString(promptLines[i], x, y + i);
		}
	}
}
