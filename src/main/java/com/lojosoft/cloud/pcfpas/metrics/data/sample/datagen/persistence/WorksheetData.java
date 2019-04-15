package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.persistence;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorksheetData {
    private String worksheetName;
    private List<List<String>> rows;

    public WorksheetData() {
        rows = new ArrayList<>();
    }

}
