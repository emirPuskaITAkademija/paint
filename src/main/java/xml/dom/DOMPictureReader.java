package xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import shape.Ellipse;
import shape.PaintShape;
import shape.Rectangle;
import xml.XMLPictureReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DOMPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> readPicture(String filename) {
        List<PaintShape> paintShapes = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            //shapes
            Element documentElement = document.getDocumentElement();
            NodeList shapeNodeList = documentElement.getElementsByTagName("shape");
            for(int i = 0; i<shapeNodeList.getLength(); i++){
                Node shapeNode = shapeNodeList.item(i);
                if(shapeNode.getNodeType()==Node.ELEMENT_NODE){
                    Element shapeElement = (Element) shapeNode;
                    Element xElement = (Element) shapeElement.getElementsByTagName("x").item(0);
                    Element yElement = (Element) shapeElement.getElementsByTagName("y").item(0);
                    Element typeElement = (Element) shapeElement.getElementsByTagName("type").item(0);
                    Element colorElement = (Element) shapeElement.getElementsByTagName("color").item(0);

                    Color color = "PLAVA".equalsIgnoreCase(colorElement.getTextContent())?Color.BLUE:Color.RED;
                    int x = Integer.parseInt(xElement.getTextContent());
                    int y = Integer.parseInt(yElement.getTextContent());
                    PaintShape paintShape;
                    if("Krug".equalsIgnoreCase(typeElement.getTextContent())){
                        paintShape = new Ellipse(x, y, color);
                    }else{
                        paintShape = new Rectangle(x, y, color);
                    }
                    paintShapes.add(paintShape);
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
        return paintShapes;
    }
}
