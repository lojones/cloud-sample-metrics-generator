package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.persistence;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsWriter {

    public static Workbook getWorkbook(List<WorksheetData> worksheets) {
        Workbook workbook = new XSSFWorkbook();


        for (WorksheetData worksheetData : worksheets) {

            Sheet xlsSheet = workbook.createSheet(worksheetData.getWorksheetName());

            Row headerRow = xlsSheet.createRow(0);
            headerRow.createCell(0).setCellValue("Path");
            headerRow.createCell(1).setCellValue("Actual Mem Consumed");
            headerRow.createCell(2).setCellValue("Allocated Mem");

            for (int i=1;i<=worksheetData.getRows().size();i++) {

                Row xlsRow = xlsSheet.createRow(i);

                List<String> rowData = worksheetData.getRows().get(i-1);

                for (int j = 0;j < rowData.size();j++) {

                    Cell xlsCell = xlsRow.createCell(j);

                    if (j==0) {
                        String data = rowData.get(j);
                        xlsCell.setCellValue(data);
                    } else {
                        Integer data = Integer.valueOf(rowData.get(j));
                        xlsCell.setCellValue(data);
                    }
                }
            }
        }

        return workbook;

    }

    public static void writeToFile(String filename, List<WorksheetData> worksheets) throws IOException {
        Workbook workbook = getWorkbook(worksheets);

        FileOutputStream f = new FileOutputStream(filename);

        workbook.write(f);

    }

    public static ByteArrayOutputStream writeToStream(List<WorksheetData> worksheets) throws IOException {
        Workbook workbook = getWorkbook(worksheets);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        workbook.write(baos);

        return baos;

    }
}
