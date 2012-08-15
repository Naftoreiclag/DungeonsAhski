package me.naftoreiclag.ahski;

import java.util.Scanner;

public class Main
{
	//functional objects-----------
	//gObject
	public static GraphicsArrayDraw gObject = new GraphicsArrayDraw();
	//input scanner
	public static Scanner inputCatcher = new Scanner(System.in);
	//scripts
	public static FancyStrings fsMaker = new FancyStrings();
	public static InputInterpreter inputInterpreter = new InputInterpreter();
	//handlers
	public static HandlerInventory invHandler = new HandlerInventory();
	public static HandlerTextNags nagHandler = new HandlerTextNags();
	public static HandlerLevel levelHandler = new HandlerLevel();
	public static HandlerEntity entityHandler = new HandlerEntity(levelHandler);
	public static HandlerTextBoxes tBoxHandler = new HandlerTextBoxes();
	
	//player id
	public static Entity myPlayer = entityHandler.spawnEntity('2',25,12);
	

	static boolean chestOpen = false;
	
	//camera stuff
	public static int camX = 0;
	public static int camY = 0;
	
	//moves
	public static int numMoves;
	
	//main
	public static void main(String[] args)
	{
		//while loop handlers
		boolean gameIsRunning;
		boolean mainMenuOpen;
		
		//input dump
		String userStringInput = " ";
		String commandEntry[] = new String[16];
		
		//guis are open
		int[] chestContents;
		
		//temporary code
		gameIsRunning = true;
		
		//load level
		levelHandler.loadLevel(0, entityHandler, myPlayer);
		
		//game loop
		while(gameIsRunning)
		{
			//-Game handling----------------------------
			
			//step events
			entityHandler.executeStepEvents();
			
			//update camera position
			camX = myPlayer.getX() - 25;
			camY = myPlayer.getY() - 12;
			
			//-gObject Stuff----------------------------
			
			//add the level
			levelHandler.drawLevel(gObject, -camX, -camY);
			
			//add the entities
			entityHandler.executeDrawEvents(gObject, -camX, -camY);
			
			//add boxes
			tBoxHandler.drawBox(gObject);
			
			//add the header
			drawHeader();
			
			//add inventory
			drawInventory();
			
			//add chest gui
			if(myPlayer.isThinking())
			{
				int thoughts[] = myPlayer.getThoughts();
				String boxText = "Chest Contents:\n";
				
				chestContents = thoughts;
				
				for(int i = 0;i < chestContents.length;i++)
				{
					if(chestContents[i] != 0)
					{
						boxText += "\n";
						if(i < 10)
						{
							boxText += " ";
						}
						
						boxText += i + 1 + "." + " ";
						boxText += fsMaker.itemIdToReadable(chestContents[i]);
					}
				}
				
				tBoxHandler.drawBox(gObject, boxText);
				
				chestOpen = true;
			}
			
			//add loss screen
			if(myPlayer.getHealth() < 1)
			{
				drawLossScreen();
				myPlayer.setAction(127);
			}
			else
			{
				numMoves += 1;
			}
			
			//advance level
			Entity sheep[] = new Entity[1];
			sheep = entityHandler.getNearbyEntites(1, myPlayer, 3, 1);
			if(sheep[0] != null)
			{
				levelHandler.loadLevel(levelHandler.getLoadedLevel() + 1, entityHandler, myPlayer);
			}
			
			//draw development console
			//entityHandler.printConsoleOutput();
			
			//draw gArray
			gObject.printGArray();
			
			//clear gArray
			gObject.clearGArray();
			
			//-Input handling-----------------------------
			
			//get some new input
			userStringInput = inputCatcher.nextLine();
			
			//interpret input
			commandEntry = inputInterpreter.stringToArray(userStringInput);
			interpretEntry(commandEntry);
			
			//close guis-----------------------------
			chestOpen = false;
		}
	}
	
	private static void drawHeader()
	{
		gObject.drawRectangleThick(0, 0, 78, 23);
		gObject.drawString("The Dungeons of Ahski 0.2.2", 1, 0);
		gObject.drawString(fsMaker.levelIdToCoolness(levelHandler.getLoadedLevel()), 1, 23);
	}
	
	private static void drawInventory()
	{
		char arrowRight = 16;
		char arrowLeft = 17;
		
		gObject.drawRectangleFilledChar(50, 1, 77, 22, ' ');
		gObject.drawRectangle(50, 1, 77, 22);
		//gObject.drawString(arrowRight + "Inventory" + arrowLeft, 59, 2);
		gObject.drawString("Inventory",51,1);
		
		//action
		gObject.drawString(fsMaker.playerActionToString(myPlayer.getAction()),52,3);
		
		//health
		gObject.drawString("Health: " + fsMaker.makeHealthBarType1(myPlayer.getHealth(), 30), 52, 4);
		invHandler.drawInventory(gObject, 52, 6, fsMaker);
		
		/*
		gObject.drawString("1. -", 52, 6);
		gObject.drawString("2. -", 52, 7);
		gObject.drawString("3. -", 52, 8);
		gObject.drawString("4. -", 52, 9);
		gObject.drawString("5. -", 52, 10);
		gObject.drawString("6. -", 52, 11);
		gObject.drawString("7. -", 52, 12);
		gObject.drawString("8. -", 52, 13);
		*/
		
		//prompt
		nagHandler.drawSelf(gObject, 52, 15);
	}

	private static void drawLossScreen()
	{
		switch(numMoves % 10)
		{
			case 0:
			{
				tBoxHandler.drawBox(gObject, "You have been slain.");
				break;
			}
			case 1:
			{
				tBoxHandler.drawBox(gObject, "You have kicked the bucket.");
				break;
			}
			case 2:
			{
				tBoxHandler.drawBox(gObject, "You have died.");
				break;
			}
			case 3:
			{
				tBoxHandler.drawBox(gObject, "You have lost.");
				break;
			}
			case 4:
			{
				tBoxHandler.drawBox(gObject, "You gave up.");
				break;
			}
			case 5:
			{
				tBoxHandler.drawBox(gObject, "You were captured by a gang\nof angry symbols.\n(And died.)");
				break;
			}
			case 6:
			{
				tBoxHandler.drawBox(gObject, "Game over");
				break;
			}
			case 7:
			{
				tBoxHandler.drawBox(gObject, "*Funeral music*");
				break;
			}
			case 8:
			{
				tBoxHandler.drawBox(gObject, "You passed away.");
				break;
			}
			case 9:
			{
				tBoxHandler.drawBox(gObject, "You have been terminated.");
				break;
			}
			case 10:
			{
				tBoxHandler.drawBox(gObject, "You won!\nJust kidding! You lost.");
				break;
			}
			default:
			{
				tBoxHandler.drawBox(gObject, "Game over.");
			}
		}
		
		/*gObject.drawRectangleFilledChar(5, 10, 45, 14,' ');
		gObject.drawRectangleThick(5, 10, 45, 14);
		gObject.drawString("You have been killed.", 15, 12);*/
	}
	
	private static void interpretEntry(String[] argument)
	{
		//dump
		String command = argument[0];
		
		//Java 1.6 not having switch() functionality for Strings makes me a sad panda------
		//movement checks
		if(command.equalsIgnoreCase("move"))
		{
			if(argument[1].equalsIgnoreCase("w") || argument[1].equalsIgnoreCase("up") || argument[1].equalsIgnoreCase("north"))
			{
				nagHandler.addLine("You begin moving north.");
				myPlayer.setAction(1);
			}
			else if(argument[1].equalsIgnoreCase("a") || argument[1].equalsIgnoreCase("left") || argument[1].equalsIgnoreCase("west"))
			{
				nagHandler.addLine("You begin moving west.");
				myPlayer.setAction(2);
			}
			else if(argument[1].equalsIgnoreCase("s") || argument[1].equalsIgnoreCase("down") || argument[1].equalsIgnoreCase("south"))
			{
				nagHandler.addLine("You begin moving south.");
				myPlayer.setAction(3);
			}
			else if(argument[1].equalsIgnoreCase("d") || argument[1].equalsIgnoreCase("right") || argument[1].equalsIgnoreCase("east"))
			{
				nagHandler.addLine("You begin moving east.");
				myPlayer.setAction(4);
			}
		}
		else if(command.equalsIgnoreCase("w") || command.equalsIgnoreCase("up") || command.equalsIgnoreCase("north"))
		{
			nagHandler.addLine("You begin moving north.");
			myPlayer.setAction(1);
		}
		else if(command.equalsIgnoreCase("a") || command.equalsIgnoreCase("left") || command.equalsIgnoreCase("west"))
		{
			nagHandler.addLine("You begin moving west.");
			myPlayer.setAction(2);
		}
		else if(command.equalsIgnoreCase("s") || command.equalsIgnoreCase("down") || command.equalsIgnoreCase("south"))
		{
			nagHandler.addLine("You begin moving south.");
			myPlayer.setAction(3);
		}
		else if(command.equalsIgnoreCase("d") || command.equalsIgnoreCase("right") || command.equalsIgnoreCase("east"))
		{
			nagHandler.addLine("You begin moving east.");
			myPlayer.setAction(4);
		}
		else if(command.equalsIgnoreCase("attack") || command.equalsIgnoreCase("slay") || command.equalsIgnoreCase("x"))
		{
			nagHandler.addLine("You attacked the enemy.");
			myPlayer.setFlagA();
			myPlayer.setAction(0);
		}
		else if(command.equalsIgnoreCase("look") || command.equalsIgnoreCase("examine"))
		{
			tBoxHandler.addBox(levelHandler.getLevelDescription());
		}
		else if(command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("pause") || command.equalsIgnoreCase("yield") || command.equalsIgnoreCase("halt") || command.equalsIgnoreCase("idle"))
		{
			myPlayer.setAction(0);
		}
		else if(command.equalsIgnoreCase("open") && argument[1].equalsIgnoreCase("chest"))
		{
			myPlayer.setFlagB();
			myPlayer.setAction(0);
		}
		else if(command.equalsIgnoreCase("get") && chestOpen)
		{
			if(inputInterpreter.itemNameToId(argument[1]) != 0)
			{
				Entity chest = myPlayer.getGenericEntityA();
				
				int chestContents[] = chest.getEntityContent();
				
				for(int i = 0; i < chestContents.length; i++)
				{
					if(chestContents[i] == inputInterpreter.itemNameToId(argument[1]))
					{
						invHandler.addItem(inputInterpreter.itemNameToId(argument[1]));
						nagHandler.addLine("You got a " + argument[1] + "!");
						
						chestContents[i] = 0;
						break;
					}
				}
				chest.setEntityContent(chestContents);
				
				chestOpen = false;
			}
		}
		else if(command.equalsIgnoreCase("use"))
		{
			int selIt = inputInterpreter.itemNameToId(argument[1]);
			
			if(selIt != 0)
			{
				if(invHandler.hasItem(selIt))
				{
					invHandler.takeItem(inputInterpreter.itemNameToId(argument[1]));
					
					//temp
					myPlayer.addHealth(5);
				}
			}
		}
		else if(command.equalsIgnoreCase("~give"))
		{
			invHandler.addItem(argument[1].length());
		}
		else if(command.equalsIgnoreCase("~killall"))
		{
			entityHandler.killAllButPlayer(myPlayer);
		}
		//inventory checks
	}
}