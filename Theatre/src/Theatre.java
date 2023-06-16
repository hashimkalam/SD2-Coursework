import java.util.*;
import java.io.*;

public class Theatre {
    public static ArrayList<Ticket> ticketList = new ArrayList<>();     // code to create the ticket array list
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Welcome to the New Theatre");         // code to print the opening message

        char[] row1 = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[] row2 = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[] row3 = {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        char[][] theatre_seats = {row1, row2, row3};        // initializing a 2D array for the rows

        boolean exit = false;
        while (!exit) {           // to keep printing the menu until the user exits the program with a '0'
            System.out.println("\n-------------------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("-------------------------------------------------");

            System.out.print("Enter option: ");
            while (!input.hasNextInt()) {      // validation for the correct data type
                System.out.println("Invalid input, please try again by entering an integer!\n");
                System.out.print("Please enter a valid option (0-8): ");
                input.next();
            }
            int option = input.nextInt();

            switch (option) {       // switch case to operate the menu
                case 0 -> {
                    System.out.print("Thank you for using our service. See you soon.");
                    System.exit(0);
                }
                case 1 -> buy_ticket(theatre_seats);
                case 2 -> print_seating_area(theatre_seats);
                case 3 -> cancel_ticket(theatre_seats);
                case 4 -> show_available(theatre_seats);
                case 5 -> save(theatre_seats);
                case 6 -> load(theatre_seats);
                case 7 -> show_tickets_info();
                case 8 -> sort_tickets();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void buy_ticket(char[][] theatre_seats) {
        System.out.print("Enter the name: ");       // code to ask for the person details (name, surname and email)
        String name = input.next();
        System.out.print("Enter the surname: ");
        String surname = input.next();
        System.out.print("Enter the email: ");
        String email = input.next();

        Person details = new Person(name, surname, email);

        System.out.print("Please input the row number: ");
        while (!input.hasNextInt()) {
            System.out.print("Invalid input, please try again by entering an integer!\n Please input the row number (1-3): ");
            input.next(); // consume invalid input
        }
        int row_num = input.nextInt();

        System.out.print("Please input the seat number: ");
        while (!input.hasNextInt()) {
            System.out.print("Invalid input, please try again by entering an integer!\n Please input the seat number: ");
            input.next(); // consume invalid input
        }
        int seat_num = input.nextInt();

        if (row_num<1 || row_num>theatre_seats.length) {            //code to check whether the row is available
            System.out.println("Row number does not exist. Please try again! (1 - 3)");
            return;
        } else if (seat_num<1 || seat_num>theatre_seats[row_num-1].length) {  //code to check whether the seat is available
            System.out.println("Seat number does not exist. Please try again!");
            return;
        } else if (theatre_seats[row_num-1][seat_num-1] == 'X') {   // code to check whether the seat is already booked
            System.out.println("The chosen seat is already occupied/booked. Please try again with a different seat!");
            return;
        } else {
            theatre_seats[row_num-1][seat_num-1] = 'X';   // code to purchase a ticket by assigning 'X' to the seat
            System.out.println("Thank you for purchasing a ticket!");
        }

        double price = 0;
        if (row_num == 1) {         // code to decide the price of the ticket depending on the row booked
            price = 8;
        } else if (row_num == 2) {
            price = 8.5;
        } else {
            price = 9;
        }

        Ticket ticketObj = new Ticket(row_num, seat_num, price, details); // code to create the object
        ticketList.add(ticketObj); // code to add the object
    }

    private static void print_seating_area(char[][] theatre_seats) {
        System.out.println("      ***********");  // code to print the heading part
        System.out.println("      *  STAGE  *");
        System.out.println("      ***********");

        System.out.print("    ");
        for (int i=0; i < theatre_seats[0].length; i++) {
            System.out.print(theatre_seats[0][i]);
            if (i==5) {              // code to print space as required in the question
                System.out.print("   ");
            }
        }
        System.out.print("\n  ");
        for (int i=0; i<theatre_seats[1].length; i++) {
            System.out.print(theatre_seats[1][i]);
            if (i == 7) {
                System.out.print("   ");
            }
        }
        System.out.println();
        for (int i=0; i<theatre_seats[2].length; i++) {
            System.out.print(theatre_seats[2][i]);
            if (i==9) {
                System.out.print("   ");
            }
        }
    }
    private static void cancel_ticket(char[][] theatre_seats) {
        System.out.print("Please input the row number: ");
        while (!input.hasNextInt()) {     // validation for wrong input type
            System.out.print("Invalid input, please try again by entering an integer!\n Please enter the row number(1-3): ");
            input.next(); // consume invalid input
        }
        int row_num = input.nextInt();
        System.out.print("Please input the seat number: ");
        while (!input.hasNextInt()) {      // validation for wrong input type
            System.out.print("Invalid input, please try again by entering an integer!\n Please enter the seat number: ");
            input.next(); // consume invalid input
        }
        int seat_num = input.nextInt();

        if (row_num<=0 || row_num>theatre_seats.length) {     // code to check whether the row number is available
            System.out.print("Invalid row number. Please try again.");
        } else if (seat_num<=0 || seat_num>theatre_seats[row_num - 1].length) {     // code to check whether the seat number is available
            System.out.print("Invalid seat number. Please try again.");
        } else if (theatre_seats[row_num - 1][seat_num - 1] == 'O') {            // code to check whether the seat is already booked or not
            System.out.print("The chosen seat is already not booked(available for booking)!");
        } else {
            theatre_seats[row_num-1][seat_num-1] = 'O';              // code to cancel the required seat
            System.out.print("The seat has successfully been cancelled!");
        }
        ticketList.removeIf(ticket -> ticket.getRow()==row_num && ticket.getSeat()==seat_num); // lambda expression to remove the specific seat from the list
    }
    private static void show_available(char[][] theatre_seats) {

        System.out.print("Seats available in row 1: ");             // code to display the available seats in row 1
        int counter_1 = 0;
        for (int i=0; i<theatre_seats[0].length; i++) {
            if (theatre_seats[0][i] == 'O') {
                counter_1++;
            }
        }
        for (int i = 0; i < theatre_seats[0].length; i++) {
            if (i != counter_1-1 && theatre_seats[0][i] == 'O') {
                System.out.print(i + 1 + ", ");
            } else if (i == counter_1-1 && theatre_seats[0][counter_1-1] == 'O') {
                System.out.print(i + 1 + ". ");
            }
        }

        System.out.print("\nSeats available in row 2: ");             // code to display the available seats in row 2
        int counter_2 = 0;
        for (int i = 0; i < theatre_seats[1].length; i++) {
            if (theatre_seats[1][i] == 'O') {
                counter_2++;
            }
        }
        for (int i = 0; i <  theatre_seats[1].length; i++) {
            if (i != counter_2-1 && theatre_seats[1][i] == 'O') {
                System.out.print(i + 1 + ", ");
            } else if (i == counter_2-1 && theatre_seats[1][counter_2-1] == 'O') {
                System.out.print(i + 1 + ". ");
            }
        }

        System.out.print("\nSeats available in row 3: ");             // code to display the available seats in row 3
        int counter_3 = 0;
        for (int i = 0; i < theatre_seats[2].length; i++) {
            if (theatre_seats[2][i] == 'O') {
                counter_3++;
            }
        }
        for (int i = 0; i <  theatre_seats[2].length; i++) {
            if (i != counter_3-1 && theatre_seats[2][i] == 'O') {
                System.out.print(i + 1 + ", ");
            } else if (i == counter_3-1 && theatre_seats[2][counter_3-1] == 'O') {
                System.out.print(i + 1 + ". ");
            }
        }
    }
    private static void save(char[][] theatre_seats) {
        try {
            FileWriter myWriter = new FileWriter("file.txt");

            myWriter.write(theatre_seats[0]);       // code to write the row info to the specified text file
            myWriter.write('\n');
            myWriter.write(theatre_seats[1]);
            myWriter.write('\n');
            myWriter.write(theatre_seats[2]);
            myWriter.write('\n');
            myWriter.close();

            System.out.print("The information of the rows have been saved successfully in a file!!");
        } catch (IOException e) {
            System.out.print("Error occurred");
        }
    }
    private static void load(char[][] theatre_seats) {
        try {
            File file = new File("file.txt");
            Scanner file_reader = new Scanner(file);        // code to read from the file.txt
            int row = 0;
            while (file_reader.hasNextLine() && row < theatre_seats.length) {
                String text = file_reader.nextLine();
                for (int i = 0; i < theatre_seats[row].length && i < text.length(); i++) {
                    theatre_seats[row][i] = text.charAt(i);
                }
                row++;
                System.out.println(text);
            }
            System.out.print("The information of the rows have been loaded and restored successfully!!");
            file_reader.close();
        } catch (IOException e) {
            System.out.print("Error while reading the file.");
            e.printStackTrace();
        }
    }
    private static void show_tickets_info() {
        double totalTicketPrice = 0;            // initialising the variable for total ticket price
        for (Ticket ticket : ticketList) {
            ticket.print();
            System.out.println();
            totalTicketPrice += ticket.price;           // code to update the total ticket price
        }
        System.out.print("The total price for the ticket is: $" + totalTicketPrice);
    }
    private static void sort_tickets() {
        int n = ticketList.size();              // use of bubble sort method to sort the tickets from the
        for (int i = 0; i < n - 1; i++) {       // cheapest price first.
            for (int j = 0; j < n - i - 1; j++) {
                Ticket ticket_1 = ticketList.get(j);
                Ticket ticket_2 = ticketList.get(j + 1);
                if (ticket_1.getPrice() > ticket_2.getPrice()) {
                    ticketList.set(j, ticket_2);
                    ticketList.set(j + 1, ticket_1);
                }
            }
        }
        System.out.println("Ticket sorted by the cheapest price first:\n");
        for (Ticket ticket : ticketList) {              // printing the data
            ticket.print();
            System.out.println();
        }
    }
}