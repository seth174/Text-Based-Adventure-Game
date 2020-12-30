/**
 * The Item class is used to describe items that are found at different locations
 * of the text based adventure game.
 * @author sethfagen_2022
 *
 */

public class Item {
	private String name;
	private String description;
	private String type;
	
	/**
	 * This constructor creates an item using the name, description, and type as a parameter
	 * @param name What the item is called
	 * @param description A description of the Item
	 * @param type what category the item is (ex- Food, weapon, etc...)
	 */
	public Item(String name, String description, String type)
	{
		this.name = name;
		this.description = description;
		this.type = type;
	}
	
	/**
	 * This method returns the name of the Item
	 * @return The name of the Item
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * This method returns a description of the item
	 * @return The description of the Item
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * This method returns what is the type(String) of the Item
	 * @return a String of what type the Item is
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * This method sets the name of the Item
	 * @param name the new name of the Item
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * This method sets the description of the Item
	 * @param description The new description of the Item
	 */
	public void setDescription(String description)
	{
		this.description = description; 
	}
	
	/**
	 * This method sets the type of the item
	 * @param type The new type of the item
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return name + " [ " + type + " ] : " + description;  
	}

}
