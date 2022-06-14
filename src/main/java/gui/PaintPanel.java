package gui;

import shape.Ellipse;
import shape.PaintShape;
import shape.Rectangle;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Ovo platno za slikanje ili čitajte JPanel da bi bilo platno za slikanje
 * mora ispunjavati neke uvjete:
 * <li>
 *     1. Postaviti listener na JPanel -> DrawListener
 * </li>
 * <li>
 *     1.1. List<PaintShape> paintShapes -> mouseEvent -> x, y -> PaintShape paintShape -> paintShapes.add(paintShape)
 *     PaintShape -> x, y, width, height, Color , Shape(Ellipse2D, Rectangle2D)
 * </li>
 * <li>
 *     1.2 repaint() -> paintComponent(Graphics g) -> Graphics2D -> forEach in paintShapes
 * </li>
 */
public class PaintPanel extends JPanel {

    private  List<PaintShape> paintShapes = new ArrayList<>();

    public PaintPanel() {
        //mouse listener
        addMouseListener(new DrawListener());
        //mouse motion listener
        addMouseMotionListener(new DrawListener());
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //Graphics -> Graphics2D
        Graphics2D graphics2D = (Graphics2D) graphics;
        for (PaintShape paintShape : paintShapes) {
            graphics2D.setColor(paintShape.getColor());
            graphics2D.fill(paintShape.createShape());
        }
    }

    public List<PaintShape> getPaintShapes() {
        return paintShapes;
    }

    public void setPaintShapes(List<PaintShape> paintShapes) {
        this.paintShapes = paintShapes;
        repaint();
    }

    private class DrawListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            addPaintShape(mouseEvent);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            addPaintShape(mouseEvent);
            repaint();
        }

        private void addPaintShape(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            PaintWindow instance = PaintWindow.instance();
            Color color = instance.isRedSelected() ? Color.RED : Color.BLUE;
            PaintShape paintShape = instance.isCircleSelected() ? new Ellipse(x, y, color) : new Rectangle(x, y, color);
            paintShapes.add(paintShape);
        }
    }
}
