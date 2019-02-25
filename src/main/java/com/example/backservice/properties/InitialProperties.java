package com.example.backservice.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:/application.yml")
@Component
@Configuration
public class InitialProperties {

    public static String INPUT_DIR;

    public static String OUTPUT_DIR;

    public static String TEMP_DIR;

    public static String EXTENSION_FILE_INPUT;

    public static Integer WAIT_REACTIVITY_FOLDER;

    public static String AMOUNT_OF_CLIENTS;

    public static String AMOUNT_OF_SALEMAN;

    public static String MOST_EXPENSIVE_SALE;

    public static String WORST_SALEMAN;

    public static String NAME_FILE_OUTPUT;

    @Autowired
    public InitialProperties(
            @Value("#{'${backservice.read.homeData}'+'${backservice.read.inputDir}' }") String INPUT_DIR,
            @Value("#{'${backservice.read.homeData}'+'${backservice.write.outputDir}' }") String OUTPUT_DIR,
            @Value("#{'${backservice.read.homeData}'+'${backservice.read.tempDir}' }") String TEMP_DIR,
            @Value("${backservice.read.extension}") String EXTENSION_FILE_INPUT,
            @Value("${backservice.read.waitForReactivityOfFolders}") Integer WAIT_REACTIVITY_FOLDER,
            @Value("${backservice.write.msgAmountCustomer}") String AMOUNT_OF_CLIENTS,
            @Value("${backservice.write.msgAmountSaleman}") String AMOUNT_OF_SALEMAN,
            @Value("${backservice.write.msgMostSale}") String MOST_EXPENSIVE_SALE,
            @Value("${backservice.write.msgWorstSaleman}") String WORST_SALEMAN,
            @Value("${backservice.write.nameFileOutput}") String NAME_FILE_OUTPUT

    ) {
        InitialProperties.INPUT_DIR = INPUT_DIR;
        InitialProperties.OUTPUT_DIR = OUTPUT_DIR;
        InitialProperties.TEMP_DIR = TEMP_DIR;
        InitialProperties.EXTENSION_FILE_INPUT = EXTENSION_FILE_INPUT;
        InitialProperties.WAIT_REACTIVITY_FOLDER = WAIT_REACTIVITY_FOLDER;
        InitialProperties.AMOUNT_OF_CLIENTS = AMOUNT_OF_CLIENTS;
        InitialProperties.AMOUNT_OF_SALEMAN = AMOUNT_OF_SALEMAN;
        InitialProperties.MOST_EXPENSIVE_SALE = MOST_EXPENSIVE_SALE;
        InitialProperties.WORST_SALEMAN = WORST_SALEMAN;
        InitialProperties.NAME_FILE_OUTPUT = OUTPUT_DIR + NAME_FILE_OUTPUT;
    }
}
