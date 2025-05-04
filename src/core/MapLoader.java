package core;

import entities.Bomber;
import entities.Balloom;
import entities.Oneal;
import tiles.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public static void loadMap(String fileName, GameMap gameMap, Bomber bomber) {
        List<String> lines = new ArrayList<>();
        Portal portal = null; // Khai báo Portal để kiểm tra sau

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                MapLoader.class.getClassLoader().getResourceAsStream(fileName)))) {

            String meta = br.readLine(); // Dòng đầu tiên là metadata
            if (meta == null) return;
            String[] tokens = meta.split(" ");
            int level = Integer.parseInt(tokens[0]);
            int height = Integer.parseInt(tokens[1]);
            int width = Integer.parseInt(tokens[2]);

            gameMap.init(width, height);

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            if (lines.size() != height) {
                System.err.println("Sai số dòng bản đồ!");
                return;
            }

            for (int y = 0; y < height; y++) {
                String row = lines.get(y);
                for (int x = 0; x < width; x++) {
                    if (x >= row.length()) continue;
                    char ch = row.charAt(x);

                    switch (ch) {
                        case '#' -> gameMap.setTile(x, y, new Wall(x, y));
                        case '*' -> gameMap.setTile(x, y, new Brick(x, y));
                        case ' ' -> gameMap.setTile(x, y, new Grass(x, y));
                        case 'p' -> {
                            gameMap.setTile(x, y, new Grass(x, y));
                            bomber.setPosition(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
                        }
                        case 'x' -> {
                            portal = new Portal(x, y, gameMap); // Khởi tạo Portal
                            portal.setVisible(false); // Ẩn Portal ban đầu
                            Brick brick = new Brick(x, y, portal); // Brick chứa Portal
                            gameMap.setTile(x, y, brick);
                        }
                        case '1' -> {
                            gameMap.setTile(x, y, new Grass(x, y));
                            gameMap.addEnemy(new Balloom(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, gameMap));
                        }
                        case '2' -> {
                            gameMap.setTile(x, y, new Grass(x, y));
                            gameMap.addEnemy(new Oneal(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, gameMap));
                        }
                        default -> gameMap.setTile(x, y, new Grass(x, y));
                    }
                }
            }




        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }
}
