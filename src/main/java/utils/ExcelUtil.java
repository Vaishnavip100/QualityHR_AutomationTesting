package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtil {

    public static Object[][] getData(String filePath, String sheetName) {
        Object[][] data=null;
        try {
            FileInputStream fis=new FileInputStream(filePath);
            Workbook wb=WorkbookFactory.create(fis);
            Sheet sheet=wb.getSheet(sheetName);

            int rows=sheet.getPhysicalNumberOfRows();
            int cols=sheet.getRow(0).getPhysicalNumberOfCells();

            data=new Object[rows - 1][cols];

            for (int i=1;i<rows;i++) {
                for (int j=0;j<cols;j++) {
                    data[i-1][j]=sheet.getRow(i).getCell(j).toString();
                }
            }
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}