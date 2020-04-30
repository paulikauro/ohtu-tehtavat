package statistics.matcher;

public class QueryBuilder {
    private Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public QueryBuilder playsIn(String team) {
        return appendAnd(new PlaysIn(team));
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        return appendAnd(new HasAtLeast(value, category));
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        return appendAnd(new HasFewerThan(value, category));
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        matcher = new Or(matchers);
        return this;
    }

    private QueryBuilder appendAnd(Matcher m) {
        matcher = new And(matcher, m);
        return this;
    }

    public Matcher build() {
        Matcher ready = matcher;
        matcher = new All();
        return ready;
    }
}
