package me.naftoreiclag.ahski;

//id = 1

public class EntityZebra extends Entity
{
	//Initialization
	public EntityZebra()
	{
		this.setEntityId(1);
		this.setSkin('A');
		this.setType(2);
	}
	
	//movement
	public void eventStep()
	{
		this.commonStep();
	}
}
