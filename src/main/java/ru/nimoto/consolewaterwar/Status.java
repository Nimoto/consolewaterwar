package ru.nimoto.consolewaterwar;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public enum Status {
    INDEX_OUT {
        @Override
        public String toString(){
            return "Try again. Choose two numbers between 0 and 9.";
        }        
    },
    DOUBLE {
        @Override
        public String toString(){
            return "You're repeating yourself";
        }          
    },
    MILK {
        @Override
        public String toString(){
            return "Oooops, sorry, buddy";
        }          
    },
    HURT {
        @Override
        public String toString(){
            return "Ship was injured.";
        }          
    },
    DIE {
        @Override
        public String toString(){
            return "ru.nimoto.consolewaterwar.Ship was killed.";
        }          
    }
}
