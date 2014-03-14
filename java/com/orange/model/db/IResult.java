package com.orange.model.db;


public interface IResult
{
	public int getItemCount();
	public IItem at(int index);
	public ErrorCode getError();
	//TODO: add iterator support
}