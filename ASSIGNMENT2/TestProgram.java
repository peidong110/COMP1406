public class TestProgram {
    public static void main(String args[]) {
        Movie m = new Movie("Despicable Me 3");
        System.out.println(m.title);
        System.out.println(m.earnings);
        Theatre theatre = new Theatre(3);
        System.out.println(theatre.capacity);
        System.out.println(theatre.seatsSold);
        theatre.moviePlaying = m;
        Patron mary = new Patron(15);
        System.out.println(mary.age);
        System.out.println(mary.ticket);
        mary.ticket = new Ticket(theatre);
        System.out.println(mary.ticket.theatre.moviePlaying.title);
    }
}
