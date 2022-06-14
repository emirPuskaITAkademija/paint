package action;

import gui.PaintWindow;
import shape.PaintShape;
import xml.XMLPictureWriter;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaveListener implements ActionListener {

    //NFS
    static final String PICTURE_EXTENSION = ".nfs";

    //Depend upon abstraction not upon concrete implementation
    private final XMLPictureWriter xmlPictureWriter;

    public SaveListener(XMLPictureWriter xmlPictureWriter) {
        this.xmlPictureWriter = xmlPictureWriter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pictureName = JOptionPane.showInputDialog("Unesite ime slike");
        if (pictureName == null || pictureName.isEmpty()) {
            return;
        }
        if (!pictureName.endsWith(PICTURE_EXTENSION)) {
            pictureName += PICTURE_EXTENSION;
        }
        PaintWindow paintWindow = PaintWindow.instance();
        List<PaintShape> paintShapes = paintWindow.getPaintShapes();
        xmlPictureWriter.savePicture(paintShapes, pictureName);
        JOptionPane.showMessageDialog(null, "Slika je sačuvana");
        paintWindow.clearPaintPanel();
    }
}
