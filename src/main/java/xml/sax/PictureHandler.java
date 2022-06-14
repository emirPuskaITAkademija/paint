package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import shape.Ellipse;
import shape.PaintShape;
import shape.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//push -> SAX
public class PictureHandler extends DefaultHandler {

    //PaintShape
    private final List<PaintShape> paintShapes;

    private int x;
    private boolean xOpen = false;
    private int y;
    private boolean yOpen = false;
    private String color;
    private boolean colorOpen;
    private String shape;
    private boolean shapeOpen;


    public PictureHandler(List<PaintShape> paintShapes){
        this.paintShapes = Optional.ofNullable(paintShapes).orElse(new ArrayList<>());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("x".equals(qName)){
            xOpen = true;
        }else if("y".equals(qName)){
            yOpen = true;
        }else if("color".equals(qName)){
            colorOpen = true;
        }else if("type".equals(qName)){
            shapeOpen = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(xOpen){
            xOpen = false;
            x = Integer.parseInt(new String(ch, start, length));
        }else if(yOpen){
            yOpen = false;
            y = Integer.parseInt(new String(ch, start, length));
        }else if(colorOpen){
            colorOpen = false;
            color = new String(ch, start, length);
        }else if(shapeOpen){
            shapeOpen = false;
            shape = new String(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("shape".equals(qName)){
            Color colorAwt = "PLAVA".equalsIgnoreCase(color)? Color.BLUE: Color.RED;
            PaintShape paintShape;
            if(shape.equalsIgnoreCase("Krug")){
                paintShape = new Ellipse(x, y, colorAwt);
            }else{
                paintShape = new Rectangle(x, y, colorAwt);
            }
            paintShapes.add(paintShape);
        }
    }
}
