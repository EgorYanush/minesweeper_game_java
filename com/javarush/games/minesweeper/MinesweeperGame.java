package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private static final String MINE="\uD83D\uDCA3";
     private static final String FLAG="\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles=SIDE*SIDE;
    private int score;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        
    }

    private void createGame() {
        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField.length; x++) {
                setCellValue(y, x, "");
            }
        }
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(4) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags=countMinesOnField;
        
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }
    private void  countMineNeighbors(){
            for (int y = 0; y < SIDE; y++) {
                  for (int x = 0; x < SIDE; x++) {
                      if(gameField[y][x].isMine==false){
                         List<GameObject> llst =getNeighbors(gameField[y][x]);
                         int neibCount=0;
                         for(GameObject g : llst){
                             if(g.isMine) neibCount++;
                         }
                         
                         gameField[y][x].countMineNeighbors=neibCount;
                      }
                
                }
                
            }
    }
    private void openTile(int x, int y){
        if (gameField[y][x].isOpen || gameField[y][x].isFlag || isGameStopped) return;
            gameField[y][x].isOpen = true;
            countClosedTiles--;
        if(gameField[y][x].isMine){
        
          setCellValueEx(x, y,Color.RED, MINE);  
          setCellColor(x, y, Color.RED);
          gameOver();
        }else{
            
            score+=5;
            setScore(score);
            setCellColor(x, y, Color.GREEN); 
            if(gameField[y][x].countMineNeighbors==0){
                setCellValue(x, y, "");
                 for(GameObject g : getNeighbors(gameField[y][x])){
                    
                    if(!g.isOpen && !g.isMine){
                        openTile(g.x, g.y);
                    }
                }
            }else{
                   setCellNumber(x, y, gameField[y][x].countMineNeighbors);
            }
        }
        if(countMinesOnField==countClosedTiles && !gameField[y][x].isMine) win();
    }
    
    private void markTile(int x, int y){
        if(isGameStopped)return;
        if (gameField[y][x].isOpen) return;
        if(countFlags==0 && !gameField[y][x].isFlag) return;
        if(!gameField[y][x].isFlag){
            gameField[y][x].isFlag=true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        }else{
            gameField[y][x].isFlag=false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.ORANGE);
        }
    }
    private void gameOver(){
        isGameStopped=true;
        showMessageDialog(Color.RED, "You LOSE", Color.BLACK, 150);
    }
    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "You win", Color.YELLOW, 55);
    }
    private void  restart(){
        isGameStopped=false;
        countClosedTiles=SIDE * SIDE;
        
        score=0;
        setScore(score);
        isGameStopped = false;
        countMinesOnField=0;
                createGame();
    }
    
    public void onMouseLeftClick(int x, int y){
        if(isGameStopped){
            restart();
            return;
        }
        openTile(x,y);
        
    }
     public void onMouseRightClick(int x, int y){
        markTile( x,  y);
    }
    
}