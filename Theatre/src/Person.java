public class Person {
    public String name;    // the attributes
    public String surname;
    public String email;

    public Person(String Nname, String Nsurname,String Nemail) {  // defining the constructor
        this.name = Nname;
        this.surname = Nsurname;
        this.email = Nemail;
    }

    public String getName() {   // getter for name
        return name;
    }

    public String getSurname() {    // getter for surname
        return surname;
    }

    public String getEmail() {     // getter for email
        return email;
    }
}