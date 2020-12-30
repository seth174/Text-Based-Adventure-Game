/**
 * 
 * The location class is used to create new locations that will fill the
 * text based adventure game
 * @author sethfagen_2022
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	
	private String name;
	private String description;
	private ArrayList<Item> items;
	private HashMap<String, Location> connections;
	
	/**
	 * The constructor used to create a new location from name and description
	 * @param name The name of the location
	 * @param description The description of the location 
	 */
	public Location(String name, String description)
	{
		this.name = name;
		this.description = description;
		items = new ArrayList<Item>();
		connections = new HashMap<String, Location>();
	}
	
	/**
	 * This method returns the name of the location
	 * @return The name of the location
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * This method returns the description of the location
	 * @return The description of the location
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * This method sets the name of the location
	 * @param name The new name of the location
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * This method sets the description of the location
	 * @param description The new description of the location
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * This method adds an item to the arraylist contained inside the object
	 * @param item The item that will be added to inside location object
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	/**
	 * This method will detect whether the location has an Item or not 
	 * @param name The name of the Item that is being searched
	 * @return Whether or not this Item is inside of the location 
	 */
	public boolean hasItem(String name)
	{
		String temp = name.toLowerCase();
		String temp2;
		for(int i = 0; i < items.size(); i ++)
		{
			temp2 = items.get(i).getName().toLowerCase();
			if(temp.equals(temp2))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns an Item that is in the location class or it will return a null Item object
	 * @param name the name of the Item that is being searched
	 * @return The Item whose name matches the parameter
	 */
	public Item getItem(String name)
	{
		Item temp = null;
		String temp2 = name.toLowerCase();
		String temp3;
		for(int i = 0; i< items.size(); i++)
		{
			temp3 = items.get(i).getName().toLowerCase();
			if(temp3.equals(temp2))
			{
				temp = items.get(i);
			}
		}
		return temp;
	}
	
	/**
	 * This method returns the item at a particular index inside of the arraylist that holds all of 
	 * the items that are at each location
	 * @param index the index for the item inside the arraylist that will be returned
	 * @return the item at the particular index inside of the arraylist
	 */
	public Item getItem(int index)
	{
		Item temp = null;
		if(items.size()-1 >= index && index >=0)
		{
			temp = items.get(index);
		}
		return temp;
	}
	
	/**
	 * This method returns the number of items that are at each location
	 * @return The number of items in a particular location
	 */
	public int numItems()
	{
		return items.size();
	}
	
	/**
	 * This method returns the Item that is being removed from the method
	 * @param name The name of the Item that wants to be removed
	 * @return The Item that is being removed
	 */
	public Item removeItem(String name)
	{
		Item temp = null;
		String temp2 = name.toLowerCase();
		String temp3;
		for(int i = 0; i<items.size(); i++)
		{
			temp3 = items.get(i).getName().toLowerCase();
			if(temp2.equals(temp3))
			{
				temp = items.get(i);
				items.remove(i);
			}
		}
		return temp;
	}
	
	public void connect(String direction, Location name)
	{
		connections.put(direction, name);
	}
	
	public boolean canMove(String direction)
	{
		if(connections.containsKey(direction))
			return true;
		else
			return false;
	}
	
	public Location getLocation(String direction)
	{
		Location temp = null;
		Object key = (Object) direction;
		temp = connections.get(key);
		return temp;
		
	}


}
