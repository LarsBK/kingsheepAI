package kingsheep;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Simulator {

    /** Font size used for the top-window text. */
    private static final int FONTSIZE = 14;

    /** Maximum number of seconds a player is allowed to think. */

    private static final int THINKLIMIT = 1000; //increased from 1000 by andreas

    /** Minimum time to wait between player turns (even if a player used
        less time to think). */
    private static final int WAITMIN = 300;

    /** Number of turns for one game. */
    private static final int TURNS = 100;

    /** 0 if the winner is undecided.
        1 if player 1 has won the game.
        2 if player 2 has won the game.
        -1 if it's a draw. */
    private int playerWon = 0;

    /** The current turn. */
    private int turn;

    /** Holds the map. */
    private Type map[][];

    /** The player turn queue. */
    private LinkedList<Creature> turnQueue;

    /** Our player objects goes in here. */
    private Player p[] = new Player[2];

    /** Java stuff for showing neat graphics. */
    private Gfx gfx;
    private BufferStrategy strategy;
    private final Color drawColor;

    /** All the images we need. */
    private ImageIcon imgEmpty;
    private ImageIcon imgSheep1;
    private ImageIcon imgSheep2;
    private ImageIcon imgWolf1;
    private ImageIcon imgWolf2;
    private ImageIcon imgGrass;
    private ImageIcon imgRhubarb;
    private ImageIcon imgSkigard;

    Simulator(String mapName, String team1, String team2) {

        try {
            p[0] = loadTeam(team1, new Color(222, 0, 0), 1);
            p[1] = loadTeam(team2, new Color(0, 0, 222), 2);
        } catch (Exception cnfe) {
            System.out.println(cnfe.getMessage());
            cnfe.printStackTrace();
            System.exit(1);
        }

        loadImages();
        strategy = gfx.getBufferStrategy();
        drawColor = new Color(222, 0, 222);

        map = MapLoader.loadMap(mapName, p);

        turnQueue = new LinkedList<Creature>();

        turnQueue.addFirst(p[1].wolf);
        turnQueue.addFirst(p[0].wolf);
        turnQueue.addFirst(p[1].sheep);
        turnQueue.addFirst(p[0].sheep);
        turnQueue.addFirst(p[1].sheep);
        turnQueue.addFirst(p[0].sheep);

        for (turn = 0; turn < TURNS && playerWon == 0; ++turn) {
            for (Creature c : turnQueue) {

                if (!c.alive)
                    continue;

                display(c);
                strategy.show();

                int oldx = c.x;
                int oldy = c.y;
                long startTime = System.nanoTime();
                c.think(c.filter(map));
                c.filter(map);
                int elapsedTime = (int)(System.nanoTime() - startTime)
                    / 1000000;

                if (oldx != c.x || oldy != c.y) {
                    System.out.println("Player " + c.playerID + " has "
                                       + "cheated! DISQUALIFIED!");
                    System.exit(0);
                }

                if (elapsedTime > THINKLIMIT) {
                    System.out.println("Player " + c.playerID + " used over "
                                       + "one second! DISQUALIFIED!");
                    playerWon = c.playerID == 1 ? 2 : 1;
                } else {
                    if (elapsedTime < WAITMIN)
                        try {
                            Thread.sleep(WAITMIN - elapsedTime);
                        } catch (InterruptedException ie) {
                            System.out.println(ie.getMessage());
                        }
                    action(c);
                }
            }
        }

        if (playerWon == 0) {
            if (p[0].score > p[1].score)
                playerWon = 1;
            else if (p[0].score < p[1].score)
                playerWon = 2;
            else
                playerWon = -1;  // It's a draw
        }

        display(null);  // Display play winning screen
        strategy.show();
    }

    /** This method does magic stuff. Proceed at your own risk. */
    Player loadTeam(String teamName, Color c, int playerID)
        throws ClassNotFoundException, InstantiationException,
               NoSuchMethodException, IllegalAccessException,
               InvocationTargetException
    {
        ClassLoader loader = getClass().getClassLoader();
        String pack = "kingsheep.team." + teamName + ".";

        Creature sheep = (Creature)loader.loadClass(pack + "Sheep")
            .getConstructors()[0]
            .newInstance(playerID == 1 ? Type.SHEEP1 : Type.SHEEP2,
                         this, playerID, -1, -1);

        Creature wolf = (Creature)loader.loadClass(pack + "Wolf")
            .getConstructors()[0]
            .newInstance(playerID == 1 ? Type.WOLF1 : Type.WOLF2,
                         this, playerID, -1, -1);

        Player p = new Player(sheep, wolf, c);

        return p;
    }

    /** Loads the necessary image files. */
    private void loadImages() {
        gfx        = new Gfx();
        imgEmpty   = loadImage("gfx/empty.png");
        imgSheep1  = loadImage("gfx/sheep1.png");
        imgSheep2  = loadImage("gfx/sheep2.png");
        imgWolf1   = loadImage("gfx/wolf1.png");
        imgWolf2   = loadImage("gfx/wolf2.png");
        imgGrass   = loadImage("gfx/grass.png");
        imgRhubarb = loadImage("gfx/rhubarb.png");
        imgSkigard = loadImage("gfx/skigard.png");
    }

    private ImageIcon loadImage(String fileName) {
        ImageIcon ret = null;
        try {
            ret = new ImageIcon(getClass().getClassLoader()
                                .getResource(fileName));
        } catch (Exception e) {
            System.err.println("Teh gigantic image loading failz0r:\n "
                               + e.getMessage());
            System.exit(1);
        }
        return ret;
    }

    /** Displays the screen.
     *
     *  @param c
     *         Creature whose turn it is to move.
     */
    private void display(Creature c) {
        Graphics g = strategy.getDrawGraphics();
        ImageIcon drawMe = null;

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Gfx.WIDTH, Gfx.HEIGHT);

        for (int i = 0; i < Gfx.YUNIT; ++i) {
            for (int j = 0; j < Gfx.XUNIT; ++j) {
                switch (map[i][j]) {
                case EMPTY:
                    drawMe = imgEmpty;
                    break;
                case GRASS:
                    drawMe = imgGrass;
                    break;
                case FENCE:
                    drawMe = imgSkigard;
                    break;
                case RHUBARB:
                    drawMe = imgRhubarb;
                    break;
                case SHEEP1:
                    drawMe = imgSheep1;
                    break;
                case SHEEP2:
                    drawMe = imgSheep2;
                    break;
                case WOLF1:
                    drawMe = imgWolf1;
                    break;
                case WOLF2:
                    drawMe = imgWolf2;
                    break;
                default:
                    break;
                }

                g.drawImage(drawMe.getImage(), j * Gfx.UNIT, i * Gfx.UNIT,
                            null, null);
            }
        }

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, FONTSIZE));
        g.setColor(Color.RED);

        if (c != null) {
            String species = c.isSheep() ? "Sheep" : "Wolf";
            g.drawString("Player " + c.playerID + " (" + species
                         + ") thinking...", 5, 14);
        }

        g.drawString("Turn " + turn + "/" + TURNS,
                     Gfx.WIDTH - 90, 14);
        g.setColor(p[0].color);
        g.drawString("Player 1 score: " + p[0].score, 300, 14);
        g.setColor(p[1].color);
        g.drawString("Player 2 score: " + p[1].score, 550, 14);

        if (playerWon != 0) {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
            if (playerWon == -1) {
                g.setColor(drawColor);
                g.drawString("It's a draw!",
                             130, Gfx.HEIGHT / 2);
            } else {
                g.setColor(p[playerWon - 1].color);
                g.drawString("Player " + playerWon + " won!",
                             100, Gfx.HEIGHT / 2);
            }
        }

        g.dispose();
    }

    /** Determines whether the planned move is legal.
     *
     *  @param x
     *         Planned x position.
     *  @param y
     *         Planned y position.
     *  @param t1
     *         Type of entity being moved.
     *  @return
     *         <code>true</code> of the planned move is legal.
     */
    private boolean legalMove(int x, int y, Type t1) {

        Type t2 = map[y][x];
        if (t2 == Type.FENCE ||
            (t1 == Type.SHEEP1) && (t2 == Type.WOLF1)  ||
            (t1 == Type.SHEEP2) && (t2 == Type.WOLF2)  ||
            (t1 == Type.SHEEP1) && (t2 == Type.SHEEP2) ||
            (t1 == Type.SHEEP2) && (t2 == Type.SHEEP1) ||
            (t1 == Type.WOLF1)  && (t2 == Type.SHEEP1) ||
            (t1 == Type.WOLF1)  && (t2 == Type.WOLF2)  ||
            (t1 == Type.WOLF2)  && (t2 == Type.WOLF1)  ||
            (t1 == Type.WOLF2)  && (t2 == Type.SHEEP2))
            return false;

        return true;
    }

    /** Attempts to move a creature according to it's plan.
     *
     *  @param c
     *         Creature to move.
     */
    public void action(Creature c) {

        switch (c.move) {
        case RIGHT:
            if ((c.x < Gfx.XUNIT - 1) && legalMove(c.x + 1, c.y, c.type)) {
                map[c.y][c.x] = Type.EMPTY;
                c.x++;
            } else {
                return;
            }
            break;
        case LEFT:
            if ((c.x > 0) && legalMove(c.x - 1, c.y, c.type)) {
                map[c.y][c.x] = Type.EMPTY;
                c.x--;
            } else {
                return;
            }
            break;
        case UP:
            if ((c.y > 0) && legalMove(c.x, c.y - 1, c.type)) {
                map[c.y][c.x] = Type.EMPTY;
                c.y--;
            } else {
                return;
            }
            break;
        case DOWN:
            if ((c.y < Gfx.YUNIT - 1) && legalMove(c.x, c.y + 1, c.type)) {
                map[c.y][c.x] = Type.EMPTY;
                c.y++;
            } else {
                return;
            }
            break;
        default:
            break;
        }

        if (map[c.y][c.x] == Type.GRASS && c.isSheep())
            p[c.playerID - 1].score++;
        else if (map[c.y][c.x] == Type.RHUBARB && c.isSheep())
            p[c.playerID - 1].score += 5;
        else if (map[c.y][c.x] == Type.SHEEP1 && c.type == Type.WOLF2)
            p[0].sheep.alive = false;
        else if (map[c.y][c.x] == Type.SHEEP2 && c.type == Type.WOLF1)
            p[1].sheep.alive = false;
        else if (map[c.y][c.x] == Type.WOLF2 && c.type == Type.SHEEP1)
            p[0].sheep.alive = false;
        else if (map[c.y][c.x] == Type.WOLF1 && c.type == Type.SHEEP2)
            p[1].sheep.alive = false;

        if (!p[0].sheep.alive && playerWon == 0 && p[0].score < p[1].score)
            playerWon = 2;
        else if (!p[1].sheep.alive && playerWon == 0
                 && p[1].score < p[0].score)
            playerWon = 1;

        map[c.y][c.x] = c.type;
    }
}
