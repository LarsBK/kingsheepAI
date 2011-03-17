package kingsheep;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Gfx extends JFrame {

    /** Size of a unit square on the map. */
    public static final int UNIT   = 48;

    /** Number of horizontal/vertical squares. */
    public static final int XUNIT  = 19;
    public static final int YUNIT  = 15;

    /** Pixel width/height of the screen. */
    public static final int WIDTH  = UNIT * XUNIT;
    public static final int HEIGHT = UNIT * YUNIT;

    protected JPanel gfx;
    private final Container pane;

    Gfx() {
        super("King Sheep");

        pane = this.getContentPane();
        gfx = new JPanel(true);
        pane.add(gfx);

        setIconImage((new ImageIcon("gfx/sheep.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gfx.setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
        createBufferStrategy(2);
    }
}
