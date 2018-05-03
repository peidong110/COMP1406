

import javafx.util.Pair;

import java.util.*;

public class MusicExchangeCenter {
    private ArrayList<Song> downloadedSongs;
    private ArrayList<User> users;
    private HashMap<String,Float> royalties;
    public MusicExchangeCenter(){
        downloadedSongs = new ArrayList<Song>();
        users = new ArrayList<User>();
        royalties = new HashMap<String, Float>();

    }

    public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }

    public HashMap<String, Float> getRoyalties() {
        return royalties;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public TreeSet<Song> uniqueDownloads(){
        TreeSet<Song> d = new TreeSet<>(downloadedSongs);
        return d;
    }

    public ArrayList<Pair<Integer,Song>> songsByPopularity(){
        ArrayList<Pair<Integer,Song>> popular = new ArrayList<>();
        for (Song dow :uniqueDownloads()){
            int thisDown = downloadsBySongName(dow.getTitle());

            popular.add(new Pair<Integer, Song>(thisDown, dow));
        }

        Collections.sort(popular, new Comparator<Pair<Integer, Song>>() {
            @Override
            public int compare(Pair<Integer, Song> o1, Pair<Integer, Song> o2) {
                return -(o1.getKey().compareTo(o2.getKey()));
            }
        });

        return popular;
    }
    private int downloadsBySongName(String name){
        int downloads =0;
        for (Song d : downloadedSongs){
            if (d.getTitle().equals(name)){
                downloads ++;
            }
        }
        return downloads;

    }
    private void deleteRepeated(ArrayList<Pair<Integer,Song>> a){
        for (int i =0; i < a.size(); i++){
            Song tempSong = a.get(i).getValue();
            String temp = a.get(i).getValue().getTitle();



        }
    }
    public void displayRoyalties() {

        ArrayList<String> outputOne = new ArrayList<>();
        //headlines
        outputOne.add(String.format("%-7s","Amount") + "Artist");
        outputOne.add("---------------");
        for (Song i : downloadedSongs) {
            if (!(royalties.containsKey(i.getArtist()))) {
                int keys = findNumOfDuplicate(i.getArtist());
                float total = keys * 0.25f;
                royalties.put(i.getArtist(), total);

            }
        }
        for (String artInRoyalties: royalties.keySet()){
            if (royalties.get(artInRoyalties)> 0){
                outputOne.add(String.format("$%1.2f  %s",royalties.get(artInRoyalties),artInRoyalties));
            }
        }

//        System.out.println(royalties);
        for (String jglks: outputOne){
            System.out.println(jglks);
        }

    }

    public int findNumOfDuplicate(String name){
        int howManyTimes =0;
        for (int i =0; i < downloadedSongs.size();i++){
            if (downloadedSongs.get(i).getArtist().equals(name)){
                howManyTimes +=1;
            }
        }
        return howManyTimes;
    }



    public ArrayList<User> onlineUsers(){
        ArrayList<User> currentOnlineUsr = new ArrayList<User>();
        for (User i : users){
            if (i.isOnline()){
                currentOnlineUsr.add(i);
            }
        }
        return currentOnlineUsr;
    }
    public ArrayList<Song> allAvailableSongs(){
        ArrayList<Song> available = new ArrayList<Song>();
        for (User temp :onlineUsers()){
            for(Song s: temp.getSongList()) available.add(s);
        }
        return available;
    }
    public String toString(){
        //quote inside a quote
        return "Music Exchange Centre( "+onlineUsers().size()+" users on-line, "+allAvailableSongs().size()+" songs available)";
    }
    public void registerUser(User x){
        if (userWithName(x.getUserName())==null) users.add(x);

    }
    public User userWithName(String s){
        for (User u : users){
            if (u.getUserName().equals(s))
                return u;
        }
        return null;
    }
    public ArrayList<Song> availableSongsByArtist(String artist){
        ArrayList<Song> onlyForThisArtist = new ArrayList<>();
        for (Song a : allAvailableSongs()){
            if (a.getArtist().equals(artist)){
                onlyForThisArtist.add(a);
            }
        }
        return onlyForThisArtist;
    }
    public Song getSong(String title, String ownerName) {
        Song returnSong = null;
        for (User x: users ) {
            if (x.getUserName().equals(ownerName)) {
                for (Song y : x.getSongList()) {
                    if (y.getTitle().equals(title)) {
                        returnSong = y;
                        break;
                    }
                }
            }
        }
        if (returnSong != null)
            downloadedSongs.add(returnSong);
        return returnSong;
    }
}
