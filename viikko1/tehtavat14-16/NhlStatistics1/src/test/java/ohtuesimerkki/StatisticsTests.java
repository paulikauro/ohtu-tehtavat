package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class StatisticsTests {
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            return players;
        }
    };

    Statistics stats;

    @Before
    public void initStats() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchingForNotFoundPlayerReturnsNull() {
        assertEquals(null, stats.search("asdf"));
    }

    @Test
    public void searchingReturnsPlayer() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }

    @Test
    public void teamsWork() {
        assertEquals(3, stats.team("EDM").size());
    }

    @Test
    public void topScorersWork() {
        List<String> topScorerNames = stats.topScorers(2).stream()
                .map(Player::getName).collect(toList());
        List<String> expected = Arrays.asList("Gretzky", "Lemieux");
        assertEquals(expected, topScorerNames);
    }
}
