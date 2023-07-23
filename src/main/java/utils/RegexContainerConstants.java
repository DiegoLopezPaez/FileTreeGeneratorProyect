package utils;

//TODO talvez estaria bueno tener tests para los regex...
public abstract class RegexContainerConstants {
    /*Verifica si tiene un punto (dot .) seguido de 0 o mas caracteres que no sean un punto*/
    public static final String REGEX_FOR_FILE_EXTENSION_CONDITION = "^.+\\.[^.]*$";

    /*Verifica que el nombre de un archivo/directorio empiece con un caracter
    * alfanumerico y que sea seguido por 0 o mas caracteres en los cuales no estan
    * incluidos los siguientes " / ? : < > * | $ \*/
    public static final String REGEX_FOR_FILE_NAME_CHARACTERS = "^\\w[^\"/?:<>*|\\\\]*$";

    /*Verifica que la extension sea valida, en esta caso verificamos que luego del punto
    * existan al menos 3 y como maximo 10 caracteres del abecedario*/
    public static final String REGEX_FOR_FILE_EXTENSION = "^.+\\.[a-zA-Z]{0,10}$";

    /*Verifica por nombres especiales de archivos que no deberian ser usados*/
    public static final String REGEX_FOR_SPECIAL_FILE_NAMES = "(?!LPT\\d+|COM\\d+|PRN|AUX|NUL|CON|CLOCK\\$|\\.|\\.\\.).*";

    /*Verifica que el tamaño del nombre del archivo/directorio no sea mayor a 255*/
    public static final String REGEX_FOR_FILE_LENGTH = "^.{1,255}$";
}
