import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P08_IncreaseMinionAge {

    private static final String INCREASE_MINION_AGE = "update minions as m set m.age = m.age + 1 where m.id = ?";
    private static final String SET_NAME_TO_LOWER_CASE = "update minions set name = LOWER(name) where id = ?";
    private static final String SELECT_ALL_MINIONS = "select name, age from minions";
    private static final String MINION_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        String[] minionId = scanner.nextLine().split(" ");

        for (int i = 0; i < minionId.length; i++) {

            int currentMinionId = Integer.parseInt(minionId[i]);

            final PreparedStatement increaseMinionAgeStatement = connection.prepareStatement(INCREASE_MINION_AGE);

            increaseMinionAgeStatement.setInt(1, currentMinionId);

            increaseMinionAgeStatement.executeUpdate();

            final PreparedStatement setNameToLowerStatement = connection.prepareStatement(SET_NAME_TO_LOWER_CASE);

            setNameToLowerStatement.setInt(1, currentMinionId);

            setNameToLowerStatement.executeUpdate();
        }

        final PreparedStatement getAllMinionsStatement = connection.prepareStatement(SELECT_ALL_MINIONS);

        ResultSet allMinionsSet = getAllMinionsStatement.executeQuery();

        while (allMinionsSet.next()) {
            String minionName = allMinionsSet.getString(Constants.COLUMN_LABEL_NAME);
            int minionAge = allMinionsSet.getInt(Constants.COLUMN_LABEL_AGE);
            System.out.printf(MINION_FORMAT, minionName, minionAge);
        }
    }
}
