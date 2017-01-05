package ie.gmit.sw.controller;

import java.util.LinkedList;
import java.util.List;

public class JarSet
{
	
	List<Class<?>> list = new LinkedList<Class<?>>();

	public int size()
	{
		return list.size();
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public boolean contains(Class<?> cls) 
	{
		return list.contains(cls);
	}

	public boolean add(Class<?> e) 
	{
		return list.add(e);
	}

	public Class<?> get(int index)
	{
		return list.get(index);
	}
	
}// End class JarSet
