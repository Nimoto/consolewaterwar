package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author User
 */
public class ConsoleWaterWar {

    public static int SIDE_SIZE = 10;
    public static int SHIP_COUNT = 10;
    public static int SHIP_MAX_CELL_COUNT = 4;

    private static Map generateBotMap() {
        Ship[] ships = new Ship[SHIP_COUNT];
        int i = 0, cellCount = SHIP_MAX_CELL_COUNT, count = 1;

        while (i < SHIP_COUNT) {
            for (int j = 0; j < count; j++) {
                ships[i] = new Ship(cellCount);
                i++;
            }
            count++;
            cellCount--;
        }

        Map map = new Map(SIDE_SIZE, ships);
        map.generate();
        return map;
    }

    public static void main(String[] args) {
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
                ;
            }
        }

        if (user.getFail()) {
            System.out.println(user.getWinCaption());
        } else {
            System.out.println(bot.getWinCaption());
        }
    }
}
