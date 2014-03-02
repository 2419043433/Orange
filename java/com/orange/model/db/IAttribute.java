package com.orange.model.db;

import com.orange.model.db.Item;
import com.orange.model.db.ErrorCode;

public interface IAttribute
{
	public final int TypeString = 0;
	public final int TypeInt = 1;
	public final int TypeBool = 2;
	
	String asString();
	int asInt();
	boolean asBool();
}