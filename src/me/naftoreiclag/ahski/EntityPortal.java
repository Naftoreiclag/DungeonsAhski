package me.naftoreiclag.ahski;

//id = 5

public class EntityPortal extends Entity
{
	private char charBricks = 177;
	
	//Initialization
	public EntityPortal()
	{
		this.setEntityId(5);
		this.setSkin('?');
		this.setType(3);
		this.setHealth(9001);
	}
	
	//special draw function
	public void eventDraw(GraphicsArrayDraw gObject, int xOff, int yOff)
	{
		gObject.drawChar(charBricks, this.getX() + xOff + -1, this.getY() + yOff);
		gObject.drawChar(charBricks, this.getX() + xOff + 1, this.getY() + yOff);
		gObject.drawChar(charBricks, this.getX() + xOff + -1, this.getY() + yOff + -1);
		gObject.drawChar(charBricks, this.getX() + xOff + 1, this.getY() + yOff + -1);
		gObject.drawChar(charBricks, this.getX() + xOff, this.getY() + yOff + -1);
	}
	
	//movement
	public void eventStep()
	{
		this.commonStep();
	}
}
