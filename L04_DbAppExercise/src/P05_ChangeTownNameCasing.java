import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05_ChangeTownNameCasing {

    private static final String UPDATE_TOWN_NAME = "update towns as t set t.name = upper(name) where t.country = ?";
    private static final String SELECT_ALL_TOWNS_BY_COUNTRY_NAME = "select name from towns as t where t.country = ?";

    private static final String AFFECTED_TOWNS_COUNT_MESSAGE = "%d town names were affected.%n";
    private static final String NO_TOWNS_AFFECTED = "No town names were affected.";

    private static final String COLUMN_LABEL_NAME = "name";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        final Connection connection = Utils.getSQLConnection();

        PreparedStatement updateStatement = connection.prepareStatement(UPDATE_TOWN_NAME);
        String countryName = scanner.nextLine();
        updateStatement.setString(1, countryName);
        int updatedCount = updateStatement.executeUpdate();

        PreparedStatement getTownsStatement = connection.prepareStatement(SELECT_ALL_TOWNS_BY_COUNTRY_NAME);
        getTownsStatement.setString(1, countryName);
        ResultSet affectedTownsSet = getTownsStatement.executeQuery();

        if (updatedCount == 0) {
            System.out.println(NO_TOWNS_AFFECTED);
        } else {
            System.out.printf(AFFECTED_TOWNS_COUNT_MESSAGE, updatedCount);

            List<String> towns = new ArrayList<>();
            while (affectedTownsSet.next()) {
                towns.add(affectedTownsSet.getString(COLUMN_LABEL_NAME));
            }

            System.out.println(towns);
        }

        connection.close();
    }
}
