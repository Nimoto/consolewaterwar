package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author User
 */
public class ConsoleWaterWar {

    public static Properties getConfig() throws IOException {
        FileInputStream configFile = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(configFile);
        return properties;
    }

    private static Map generateBotMap() throws IOException {
        Properties properties = getConfig();
        int shipCount = Integer.getInteger(properties.getProperty("main.SHIP_COUNT"));
        int shipMaxCellCount = Integer.getInteger(properties.getProperty("main.SHIP_MAX_CELL_COUNT"));
        int sideSize = Integer.getInteger(properties.getProperty("main.SIDE_SIZE"));
        Ship[] ships = new Ship[shipCount];
        int i = 0, cellCount = shipMaxCellCount, count = 1;

        while (i < shipCount) {
            for (int j = 0; j < count; j++) {
                ships[i] = new Ship(cellCount);
                i++;
            }
            count++;
            cellCount--;
        }

        Map map = new Map(sideSize, ships);
        map.generate();
        return map;
    }

    public static void main(String[] args) throws IOException {
        Map userMap = generateBotMap();
        Gamer user = new Gamer(userMap);
        GamerBot bot = new GamerBot(generateBotMap());
        System.out.println(user.getMapCaption());
        userMap.print(true);
        while (!user.getFail() && !bot.getFail()) {
            while (user.step() && !user.getFail()) {
            }
            if (!user.getFail()) {
                while (bot.step() && !bot.getFail()) {
                }
            }
        }

        if (user.getFail()) {
            System.out.println(user.getWinCaption());
        } else {
            System.out.println(bot.getWinCaption());
        }
    }
}
