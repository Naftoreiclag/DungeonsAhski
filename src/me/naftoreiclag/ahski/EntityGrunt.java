package me.naftoreiclag.ahski;

//id = 4

public class EntityGrunt extends Entity
{
	//Initialization
	public EntityGrunt()
	{
		this.setEntityId(4);
		this.setSkin('Z');
		this.setType(2);
		this.setAction(1);
	}
	
	//movement
	public void eventStep()
	{
		Entity tomatoes[] = new Entity[1];
		tomatoes = this.getHandler().getNearbyEntites(1, this, 1, 1);
		
		Entity potatoes[] = new Entity[1];
		potatoes = this.getHandler().getNearbyEntites(256, this, 1, 1);
		
		Entity player;
		//no nearby players within radius of 1
		if(tomatoes[0] == null)
		{
			//persue
			this.setAction(1);
		}
		else
		{
			//attack
			this.setAction(2);
		}

		int newX = this.getX();
		int newY = this.getY();
		
		//actions
		switch(this.getAction())
		{
		
			case 1:
			{
				//Persue player
				if(potatoes[0] != null)
				{
					player = potatoes[0];
					
					if(this.getX() < player.getX())
					{
						newX = this.getX() + 1;
					}
					else if(this.getX() > player.getX())
					{
						newX = this.getX() - 1;
					}
					else if(this.getY() < player.getY())
					{
						newY = this.getY() + 1;
					}
					else if(this.getY() > player.getY())
					{
						newY = this.getY() - 1;
					}
					
				}
				break;
			}
			case 2:
			{
				if(potatoes[0] != null)
				{
					player = potatoes[0];
					
					player.addHealth(-1);
				}
				break;
			}
			
			
		}
		
		//no illegal positions
		if(this.getLvlHandle().placeFree(newX, this.getY()) && this.getHandler().placeFree(this, newX, this.getY()))
		{
			this.setX(newX);
		}
		if(this.getLvlHandle().placeFree(this.getX(), newY) && this.getHandler().placeFree(this, this.getX(), newY))
		{
			this.setY(newY);
		}
		
		//common
		this.commonStep();
	}
}
