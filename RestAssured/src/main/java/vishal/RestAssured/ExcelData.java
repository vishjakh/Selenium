package vishal.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	public XSSFSheet getSheet() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/resources/config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String filepath = prop.getProperty("filepath");
		System.out.println("FilePath is" + filepath);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(filepath);
		
		XSSFSheet  sheet= workbook.getSheet("Sheet1");
		
		return sheet;
	}
	
	public int getTotalRows() throws IOException {
		int rowCount=getSheet().getLastRowNum()-getSheet().getFirstRowNum();
		return rowCount;
	}
	
	public int getCellCount() throws IOException {
		int cellCount=0;
		for(int i=0;i<1;i++) {
			cellCount=getSheet().getRow(i).getLastCellNum();
		}
		return cellCount;
	}

}
