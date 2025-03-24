import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Ticket extends Person{

    //Attributes
    private char row;
    private int seat;
    private double price;

    //Constructor
    Ticket(String name,String surname, String email,char row,int seat,double price ){
        super(name, surname, email);
        this.row = row;
        this.seat = seat;
        this.price = price;
    }

    public char getRow() {
        return row;
    }
    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat(){
        return seat;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public void printInformation(){
        System.out.println("Row letter : " + this.row);
        System.out.println("Seat Number: " + this.seat);
        System.out.println("Price      : " + this.price + "£");
        super.personInfo();
    }

    //Saves the information of the Ticket in a file.
    public void save(){
        //Generate the file name
        String fileName = String.valueOf(this.row) + String.valueOf(this.seat) + ".txt";

        try{
            File file = new File(fileName);

            FileWriter writer = new FileWriter(fileName); //Create a file writer object

            //Write information to the file
            writer.write("Row Letter : " + getRow() + "\n");
            writer.write("Seat number: " + getSeat() + "\n");
            writer.write("Price      : " + getPrice() + "£\n");
            writer.write("Name       : " + getName() +"\n");
            writer.write("Surname    : " + getSurname() + "\n");
            writer.write("Email      : " + getEmail() + "\n" );


            //Close the FileWriter
            writer.close();
        }

        catch(IOException ex){
            System.out.println("Error occurred: " + ex.getMessage());
        }

    }

    //Remove the files of cancelled tickets.
    public void removeTicket(){
        String fileName = String.valueOf(this.row) + String.valueOf(this.seat) + ".txt";
        File file = new File(fileName);
        String fileStatus = "";

        if (file.exists()) {
            boolean fileDeleted = file.delete();

            // Store file deletion status for potential troubleshooting in case of errors
            if (fileDeleted){
                fileStatus = "Deletion successful.";
            }
            else{
                fileStatus = "Deletion unsuccessful.";
            }
        }
        else{
            System.out.println("Ticket file " + fileName +" does not exists.");
        }
    }
}

