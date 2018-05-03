import java.util.ArrayList;

public class User {
    private String userName;
    private boolean online;
    private int amtTime;
    private ArrayList<Song> songList;

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public User()  { this(""); }
    public int getAmtTime() {
        return amtTime;
    }

    public void setOnline(boolean o) {
        online = o;
    }

    public User(String u)  {
        this.userName = u;
        this.online = false;
        songList = new ArrayList<Song>();
    }

    public String getUserName() { return userName; }
    public boolean isOnline() { return online; }

    public String toString()  {
        String s = "" + userName + ": " +songList.size()+" songs (";
        if (!online) s += "not ";
        return s + "online)";
    }
    public void addSong(Song a){
        Song newSong = new Song(a.getTitle(),a.getArtist(),a.getMinutes(),a.getSeconds());
        newSong.setOwner(this);
        songList.add(newSong);

    }
    public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m){
        ArrayList<String> fullList = new ArrayList<>();
        int counter = 1;
        fullList.add(String.format(String.format("%-4s","")+"%-30s","TITLE")+String.format("%-20s","ARTIST")+"TIME"+String.format("%-3s","")+"OWNER");
        fullList.add("");
        for (User i : m.getUsers()){
            for (Song j : i.songList){
                fullList.add(String.format("%-4s",counter+".")+String.format("%-30s",j.getTitle())+String.format("%-20s",j.getArtist())+j.getMinutes()+":"+String.format("%02d",j.getSeconds())+String.format("%-3s","")+j.getOwner().getUserName());
                counter++; } }
        counter=0;
        return fullList;

    }
    public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m,String artist){
        int counter = 1;
        ArrayList<String> songByArtist = new ArrayList<>();

        songByArtist.add(String.format("%-4s","")+String.format("%-20s","TITLE")+String.format("%-10S","ARTIST")+String.format("%-12s","TIME")+String.format("%-10s","OWNER"));
        songByArtist.add("");
        for (Song i: m.availableSongsByArtist(artist)){
            songByArtist.add(String.format("%-4s",counter+".")+String.format("%-20s",i.getTitle())+String.format("%-10s",i.getArtist())+i.getMinutes()+":"+String.format("%-10s",i.getSeconds())+String.format("%-10s",i.getOwner().getUserName()));
            counter++;
        }

        return songByArtist;

    }

    public void downloadSong(MusicExchangeCenter m, String title, String ownerName){
        Song tem = m.getSong(title, ownerName);

        if (tem != null && m.userWithName(ownerName).isOnline()){
            tem.setOwner(m.userWithName(ownerName));
            this.addSong(tem);
        }


    }



    public int totalSongTime(){
        amtTime = 0;
        for (Song a : songList){
            amtTime += a.getDuration();
        }

        return amtTime;
    }
    public void register(MusicExchangeCenter m){
        m.registerUser(this);
    }
    public void logon(MusicExchangeCenter m ){
        if ((m.userWithName(this.userName)!= null) && !online){
            online = true;
        }
    }
    public void logoff(MusicExchangeCenter m){
        if ((m.userWithName(this.userName) != null) && online){
            online = false;
        }
    }
    public static User DiscoStew() {
        User discoStew = new User("Disco Stew");
        discoStew.addSong(new Song("Hey Jude", "The Beatles", 4, 35));
        discoStew.addSong(new Song("Barbie Girl", "Aqua", 3, 54));
        discoStew.addSong(new Song("Only You Can Rock Me", "UFO", 4, 59));
        discoStew.addSong(new Song("Paper Soup Cats", "Jaw", 4, 18));
        return discoStew;
    }
    public static User SleepingSam() {
        User sleepingSam = new User("Sleeping Sam");
        sleepingSam.addSong(new Song("Meadows", "Sleepfest", 7, 15));
        sleepingSam.addSong(new Song("Calm is Good", "Waterfall", 6, 22));
        return sleepingSam;
    }
    public static User RonnieRocker() {
        User ronnieRocker = new User("Ronnie Rocker");
        ronnieRocker.addSong(new Song("Rock is Cool", "Yeah", 4, 17));
        ronnieRocker.addSong(new Song("My Girl is Mean to Me", "Can't Stand Up", 3, 29));
        ronnieRocker.addSong(new Song("Only You Can Rock Me", "UFO", 4, 52));
        ronnieRocker.addSong(new Song("We're Not Gonna Take It", "Twisted Sister", 3, 9));
        return ronnieRocker;
    }
    public static User CountryCandy() {
        User countryCandy = new User("Country Candy");
        countryCandy.addSong(new Song("If I Had a Hammer", "Long Road", 4, 15));
        countryCandy.addSong(new Song("My Man is a 4x4 Driver", "Ms. Lonely", 3, 7));
        countryCandy.addSong(new Song("This Song is for Johnny", "Lone Wolf", 4, 22));
        return countryCandy;
    }
    public static User PeterPunk() {
        User peterPunk = new User("Peter Punk");
        peterPunk.addSong(new Song("Bite My Arms Off", "Jaw", 4, 12));
        peterPunk.addSong(new Song("Where's My Sweater", "The Knitters", 3, 41));
        peterPunk.addSong(new Song("Is that My Toenail ?", "Clip", 4, 47));
        peterPunk.addSong(new Song("Anvil Headache", "Clip", 4, 34));
        peterPunk.addSong(new Song("My Hair is on Fire", "Jaw", 3, 55));
        return peterPunk;
    }
}
