package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author User
 */
public class GamerBot extends Gamer{
    
    private Random r;
    
    public GamerBot(Map map) {
        super(map);
        this.r = new Random();
    }

    @Override
    public String getEnemyMapCaption() {
        return "Your map:";
    }

    @Override
    public String getTurnCaption() {
        return "Enemy turn:";
    }

    @Override
    public String getXCaption() {
        return "x:";
    }

    @Override
    public String getYCaption() {
        return "y:";
    }

    @Override
    public String getWinCaption() {
        return "========YOU WIN!!!!!!!!!!!!!!!!!!!!!!!!!=============";
    }

    @Override
    public String getMapCaption() {
        return "Your map:";
    }

    @Override
    protected void printMap() {
        this.map.print(true);
    }
    
    @Override
    protected int setCoordinate() {
        int coord = r.nextInt(this.map.getSideSize() - 1);
        System.out.println(coord);
        return coord;
    }
}
