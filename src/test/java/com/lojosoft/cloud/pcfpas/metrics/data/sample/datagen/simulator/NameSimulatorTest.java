package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.simulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class NameSimulatorTest {

    Logger logger = LoggerFactory.getLogger(NameSimulatorTest.class);

    @Test
    public void getOrgName() {
        int i = 0;
        while ( i < 100) {
            String orgname=NameSimulator.getOrgName();
            logger.info(orgname);
            i++;
        }

    }
}