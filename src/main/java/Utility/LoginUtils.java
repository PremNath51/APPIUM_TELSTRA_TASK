package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoginUtils 
{
	
	   private static XSSFSheet ExcelWSheet;
	   private static XSSFWorkbook ExcelWBook;
	   private static XSSFCell Cell;
	
	// Help to retereive and read the table in spreadsheet
	   
public static String[][] getTableArray(String FilePath, String SheetName) throws Exception 
{   

	       String[][] tabArray = null;

 try
 {

	        // This function is help to read the file from spreadsheet
	 
	         FileInputStream ExcelFile = new FileInputStream(FilePath);

	         ExcelWBook = new XSSFWorkbook(ExcelFile);

	         ExcelWSheet = ExcelWBook.getSheet(SheetName);

	         int startRow = 1;

	         int startCol = 1;

	         int ci,cj;

	         int totalRows = 1;

	        // This is help to get the column count

	        int totalCols = 4;

	        tabArray=new String[totalRows][totalCols];

	        ci=0;

	        for (int i=startRow;i<=totalRows;i++, ci++) 
	        {           	   

		      cj=0;

		       for (int j=startCol;j<=totalCols;j++, cj++)
		       {

			       tabArray[ci][cj]=getCellData(i,j);

			       System.out.println(tabArray[ci][cj]);  

			   }

			}

		}

	   catch (FileNotFoundException e)
  {

		  System.out.println("Could not read the Excel sheet");

		  e.printStackTrace();

		}

	  catch (IOException e)
   {

		  System.out.println("Could not read the Excel sheet");

		  e.printStackTrace();

	  }

	  return(tabArray);

	}

// Help to retereive the row data from the eachrow

public static String getCellData(int RowNum, int ColNum) throws Exception 
{

	     String CellData = "";
	  
	  try
	  {
	 	
	 	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		

  if(Cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
  {
      CellData = String.format("%.0f", Cell.getNumericCellValue());

	 }
    if(Cell.getCellType()== XSSFCell.CELL_TYPE_STRING)
    {
       CellData = String .format("%s", Cell.getRichStringCellValue());
    }

     return CellData;
	  }
	  catch (Exception e)
	  {

		   System.out.println(e.getMessage());

		   throw (e);

	  }

	}

public static int getRowUsed()
{
	   // TODO Auto-generated method stub
	   return 0;
}

}
