package me.naftoreiclag.ahski;

public class FancyStrings
{
	public String makeHealthBarType1(int amount, int max)
	{
		char full = 'O';//219;
		char half = 'o';//221;
		char empt = '.';//' ';
		
		String sFull = "" + full;
		String sHalf = "" + half;
		String sEmpt = "" + empt;
		
		String returnValue = "[";
		
		for(int i = 0;i < amount;i++)
		{
			if(returnValue.endsWith(sHalf))
			{
				returnValue = returnValue.replace(sHalf, sFull);
			}
			else
			{
				returnValue += sHalf;
			}
		}
		
		for(int i = 0;i < (max - amount) / 2;i ++)
		{
			returnValue += sEmpt;
		}
		
		returnValue += "]";
		
		return returnValue;
	}
	
	public String playerActionToString(int i)
	{
		switch(i)
		{
			case 0:
			{
				return "Idle";
			}
			case 1:
			{
				return "Moving Up";
			}
			case 2:
			{
				return "Moving Left";
			}
			case 3:
			{
				return "Moving Down";
			}
			case 4:
			{
				return "Moving Right";
			}
			case 127:
			{
				return "Being Dead";
			}
		}
		return "Singing";
	}
	
	public String itemIdToReadable(int i)
	{
		switch(i)
		{
			case 1:
			{
				return "Potion";
			}
		}
		return "MissignNo";
	}
	
	public String levelIdToCoolness(int i)
	{
		switch(i)
		{
			case 0:
			{
				return "Surface";
			}
		}
		return "Level " + i;
	}
}
