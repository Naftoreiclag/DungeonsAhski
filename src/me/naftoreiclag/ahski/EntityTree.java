package me.naftoreiclag.ahski;

//id = 3

public class EntityTree extends Entity
{
	private char charTree = 237;
	
	//Initialization
	public EntityTree()
	{
		this.setEntityId(3);
		this.setSkin(charTree);
		this.setType(0);
	}
	
	//movement
	public void eventStep()
	{
		this.commonStep();
	}
}
