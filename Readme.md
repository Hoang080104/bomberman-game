# Bomberman Game - Class Inheritance Diagram

This project is a Java implementation of the Bomberman game, which includes various classes for game objects, movable objects, items, and tiles. Below is the inheritance diagram representing the structure of the game's classes.

## Class Inheritance Diagram

```
GameObject (abstract)
├── Thuộc tính:
│   ├── # x: int
│   └── # y: int
├── Phương thức:
│   ├── + GameObject(int x, int y)
│   ├── + render(Graphics g): abstract void
│   ├── + update(): abstract void
│   ├── + getX(): int
│   └── + getY(): int
│
├── MovableGameObject (abstract)
│   ├── Thuộc tính:
│   │   └── # gameMap: GameMap
│   ├── Phương thức:
│   │   ├── + MovableGameObject(int x, int y, GameMap gameMap)
│   │   ├── + canMoveTo(int newX, int newY): boolean
│   │   └── + update(): abstract void
│   │
│   ├── Bomber
│   │   ├── Thuộc tính:
│   │   │   ├── - speed: final int
│   │   │   └── - alive: boolean
│   │   ├── Phương thức:
│   │   │   ├── + Bomber(int x, int y, GameMap gameMap)
│   │   │   ├── + isAlive(): boolean
│   │   │   ├── + setAlive(boolean alive): void
│   │   │   ├── + setPosition(int x, int y): void
│   │   │   ├── + update(): void  (Override)
│   │   │   ├── + render(Graphics g): void (Override)
│   │   │   ├── + handleKeyPressed(KeyEvent e): void
│   │   │   └── - placeBomb(): void
│   │   │
│   └── Enemy (abstract)
│       ├── Thuộc tính:
│       │   ├── - alive: boolean
│       │   ├── - direction: int
│       │   ├── - moveCounter: int
│       │   └── - random: Random
│       ├── Phương thức:
│       │   ├── + Enemy(int x, int y, GameMap gameMap)
│       │   ├── + isAlive(): boolean
│       │   ├── + kill(): void
│       │   ├── + update(): void (Override)
│       │   ├── + render(Graphics g): void (Override)
│       │   └── + canMoveTo(int newX, int newY): boolean
│       │
│       ├── Balloom
│       │   ├── Thuộc tính:
│       │   │   ├── - map: GameMap
│       │   │   ├── - random: Random
│       │   │   └── - moveCounter: int
│       │   ├── Phương thức:
│       │   │   ├── + Balloom(int x, int y, GameMap map)
│       │   │   ├── + update(): void (Override)
│       │   │   ├── + render(Graphics g): void (Override)
│       │   │   └── - move(): void
│       │   │
│       └── Oneal
│           ├── Thuộc tính:
│           │   ├── - map: GameMap
│           │   ├── - random: Random
│           │   └── - moveCounter: int
│           ├── Phương thức:
│           │   ├── + Oneal(int x, int y, GameMap map)
│           │   ├── + update(): void (Override)
│           │   ├── + render(Graphics g): void (Override)
│           │   ├── - move(): void
│           │   └── - moveRandom(): void
│   │
├── Bomb
│   ├── Thuộc tính:
│   │   ├── - timer: int
│   │   ├── - exploded: boolean
│   │   ├── - gameMap: GameMap
│   │   ├── - flamePoints: List<Point>
│   │   └── - flameDuration: int
│   ├── Phương thức:
│   │   ├── + Bomb(int x, int y, GameMap gameMap)
│   │   ├── + update(): void (Override)
│   │   ├── - explode(): void
│   │   ├── - addFlame(int tx, int ty): void
│   │   ├── + render(Graphics g): void (Override)
│   │   ├── + isExploded(): boolean
│   │   └── + getFlamePoints(): List<Point>
│   │
├── Flame
│   ├── Phương thức:
│   │   ├── + Flame(int x, int y)
│   │   ├── + update(): void (Override)
│   │   └── + render(Graphics g): void (Override)
│   │
├── Item 
│   ├── BombItem (placeholder)
│   ├── FlameItem (placeholder)
│   └── SpeedItem (placeholder)
│
└── Tile (abstract)
    ├── Thuộc tính:
    │   ├── # x: int
    │   ├── # y: int
    │   └── + TILE_SIZE: final int (static)
    ├── Phương thức:
    │   ├── + Tile(int x, int y)
    │   ├── + render(Graphics g): abstract void
    │   └── + isPassable(): abstract boolean
    │
    ├── Wall
    │   ├── Phương thức:
    │   │   ├── + Wall(int x, int y)
    │   │   ├── + render(Graphics g): void (Override)
    │   │   └── + isPassable(): boolean (Override)
    │   │
    ├── Grass
    │   ├── Phương tính:
    │   │   ├── + Grass(int x, int y)
    │   │   ├── + render(Graphics g): void (Override)
    │   │   └── + isPassable(): boolean (Override)
    │   │
    ├── Brick
    │   ├── Thuộc tính:
    │   │   └── - hiddenTile: Tile
    │   ├── Phương thức:
    │   │   ├── + Brick(int x, int y)
    │   │   ├── + Brick(int x, int y, Tile hiddenTile)
    │   │   ├── + getHiddenTile(): Tile
    │   │   ├── + render(Graphics g): void (Override)
    │   │   └── + isPassable(): boolean (Override)
    │   │
    └── Portal
        ├── Thuộc tính:
        │   ├── - visible: boolean
        │   └── - gameMap: GameMap
        ├── Phương thức:
        │   ├── + Portal(int x, int y, GameMap gameMap)
        │   ├── + getPixelX(): int
        │   ├── + getPixelY(): int
        │   ├── + getX(): int
        │   ├── + getY(): int
        │   ├── + setVisible(boolean visible): void
        │   ├── + isVisible(): boolean
        │   ├── + render(Graphics g): void (Override)
        │   └── + isPassable(): boolean (Override)
```
