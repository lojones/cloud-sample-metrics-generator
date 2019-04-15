package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.simulator;

import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.Foundation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class SimulatorTest {

    Logger logger = LoggerFactory.getLogger(SimulatorTest.class);

    Simulator simulator = new Simulator();

    @Test
    public void simulateFoundation() {
        Foundation foundation = simulator.simulateFoundation();
        Assert.assertNotNull(foundation);
        Assert.assertNotNull(foundation.getFoundationName());
        Assert.assertFalse(foundation.getFoundationName().contains(" "));
        Assert.assertTrue(foundation.getOrgs().size()>1);
        Assert.assertNotNull(foundation.getOrgs().get(0).getOrgName());
        Assert.assertTrue(foundation.getOrgs().get(0).getSpaces().size()>=1);
    }

    @Test
    public void simulateFoundation_sizeTest() {
        Foundation foundation = simulator.simulateFoundation();
        logger.info("Num AI's in foundation {} is {}",foundation.getFoundationName(), foundation.treesize());
        Assert.assertTrue(foundation.treesize()>=100);
    }
}