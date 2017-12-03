package ohtu;

public class TennisGame {
    
    private int m_scorePlayer1 = 0;
    private int m_scorePlayer2 = 0;
    private String m_player1Name;
    private String m_player2Name;
    
    private static final int SCORE_LOVE = 0;
    private static final int SCORE_FIFTEEN = 1;
    private static final int SCORE_THIRTY = 2;
    private static final int SCORE_FORTY = 3;

    public TennisGame(String player1Name, String player2Name) {
        m_player1Name = player1Name;
        m_player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == m_player1Name)
            m_scorePlayer1 += 1;
        else
            m_scorePlayer2 += 1;
    }
    
    private String getEqualScore() {
        switch (m_scorePlayer1)
        {
            case SCORE_LOVE:
                return "Love-All";
            case SCORE_FIFTEEN:
                return "Fifteen-All";
            case SCORE_THIRTY:
                return "Thirty-All";
            case SCORE_FORTY:
                return "Forty-All";
        }
        return "Deuce";
    }
    
    private String getWinner() {
        if (m_scorePlayer1 > m_scorePlayer2)
            return "Win for " + m_player1Name;
        return "Win for " + m_player2Name;
    }
    
    private String getAdvantage() {
        if (m_scorePlayer1 > m_scorePlayer2)
            return "Advantage " + m_player1Name;
        return "Advantage " + m_player2Name;
    }
    
    private String getScoreText(int score) {
        switch (score)
        {
            case SCORE_LOVE:
                return "Love";
            case SCORE_FIFTEEN:
                return "Fifteen";
            case SCORE_THIRTY:
                return "Thirty";
            case SCORE_FORTY:
                return "Forty";
        }
        return "";
    }

    public String getScore() {
        if (m_scorePlayer1 == m_scorePlayer2)
            return getEqualScore();
        else if (m_scorePlayer1 > SCORE_FORTY || m_scorePlayer2 > SCORE_FORTY)
        {
            if (Math.abs(m_scorePlayer1 - m_scorePlayer2) == 1)
                return getAdvantage();
            return getWinner();
        }
        
        String score = getScoreText(m_scorePlayer1);
        score += "-";
        score += getScoreText(m_scorePlayer2);
        return score;
    }
}