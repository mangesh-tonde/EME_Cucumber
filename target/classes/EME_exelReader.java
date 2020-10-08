package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EME_exelReader 
{
	public ArrayList<String> getData(String parameter) throws IOException
	{

	    //FileInputSteam - file handler
	    FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\test-data\\EME-datasheet.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		
		int xlsheetcount= workbook.getNumberOfSheets();

		ArrayList<String> a = new ArrayList();
		
		for(int i=0;i<xlsheetcount;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Wishlist"))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);
				//identify column by scanning entire row.
			
				Iterator<Row> rw = sheet.iterator();//Sheet collection of rows
				Row firstrow = rw.next();
				
				Iterator<Cell> ce = firstrow.cellIterator();//row collection of cell
				int k=0;
				int column=0;
				while(ce.hasNext())
				{
					Cell value = ce.next();
					{
						if(value.getStringCellValue().equalsIgnoreCase("Testdata"))
						{
							column=k;
						}
					}
					k++;
				}
			 System.out.println(column);
			 
			 	while(rw.hasNext())
			 	{
			 		Row r = rw.next();
				 	if(r.getCell(column).getStringCellValue().equalsIgnoreCase(parameter))
				 	{
				 		Iterator<Cell> cv=r.cellIterator();
				 		while(cv.hasNext())
				 			
				 		{
				 			Cell c = cv.next();
				 			if(c.getCellTypeEnum()==CellType.STRING)
				 			{
				 				a.add(c.getStringCellValue());
				 			}
				 			else
				 			{
				 				a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
				 			}
				 		}
				 	}
			 	}
			 }
			
		}
		return a;
	}
}
