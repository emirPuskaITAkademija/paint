package gui;

import action.ExitListener;
import action.OpenListener;
import action.SaveListener;
import shape.PaintShape;
import xml.dom.DOMPictureReader;
import xml.dom.DOMPictureWriter;
import xml.jaxb.JAXBPictureReader;
import xml.jaxb.JAXBPictureWriter;
import xml.sax.SAXPictureReader;
import xml.sax.SAXPictureWriter;
import xml.stax.STAXPictureReader;
import xml.stax.STAXPictureWriter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Naš glavni prozor se sastoji od:
 * <li>1. PaintSettingsPanel -> postavke slikanja</li>
 * <li>2. PaintPanel -> platno za slikanje</li>
 */
public class PaintWindow extends JFrame {

    private static PaintWindow INSTANCE = null;

    private final PaintSettingsPanel paintSettingsPanel = new PaintSettingsPanel();
    private final PaintPanel paintPanel = new PaintPanel();

    private PaintWindow() {
        setTitle("Paint 2D");
        setSize(500, 300);
        add(paintSettingsPanel, BorderLayout.NORTH);
        add(paintPanel);
        //menu bar
        JMenuBar menuBar = new JMenuBar();
        //menu -> File
        JMenu fileMenu = new JMenu("Fajl");
        fileMenu.setMnemonic('F');


        JMenu saveMenu = new JMenu("Snimi");
        //SAX
        JMenuItem saxMenuItem = createMenuItem("SAX", 'S', new SaveListener(new SAXPictureWriter()));
        saveMenu.add(saxMenuItem);
        //DOM
        JMenuItem domMenuItem = createMenuItem("DOM", 'D', new SaveListener(new DOMPictureWriter()));
        saveMenu.add(domMenuItem);
        //STAX
        JMenuItem staxMenuItem = createMenuItem("StAX", 'A', new SaveListener(new STAXPictureWriter()));
        saveMenu.add(staxMenuItem);
        //JAXB
        JMenuItem jaxbMenuItem = createMenuItem("JAXB", 'J', new SaveListener(new JAXBPictureWriter()));
        saveMenu.add(jaxbMenuItem);


        JMenu openMenu = new JMenu("Otvori");
        //SAX
        JMenuItem saxOpenMenuItem = createMenuItem("SAX", 'O', new OpenListener(new SAXPictureReader()));
        openMenu.add(saxOpenMenuItem);
        //DOM
        JMenuItem domOpenMenuItem = createMenuItem("DOM", 'D', new OpenListener(new DOMPictureReader()));
        openMenu.add(domOpenMenuItem);
        //StAX
        JMenuItem staxOpenMenuItem = createMenuItem("StAX", 'X', new OpenListener(new STAXPictureReader()));
        openMenu.add(staxOpenMenuItem);
        //JAXB
        JMenuItem jaxbOpenMenuItem = createMenuItem("JAXB", 'J', new OpenListener(new JAXBPictureReader()));
        openMenu.add(jaxbOpenMenuItem);

        JMenuItem exitMenuItem = createMenuItem("Izlaz", 'I', new ExitListener());
        Stream.of(saveMenu, openMenu, exitMenuItem).forEach(fileMenu::add);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private JMenuItem createMenuItem(String label , int mnemonic, ActionListener listener){
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.setMnemonic(mnemonic);
        menuItem.addActionListener(listener);
        return menuItem;
    }


    public boolean isRedSelected() {
        return paintSettingsPanel.isRedSelected();
    }

    public boolean isCircleSelected() {
        return paintSettingsPanel.isCircleSelected();
    }


    public static PaintWindow instance() {
        if (INSTANCE == null) {
            INSTANCE = new PaintWindow();
        }
        return INSTANCE;
    }

    public List<PaintShape> getPaintShapes() {
        return paintPanel.getPaintShapes();
    }

    public void addPaintShapesToPaintPanel(List<PaintShape> paintShapes){
        paintPanel.getPaintShapes().clear();
        paintPanel.setPaintShapes(paintShapes);
    }

    public void clearPaintPanel(){
        paintPanel.setPaintShapes(Collections.emptyList());
    }
}
