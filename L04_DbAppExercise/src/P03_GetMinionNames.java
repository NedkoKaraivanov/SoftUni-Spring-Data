import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P03_GetMinionNames {

    private static final String GET_MINIONS_NAME_AGE_BY_VILLAIN_ID =
            "select m.name, m.age" +
                    " from minions as m" +
                    " join minions_villains as mv on m.id = mv.minion_id" +
                    " where mv.villain_id = ?";

    private static final String GET_VILLAIN_NAME_BY_ID = "select v.name " +
            " from villains as v" +
            " where v.id = ?";

    private static final String COLUMN_LABEL_AGE = "age";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";

    private static final String NO_VILLAIN_FOUND_FORMAT = "No villain with ID %d exists in the database.";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        final int villainId = scanner.nextInt();

        villainStatement.setInt(1, villainId);

        final ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN_FOUND_FORMAT, villainId);
            connection.close();
            return;
        }

        final String villainName = villainSet.getString(Constants.COLUMN_LABEL_NAME);

        System.out.printf(VILLAIN_FORMAT, villainName);

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_NAME_AGE_BY_VILLAIN_ID);

        minionsStatement.setInt(1, villainId);

         final ResultSet minionsSet = minionsStatement.executeQuery();

        for (int index = 1; minionsSet.next(); index++) {
            final String minionName = minionsSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionAge = minionsSet.getInt(COLUMN_LABEL_AGE);

            System.out.printf(MINION_FORMAT, index, minionName, minionAge);
        }

        connection.close();
    }
}

