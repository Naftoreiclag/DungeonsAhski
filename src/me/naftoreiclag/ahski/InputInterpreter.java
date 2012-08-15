package me.naftoreiclag.ahski;

public class InputInterpreter
{
	public String[] stringToArray(String stringInput)
	{
		String commandEntry[] = new String[16];
		
		for(int i = 0;i < 16;i++)
		{
			commandEntry[i] = "null";
		}
		
		int numEntries = 0;
		String tempMem;
		
		stringInput += " ";
		
		tempMem = "";
		for(int i = 0; i < stringInput.length(); i++)
		{
			if(stringInput.charAt(i) != ' ')
			{
				tempMem += stringInput.charAt(i);
			}
			else
			{
				if(numEntries < 16)
				{
					commandEntry[numEntries] = tempMem;
					numEntries++;
					tempMem = "";
				}
				else
				{
					break;
				}
			}
		}
		
		return commandEntry;
	}
	
	public int itemNameToId(String in)
	{
		if(in.equalsIgnoreCase("potion"))
		{
			return 1;
		}
		
		return 0;
	}
}
