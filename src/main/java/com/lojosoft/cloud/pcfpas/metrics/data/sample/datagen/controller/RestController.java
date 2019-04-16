package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.controller;

import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.Foundation;
import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.persistence.WorksheetData;
import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.persistence.XlsWriter;
import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.simulator.Simulator;
import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.transform.FlattenPojo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    Simulator simulator = new Simulator();

    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public ResponseEntity<Resource> getFile() throws IOException {
        Foundation foundation = simulator.simulateFoundation();
        List<List<String>> rows = FlattenPojo.flatten(foundation);
        WorksheetData worksheetData = new WorksheetData();
        worksheetData.setWorksheetName("Foundation-1");
        worksheetData.setRows(rows);

        ByteArrayOutputStream baos = XlsWriter.writeToStream(Arrays.asList(worksheetData));

        int size = baos.size();
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        Resource resource = new InputStreamResource(is);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pcf-mem-usage.xlsx")
                .contentLength(size)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }
}
