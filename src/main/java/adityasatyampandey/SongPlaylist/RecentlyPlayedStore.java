package adityasatyampandey.SongPlaylist;import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RecentlyPlayedStore {
    private int capacity;
    private Map<String, User> userMap;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.userMap = new HashMap<>();
    }

    public void playSong(String songName, String userName) {
        userMap.putIfAbsent(userName, new User(userName));
        User user = userMap.get(userName);

        // Remove the song if it was already in the recently played list
        Song existingSong = null;
        for (Song song : user.getRecentlyPlayed()) {
            if (song.getName().equals(songName)) {
                existingSong = song;
                break;
            }
        }
        if (existingSong != null) {
            user.getRecentlyPlayed().remove(existingSong);
        }

        // Add the new song to the recently played list
        Song newSong = new Song(songName);
        user.addSongToRecentlyPlayed(newSong);

        // Check if the recently played list exceeds capacity and remove the least recently played song if needed
        if (user.getRecentlyPlayed().size() > capacity) {
            user.removeLeastRecentlyPlayed();
        }
    }

    public List<String> getRecentlyPlayed(String userName) {
        userMap.putIfAbsent(userName, new User(userName));
        User user = userMap.get(userName);
        List<String> recentlyPlayedSongs = new ArrayList<>();
        for (Song song : user.getRecentlyPlayed()) {
            recentlyPlayedSongs.add(song.getName());
        }
        return recentlyPlayedSongs;
    }
}