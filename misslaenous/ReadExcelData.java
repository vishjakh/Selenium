package vishal.github;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fisProp = new FileInputStream(System.getProperty("user.dir")+"//Resources//main.properties");
		
		Properties p = new Properties();
		p.load(fisProp);
		
		FileInputStream fisCsv = new FileInputStream(p.getProperty("csvPath"));
		
		XSSFWorkbook book = new XSSFWorkbook(fisCsv);
		
		XSSFSheet sheet = book.getSheetAt(0);
		
		int totalRows= sheet.getLastRowNum() - sheet.getFirstRowNum();
		int totalColumn = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();

		for (int i=1;i<totalRows; i++ ) {
			for(int j=0;j<totalColumn;j++) {
				System.out.print(sheet.getRow(i).getCell(j)+"\t");
			}
			System.out.println("\n");
		}
		
		
	
	}

}
