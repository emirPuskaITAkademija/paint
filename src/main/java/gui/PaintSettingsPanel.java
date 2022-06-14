package gui;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class PaintSettingsPanel extends JPanel {
    private final JRadioButton circleRadioButton = new JRadioButton("Krug");
    private final JRadioButton squareRadioButton = new JRadioButton("Kvadrat");

    private final JRadioButton blueRadioButton = new JRadioButton("Plavo");
    private final JRadioButton redRadioButton = new JRadioButton("Crveno");

    public PaintSettingsPanel(){
        //shape panel
        JPanel shapePanel = new JPanel();
        shapePanel.add(circleRadioButton);
        circleRadioButton.setSelected(true);
        shapePanel.add(squareRadioButton);
        TitledBorder shapeTitledBorder = new TitledBorder("Oblik");
        shapePanel.setBorder(shapeTitledBorder);
        ButtonGroup shapeButtonGroup = new ButtonGroup();
        shapeButtonGroup.add(circleRadioButton);
        shapeButtonGroup.add(squareRadioButton);
        //color panel
        JPanel colorPanel = new JPanel();
        colorPanel.add(blueRadioButton);
        blueRadioButton.setSelected(true);
        colorPanel.add(redRadioButton);
        TitledBorder colorTitledBorder = new TitledBorder("Boja");
        colorPanel.setBorder(colorTitledBorder);
        ButtonGroup colorButtonGroup = new ButtonGroup();
        colorButtonGroup.add(blueRadioButton);
        colorButtonGroup.add(redRadioButton);

        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(shapePanel);
        add(colorPanel);
    }

    public boolean isCircleSelected(){
        return circleRadioButton.isSelected();
    }

    public boolean isRedSelected(){
        return redRadioButton.isSelected();
    }
}
