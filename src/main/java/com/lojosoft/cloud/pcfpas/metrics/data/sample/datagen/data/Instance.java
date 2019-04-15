package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Instance implements PcfMetaData {
    private String instanceName;
    private String path;
    private Integer memAllocationMB;
    private Integer memActualConsumptionMB;
    private PcfMetaData parent;

    public List<PcfMetaData> getMeAndAllChildrenAsList() {
        List<PcfMetaData> all = new ArrayList<>();
        all.add(this);
        return all;
    }
}
