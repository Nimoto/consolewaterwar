package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author User
 */
public class Cell {
    private boolean active;
    private boolean lock;
    private boolean free;
    private int x;
    private int y;
    private Ship ship;
    
    Cell(int x, int y) {
        this.active = false;
        this.lock = false;
        this.free = true;
        this.setPosition(x, y);
    }
    
    void activate() {
        this.active = true;
    }
    
    void lock() {
        this.lock = true;
    }
    
    void fill() {
        this.free = false;
    }
        
    private void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    int getPositionX() {
        return this.x;
    }
    
    int getPositionY() {
        return this.y;
    }
    
    boolean isLock() {
        return this.lock;
    }
    
    boolean isActive() {
        return this.active;
    }
    
    boolean isFree() {
        return this.free;
    }
    
    void print(boolean isUserMap) throws IOException {
        Properties properties = ConsoleWaterWar.getConfig();
        if (this.isActive() && this.isFree()) {
            System.out.print(properties.getProperty("cell.NOT_SHIP_ACTIVE"));
        } else if (this.isActive() && !this.isFree()) {
            System.out.print(properties.getProperty("cell.SHIP_ACTIVE"));
        } else if (!this.isActive() && !this.isFree() && isUserMap) {
            System.out.print(properties.getProperty("cell.SHIP_NOT_ACTIVE"));
        } else { 
            System.out.print(properties.getProperty("cell.NOT_SHIP_NOT_ACTIVE"));
        }
        System.out.print(' ');
    }
    
    void setShip(Ship ship) {
        this.ship = ship;
    }
    
    Status fire() {
        if (this.isActive()) {
            return Status.DOUBLE;
        } else {
            this.activate();
            if (this.isFree()) {
                return Status.MILK;
            } else {
                this.ship.updateStatus();
                if (!this.ship.getStatus()) {
                    return Status.DIE;
                } else {
                    return Status.HURT;
                }            
            }
        }
    }
}
