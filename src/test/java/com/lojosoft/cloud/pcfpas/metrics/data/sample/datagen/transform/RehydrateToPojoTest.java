package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.transform;

import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.Foundation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RehydrateToPojoTest {

    static Logger logger = LoggerFactory.getLogger(RehydrateToPojoTest.class);
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void rehydrate() {
        List<List<String>> rows = new ArrayList<List<String>>();
        List<String> row = new ArrayList<>();

        row.addAll(Arrays.asList("/ORG/SPACE/APP/0","500","1000"));

        rows.add(row);

        Foundation f = RehydrateToPojo.rehydrate(rows);

        Assert.assertEquals("ORG",f.getOrgs().get(0).getOrgName());
        Assert.assertEquals("SPACE",f.getOrgs().get(0).getSpaces().get(0).getSpaceName());
        Assert.assertEquals("APP",f.getOrgs().get(0).getSpaces().get(0).getApps().get(0).getAppName());
        Assert.assertEquals("0",f.getOrgs().get(0).getSpaces().get(0).getApps().get(0).getInstances().get(0).getInstanceName());

    }
}