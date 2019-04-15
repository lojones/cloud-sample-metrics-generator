package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.simulator;

import com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {

    List<Foundation> foundations = new ArrayList<>();
    Random random = new Random(74667949);

    public Foundation simulateFoundation() {

        List<Org> orgs = new ArrayList<>();

        Foundation foundation = new Foundation("GTA",orgs,"/",null);

        List<String> orgNames = new ArrayList<>();
        for (int i=0;i<100;i++){
            String orgName=NameSimulator.getOrgName();
            if (!orgNames.contains(orgName)) {
                orgs.add(simulateOrg(orgName, foundation));
            }
        }
        return foundation;
    }

    private Org simulateOrg(String orgName,Foundation parent) {

        List<String> spaceNames;

        List<Space> spaces = new ArrayList<>();

        Org org = new Org(orgName,spaces,parent.getPath()+"/"+orgName,parent);

        Integer a = random.nextInt(4);

        if (a==0) {
            spaceNames = NameSimulator.getFullNonProdEnvs();
        }
        else if (a==1) {
            spaceNames = NameSimulator.getFullProdEnvs();
        }
        else if (a==2) {
            spaceNames = NameSimulator.getSimpleNonProdEnvs();
        }
        else {
            spaceNames = NameSimulator.getSimpleProdEnvs();
        }

        for (String spaceName : spaceNames){
            String fullName = org.getOrgName()+"_"+NameSimulator.getNonProdEnvName();
            spaces.add(simulateSpace(fullName,org));
        }
        return org;
    }

    private Space simulateSpace(String spaceName, Org parent) {

        List<App> apps = new ArrayList<>();
        Space space = new Space(spaceName,apps,parent.getPath()+"/"+spaceName,parent);
        int numApps = random.nextInt(50)+1;

        for (int i=0;i<numApps;i++) {
            apps.add(simulateApp(space.getPath(),space));
        }
        return space;
    }

    private App simulateApp(String parentPath,Space parent) {
        int numInstances = random.nextInt(10)+1;
        int memAllocGB = random.nextInt(10)+1;
        String appName=NameSimulator.getAppName();
        List<Instance> instances = new ArrayList<>();

        App app = new App(appName,instances,parentPath+"/"+appName,parent);

        for (Integer i=1;i<=numInstances;i++){
            instances.add(simulateInstance(i.toString(),memAllocGB*1000,app));
        }
        return app;
    }

    private Instance simulateInstance(String name, Integer memAllocMB, App parent) {
        Instance instance = new Instance(name,parent.getPath()+"/"+name,memAllocMB,random.nextInt(memAllocMB),parent);
        return instance;
    }
}
