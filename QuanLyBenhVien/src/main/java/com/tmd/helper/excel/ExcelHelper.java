package com.tmd.helper.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tmd.dto.StaffDTO;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERS = { "Họ lót", "Tên", "Tài khoản", "Điện thoại", "Email", "Trạng thái", "Loại", "Trình độ",
			"Chức vụ", "Đơn vị" };
	static String SHEET = "danhmucnhanvien";

	public static ByteArrayInputStream staffToExcel(List<StaffDTO> staffs) {

		try (Workbook workbook = new XSSFWorkbook()) {
			// style
			CellStyle style = workbook.createCellStyle();
		    Font font = workbook.createFont();
		    font.setBold(true);;
		    style.setFont(font);
		    
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Sheet sheet = workbook.createSheet(SHEET);
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERS.length; col++) {
				//sheet.autoSizeColumn(col);
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERS[col]);
				cell.setCellStyle(style);
			}
			int rowIdx = 1;
			for (StaffDTO staff : staffs) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(staff.getHolot());
				row.createCell(1).setCellValue(staff.getTen());
				row.createCell(2).setCellValue(staff.getTaikhoan());
				row.createCell(3).setCellValue(staff.getMobile());
				row.createCell(4).setCellValue(staff.getEmail());
				row.createCell(5).setCellValue(staff.getTrangthai());
				row.createCell(6).setCellValue(staff.getLoai());
				row.createCell(7).setCellValue(staff.getTrinhdo());
				row.createCell(8).setCellValue(staff.getChucvu());
				row.createCell(9).setCellValue(staff.getDonvi());
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());

		} catch (IOException e) {
			throw new RuntimeException("fail to import data staff to Excel file: " + e.getMessage());
		}
	}

}
