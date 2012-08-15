package me.naftoreiclag.ahski;

import java.util.ArrayList;

//version 1
public class HandlerEntity
{
	//entityArray
	ArrayList<Entity> entityArray = new ArrayList<Entity>();
	
	//console variable
	private String consoleOutput = "==EntityHandler console messages:==\n";
	
	//info for entity
	HandlerLevel lvlHandle;
	
	public HandlerEntity(HandlerLevel h)
	{
		lvlHandle = h;
	}
	
	//create new entity; returns object
	public Entity spawnEntity(char entityID, int x, int y)
	{
		//spawn entity with ID entityID at position x y
		switch(entityID)
		{
			//spawn a zebra
			case '1':
			{
				Entity focus = new EntityZebra();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//player
			case '2':
			{
				Entity focus = new EntityPlayer();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//tree
			case '3':
			{
				Entity focus = new EntityTree();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//grunt
			case 'Z':
			{
				Entity focus = new EntityGrunt();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//gruntII
			case 'Y':
			{
				Entity focus = new EntityGruntII();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//gruntIII
			case 'X':
			{
				Entity focus = new EntityGruntIII();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//portal
			case '@':
			{
				Entity focus = new EntityPortal();
				entityArrayAdd(focus, x, y);
				return focus;
			}
			//chest
			case '=':
			{
				Entity focus = new EntityChest();
				entityArrayAdd(focus, x, y);
				return focus;
			}
		}
		
		return null;
	}
	
	//place free
	public boolean placeFree(Entity me, int x, int y)
	{
		boolean honey = true;
		/*for(int i = 0;i < entityArray.size();i++)
		{
			//focus attention on object at index i
			Entity focus = entityArray.get(i);
			
			if(!focus.equals(me))
			{
				if(focus.getX() == x || focus.getY() == y)
				{
					honey = false;
				}
			}
		}*/
		return honey;
	}
	
	//kill all with type
	public void killAll(int entType)
	{
		for(int i = 0;i < entityArray.size();i++)
		{
			//focus attention on object at index i
			Entity focus = entityArray.get(i);
			
			if(focus.getType() == entType)
			{
				consoleOutput += "I killed an entity with type " + focus.getType() + ". There are now " + entityArray.size() + " entities in entityArray.\n";
				grimReaper(focus);
			}
		}
	}
	
	//kill all!
	public void killAll()
	{
		entityArray.clear();
	}
	
	//kill non-players (useful for reloading levels!)
	public void killAllButPlayer(Entity player)
	{
		entityArray.clear();
		entityArray.add(player);
	}
	
	//add an object to the entityArray at x,y
	private void entityArrayAdd(Entity focus, int x, int y)
	{
		focus.setX(x);
		focus.setY(y);
		focus.setLvlHandle(lvlHandle);
		focus.setHandler(this);
		
		entityArray.add(focus);
		
		consoleOutput += "There are now " + entityArray.size() + " entities in entityArray.\n";
	}
	
	//find nearby entities
	public Entity[] getNearbyEntites(int dist, Entity self, int entType, int strength)
	{
		Entity entList[] = new Entity[strength];
		int num = 0;
		int minX = self.getX() - dist;
		int minY = self.getY() - dist;
		int maxX = self.getX() + dist;
		int maxY = self.getY() + dist;
		
		for(int i = 0;i < entityArray.size() && num < strength;i++)
		{
			//focus attention on object at index i
			Entity focus = entityArray.get(i);
			
			//
			if(focus.getType() == entType || entType == 0)
			{
				if((focus.getX() >= minX && focus.getX() <= maxX) && (focus.getY() >= minY && focus.getY() <= maxY))
				{
					entList[num] = focus;
					num ++;
				}
			}
		}
		
		return entList;
	}
	
	//print out console text
	public void printConsoleOutput()
	{
		consoleOutput += "Entites:  " + entityArray.size() + "\n";
		
		//print it
		System.out.println(consoleOutput);
		
		//clear it
		consoleOutput = "==EntityHandler console messages:==\n";
	}
	
	//step event handler
	public void executeStepEvents()
	{
		for(int i = 0;i < entityArray.size();i++)
		{
			//focus attention on object at index i
			Object focus = entityArray.get(i);
			
			//execute draw function
			((Entity) focus).eventStep();
		}
	}
	
	//draw event handler
	public void executeDrawEvents(GraphicsArrayDraw gObject, int xOff, int yOff)
	{
		for(int i = 0;i < entityArray.size();i++)
		{
			//focus attention on object at index i
			Entity focus = entityArray.get(i);
			
			//execute draw function
			focus.eventDraw(gObject, xOff, yOff);
			
			////old code
			//gObject.drawChar(((BaseEntity) focus).getSkin(),((BaseEntity) focus).getX(),((BaseEntity) focus).getY());
		}
		//gObject.drawChar('T',5,5);
	}

	//kills victim
	public void grimReaper(Entity victim)
	{
		entityArray.remove(victim);
	}
}
