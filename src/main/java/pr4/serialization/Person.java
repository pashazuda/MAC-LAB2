package pr4.serialization;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="cfg")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlElement(name = "name")
    private String myName;
    @XmlElement
    private int age;

    @XmlElement
    private Address address;

}
