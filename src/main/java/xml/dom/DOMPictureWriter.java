package xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import shape.Ellipse;
import shape.PaintShape;
import xml.XMLPictureWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.Color;
import java.io.File;
import java.util.List;

public class DOMPictureWriter implements XMLPictureWriter {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //document JS
            Document document = documentBuilder.newDocument();
            Element shapesElement = document.createElement("shapes");
            document.appendChild(shapesElement);
            for (PaintShape paintShape : paintShapes) {
                Element shapeElement = document.createElement("shape");
                Element xElement = document.createElement("x");
                xElement.setTextContent(paintShape.getX() + "");
                shapeElement.appendChild(xElement);
                Element yElement = document.createElement("y");
                yElement.setTextContent(paintShape.getY() + "");
                shapeElement.appendChild(yElement);
                Element colorElement = document.createElement("color");
                colorElement.setTextContent(paintShape.getColor().equals(Color.BLUE) ? "PLAVA" : "CRVENA");
                shapeElement.appendChild(colorElement);
                Element typeElement = document.createElement("type");
                typeElement.setTextContent((paintShape instanceof Ellipse)?"Krug":"Kvadrat");
                shapeElement.appendChild(typeElement);

                shapesElement.appendChild(shapeElement);
            }

            //document -> DOM -> file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //dom source objekat -> document
            DOMSource domSource = new DOMSource(document);
            //Stream result -> povezan sa fajlom
            StreamResult streamResult = new StreamResult(new File(filename));
            //dom source -> stream result = >    prebačen sadržaj document objekat u taj fajl
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
