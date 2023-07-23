package pruebas_de_marshal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
//@XmlType(propOrder = {"name", "age", "father", "mother", "friends"})
public class Person {
    public Person(){

    }
    public Person(String name, int age){
        this.age = age;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getFather() {
        return father;
    }

    @XmlTransient
    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    @XmlElement
    public void setMother(Person mother) {
        this.mother = mother;
    }

    public List<Person> getFriends() {
        return friends;
    }

    @XmlElement
    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    private String name;
    private int age;
    private Person father;
    private Person mother;
    private List<Person> friends;
}
