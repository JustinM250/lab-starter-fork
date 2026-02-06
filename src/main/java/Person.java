public class Person {
    String name;
    int age;

    public static final String CLASS_VARIABLE_MESSAGE = "CLASS VARIABLE MESSAGE";

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    Person(){
        this.name = "DefaultName";
        this.age = 25;
    }

    public void show_instance_stats(){
        System.out.println(String.format("This person's name is: %s", name));
        System.out.println(String.format("This person's age is: %s", age));
    }

    public static void show_class_variables(){
        System.out.println(CLASS_VARIABLE_MESSAGE);
    }
}