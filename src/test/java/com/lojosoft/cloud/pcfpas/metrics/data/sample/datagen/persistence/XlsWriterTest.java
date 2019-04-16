package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class XlsWriterTest {

    WorksheetData worksheetData = new WorksheetData();

    @Before
    public void setup() {
        worksheetData.setWorksheetName("TestWorkSheetName");
        worksheetData.getRows().add(Arrays.asList("/","1500","3000"));
        worksheetData.getRows().add(Arrays.asList("/ORG","1500","3000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE1","1000","2000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE1/APP","1000","2000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE1/APP/0","600","1000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE1/APP/1","500","1000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE2","500","1000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE2/APP2","500","1000"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE2/APP2/0","300","500"));
        worksheetData.getRows().add(Arrays.asList("/ORG/SPACE2/APP2/1","200","500"));


    }

    @Test
    public void write() throws IOException {
        List<List<String>> rows = new ArrayList<List<String>>();
        List<String> row = Arrays.asList("/ORG/SPACE/APP/0","490");
        XlsWriter.writeToFile("test.xlsx",Arrays.asList(worksheetData));
    }
}