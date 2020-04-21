package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        List<Player> finnishPlayers = Arrays.stream(mapper.fromJson(bodyText,
                Player[].class))
                .filter(p -> p.getNationality().equals("FIN"))
                .collect(toList());
        finnishPlayers.sort(comparing(Player::score).reversed());

        System.out.println("Players from FIN\n");
        for (Player player : finnishPlayers) {
            System.out.println(player);
        }
    }

}
