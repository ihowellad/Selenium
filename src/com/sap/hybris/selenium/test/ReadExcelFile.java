package com.sap.hybris.selenium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	XSSFWorkbook wb;
	XSSFSheet sheet;

	public ReadExcelFile(String excelPath) {
		
		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getData(int sheetnumber, int row, int column) {
		sheet = wb.getSheetAt(sheetnumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public int getRowCount(int sheetindex) {
		int row = wb.getSheetAt(sheetindex).getLastRowNum();
		row = row + 1;
		return row;
	}
	
}
