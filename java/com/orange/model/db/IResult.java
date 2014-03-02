package com.orange.model.db;

import com.orange.model.db.Item;
import com.orange.model.db.ErrorCode;

public interface IResult
{
	public int getItemCount();
	public Item at(int index);
	public ErrorCode getError();
	//TODO: add iterator support
}