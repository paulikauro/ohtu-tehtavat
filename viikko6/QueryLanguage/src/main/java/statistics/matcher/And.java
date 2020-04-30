package statistics.matcher;

import statistics.Player;

import java.util.Arrays;

public class And implements Matcher {

    private Matcher[] matchers;

    public And(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        return Arrays.stream(matchers)
                .allMatch(matcher -> matcher.matches(p));
    }
}
