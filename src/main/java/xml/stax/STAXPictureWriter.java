package xml.stax;

import shape.Ellipse;
import shape.PaintShape;
import xml.XMLPictureWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Od čega se sastoji ovaj naš MEDO ????
 *
 * <li>
 *     List<PaintShape> paintShapes
 * </li>
 * <li>
 *     File -> filename
 * </li>
 */
public class STAXPictureWriter implements XMLPictureWriter {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(new FileWriter(filename));
            writer.writeStartDocument();
            writer.writeStartElement("shapes");
            for(int i = 0; i<paintShapes.size(); i++){
                PaintShape paintShape = paintShapes.get(i);
                writer.writeStartElement("shape");
                writer.writeStartElement("x");
                writer.writeCharacters(paintShape.getX()+"");
                writer.writeEndElement();
                writer.writeStartElement("y");
                writer.writeCharacters(paintShape.getY()+"");
                writer.writeEndElement();
                writer.writeStartElement("color");
                writer.writeCharacters(paintShape.getColor().equals(Color.BLUE)?"PLAVA":"CRVENA");
                writer.writeEndElement();
                writer.writeStartElement("type");
                writer.writeCharacters((paintShape instanceof Ellipse)?"KRUG":"KVADRAT");
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        }catch (IOException | XMLStreamException e){
            System.err.println(e.getMessage());
        }
    }
}
