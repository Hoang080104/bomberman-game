# Bomberman Game - Class Inheritance Diagram

This project is a Java implementation of the Bomberman game, which includes various classes for game objects, movable objects, items, and tiles. Below is the inheritance diagram representing the structure of the game's classes.

## Class Inheritance Diagram


GameObject
│
├── MovableGameObject
│   ├── Bomber
│   ├── Enemy
│   │   ├── Balloom
│   │   └── Oneal
│   ├── Bomb
│   └── Flame
│
├── Item
│   ├── BombItem
│   ├── FlameItem
│   └── SpeedItem
│
└── Tile
    ├── Wall
    ├── Grass
    ├── Brick
    └── Portal
