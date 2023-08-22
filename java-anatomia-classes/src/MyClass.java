public class MyClass { //Use PascalCase for file names
    
    public static void main (String [] args) {

        String firstName = "John";
        String lastName = "Wick";

        String fullName = fullName(firstName, lastName);
        System.out.println(fullName);
    }

    public static String fullName (String firstName, String lastName) {
        
        //return firstName.concat(" ").concat(lastName);
        return firstName + " " + lastName;
    }
}