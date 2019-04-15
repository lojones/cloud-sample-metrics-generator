package com.lojosoft.cloud.pcfpas.metrics.data.sample.datagen.simulator;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class NameSimulator {

    static Logger logger = LoggerFactory.getLogger(NameSimulator.class);

    static Set<Character> consonants = new HashSet<Character>();
    static Set<Character> vowels = new HashSet<Character>();
    static List<String> nonProdEnvNames = new ArrayList<>();
    static List<String> prodEnvNames = new ArrayList<>();


    static Random random = new Random(64643234);

    static Faker faker = new Faker();

    static {
        logger.info("creating vowels and consonants");
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');

        for (int i=97;i <= 122;i++){
            if (! vowels.contains((char)i)) {
                consonants.add((char)i);
            }
        }

        nonProdEnvNames.add("DEV");
        nonProdEnvNames.add("QA");
        nonProdEnvNames.add("UAT");
        nonProdEnvNames.add("BLUE");
        nonProdEnvNames.add("GREEN");
        nonProdEnvNames.add("IST");
        nonProdEnvNames.add("PERF");
        nonProdEnvNames.add("SANDBOX");
        nonProdEnvNames.add("BRANCH_A");
        nonProdEnvNames.add("BRANCH_B");
        nonProdEnvNames.add("MASTER");

        prodEnvNames.add("PROD");
        prodEnvNames.add("PROD_BLUE");
        prodEnvNames.add("PROD_GREEN");
        prodEnvNames.add("PROD_PERF");

    }

    private static String getRandomChar(Set<Character> charset) {
        int max=charset.size();
        int i=1;
        int r = random.nextInt(max)+1;
        for (char c : charset) {
            if (i==r) {
                return String.valueOf(c);
            }
            i++;
        }
        return "B";
    }

    private static String getConsonant() {
        return getRandomChar(consonants);
    }

    private static String getVowel() {
        return getRandomChar(vowels);
    }

    private static String getRandomNum() {
        return String.valueOf(random.nextInt(10));
    }

    public static String getOrgName() {
        return String.valueOf(getConsonant()+getVowel()+getConsonant()+getRandomNum()).toUpperCase();
    }

    public static String getAppName() {
        return faker.app().name();
    }

    public static String getNonProdEnvName() {
        int i = random.nextInt(nonProdEnvNames.size());
        return nonProdEnvNames.get(i);
    }

    public static String getProdEnvName() {
        int i = random.nextInt(prodEnvNames.size());
        return prodEnvNames.get(i);
    }

    public static List<String> getSimpleNonProdEnvs() {
        return Arrays.asList(nonProdEnvNames.get(0),nonProdEnvNames.get(1),nonProdEnvNames.get(2));
    }

    public static List<String> getFullNonProdEnvs() {
        return nonProdEnvNames;
    }

    public static List<String> getSimpleProdEnvs() {
        return Arrays.asList(nonProdEnvNames.get(0));
    }

    public static List<String> getFullProdEnvs() {
        return prodEnvNames;
    }
}
