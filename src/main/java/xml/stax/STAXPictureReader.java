package xml.stax;

import shape.Ellipse;
import shape.PaintShape;
import shape.Rectangle;
import xml.XMLPictureReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * StAX -> kombinuje najbolje od SAX i DOM .
 * dio je JAVA SE
 * <>Dva moda rada</>
 * <li>1. cursor</li>
 * <li>2. iterator</li>
 * <p>
 * java.xml.stream
 * <li>XMLStreamReader -> KURSORA</li>
 * <li>XMLEventReader -> ITERATOR </li>
 */
public class STAXPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> readPicture(String filename) {
        List<PaintShape> shapes = new ArrayList<>();
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileReader(filename));
            boolean openX = false;
            String x="";
            boolean openY = false;
            String y="";
            boolean openColor = false;
            String color="";
            boolean openType = false;
            String type="";
            while (reader.hasNext()) {
                int whichElement = reader.next();
                switch (whichElement) {
                    case XMLStreamConstants.START_ELEMENT://<x> <y> <color> <type>
                        if ("x".equals(reader.getName().toString())) {
                            openX = true;
                        } else if ("y".equals(reader.getName().toString())) {
                            openY = true;
                        } else if ("color".equals(reader.getName().toString())) {
                            openColor = true;
                        } else if ("type".equals(reader.getName().toString())) {
                            openType = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (openX) {
                            x = reader.getText();
                        } else if (openY) {
                            y = reader.getText();
                        } else if (openColor) {
                            color = reader.getText();
                        } else if (openType) {
                            type = reader.getText();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if("x".equals(reader.getName().toString())){
                            openX = false;
                        }else if("y".equals(reader.getName().toString())){
                            openY = false;
                        }else if("color".equals(reader.getName().toString())){
                            openColor = false;
                        }else if("type".equals(reader.getName().toString())){
                            openType = false;
                        }else if("shape".equals(reader.getName().toString())){
                            Color colorAwt = "PLAVA".equalsIgnoreCase(color)?Color.BLUE:Color.RED;
                            if("KRUG".equalsIgnoreCase(type)){
                                PaintShape circle = new Ellipse(Integer.parseInt(x), Integer.parseInt(y), colorAwt);
                                shapes.add(circle);
                            }else{
                                PaintShape square = new Rectangle(Integer.parseInt(x), Integer.parseInt(y), colorAwt);
                                shapes.add(square);
                            }
                        }
                        break;
                }
            }

        } catch (FileNotFoundException | NumberFormatException | XMLStreamException e) {
            System.err.println(e.getMessage());
        }
        return shapes;
    }
}
