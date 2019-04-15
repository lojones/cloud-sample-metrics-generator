package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.persistence;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsWriter {


    public static void write(String filename, List<WorksheetData> worksheets) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        FileOutputStream f = new FileOutputStream(filename);

        for (WorksheetData worksheetData : worksheets) {

            Sheet xlsSheet = workbook.createSheet(worksheetData.getWorksheetName());

            for (int i=0;i<worksheetData.getRows().size();i++) {

                Row xlsRow = xlsSheet.createRow(i);

                List<String> rowData = worksheetData.getRows().get(i);

                for (int j = 0;j < rowData.size();j++) {

                    Cell xlsCell = xlsRow.createCell(j);
                    xlsCell.setCellValue(rowData.get(j));
                }
            }

        }

        workbook.write(f);

    }
}
