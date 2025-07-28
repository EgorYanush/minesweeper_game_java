# Minesweeper Game (JavaRush)

This is a simple implementation of the classic **Minesweeper** game, written in **Java**, using the [JavaRush Game Engine](https://javarush.com/).

## 📦 Features

- 9x9 game field
- Random mine generation
- Automatic recursive opening of safe tiles
- Flagging system
- Win/Lose logic with score tracking
- Visual feedback using emojis 💣 🚩

## 🧠 Technologies

- Java
- JavaRush proprietary engine (`com.javarush.engine.cell`)

> ⚠️ **Note:** This project is designed to run inside the JavaRush platform and depends on its game engine. It will not compile outside of that environment without modifications.

## 📁 Files

- `MinesweeperGame.java` — main game logic
- `GameObject.java` — representation of a single cell

## 🎮 How to Play

- Left click to open a tile
- Right click to place/remove a flag
- If you click on a mine — you lose
- Clear all safe tiles — you win

## 👨‍💻 Author

Developed as part of the JavaRush learning platform.
