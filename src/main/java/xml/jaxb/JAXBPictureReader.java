package xml.jaxb;

import shape.Ellipse;
import shape.PaintShape;
import shape.Rectangle;
import xml.XMLPictureReader;
import xml.jaxb.xsd.Shape;
import xml.jaxb.xsd.Shapes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JAXBPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> readPicture(String filename) {
        List<PaintShape> paintShapes = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("xml.jaxb.xsd");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Shapes shapes = (Shapes) unmarshaller.unmarshal(new FileReader(filename));
            for (Shape shape : shapes.getShape()) {
                Color colorAwt = shape.getColor().equalsIgnoreCase("PLAVA") ? Color.BLUE : Color.RED;
                if (shape.getType().equalsIgnoreCase("KRUG")) {
                    PaintShape paintShape = new Ellipse(shape.getX().intValue(), shape.getY().intValue(), colorAwt);
                    paintShapes.add(paintShape);
                } else {
                    PaintShape paintShape = new Rectangle(shape.getX().intValue(), shape.getY().intValue(), colorAwt);
                    paintShapes.add(paintShape);
                }
            }
        } catch (JAXBException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return paintShapes;
    }
}
