
import java.time.Period;
import java.util.ArrayList;

public class BoxOffice {
    //initialize 3 final values of ticket prices
    public static final float CHILDREN =6.25f;
    public static final float ADULTS =12.50F;
    public static final float ELDER =5.75F;


    //create 2 null theatre so far
    public Theatre theatreA = null;
    public Theatre theatreB = null;
    //create a new Array list to store all the playing movie
    //ArrayList<String> movieList = new ArrayList<>();
    //set the size of theatre
    public BoxOffice(int sizeA, int sizeB){
        theatreA = new Theatre();
        theatreB = new Theatre();
        this.theatreA.capacity = sizeA;
        this.theatreB.capacity = sizeB;

    }
    public void openMovie(String openMovieName, Theatre x){
        if (x.moviePlaying == null || x.moviePlaying.title.equals(openMovieName) == false){
            x.TheatreMovie(openMovieName);
            //movieList.add(openMovieName);
            //x.seatsSold  = 0;
        }

    }
    public void sellTicket(Patron x, String sellName){
        if (theatreA.moviePlaying.title.equals(sellName)== false && theatreB.moviePlaying.title.equals(sellName)==false){
            System.out.println("Movie is not currently playing\n");
        }
        else {
            if(theatreA.moviePlaying.title == sellName && theatreA.isFull() == false){
                if (x.age < 12)
                    theatreA.moviePlaying.earnings+= CHILDREN;
                else if (x.age >= 65)
                    theatreA.moviePlaying.earnings+= ELDER;
                else{
                    theatreA.moviePlaying.earnings += ADULTS;
                }
                //counterA += 1;
                theatreA.seatsSold += 1;
                x.currentMovie = sellName;
                x.ticket = new Ticket(this.theatreA);

            }
            else if(theatreB.moviePlaying.title == sellName && theatreB.isFull() == false){
                if (x.age < 12)
                    theatreB.moviePlaying.earnings += CHILDREN;
                else if (x.age >= 65)
                    theatreB.moviePlaying.earnings += ELDER;
                else{
                    theatreB.moviePlaying.earnings += ADULTS;
                }
                //counterB +=1;

                theatreB.seatsSold += 1;
                x.currentMovie = sellName;
                x.ticket = new Ticket(this.theatreB);
            }
        }
    }
    public void returnTicket(Patron p){
        if (p.ticket == null){
            System.out.println("Patron does not have a ticket\n");
        }
        else {
            if(p.ticket.theatre.seatsSold > 0){

                if (p.currentMovie == theatreA.moviePlaying.title){
                    //counterA -=1;
                    if (p.age < 12)
                        theatreA.moviePlaying.earnings -= CHILDREN;
                    else if (p.age >= 65)
                        theatreA.moviePlaying.earnings -= ELDER;
                    else{
                        theatreA.moviePlaying.earnings -= ADULTS;
                    }
                }
                else if (p.currentMovie ==theatreB.moviePlaying.title){
                    //counterB -=1;
                    if (p.age < 12)
                        theatreB.moviePlaying.earnings -= CHILDREN;
                    else if (p.age >= 65)
                        theatreB.moviePlaying.earnings -= ELDER;
                    else{
                        theatreB.moviePlaying.earnings -= ADULTS;
                    }
                }
                p.ticket.theatre.seatsSold -=1;
                p.ticket = null;
                p.currentMovie = null;

            }
        }
    }

    public Movie bestMovie() {
            if (theatreA.moviePlaying.earnings > theatreB.moviePlaying.earnings)
                return theatreA.moviePlaying;
            else
                return theatreB.moviePlaying;
    }


}
