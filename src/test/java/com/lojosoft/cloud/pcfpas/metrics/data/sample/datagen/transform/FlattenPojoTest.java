package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.transform;

import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FlattenPojoTest {

    Foundation foundation;
    @Before
    public void setup() {
        List<Org> orgs = new ArrayList<>();
        List<Space> spaces = new ArrayList<>();
        List<App> apps = new ArrayList<>();
        List<Instance> instances = new ArrayList<>();

        foundation=new Foundation("GTA",orgs,"/",null);

        Org org = new Org("test_orgname",spaces,"/test_orgname",foundation);

        Space space = new Space("test-spacename",apps,org.getPath()+"/"+"test-spacename",org);

        App app = new App("test_appname",instances,space.getPath()+"/"+"test_appname",space);

        Instance instance0 = new Instance("0",app.getPath()+"/0",1000,578,app);
        Instance instance1 = new Instance("1",app.getPath()+"/1",1000,744,app);

        app.getInstances().add(instance0);
        app.getInstances().add(instance1);

        space.getApps().add(app);

        org.getSpaces().add(space);

        foundation.getOrgs().add(org);

    }

    @Test
    public void flatten() {
        List<List<String>> expectedRows = new ArrayList<List<String>>();

        expectedRows.add(Arrays.asList("/","1322","2000"));
        expectedRows.add(Arrays.asList("/test_orgname","1322","2000"));
        expectedRows.add(Arrays.asList("/test_orgname/test-spacename","1322","2000"));
        expectedRows.add(Arrays.asList("/test_orgname/test-spacename/test_appname","1322","2000"));
        expectedRows.add(Arrays.asList("/test_orgname/test-spacename/test_appname/0","578","1000"));
        expectedRows.add(Arrays.asList("/test_orgname/test-spacename/test_appname/1","744","1000"));

        List<List<String>> actual = FlattenPojo.flatten(foundation);

        Assert.assertEquals(expectedRows,actual);

    }
}