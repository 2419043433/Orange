package com.orange.model.db;

public interface IItem
{
	int getInt(int key);
	int getInt(String key);
	String getString(int key);
	String getString(String key);
}