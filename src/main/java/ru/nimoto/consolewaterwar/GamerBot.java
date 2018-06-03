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
    
    protected enum Title {
        MAP_TITLE {
            @Override
            public String toString(){
                return "Your enemy map:";
            }                
        },
        X_TITLE {
            @Override
            public String toString(){
                return "x:";
            }                
        },
        Y_TITLE {
            @Override
            public String toString(){
                return "y:";
            }                
        },
        WIN_TITLE {
            @Override
            public String toString(){
                return "You lose. Bye-bye, loser.";
            }                
        }
    }
    
    public GamerBot(Map map) {
        super(map);
        this.r = new Random();
    }

    @Override
    public String getEnemyMapTitle() {
        return "Your map:";
    }

    @Override
    public String getTurnCaption() {
        return "Enemy turn:";
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
