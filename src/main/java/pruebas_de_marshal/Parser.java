package pruebas_de_marshal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        objectToXML();
    }

    public static void objectToXML() throws JAXBException, FileNotFoundException {
        JAXBContext contextObj = JAXBContext.newInstance(Person.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Person emp1=new Person("Juan", 51);
        emp1.setFather(new Person("Padre", 89));
        List<Person> friends = new LinkedList<>();
        Person amigo1 = new Person("Amigo1", 22);
        friends.add(amigo1);
        friends.add(new Person("Amigo2", 32));
        emp1.setFriends(friends);
        List<Person> friendsOf1 = new LinkedList<>();
        friendsOf1.add(new Person("amam", 12));
        friendsOf1.add(new Person("nono", 123));
        amigo1.setFriends(friendsOf1);

        marshallerObj.marshal(emp1, new FileOutputStream(".\\xmldocuments\\persona1.xml"));
    }
}
