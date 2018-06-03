package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class ConsoleWaterWar {

    /**
     */
    enum Params {
        SIDE_SIZE(10),
        SHIP_COUNT(10),
        SHIP_MAX_CELL_COUNT(4);
        
        private int value;
        
        Params(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    private static Map generateBotMap() {
        Ship[] ships = new Ship[Params.SHIP_COUNT.getValue()];
        int i = 0, cellCount = Params.SHIP_MAX_CELL_COUNT.getValue(), count = 1;

        while (i < Params.SHIP_COUNT.getValue()) {
            for (int j = 0; j < count; j ++) {
                ships[i] = new Ship(cellCount);
                i ++;
            }
            count ++;
            cellCount --;
        }
        
        Map map = new Map(Params.SIDE_SIZE.getValue(), ships);
        map.generate();  
        return map;
    }
    
    public static void main(String[] args) {
        Map userMap = generateBotMap();
        System.out.println(Gamer.Title.MAP_TITLE);
        userMap.print(true);
        Gamer user = new Gamer(userMap);
        GamerBot bot = new GamerBot(generateBotMap());
        while (!user.getFail() && !bot.getFail()) {
            while (user.step() && !user.getFail()){}
            
            if (!user.getFail()) {
                while (bot.step() && !bot.getFail()){};
            }
        }
        
        if (user.getFail()) {
            System.out.println(Gamer.Title.WIN_TITLE);
        } else {
            System.out.println(GamerBot.Title.WIN_TITLE);            
        }
    }    
}
