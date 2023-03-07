import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillainV2 {

    private static final String GET_VILLAIN_BY_ID = "select v.name from villains as v where id = ?";
    private static final String NO_SUCH_VILLAIN_MESSAGE = "No such villain was found";
    private static final String GET_MINION_COUNT_BY_VILLAIN_ID = "select count( mv.minion_id) as minionsCount from minions_villains as mv where mv.villain_id = ?";
    private static final String COLUMN_LABEL_MINION_COUNT = "minionsCount";
    private static final String DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID = "delete from minions_villains as mv where mv.villain_id = ?";
    private static final String DELETE_VILLAIN_BY_ID = "delete from villains as v where v.id = ?";
    private static final String DELETED_VILLAIN_FORMAT = "%s was deleted%n";
    private static final String DELETED_COUNT_MINIONS_FORMAT = "%d minions released%n";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        connection.setAutoCommit(false);

        final int villainId = scanner.nextInt();

        final PreparedStatement selectedVillainStatement = connection.prepareStatement(GET_VILLAIN_BY_ID);
        selectedVillainStatement.setInt(1, villainId);

        final ResultSet villainSet = selectedVillainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.println(NO_SUCH_VILLAIN_MESSAGE);
            return;
        }

        final String villainName = villainSet.getString(Constants.COLUMN_LABEL_NAME);

        final PreparedStatement selectAllMinions = connection.prepareStatement(GET_MINION_COUNT_BY_VILLAIN_ID);
        selectAllMinions.setInt(1, villainId);

        final ResultSet countMinionsSet = selectAllMinions.executeQuery();
        countMinionsSet.next();

        final int countDeletedMinions = countMinionsSet.getInt(COLUMN_LABEL_MINION_COUNT);

        try (PreparedStatement deleteMinionStatement = connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID);
             PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN_BY_ID)) {

            deleteMinionStatement.setInt(1, villainId);
            deleteMinionStatement.executeUpdate();

            deleteVillainStatement.setInt(1, villainId);
            deleteVillainStatement.executeUpdate();

            connection.commit();

            System.out.printf(DELETED_VILLAIN_FORMAT, villainName);
            System.out.printf(DELETED_COUNT_MINIONS_FORMAT, countDeletedMinions);

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }
}
