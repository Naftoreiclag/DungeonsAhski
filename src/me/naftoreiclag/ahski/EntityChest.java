package me.naftoreiclag.ahski;

//id = 1

public class EntityChest extends Entity
{
	private char charChest = 227;
	
	//Initialization
	public EntityChest()
	{
		this.setEntityId(90);
		this.setSkin(charChest);
		this.setType(4);//4 = chest
		this.setHealth(9001);
		this.entityContent[0] = 1;
	}
	
	//movement
	public void eventStep()
	{
		this.commonStep();
	}
	
	//
}
