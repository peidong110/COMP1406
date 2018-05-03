public class Theatre {
    public int capacity;
    public int seatsSold;

    Movie moviePlaying = null;

    public Theatre(){
        capacity = 0;
        seatsSold = 0;

    }
    public Theatre(int capacity){
        this.capacity = capacity;
    }
    public void TheatreMovie(String movieNow){
        moviePlaying = new Movie(movieNow);
        this.seatsSold = 0;
    }
    public boolean isFull(){
        if(this.capacity -this.seatsSold <=0){
            return true;
        }
        else {
            return false;
        }
    }
}
