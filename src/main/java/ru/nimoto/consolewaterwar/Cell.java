package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    enum DrawParams {
        SHIP_NOT_ACTIVE('#'),
        SHIP_ACTIVE('X'),
        NOT_SHIP_ACTIVE('O'),
        NOT_SHIP_NOT_ACTIVE('*'),
        IS_LOCK('L');
        
        private char symbol;
        
        DrawParams(char symbol) {
            this.symbol = symbol;
        }
        
        public char getSymbol() {
            return this.symbol;
        }
    }
    
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
    
    void print(boolean isUserMap) {
        if (this.isActive() && this.isFree()) {
            System.out.print(DrawParams.NOT_SHIP_ACTIVE.getSymbol());
        } else if (this.isActive() && !this.isFree()) {
            System.out.print(DrawParams.SHIP_ACTIVE.getSymbol());            
        } else if (!this.isActive() && !this.isFree() && isUserMap) {
            System.out.print(DrawParams.SHIP_NOT_ACTIVE.getSymbol());            
        } else { 
            System.out.print(DrawParams.NOT_SHIP_NOT_ACTIVE.getSymbol());            
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
