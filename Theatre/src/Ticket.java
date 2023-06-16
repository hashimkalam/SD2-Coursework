public class Ticket {
    public int row;
    public int seat;
    public double price;  // instance variable of Person
    public Person person;
    public Ticket(int row_, int seat_, double price_, Person person_) {    // defining the constructor
        this.row = row_;
        this.seat = seat_;
        this.price = price_;
        this.person = person_;
    }
    public int getRow() {    // getter for surname
        return row;
    }
    public int getSeat() {    // getter for surname
        return seat;
    }
    public double getPrice() {    // getter for surname
        return price;
    }
    public void print(){        // creation of the print method
        System.out.println("Name: " + this.person.getName());
        System.out.println("Surname: " + this.person.getSurname());
        System.out.println("Email: " + this.person.getEmail());
        System.out.println("Row number: " + this.row);
        System.out.println("Seat number: " + this.seat);
        System.out.println("Price: " + this.price);
    }


}