package com.myapp.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAndReadAllFile
{

	/** 
	 * 获取文件的扩展名 
	 *  
	 * @param filename 
	 * @return 
	 */
	public static String getExtension(String filename)
	{
		if ((filename != null) && (filename.length() > 0))
		{
			int i = filename.lastIndexOf('.');

			if ((i > -1) && (i < (filename.length() - 1)))
			{
				return filename.substring(i + 1).toLowerCase();
			}
		}

		return "";
	}

	/** 
	 * 获取一个文件夹下的所有文件 要求：后缀名为txt (可自己修改) 
	 *  
	 * @param file 
	 * @return 
	 */
	public static List<String> getFileList(File file)
	{
		List<String> result = new ArrayList<String>();
		if (!file.isDirectory())
		{
			System.out.println(file.getAbsolutePath());
			result.add(file.getAbsolutePath());
		}
		else
		{
			// // 内部匿名类，用来过滤文件类型
			File[] directoryList = file.listFiles(new FileFilter()
			{
				public boolean accept(File file)
				{
					return Arrays.asList("ttf", "ttc").contains(
							getExtension(file.getName()));

					// return file.isFile();
				}
			});

			// File[] fileList = file.listFiles(new FileFilter()
			// {
			// public boolean accept(File file)
			// {
			// return file.isFile();
			// }
			// });

			for (File childFile : directoryList)
			{
				result.add(childFile.getAbsolutePath());
			}
		}
		return result;
	}

	public static List<String> getAllFontList()
	{
		File file = new File("C:\\Windows\\Fonts");

		return getFileList(file);
	}

	/** 
	 * @param args 
	 */
	public static void main(String[] args) throws IOException
	{
		File file = new File("C:\\Windows\\Fonts");

		List<String> childList = getFileList(file);

		System.out.println("文件数目：" + childList.size());

		if (childList != null)
		{
			for (String filePath : childList)
			{
				System.out.println(filePath);
			}
		}
	}
}
