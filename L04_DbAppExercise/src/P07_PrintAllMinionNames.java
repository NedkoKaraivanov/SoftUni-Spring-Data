import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P07_PrintAllMinionNames {

    public static final String GET_ALL_MINIONS = "select name from minions";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        List<String> minionsList = new ArrayList<>();

        final PreparedStatement getAllMinions = connection.prepareStatement(GET_ALL_MINIONS);
        final ResultSet allMinionsSet = getAllMinions.executeQuery();

        while (allMinionsSet.next()) {
            minionsList.add(allMinionsSet.getString(Constants.COLUMN_LABEL_NAME));
        }

        for (int i = 0; i < minionsList.size() / 2; i++) {
            System.out.println(minionsList.get(i));
            System.out.println(minionsList.get(minionsList.size() - i - 1));
        }
    }
}
