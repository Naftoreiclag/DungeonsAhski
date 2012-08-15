package me.naftoreiclag.ahski;

//id = 2

public class EntityPlayer extends Entity
{
	private char steve = 2;
	
	private int thoughts[] = new int[16];
	
	//Initialization
	public EntityPlayer()
	{
		this.setEntityId(2);
		this.setSkin(steve);
		this.setHealth(30);
		this.setType(1);
		this.setMaxHealth(30);
	}
	
	//mind reading
	public int[] getThoughts()
	{
		return thoughts;
	}
	public boolean isThinking()
	{
		boolean returnValue = false;
		for(int i = 0;i < thoughts.length;i++)
		{
			if(thoughts[i] != 0)
			{
				returnValue = true;
			}
		}
		
		return returnValue;
	}
	
	
	public void eventStep()
	{
		int newX = this.getX();
		int newY = this.getY();
		
		//attacking
		if(this.getFlagA())
		{
			//get target list
			Entity entList[] = new Entity[2];
			entList = this.getHandler().getNearbyEntites(2, this, 2, 2);
			
			//attack stuff
			for(int i = 0;i < entList.length;i++)
			{
				if(entList[i] != null)
				{
					entList[i].addHealth(-1);
				}
			}
			
			//I finished attacking, master flag A.
			this.unsetFlagA();
		}
		
		//opening chests
		if(this.getFlagB())
		{
			//get target list
			Entity entList[] = new Entity[1];
			entList = this.getHandler().getNearbyEntites(2, this, 4, 1);
			
			//
			if(entList[0] != null)
			{
				//get the contents
				Entity chest = entList[0];
				int[] chestContents = chest.getEntityContent();
				
				setGenericEntityA(chest);
				
				//think about it
				thoughts = chestContents;
				
				//examine (DEV)
				for(int i = 0; i < chestContents.length; i++)
				{
					if(chestContents[i] != 0)
					{
						//System.out.println(chestContents[i]);
					}
				}
			}

			this.unsetFlagB();
		}
		
		//movement
		switch(this.getAction())
		{
			case 1:
			{
				newY = this.getY() - 1;
				break;
			}
			case 2:
			{
				newX = this.getX() - 1;
				break;
			}
			case 3:
			{
				newY = this.getY() + 1;
				break;
			}
			case 4:
			{
				newX = this.getX() + 1;
				break;
			}
		}
		
		//no illegal positions
		if(this.getLvlHandle().placeFree(newX, this.getY()))
		{
			this.setX(newX);
		}
		if(this.getLvlHandle().placeFree(this.getX(), newY))
		{
			this.setY(newY);
		}
		
		//common actions
		this.commonStep();
	}
}
