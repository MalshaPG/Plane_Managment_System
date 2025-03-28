public class Person {

    //Attributes
    protected String name;
    protected String surname;
    protected String email;

    //Constructor
    Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void personInfo(){
        System.out.println("Name       : " + this.name);
        System.out.println("Surname    : " + this.surname);
        System.out.println("Email      : " + this.email);
    }
}

