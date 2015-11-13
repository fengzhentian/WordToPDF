package com.myapp.io;

public class GetLibraryPath
{

	public static void main(String[] args)
	{
		String property = System.getProperty("java.library.path");

		System.out.println(property);
	}

}
