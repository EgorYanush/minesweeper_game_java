package com.javarush.games.minesweeper;


public class GameObject {
    public int x; // Координата x
    public int y; // Координата y
    public int countMineNeighbors;
    public boolean isMine;
    public boolean isOpen;
    public boolean isFlag;
    public GameObject(int x, int y, boolean isMine) {
        this.x = x; // Инициализация поля x
        this.y = y;
        this.isMine=isMine;// Инициализация поля y
    }
}