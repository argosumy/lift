package org.lift.progect.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {
    public static final int AMOUNT_FLOOR_MAX;
    public static final int AMOUNT_FLOOR_MIN;
    public static final int AMOUNT_USERS_FLOOR;
    public static final int LIFT_SIZE;

    static {
        Properties prop = new Properties();
        try (
                InputStream in = Files.newInputStream(Paths.get("src/main/resources/application.properties"))) {
            prop.load(in);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        AMOUNT_FLOOR_MAX = Integer.parseInt(prop.getProperty("house.floor.amount.max"));
        AMOUNT_FLOOR_MIN = Integer.parseInt(prop.getProperty("house.floor.amount.min"));
        AMOUNT_USERS_FLOOR = Integer.parseInt(prop.getProperty("house.floor.amount.users"));
        LIFT_SIZE = Integer.parseInt(prop.getProperty("lift.size"));
    }
}
