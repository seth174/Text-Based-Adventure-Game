import java.util.ArrayList;

/** 
 * The ContainerItem class is a special type of the Item class. It is an Item that can be placed
 * around the virtual world,but it can also store other items inside of it. It is a container
 * of Items.
 * @author sethfagen_2022
 *
 */
public class ContainerItem extends Item
{
	private ArrayList<Item> items;
	
	/**
	 * This constructor takes a name, description, and type to call the superclass
	 * Item constructor and initializes an array of Items that it has the ability to store
	 * @param name what the ContainerItem is called
	 * @param description a description of the ContainerItem
	 * @param type The type of the ContainerItem
	 */
	public ContainerItem(String name, String description, String type)
	{
		super(name, description, type);
		items = new ArrayList<Item>();
	}
	
	/**
	 * This method adds an Item to the ContainerItem.
	 * @param The item that will be added to inside of the ContainerItem
	 */
	public void addItem(Item name)
	{
		items.add(name);
	}
	
	/**
	 * This method will return whether or not the ContainerItem is storing an Item.
	 * @param name the name of the Item that is being looked for inside of the ContainerItem
	 * @return true or false whether the Item is in the ContainerItem
	 */
	public boolean hasItem(String name)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	/**
	 * This method will remove an Item from inside of the ContainerItem 
	 * and return the Item
	 * @param name The name of the Item that wants to be removed
	 * @return will return null if the Item is not inside of the ContainerItem or the Item being removed
	 */
	public Item removeItem(String name)
	{
		Item temp = null;
		if(this.hasItem(name) == false)
			return null;
		else
		{
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getName().equalsIgnoreCase(name))
				{
					temp = items.get(i);
					items.remove(i);
					return temp;
				}
			}
		}
		return temp;
	}
	
	/**
	 * Will return whether or not there are items inside of the ContainerItem
	 * @return true or false if items are inside of the ContainerItem
	 */
	public boolean isEmpty()
	{
		if(items.size() == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		String temp= super.toString();
		for(int i = 0; i < items.size(); i++)
		{
			temp = temp + "\n" + items.get(i).getName();			
		}
		return temp;
	}

}
