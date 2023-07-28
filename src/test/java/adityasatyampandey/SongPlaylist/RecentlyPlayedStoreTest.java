package adityasatyampandey.SongPlaylist;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class RecentlyPlayedStoreTest {
    private RecentlyPlayedStore store;
    private static final Logger logger = Logger.getLogger(RecentlyPlayedStoreTest.class.getName());

    @BeforeMethod
    public void setUp() {
        store = new RecentlyPlayedStore(3);
    }

    @Test
    public void testPlaySongSingleUser() {
        logger.info("Running testPlaySongSingleUser...");
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User1");
        store.playSong("Song3", "User1");

        List<String> recentlyPlayed = store.getRecentlyPlayed("User1");
        assertEquals(recentlyPlayed.size(), 3);
        assertEquals(recentlyPlayed.get(0), "Song3");
        assertEquals(recentlyPlayed.get(1), "Song2");
        assertEquals(recentlyPlayed.get(2), "Song1");
    }

    @Test
    public void testPlaySongMultipleUsers() {
        logger.info("Running testPlaySongMultipleUsers...");
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User2");
        store.playSong("Song3", "User1");
        store.playSong("Song4", "User3");

        List<String> user1RecentlyPlayed = store.getRecentlyPlayed("User1");
        assertEquals(user1RecentlyPlayed.size(), 2);
        assertEquals(user1RecentlyPlayed.get(0), "Song3");
        assertEquals(user1RecentlyPlayed.get(1), "Song1");

        List<String> user2RecentlyPlayed = store.getRecentlyPlayed("User2");
        assertEquals(user2RecentlyPlayed.size(), 1);
        assertEquals(user2RecentlyPlayed.get(0), "Song2");

        List<String> user3RecentlyPlayed = store.getRecentlyPlayed("User3");
        assertEquals(user3RecentlyPlayed.size(), 1);
        assertEquals(user3RecentlyPlayed.get(0), "Song4");
    }

    @Test
    public void testPlaySongExceedCapacity() {
        logger.info("Running testPlaySongExceedCapacity...");
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User1");
        store.playSong("Song3", "User1");
        store.playSong("Song4", "User1");
        store.playSong("Song5", "User1");

        List<String> recentlyPlayed = store.getRecentlyPlayed("User1");
        assertEquals(recentlyPlayed.size(), 3);
        assertEquals(recentlyPlayed.get(0), "Song5");
        assertEquals(recentlyPlayed.get(1), "Song4");
        assertEquals(recentlyPlayed.get(2), "Song3");
    }

    @Test
    public void testPlaySongExistingSong() {
        logger.info("Running testPlaySongExistingSong...");
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User1");
        store.playSong("Song3", "User1");
        store.playSong("Song2", "User1"); // Play existing song again

        List<String> recentlyPlayed = store.getRecentlyPlayed("User1");
        assertEquals(recentlyPlayed.size(), 3);
        assertEquals(recentlyPlayed.get(0), "Song2"); // Song2 should move to the front
        assertEquals(recentlyPlayed.get(1), "Song3");
        assertEquals(recentlyPlayed.get(2), "Song2");
    }

    @Test
    public void testPlaySongNewUser() {
        logger.info("Running testPlaySongNewUser...");
        store.playSong("Song1", "User1");
        store.playSong("Song2", "User1");
        store.playSong("Song3", "User1");
        store.playSong("Song1", "User2");
        store.playSong("Song2", "User2");
        store.playSong("Song3", "User2");
        store.playSong("Song4", "User2");
        store.playSong("Song5", "User2");

        List<String> user1RecentlyPlayed = store.getRecentlyPlayed("User1");
        List<String> user2RecentlyPlayed = store.getRecentlyPlayed("User2");

        assertEquals(user1RecentlyPlayed.size(), 3);
        assertEquals(user1RecentlyPlayed.get(0), "Song3");
        assertEquals(user1RecentlyPlayed.get(1), "Song2");
        assertEquals(user1RecentlyPlayed.get(2), "Song1");

        assertEquals(user2RecentlyPlayed.size(), 3);
        assertEquals(user2RecentlyPlayed.get(0), "Song5");
        assertEquals(user2RecentlyPlayed.get(1), "Song4");
        assertEquals(user2RecentlyPlayed.get(2), "Song3");
    }

    @Test
    public void testPlaySongEmptyRecentlyPlayed() {
        logger.info("Running testPlaySongEmptyRecentlyPlayed...");
        List<String> recentlyPlayed = store.getRecentlyPlayed("User1");
        assertEquals(recentlyPlayed.size(), 0);
    }
}
