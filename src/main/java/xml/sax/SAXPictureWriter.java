package xml.sax;

import shape.Ellipse;
import shape.PaintShape;
import xml.XMLPictureWriter;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SAXPictureWriter implements XMLPictureWriter {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try(PrintWriter out = new PrintWriter(new FileWriter(filename))){
            out.println("<?xml version=\"1.0\"?>");
            out.println("<shapes>");
            for(PaintShape paintShape: paintShapes){
                out.println("<shape>");
                out.println("<x>" + paintShape.getX()+"</x>");
                out.println("<y>" + paintShape.getY()+"</y>");
                out.println("<color>" + (paintShape.getColor().equals(Color.RED)?"CRVENA":"PLAVA")+"</color>");
                if(paintShape instanceof Ellipse){
                    out.println("<type>Krug</type>");
                }else{
                    out.println("<type>Kvadrat</type>");
                }
                out.println("</shape>");
            }
            out.println("</shapes>");
        }catch (IOException exception){
            System.err.println(exception.getMessage());
        }
    }
}
