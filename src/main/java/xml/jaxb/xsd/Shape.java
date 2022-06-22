package xml.jaxb.xsd;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "x",
        "y",
        "color",
        "type"
})
@XmlRootElement(name = "shape")
public class Shape {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger x;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger y;
    @XmlElement(required = true)
    protected String color;
    @XmlElement(required = true)
    protected String type;


    public BigInteger getX() {
        return x;
    }


    public void setX(BigInteger value) {
        this.x = value;
    }


    public BigInteger getY() {
        return y;
    }


    public void setY(BigInteger value) {
        this.y = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String value) {
        this.color = value;
    }

    public String getType() {
        return type;
    }


    public void setType(String value) {
        this.type = value;
    }
}

