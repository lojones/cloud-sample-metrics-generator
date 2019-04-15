package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.transform;

import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.PcfMetaData;
import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.Foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlattenPojo {
    public static List<List<String>> flatten(Foundation foundation) {
        List<List<String>> rows = new ArrayList<List<String>>();
        List<PcfMetaData> allPojos = foundation.getMeAndAllChildrenAsList();
        for (PcfMetaData data: allPojos) {
            rows.add(Arrays.asList(data.getPath(),data.getMemActualConsumptionMB().toString(), data.getMemAllocationMB().toString()));
        }
        return rows;
    }
}
