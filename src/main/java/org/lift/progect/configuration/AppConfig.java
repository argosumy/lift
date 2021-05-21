package org.lift.progect.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {
    public final int AMOUNT_FLOOR_MAX;
    public final int AMOUNT_FLOOR_MIN;
    public final int AMOUNT_USERS_FLOOR;
    public final int LIFT_SIZE;


    public AppConfig() throws IOException {
        Properties prop = new Properties();
        InputStream in = Files.newInputStream(Paths.get("src/main/resources/application.properties"));
            prop.load(in);
            AMOUNT_FLOOR_MAX = Integer.parseInt(prop.getProperty("house.floor.amount.max"));
            AMOUNT_FLOOR_MIN = Integer.parseInt(prop.getProperty("house.floor.amount.min"));
            AMOUNT_USERS_FLOOR = Integer.parseInt(prop.getProperty("house.floor.amount.users"));
            LIFT_SIZE = Integer.parseInt(prop.getProperty("lift.size"));
    }
}