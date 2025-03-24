import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
    static Scanner sc = new Scanner(System.in);
    static int[][] seats = new int[4][]; //declare the 'seats' array to implement the seat management system
    static Ticket[] tickets = new Ticket[52];
    static int rowNumber;
    static char rowLetter;


    public static void main(String[] args) {

        //initialize the seat ragged array with specified number of seats for each row
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 0;
            }
        }

        System.out.println();
        System.out.println("Welcome to the plane Management application.");


        boolean quit = false;

        while (!quit ) {
            try {
                System.out.println("""
                            
                            ******************************************************
                            *                    MENU OPTIONS                    *
                            ******************************************************
                                      1) Buy a seat
                                      2) Cancel a seat
                                      3) Find first available seat
                                      4) Show seating plan
                                      5) Print tickets information and total sales
                                      6) Search ticket
                                      0) Quit
                            ******************************************************
                            
                            """);

                System.out.println("Please select an option: ");
                int option =sc.nextInt();
                sc.nextLine(); // Consume the newline character after reading the integer

                //check if the opinion is valid and perform corresponding actions
                if (option >= 0 && option < 7) {
                    switch (option) {
                        case 1:
                            buy_seat();
                            break;

                        case 2:
                            cancel_seat();
                            break;

                        case 3:
                            System.out.println(find_first_available());
                            break;

                        case 4:
                            show_seating_plan();
                            break;

                        case 5:
                            print_ticket_info();
                            break;

                        case 6:
                            System.out.println(search_ticket());
                            break;

                        case 0:
                            System.out.println("Thank you for being with us.");
                            System.out.println("Have a nice day!");
                            quit = true; //set the quit flag true to exit from the loop
                    }
                }
                else{
                    System.out.println("Invalid input.Please enter a valid option.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input.Please enter a valid option.");
                sc.nextLine(); //Clear the input buffer before allowing the user to input a new value.
            }
        }

        sc.close(); //close the scanner object

    }

    //decides the row number of a given row letter and assigns that value to 'rawNumber' variable
    static void letterToNumber(char rowLetter){
        rowNumber = 0;
        switch (rowLetter) {
            case 'A':
                break;
            case 'B':
                rowNumber = 1;
                break;
            case 'C':
                rowNumber = 2;
                break;
            case 'D':
                rowNumber = 3;
                break;
            default:
                System.out.println("Invalid row letter.");
                break;
        }
    }

    //checks if the inputted row letter is true
    static void correctRow(){

        boolean correctRowLetter = false;

        //continually asks for a valid row letter
        while (!correctRowLetter) {

            System.out.println("Enter the row letter (A, B, C, D):  ");
            char row_letter = sc.next().charAt(0);
            sc.nextLine(); // Consume the newline character after reading the character

            rowLetter = Character.toUpperCase(row_letter);

            String rowLetterString = String.valueOf(rowLetter); //converting char to string to check the length

            if (rowLetterString.length() == 1) {
                if (rowLetter == 'A' || rowLetter == 'B' || rowLetter == 'C' || rowLetter == 'D') {
                    correctRowLetter = true; //set the flag to true to exit the loop if the row letter is valid

                    letterToNumber(rowLetter);
                }
                else {

                    System.out.println("Row letter is invalid.Please enter a valid row letter.");
                }
            }
            else{
                System.out.println("Row letter is invalid.Please enter a valid row letter.");
            }
        }
    }

    //Determines the price of a seat based on its seat number
    static int seatPrice(int seatNumber){

        int seatPrice;

        if (seatNumber > 0 && seatNumber < 6){
            seatPrice = 200;
        }
        else if (seatNumber > 5  && seatNumber < 10 ){
            seatPrice = 150;
        }
        else{
            seatPrice = 180;
        }
        return seatPrice;
    }

    //Continuously prompts the user to enter an email address until a valid email
    static String emailCorrectFormatChecker(){

        String email;

        do {
            System.out.println("Enter your email: ");
             email = sc.nextLine();
            System.out.println();

             if(!email.endsWith("@gmail.com")){
                 System.out.println("Please enter a valid email address.");
             }
        }
        while(!email.endsWith("@gmail.com")) ;

    return email;
    }

    //prompts for the seat number
    static int getSeatNumber(){

        System.out.println("Enter the seat number: ");
        int seatNumber = sc.nextInt();
        sc.nextLine(); // Consume the newline character after reading the integer

        return seatNumber;
    }

    static void buy_seat() {

        correctRow();

        boolean correctSeatNumber = false;
        while(!correctSeatNumber) { //Continue asking for a valid seat number
            try {
                int seatNumber = getSeatNumber();

                if (seatNumber < 0 || seatNumber == -0){
                    System.out.println("Invalid seat number. Seat number cannot be negative.");
                    continue;
                }

                int price = seatPrice(seatNumber);

                //Check if the seat number is valid
                if (seatNumber <= seats[rowNumber].length) {

                    correctSeatNumber = true; // Set the flag to true to exit the while loop

                    if (seats[rowNumber][seatNumber - 1] == 0) {

                        System.out.println("The seat is available.");
                        System.out.println();

                        System.out.println("Enter your first name: ");
                        String name = sc.nextLine();
                        System.out.println();

                        System.out.println("Enter your surname: ");
                        String surname = sc.nextLine();
                        System.out.println();

                        String correctEmail = emailCorrectFormatChecker();

                        //Creates a ticket object
                        Ticket ticket = new Ticket(name, surname,correctEmail, rowLetter, seatNumber, price);

                        //Add the ticket to the ticket array
                        for (int j = 0; j<53; j++){
                            if (tickets[j] == null){
                                tickets[j] = ticket;
                                ticket.save(); //Save the ticket information in a file
                                break;
                            }
                        }

                        //mark the seat as sold
                        seats[rowNumber][seatNumber - 1] = 1;

                        System.out.println("Seat booked successfully. Thank you for your purchase!");
                    }
                    else {
                        System.out.println("Sorry, the seat is already been sold.");
                    }

                }
                else {
                    System.out.println("Invalid seat number.Please enter a valid seat number.");
                }
            }

            catch(InputMismatchException | IndexOutOfBoundsException e ){
                System.out.println("Invalid seat number.Please enter a valid seat number.");
                sc.nextLine();//Clears the buffer before asking the user to enter the seat number again.
            }
        }
    }

    //Makes a seat available
    static void cancel_seat(){

        correctRow();

        boolean correctSeatNumber = false;
        while (!correctSeatNumber) {
            try {
                int seatNumber = getSeatNumber();

                if (seatNumber < 0 || seatNumber == -0){
                    System.out.println("Invalid seat number. Seat number cannot be negative.");
                    continue;
                }

                //Check if the seat number is valid
                if (seatNumber <= seats[rowNumber].length) {

                    correctSeatNumber = true; // Set the flag to true to exit the while loop

                    //check whether the seat is sold
                    if (seats[rowNumber][seatNumber - 1] == 1) {
                        for (int i = 0; i < tickets.length; i++) {
                            Ticket theTicket = tickets[i]; //Ticket by ticket get all the tickets from the 'tickets' array.

                            if (theTicket != null && theTicket.getRow() == rowLetter && theTicket.getSeat() == seatNumber) {
                                tickets[i] = null; // Remove the ticket from the array
                                theTicket.removeTicket(); //deletes the corresponding text file of the cancelled ticket

                                System.out.println("Cancellation successful.");
                                break;
                            }
                        }

                        seats[rowNumber][seatNumber - 1] = 0; //mark the seat as available in the 'seats' array

                    }
                    else {
                        System.out.println("The seat is not booked. Please check whether you have entered the correct seat number.");
                    }
                }
                else {
                    System.out.println("Invalid seat number.Please enter a valid seat number.");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid seat number.Please enter a valid seat number.");
                sc.nextLine();//clearing the buffer before asking the user to enter the seat number again.
            }
        }
    }

    //Find the first seat which is still available.
    static String find_first_available() {

        for (int i = 0; i < 4; i++) {
            char rowLetter = 'A';

            // Map the row index to the corresponding row letter
            switch (i) {
                case 0:
                    break;
                case 1:
                    rowLetter = 'B';
                    break;
                case 2:
                    rowLetter = 'C';
                    break;
                case 3:
                    rowLetter = 'D';
                    break;
            }

            for (int j = 0; j < seats[i].length; j++) {
                //Return the information of first available seat
                if (seats[i][j] == 0) {
                    return ("The first available seat is in row " + rowLetter + " seat " + (j + 1));
                }
            }
        }
        return ("Sorry,there are no available seats at the moment.");
    }

    //Shows the seats that are available and the seats that have been sold
    static void show_seating_plan(){
        System.out.println();
        for (int[] seat : seats) {
            for (int i : seat) {
                if (i == 0) {
                    System.out.print("O ");
                } else if (i == 1) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    /*
    *Prints the information of all tickets that have been sold during the session,
    *and calculates the total price of the tickets sold during the session
    */
    static void print_ticket_info(){

        double totalPrice = 0.0;

        System.out.println("Information of all tickets that have been sold during this session: ");

        for(Ticket ticket: tickets){
            if (ticket != null){
                System.out.println();
                ticket.printInformation();
                totalPrice += ticket.getPrice();
            }
        }

        if(totalPrice == 0.0){
            System.out.println("No tickets were sold in this session.");
        }
        System.out.println();
        System.out.println("The total price of the tickets sold during this session: " + totalPrice +"£");
    }

    //Asks the user to input a row letter and a seat number and searches if someone has bought that seat.
    static String search_ticket() {
        try {
            System.out.println("Enter the information of the seat you want to search.");
            correctRow();

            int seatNum = getSeatNumber();

            boolean correctSeatNumber = false;
            while (!correctSeatNumber) {
                if (seatNum <= seats[rowNumber].length) {
                    correctSeatNumber = true;
                } else {
                    System.out.println("Seat number is invalid.Please enter a valid seat number.");
                    seatNum = getSeatNumber();
                }

                System.out.println();

                for (Ticket ticket : tickets) {
                    if (ticket != null) {
                        if (ticket.getRow() == rowLetter && ticket.getSeat() == seatNum) {
                            System.out.println("Sorry, this seat is already sold.");
                            ticket.printInformation();
                            return "";
                        }
                    }
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input.Please try again");
        }
        return "This seat is available.";
    }

}





