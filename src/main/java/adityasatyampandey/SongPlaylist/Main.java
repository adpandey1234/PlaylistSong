package adityasatyampandey.SongPlaylist;

public class Main {
    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User1");
        store.playSong("Song3", "User2");
        store.playSong("Song4", "User1");
        store.playSong("Song5", "User3");
        store.playSong("Song6", "User1"); 
        store.playSong("Song4", "User2");
        store.playSong("Song5", "User3");
        store.playSong("Song6", "User2");
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User3");
        store.playSong("Song3", "User2");
        System.out.println(store.getRecentlyPlayed("User1")); 
        System.out.println(store.getRecentlyPlayed("User2")); 
        System.out.println(store.getRecentlyPlayed("User3")); 
    }
}