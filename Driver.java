import java.util.Scanner;

public class Driver {
	private static Location currentLocation; //Current location of the player
	
	private static ContainerItem myInventory; //the inventory of the player which is a ContainerItem object
	
	public static void createWorld()
	{
		myInventory = new ContainerItem("backpack", "a small blue backpack", "storage");
		
		Location kitchen = new Location("Kitchen", "A dark scary kitchen");
		Location hallway = new Location("Hallway", "A dark scary hallway");
		Location bedroom = new Location("Bedroom", "A dark scary bedroom");
		Location diningRoom = new Location("dining room", "A dark scary dining room");
		
		kitchen.connect("east", diningRoom);
		kitchen.connect("south", hallway);
		diningRoom.connect("west", kitchen);
		hallway.connect("north", kitchen);
		hallway.connect("south", bedroom);
		bedroom.connect("north", hallway);
		
		Item knife = new Item("Knife", "A sharp cutting knife", "weapon");
		Item Turkey = new Item("Turkey", "A turkey that would be served on thanksgiving", "food");
		Item plate = new Item("plate", "A dinner plate", "Utensil");
		
		kitchen.addItem(Turkey);
		kitchen.addItem(knife);
		kitchen.addItem(plate);
		
		Item note = new Item("note", "A note that says \"help me\"", "clue");
		Item watch = new Item("watch", "A gold rolex watch", "accesory");
		ContainerItem chest = new ContainerItem("Chest", "A big brown chest", "Storage");
		
		
		bedroom.addItem(note);
		bedroom.addItem(chest);
		chest.addItem(watch);
		
		
		Item gun = new Item("gun", "A pistol", "weapon");
		
		hallway.addItem(gun);
		
		Item glass = new Item("glass", "A drinking glass", "utensil");
		Item glove = new Item("glove", "A size 8 right hand men's glove.", "clue");
		ContainerItem desk = new ContainerItem("desk", "A small wooden desk that contains:", "Storage");
		
		diningRoom.addItem(glass);
		diningRoom.addItem(desk);
		desk.addItem(glove);
		
		Driver.currentLocation = kitchen;
	}
	
	
	public static void main(String[] args)
	{
		
		createWorld();
		
		Scanner myScanner = new Scanner(System.in);		
		String input = null;
		String containerName = null;
		
		 String[] arrOfStr = null;
		 String parameter = null;
		
		while(input != "quit")
		{
			parameter = null;
			System.out.println("Enter command: ");
			input = myScanner.nextLine();
			
			arrOfStr = input.split(" ", 4);
			input = arrOfStr[0];
			
			if(arrOfStr.length == 2)
			{
				parameter = arrOfStr[1];
			}
			else if(arrOfStr.length == 4)
			{
				parameter = arrOfStr[1];
				containerName = arrOfStr[3];
				input = input + " " + arrOfStr[2];
			}
			
			switch(input)
			{
			
			case "quit":
			{
				input = "quit";
				break;
			}
			
			case "inventory":
			{
				if(myInventory.isEmpty())
				{
					System.out.println("Your inventory is empty");
				}
				else
				{
					System.out.println(myInventory);
				}
				break;
			}
			
			case "take":
			{
				if(arrOfStr.length == 1)
				{
					System.out.println("please enter take something");
				}
				else if(arrOfStr.length == 3)
				{
					System.out.println("Please enter \"take\" + Items name\n");
					System.out.println("Or enter \"take\" + name + \"from\" + containers name\n");
					System.out.println("Not three words. Either two or four words. \n");
				}
				else if(arrOfStr.length != 2)
				{
					System.out.println("please enter take something, two words");
				}
				else if(!currentLocation.hasItem(parameter))
				{
					System.out.println("Cannot find that item here");
				}
				else if(currentLocation.hasItem(parameter))
				{
					Item temp = currentLocation.removeItem(parameter);
					myInventory.addItem(temp);
				}
				break;
			}
			
			case "drop":
			{
				if(arrOfStr.length == 1)
				{
					System.out.println("Please enter drop something");
				}
				else if(arrOfStr.length != 2)
				{
					System.out.println("Please enter drop 'something', two words");
				}
				else if(!myInventory.hasItem(parameter))
				{
					System.out.println("Cannot find that item en your inventory");
				}
				else if(myInventory.hasItem(parameter))
				{
					Item temp = myInventory.removeItem(parameter);
					currentLocation.addItem(temp);
				}
				break;
			}
			
			case "take from":
			{
				if(!currentLocation.hasItem(containerName))
				{
					System.out.println("This location does not have that object");
				}
				else if(!(currentLocation.getItem(containerName) instanceof ContainerItem))
				{
					System.out.println("That item is not a container item");
				}
				else if(!((ContainerItem) currentLocation.getItem(containerName)).hasItem(parameter))
				{		
					System.out.println("That item is not in that container item");
				}
				else if(currentLocation.hasItem(containerName))
				{
					Item temp1;
					Item temp = currentLocation.getItem(containerName);
					temp1 = ((ContainerItem) temp).removeItem(parameter);
					myInventory.addItem(temp1);
				}
				break;
			}
			
			case "put in":
			{
				
				if(!myInventory.hasItem(parameter))
				{
					System.out.println("You do not have that item");
				}
				else if(!currentLocation.hasItem(containerName))
				{
					System.out.println("That container item does not exist");
				}
				else if(!(currentLocation.getItem(containerName) instanceof ContainerItem))
				{
					System.out.println("That item is not a container");
				}
				else if(myInventory.hasItem(parameter) && currentLocation.hasItem(containerName))
				{
					Item temp = currentLocation.getItem(containerName);
					Item temp1 = myInventory.removeItem(parameter);
					((ContainerItem)temp).addItem(temp1);
				}
				break;
			}
			
			case "go":
			{
				if(arrOfStr.length == 1)
				{
					System.out.println("please enter a direction");
				}
				else if(arrOfStr.length != 2)
				{
					System.out.println("Please enter go 'direction'\n");
					System.out.println("Two words.\n");
				}
				else if(!(parameter.equalsIgnoreCase("west")) && !(parameter.equalsIgnoreCase("north")) 
						&& !(parameter.equalsIgnoreCase("south")) && !(parameter.equalsIgnoreCase("east")))
				{
					System.out.println("Please enter a valid cardinal direction");
				}
				else if(!Driver.currentLocation.canMove(parameter))
				{
					System.out.println("You cannot go that way");
				}
				else if(Driver.currentLocation.canMove(parameter))
				{
					Driver.currentLocation = Driver.currentLocation.getLocation(parameter);
					
				}
				
				break;
			}
			
			case "look":
			{
				System.out.println(Driver.currentLocation.getName() + "-" + Driver.currentLocation.getDescription() + " currently "
						+ "has the following items");
				for(int i = 0; i < Driver.currentLocation.numItems(); i++)
				{
					System.out.println(Driver.currentLocation.getItem(i).getName());
				}
				break;
			}
			
			case  "examine":
			{
				if(arrOfStr.length==1)
				{
					System.out.println("please enter examine 'something'");
					break;
				}
				else if(arrOfStr.length != 2)
				{
					System.out.println("Please enter \"examine\" + Items name. \n");
					System.out.println("Two words. \n");
					break;
				}
				else if(Driver.currentLocation.getItem(parameter)==null)
				{
					System.out.println("cannot find item");
					parameter = null;
					break;
				}
				else if(currentLocation.getItem(parameter) instanceof ContainerItem)
				{
					System.out.println(currentLocation.getItem(parameter));
					break;
				}
				else if(currentLocation.hasItem(parameter))
				{
					System.out.println( Driver.currentLocation.getItem(parameter).getName() + " - " +
							Driver.currentLocation.getItem(parameter).getDescription());
					parameter = null;
					break;
				}
				break;
			}
			
			case "help":
			{
				System.out.println("Here are the following commands that the programs supports: \n");
				
				System.out.println("\"examine\" + Item's name- will tell you information about that "
						+ "Item, including a brief description of that item. \n");
				
				System.out.println("\"look\"- will print out the current location that you are in "
						+ ", a brief description of that location, and what items are "
						+ "in that location. \n");
				
				System.out.println("\"go\" + cardinal direction- your player will be moved to "
						+ "the direction that is specified if it is possible. \n");
				
				System.out.println("\"put\" + Item's name + \"in\" + container Item's name- "
						+ "will take the item from your inventory and will put it in the "
						+ "container of Items. \n");
				
				System.out.println("\"take\" + name + \"from\" + containers name- Will take an Item "
						+ "out of the container of items and put it in your inventory. \n");
				
				System.out.println("\"drop\" + Item's name- Will take the specified Item out of "
						+ "your inventory and leave it at that current location. \n");
				
				System.out.println("\"take\" + Item's name- Will pick up the specified Item "
						+ "and put it in your inventory. \n");
				
				System.out.println("\"inventory\"- Will tell you what is currently in your"
						+ " inventory\n");
				
				System.out.println("\"quit\"- Will stop the game. \n");
				
				break;
				
			}
		
			default:
				System.out.println("I do not know how to do that. \n");
				System.out.println("Type \"help\" for a full run down of possible commands. \n");
			}
        }
        myScanner.close();
	}

}