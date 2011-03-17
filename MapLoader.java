package kingsheep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class MapLoader {

    public static Type[][] loadMap(String mapName, Player[] p) {

        Type[][] map = new Type[Gfx.YUNIT][Gfx.XUNIT];
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(mapName)));
        } catch (FileNotFoundException fnfe) {
            System.err.printf("Could not open map file '%s'\n", mapName);
            System.exit(1);
        }

        int next = -1;
        for (int y = 0; y < Gfx.YUNIT; ++y) {
            for (int x = 0; x < Gfx.XUNIT; ++x) {
                try {
                    next = in.read();
                    if (next == 10)
                        next = in.read();  // Skip line break
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }

                if (next == -1)
                    break;

                map[y][x] = Type.getType((char)next);

                if (map[y][x] == Type.SHEEP1) {
                    p[0].sheep.x = x;
                    p[0].sheep.y = y;
                } else if (map[y][x] == Type.SHEEP2) {
                    p[1].sheep.x = x;
                    p[1].sheep.y = y;
                } else if (map[y][x] == Type.WOLF1) {
                    p[0].wolf.x = x;
                    p[0].wolf.y = y;
                } else if (map[y][x] == Type.WOLF2) {
                    p[1].wolf.x = x;
                    p[1].wolf.y = y;
                }
            }
        }

        return map;
    }
}