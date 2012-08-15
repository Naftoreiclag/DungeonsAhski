package me.naftoreiclag.ahski;


public class Entity
{
	//protected = private for not only itself, but accessible to all child classes
	private char skin = '?';
	private int posX;
	private int posY;
	private int lifetime = 0;
	private int health = 1;
	private int maxHealth = 1;
	private int action = 0;
	private int timerA = 0;
	private int entityId = 0;
	/*Types:
	 * 0. decoration
	 * 1. player
	 * 2. hostile enemy
	 * 3. portal
	 * 4. chest
	 * */
	private int type = 2;
	private boolean flagA = false;
	private boolean flagB = false;
	private int genericIntA = 0;
	private Entity genericEntityA;
	private HandlerLevel lvlHandle;
	
	protected int[] entityContent = new int[16];
	protected HandlerEntity myHandler;
	
	//obvious
	public void setGenericEntityA(Entity e)
	{
		genericEntityA = e;
	}
	
	public void setMaxHealth(int m)
	{
		maxHealth = m;
	}
	
	//init
	public void setHandler(HandlerEntity h)
	{
		myHandler = h;
	}
	
	//obvious
	public void setLvlHandle(HandlerLevel h)
	{
		lvlHandle = h;
	}
	
	//obvious
	public void setType(int t)
	{
		type = t;
	}
	
	//obvious
	public void setEntityId(int x)
	{
		entityId = x;
	}
	
	//obvious
	public void setSkin(char s)
	{
		skin = s;
	}
	
	//obvious
	public void setX(int x)
	{
		posX = x;
	}
	
	//obvious
	public void setY(int y)
	{
		posY = y;
	}
	
	//obvious
	public void setHealth(int h)
	{
		health = h;
	}
	
	//obvious
	public void setTimerA(int i)
	{
		timerA = i;
	}
	
	//obvious
	public void setAction(int a)
	{
		action = a;
	}
	
	//obvious
	public void setEntityContent(int[] egg)
	{
		entityContent = egg;
	}
	
	//obvious
	public void setFlagA()
	{
		flagA = true;
	}
	
	//obvious
	public void unsetFlagA()
	{
		flagA = false;
	}
	
	//obvious
	public void setFlagB()
	{
		flagB = true;
	}
	
	//obvious
	public void unsetFlagB()
	{
		flagB = false;
	}
	
	public void addHealth(int i)
	{
		if(health != 9001)
		{
			if(health + i < maxHealth)
			{
				health += i;
			}
			else
			{
				health = maxHealth;
			}
		}
	}
	
	//Initialization
	public void eventCreate()
	{
		//null
	}
	
	//executed every tick
	public void eventStep()
	{
		this.commonStep();
	}
	
	public void commonStep()
	{
		//timers
		if(timerA > 0)
		{
			timerA--;
		}
		
		//flags
		flagA = false;
		flagB = false;
		
		//heartbeats
		lifetime += 1;
		
		//death conditions
		if(health <= 0)
		{
			myHandler.grimReaper(this);
		}
	}
	
	//Draw itself
	public void eventDraw(GraphicsArrayDraw gObject, int xOff, int yOff)
	{
		gObject.drawChar(this.skin, this.posX + xOff, this.posY + yOff);
	}
	
	//obvious
	public int getLifetime()
	{
		return lifetime;
	}
	
	//obvious
	public HandlerLevel getLvlHandle()
	{
		return lvlHandle;
	}
	
	//obvious
	public int getEntityId()
	{
		return entityId;
	}
	
	//obvious
	public char getSkin()
	{
		return skin;
	}
	
	//obvious
	public int getX()
	{
		return posX;
	}
	
	//obvious
	public int getY()
	{
		return posY;
	}
	
	//obvious
	public int getAction()
	{
		return action;
	}
	
	//obvious
	public int getTimerA()
	{
		return timerA;
	}
	
	//obvious
	public void setPosition(int x,int y)
	{
		posX = x;
		posY = y;
	}
	
	//obvious
	public int getHealth()
	{
		return health;
	}

	//obvious
	public boolean getFlagB()
	{
		return flagB;
	}
	
	//obvious
	public boolean getFlagA()
	{
		return flagA;
	}
	
	//obvious
	public Entity getGenericEntityA()
	{
		return genericEntityA;
	}
	
	//obvious
	public HandlerEntity getHandler()
	{
		return myHandler;
	}
	
	//obvious
	public int getType()
	{
		return type;
	}
	//obvious
	public int getMaxHealth()
	{
		return maxHealth;
	}

	public int[] getEntityContent()
	{
		return this.entityContent;
	}

	public boolean isThinking()
	{
		return false;
	}

	public int[] getThoughts()
	{
		int dummy[] = new int[16];
		
		return dummy;
	}
}
