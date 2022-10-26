package pr4.serialization;

import lombok.SneakyThrows;

import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
//        String cfgPath = "src/main/resources/cfg.xml";
//        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        Person person = (Person) unmarshaller.unmarshal(new File(cfgPath));
//        System.out.println(person);
        Config c = new Config();
        c.setCount(2);
        c.setPath("src/main/resources/pp.txt");
        XmlHelper.marshalAny(c, "src/main/resources/newCfg.xml");

        String jack = JsonHelper.toJson(new JsonExampleDTO("Jack", 12));
        System.out.println(jack);
        JsonExampleDTO jsonExampleDTO = JsonHelper.fromJson(jack, JsonExampleDTO.class);

        List<JsonExampleDTO> l = List.of(
                new JsonExampleDTO("John", 1),
                new JsonExampleDTO("Ally", 2),
                new JsonExampleDTO("Kate", 3)
        );
        String jsonList = JsonHelper.toJson(l);
        System.out.println(jsonList);
        List<JsonExampleDTO> unmarshaled = List.of(JsonHelper.fromJson(jsonList, JsonExampleDTO[].class));
        System.out.println();
    }
}