package me.naftoreiclag.ahski;

public class HandlerInventory
{
	private int inventory[] = new int[8];
	
	//returns if it fits
	public boolean addItem(int id)
	{
		for(int i = 0;i < inventory.length;i++)
		{
			if(inventory[i] == 0)
			{
				inventory[i] = id;
				return true;
			}
		}
		
		return false;
	}
	
	//returns if has item
	public boolean hasItem(int id)
	{

		for(int i = 0;i < inventory.length;i++)
		{
			if(inventory[i] == id)
			{
				return true;
			}
		}
		
		return false;
	}
	
	//remove item
	public boolean takeItem(int id)
	{
		for(int i = 0;i < inventory.length;i++)
		{
			if(inventory[i] == id)
			{
				inventory[i] = 0;
				return true;
			}
		}
		
		return false;
	}
	
	public void drawInventory(GraphicsArrayDraw gObject, int x, int y, FancyStrings fsMaker)
	{
		for(int i = 0;i < inventory.length;i++)
		{
			if(inventory[i] != 0)
			{
				gObject.drawString((i + 1) + ". " + fsMaker.itemIdToReadable(inventory[i]), x, y + i);
			}
			else
			{
				gObject.drawString((i + 1) + ". -", x, y + i);
			}
		}
	}
}
