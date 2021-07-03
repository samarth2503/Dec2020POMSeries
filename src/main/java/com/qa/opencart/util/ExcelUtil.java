package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static String Test_Data_Sheet="C:\\Users\\samarth.jain\\eclipse-workspace\\Dec2020POMSeies\\src\\test\\resources\\ExcelData\\DataProvider.xlsx";
	
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getData(String sheetname)
	{
		Object data[][]=null;
		try {
			FileInputStream ip = new FileInputStream(Test_Data_Sheet);
			
			book = WorkbookFactory.create(ip);
			
			sheet = book.getSheet(sheetname);
			
			System.out.println("Sheet Number is "+book.getSheetIndex(sheetname));
			
			int row = sheet.getPhysicalNumberOfRows();
			System.out.println("Rows are "+row);
			int col = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("Columns are "+col);
			data= new Object[row-1][col];
		
			for(int i=1;i<row;i++)
			{
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					
					try {
						String a = sheet.getRow(i).getCell(j).toString();
						System.out.println("Data is "+a);
						data[i-1][j]=sheet.getRow(i).getCell(j).toString();
					/*	if(a.equals("##"))
						{
							break;
						}
						else
						{
							
						} */
						
					}
					catch(NullPointerException e)
					{
						break;
					}
				}
			}
		
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		System.out.println(data);
		
		return data;
	}
	
	
}
