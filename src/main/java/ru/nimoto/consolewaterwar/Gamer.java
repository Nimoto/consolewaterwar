package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author User
 */
public class Gamer {

    protected Map map;
    protected boolean fail = false;
    protected int shipCount;

    public Gamer(Map map) {
        this.map = map; /*map of the enemy*/
        this.shipCount = this.map.getShipsCount();
    }

    public String getEnemyMapCaption() {
        return "Your enemy map:";
    }

    public String getTurnCaption() {
        return "Your turn:";
    }

    public String getXCaption() {
        return "x:";
    }

    public String getYCaption() {
        return "y:";
    }

    public String getWinCaption() {
        return "========YOU WIN!!!!!!!!!!!!!!!!!!!!!!!!!=============";
    }

    public String getMapCaption() {
        return "Your map:";
    }

    protected int setCoordinate() {
        int coord = -1;
        boolean error = true;
        while (error) {
            error = false;
            try {
                Scanner in = new Scanner(System.in);
                coord = in.nextInt();
            } catch (InputMismatchException e) {
                error = true;
                System.out.println(Status.INDEX_OUT.toString());
            }
        }
        return coord;
    }

    protected void printMap() {
        this.map.print(false);
    }

    public boolean step() {
        int x, y;
        Status status;
        boolean continuing = true;
        System.out.println(getEnemyMapCaption());
        this.printMap();
        System.out.println(getTurnCaption());
        do {
            System.out.print(getXCaption());
            x = setCoordinate();
            System.out.print(getYCaption());
            y = setCoordinate();
            System.out.println();
            status = this.map.fire(x, y);
            switch (status) {
                case INDEX_OUT:
                    System.out.println(Status.INDEX_OUT.toString());
                    break;
                case DOUBLE:
                    System.out.println(Status.DOUBLE.toString());
                    break;
                case HURT:
                    System.out.println(Status.HURT.toString());
                    break;
                case DIE:
                    System.out.println(Status.DIE.toString());
                    this.shipCount--;
                    break;
                default:
                    System.out.println(Status.MILK.toString());
                    continuing = false;
                    break;
            }
        } while (status == Status.INDEX_OUT || status == Status.DOUBLE);
        return continuing;
    }

    public boolean getFail() {
        if (this.shipCount == 0) {
            this.fail = true;
        }
        return this.fail;
    }
}
