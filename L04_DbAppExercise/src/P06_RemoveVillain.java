import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P06_RemoveVillain {

    private static final String REMOVE_FROM_VILLAINS_BY_ID = "delete from villains as v where v.id = ?";
    private static final String NO_SUCH_VILLAIN_MESSAGE = "No such villain was found";
    private static final String GET_VILLAIN_NAME_BY_ID = "select name from villains where id = ?";
    private static final String REMOVED_VILLAIN_NAME_MESSAGE = "%s was deleted%n";

    private static final String GET_COUNT_MINIONS = "select count(*) as minionsCount from minions_villains as mv where villain_id = ?";
    private static final String COLUMN_LABEL_MINIONS_COUNT = "minionsCount";
    private static final String REMOVE_FROM_MINIONS_VILLAINS = "delete from minions_villains as mv where mv.villain_id = ?";
    private static final String REMOVED_MINIONS_COUNT_MESSAGE = "%d minions released%n";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        int villainId = Integer.parseInt(scanner.nextLine());
        String villainName = "";

        final PreparedStatement getVillainNameStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        getVillainNameStatement.setInt(1, villainId);
        ResultSet villainNameSet = getVillainNameStatement.executeQuery();

        if (villainNameSet.next()) {
            villainName = villainNameSet.getString(Constants.COLUMN_LABEL_NAME);
        } else {
            System.out.println(NO_SUCH_VILLAIN_MESSAGE);
            return;
        }

        PreparedStatement getCountMinionsStatement = connection.prepareStatement(GET_COUNT_MINIONS);
        getCountMinionsStatement.setInt(1, villainId);
        ResultSet countMinionsSet = getCountMinionsStatement.executeQuery();

        int countMinions = 0;
        if (countMinionsSet.next()) {
            countMinions = countMinionsSet.getInt(COLUMN_LABEL_MINIONS_COUNT);
        }

        final PreparedStatement removeFromMinionsVillains = connection.prepareStatement(REMOVE_FROM_MINIONS_VILLAINS);
        removeFromMinionsVillains.setInt(1, villainId);
        removeFromMinionsVillains.executeUpdate();

        final PreparedStatement removeVillainStatement = connection.prepareStatement(REMOVE_FROM_VILLAINS_BY_ID);
        removeVillainStatement.setInt(1, villainId);
        removeVillainStatement.executeUpdate();

        System.out.printf(REMOVED_VILLAIN_NAME_MESSAGE, villainName);

        System.out.printf(REMOVED_MINIONS_COUNT_MESSAGE, countMinions);

        connection.close();

    }
}
