import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LinesDrawingExample extends JFrame {

    private static final long serialVersionUID = 3775690273871048733L;

    private DrawingPanel drawingPanel;

    public LinesDrawingExample() {
        super("Lines Drawing Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Hexagon hexagon = new Hexagon(new Point(250, 250), 200);

        drawingPanel = new DrawingPanel(hexagon);
        add(drawingPanel);

        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }


    public class DrawingPanel extends JPanel {
        private static final long serialVersionUID = 5701311351092275287L;

        private Hexagon hexagon;

        public DrawingPanel(Hexagon hexagon) {
            this.hexagon = hexagon;
            this.setPreferredSize(new Dimension(500, 500));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.RED);
            g.drawPolygon(hexagon.getHexagon());
        }
    }

    public class Hexagon {
        private final int radius;

        private final Point center;

        private final Polygon hexagon;

        public Hexagon(Point center, int radius) {
            this.center = center;
            this.radius = radius;
            this.hexagon = createHexagon();
        }

        private Polygon createHexagon() {
            Polygon polygon = new Polygon();

            for (int i = 0; i < 6; i++) {
                int xval = (int) (center.x + radius
                        * Math.cos(i * 2 * Math.PI / 6D));
                int yval = (int) (center.y + radius
                        * Math.sin(i * 2 * Math.PI / 6D));
                polygon.addPoint(xval, yval);
            }

            return polygon;
        }

        public int getRadius() {
            return radius;
        }

        public Point getCenter() {
            return center;
        }

        public Polygon getHexagon() {
            return hexagon;
        }

    }
}