package xml.sax;

import shape.PaintShape;
import xml.XMLPictureReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SAXPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> readPicture(String filename) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //handler -> način na koji ćemo čitati sadržaj slike ćemo definirati u tom handleru
            List<PaintShape> paintShapes = new ArrayList<>();
            saxParser.parse(filename, new PictureHandler(paintShapes));
            return paintShapes;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}
