//import static java.lang.IO.*; // This causes error.

public class Main{
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Person new_person = new Person("Bob", 31 );
        new_person.show_instance_stats();
        Person.show_class_variables();
    }
}
