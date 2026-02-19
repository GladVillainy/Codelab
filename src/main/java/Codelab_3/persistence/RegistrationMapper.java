package Codelab_3.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationMapper {
    private Database database;
    public RegistrationMapper(Database database) {
        this.database = database;
    }


    public void addToTeam(int member_id, String team_id, int price){

        boolean result = false;

        String sql =
                "insert into registration(member_id, team_id, price) values (?,?,?)";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, member_id);
                ps.setString(2, team_id);
                ps.setInt(3, price);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(result);
    }

    public void getAllRegistrations(){
        List <Registration> registrations = new ArrayList<>();

        String sql = "SELECT * FROM registration";

        try(Connection connection = database.connect()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ps.getMetaData();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
