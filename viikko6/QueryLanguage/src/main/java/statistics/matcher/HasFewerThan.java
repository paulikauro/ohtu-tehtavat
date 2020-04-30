package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
    private final Matcher inner;

    public HasFewerThan(int value, String category) {
        this.inner = new Not(new HasAtLeast(value, category));
    }

    @Override
    public boolean matches(Player p) {
        return inner.matches(p);
    }
}
