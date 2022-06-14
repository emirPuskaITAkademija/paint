package action;

import static action.SaveListener.PICTURE_EXTENSION;
import gui.PaintWindow;
import shape.PaintShape;
import xml.XMLPictureReader;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OpenListener implements ActionListener {

    private final XMLPictureReader xmlPictureReader;

    public OpenListener(XMLPictureReader xmlPictureReader){
        this.xmlPictureReader = xmlPictureReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pictureName = JOptionPane.showInputDialog("Unesi ime slike koju želiš prikazati na platnu:");
        if(pictureName == null || pictureName.isEmpty()){
            return;
        }
        if(!pictureName.endsWith(PICTURE_EXTENSION)){
            pictureName = pictureName+PICTURE_EXTENSION;
        }
        List<PaintShape> paintShapes =  xmlPictureReader.readPicture(pictureName);
        PaintWindow.instance().addPaintShapesToPaintPanel(paintShapes);
    }
}
