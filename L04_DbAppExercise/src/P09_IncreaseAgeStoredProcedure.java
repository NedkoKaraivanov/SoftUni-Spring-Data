import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P09_IncreaseAgeStoredProcedure {

    private static final String CALL_STORED_PROCEDURE = "CALL usp_get_older(?)";
    private static final String SELECT_MINION = "select * from minions where id = ?";
    private static final String MINION_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        int currentMinionId = Integer.parseInt(scanner.nextLine());

        final PreparedStatement increaseMinionAgeStatement = connection.prepareStatement(CALL_STORED_PROCEDURE);

        increaseMinionAgeStatement.setInt(1, currentMinionId);

        increaseMinionAgeStatement.executeUpdate();

        final PreparedStatement getMinionStatement = connection.prepareStatement(SELECT_MINION);
        getMinionStatement.setInt(1, currentMinionId);

        ResultSet currentMinionsSet = getMinionStatement.executeQuery();

        if (!currentMinionsSet.next()) {
            System.out.println("No such minion Id");
        } else {
            String minionName = currentMinionsSet.getString(Constants.COLUMN_LABEL_NAME);
            int minionAge = currentMinionsSet.getInt(Constants.COLUMN_LABEL_AGE);

            System.out.printf(MINION_FORMAT, minionName, minionAge);
        }
    }
}
