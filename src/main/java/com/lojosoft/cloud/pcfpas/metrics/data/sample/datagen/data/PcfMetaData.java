package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data;

import java.util.List;

public interface PcfMetaData {
    public String getPath();
    public Integer getMemAllocationMB();
    public Integer getMemActualConsumptionMB();
    public List<PcfMetaData> getMeAndAllChildrenAsList();
    public PcfMetaData getParent();

}
