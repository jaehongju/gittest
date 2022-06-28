package com.youhost.common.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtil {
	public static void setCellValue(Sheet sheet, int rowIndex, int cellIndex, Object value) throws Exception {
		Row row = sheet.getRow(rowIndex);
		if(row==null) return;
		Cell cell = row.getCell(cellIndex);
		if(cell==null) return;
		if(value instanceof Double){
			cell.setCellValue((Double)value);
		}else if(value instanceof Integer){
			cell.setCellValue(""+(Integer)value);
		}else if(value instanceof Float){
			cell.setCellValue(""+(Float)value);
		}else{
			cell.setCellValue((String)value);
		}
	}
	
	public static void createRow(Sheet sheet, int rowIndex, int colCount, CellStyle cellStyle) throws Exception {
		Row row = sheet.createRow(rowIndex);
		for(int i=0; i<colCount; i++){
			row.createCell(i);
			row.getCell(i).setCellStyle(cellStyle);
		}
	}
	
	public static void setRowValue(Sheet sheet, int rowIndex, Object[] values) throws Exception {
		Row row = sheet.getRow(rowIndex);
		if(row==null || values==null || values.length==0) return;
		for(int i=0; i<values.length;i++){
			setCellValue(sheet, rowIndex, i, values[i]);
		}
	}
}
