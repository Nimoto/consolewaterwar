package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Random;

/**
 * @author User
 */
public class Map {

    private int sideSize;
    private Ship[] ships;
    private Cell[][] map;

    public Map(int sideSize, Ship[] ships) {
        this.sideSize = sideSize;
        this.ships = ships;
        this.map = new Cell[this.sideSize][this.sideSize];
        for (int i = 0; i < this.sideSize; i++) {
            for (int j = 0; j < this.sideSize; j++) {
                map[i][j] = new Cell(i, j);
            }
        }
    }

    public void generate() {
        for (Ship ship : this.ships) {
            Cell[] cells = fillShipCells(ship);
            ship.setCells(cells);
            for (Cell cell : cells) {
                int x = cell.getPositionX();
                int y = cell.getPositionY();
                this.map[x][y].setShip(ship);
                this.map[x][y].lock();
                this.map[x][y].fill();
                this.lock(x - 1, y);
                this.lock(x, y - 1);
                this.lock(x + 1, y - 1);
                this.lock(x + 1, y);
                this.lock(x + 1, y + 1);
                this.lock(x, y + 1);
                this.lock(x - 1, y + 1);
                this.lock(x - 1, y - 1);
            }
        }
    }

    private Cell[] fillShipCells(Ship ship) {
        Random random = new Random();
        Cell[] cells = new Cell[ship.getCellCount()];
        int x, y;
        fillCells:
        while (true) {
            do {
                x = random.nextInt(this.sideSize - 1);
                y = random.nextInt(this.sideSize - 1);
            } while (this.map[x][y].isLock());
            int direction = random.nextInt(10);
            int dx = 0;
            int dy = 0;
            if (direction < 5) {
                dx = 1;
            } else {
                dy = 1;
            }

            if (dx * (x + ship.getCellCount()) > this.sideSize
                    || dy * (y + ship.getCellCount()) > this.sideSize) {
                continue;
            }

            for (int j = 0; j < ship.getCellCount(); j++) {

                cells[j] = this.map[x][y];
                if (cells[j].isLock()) {
                    continue fillCells;
                }

                x += dx;
                y += dy;
            }
            break;
        }
        return cells;
    }

    private void lock(int x, int y) {
        try {
            this.map[x][y].lock();
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    public void print(Boolean isUserMap) throws IOException {
        for (int i = 0; i < this.sideSize; i++) {
            if (i == 0) {
                System.out.print("  ");
                for (int j = 0; j < this.sideSize; j++) {
                    System.out.print(j);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.print(i);
            System.out.print(" ");
            for (int j = 0; j < this.sideSize; j++) {
                map[j][i].print(isUserMap);
            }
            System.out.println();
        }
    }

    public Status fire(int x, int y) {
        try {
            return map[x][y].fire();
        } catch (ArrayIndexOutOfBoundsException e) {
            return Status.INDEX_OUT;
        }
    }

    public int getSideSize() {
        return this.sideSize;
    }

    public int getShipsCount() {
        return this.ships.length;
    }
}
