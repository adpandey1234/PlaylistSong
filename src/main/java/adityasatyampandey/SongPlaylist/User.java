package adityasatyampandey.SongPlaylist;
import java.util.ArrayList;
import java.util.List;
class User {
    private String name;
    private List<Song> recentlyPlayed;

    public User(String name) {
        this.name = name;
        this.recentlyPlayed = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getRecentlyPlayed() {
        return new ArrayList<>(recentlyPlayed);
    }

    public void addSongToRecentlyPlayed(Song song) {
        recentlyPlayed.add(0, song);
    }

    public void removeLeastRecentlyPlayed() {
        if (!recentlyPlayed.isEmpty()) {
            recentlyPlayed.remove(recentlyPlayed.size() - 1);
        }
    }
}