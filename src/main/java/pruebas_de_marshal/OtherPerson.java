package pruebas_de_marshal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
public class OtherPerson extends Person{
    public boolean isHappy() {
        return isHappy;
    }
//Hola soy un comentario
    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    boolean isHappy;
}
