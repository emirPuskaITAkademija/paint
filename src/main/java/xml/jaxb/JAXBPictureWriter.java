package xml.jaxb;

import shape.Ellipse;
import shape.PaintShape;
import xml.XMLPictureWriter;
import xml.jaxb.xsd.ObjectFactory;
import xml.jaxb.xsd.Shape;
import xml.jaxb.xsd.Shapes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * ORM -> Privilege, User, ..@Entity
 *
 * OXM -> Shapes, Shape -> ObjectFactory
 */
public class JAXBPictureWriter implements XMLPictureWriter {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("xml.jaxb.xsd");
            Marshaller marshaller = jaxbContext.createMarshaller();
            //ORM -> OXM -> SHapes i Shape (picture.xsd)
            ObjectFactory objectFactory = new ObjectFactory();
            Shapes shapes = objectFactory.createShapes();
            for (PaintShape paintShape : paintShapes) {
                Shape shape = objectFactory.createShape();
                shape.setX(BigInteger.valueOf(paintShape.getX()));
                shape.setY(BigInteger.valueOf(paintShape.getY()));
                shape.setColor(paintShape.getColor().equals(Color.BLUE) ? "PLAVA" : "CRVENA");
                shape.setType((paintShape instanceof Ellipse) ? "KRUG" : "KVADRAT");
                shapes.getShape().add(shape);
            }
            marshaller.marshal(shapes, new FileWriter(filename));
        } catch (IOException | JAXBException e) {
            System.err.println(e.getMessage());
        }
    }
}
