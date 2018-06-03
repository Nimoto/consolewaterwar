package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Ship {
    private int cellCount;
    private boolean status;
    private Cell[] cells;

    private boolean checkCellsSum() {
        int cellSum = 0;
        for (int i = 0; i < this.cellCount; i++) {
            if (cells[i].isActive()) {
                cellSum++;
            }
        }
        return cellSum != this.cellCount;
    }
    
    public Ship(int cellCount) {
        this.cellCount = cellCount;
        this.cells = new Cell[this.cellCount];
        this.status = true;
    }
    
    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public int getCellCount() {
        return this.cellCount;
    }

    public Boolean getStatus() {
        return this.status;
    }
    
    public void updateStatus() {
        this.status = this.checkCellsSum();
    }
}
