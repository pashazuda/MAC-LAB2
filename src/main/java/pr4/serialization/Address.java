package pr4.serialization;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute
    private String street;
    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    private List<Integer> houses;
}
