package xml.jaxb.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "shape"
})
@XmlRootElement(name = "shapes")
public class Shapes {

    @XmlElement(required = true)
    protected List<Shape> shape;


    public List<Shape> getShape() {
        if (shape == null) {
            shape = new ArrayList<Shape>();
        }
        return this.shape;
    }

}