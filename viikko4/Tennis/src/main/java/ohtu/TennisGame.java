package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        assert player1Name != null;
        assert player2Name != null;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName)) {
            player1Score++;
        } else if (player2Name.equals(playerName)){
            player2Score++;
        } else {
            throw new IllegalArgumentException("Player name is invalid");
        }
    }

    public String getScore() {
        if (player1Score == player2Score)  {
            return scoresAreEqual(player1Score);
        }

        if (player1Score >= 4 || player2Score >= 4)  {
            int scoreDifference = player1Score - player2Score;
            return scoresAreHigh(scoreDifference);
        }

        return lowScorePrefix(player1Score)
                + "-" + lowScorePrefix(player2Score);
    }

    private String scoresAreHigh(int scoreDifference) {
        String leadingPlayerName =
                scoreDifference > 0 ? player1Name : player2Name;
        return advantageOrWinPrefix(Math.abs(scoreDifference))
                + leadingPlayerName;
    }

    private String advantageOrWinPrefix(int scoreDifference) {
        if (scoreDifference == 1) {
            return "Advantage ";
        }
        return "Win for ";
    }

    private String scoresAreEqual(int score) {
        if (score > 3) {
            return "Deuce";
        }
        return lowScorePrefix(score) + "-All";
    }

    private String lowScorePrefix(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }
}