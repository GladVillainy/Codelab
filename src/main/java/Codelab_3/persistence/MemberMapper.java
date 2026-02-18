package Codelab_3.persistence;

import Codelab_3.entities.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberMapper {

    private Database database;

    public MemberMapper(Database database) {
        this.database = database;
    }

    public List<Member> getAllMembers() {

        List<Member> memberList = new ArrayList<>();

        String sql = "select member_id, name, address, m.zip, gender, city, year " +
                "from member m inner join zip using(zip) " +
                "order by member_id";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int memberId = rs.getInt("member_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int zip = rs.getInt("zip");
                    String city = rs.getString("city");
                    String gender = rs.getString("gender");
                    int year = rs.getInt("year");
                    memberList.add(new Member(memberId, name, address, zip, city, gender, year));
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return memberList;
    }

    public Member getMemberById(int memberId) {
        Member member = null;

        String sql = "select member_id, name, address, m.zip, gender, city, year " +
                "from member m inner join zip using(zip) " +
                "where member_id = ?";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, memberId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    memberId = rs.getInt("member_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int zip = rs.getInt("zip");
                    String city = rs.getString("city");
                    String gender = rs.getString("gender");
                    int year = rs.getInt("year");
                    member = new Member(memberId, name, address, zip, city, gender, year);
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int a = 1;
        return member;
    }

    public boolean deleteMember(int member_id) {
        boolean result = false;
        String sql = "delete from member where member_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, member_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }

    public Member insertMember(Member member) {
        boolean result = false;
        int newId = 0;
        String sql = "insert into member (name, address, zip, gender, year) values (?,?,?,?,?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, member.getName());
                ps.setString(2, member.getAddress());
                ps.setInt(3, member.getZip());
                ps.setString(4, member.getGender());
                ps.setInt(5, member.getYear());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);
                    member.setMemberId(newId);
                } else {
                    member = null;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return member;
    }

    public boolean updateMember(Member member) {
        boolean result = false;
        String sql = "update member " +
                "set name = ?, address = ?, zip = ?, gender = ?, year = ? " +
                "where member_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, member.getName());
                ps.setString(2, member.getAddress());
                ps.setInt(3, member.getZip());
                ps.setString(4, member.getGender());
                ps.setInt(5, member.getYear());
                ps.setInt(6, member.getMemberId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }

    public void teamParticipants() {
        String sql =
                "SELECT t.team_id, COUNT(m.member_id)\n" +
                        "FROM \"member\" m \n" +
                        "JOIN registration r ON m.member_id = r.member_id\n" +
                        "JOIN team t ON r.team_id = t.team_id\n" +
                        "GROUP BY t.team_id";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String teamid = rs.getString("team_id");
                    int count = rs.getInt("count");
                    System.out.println(
                            "Team id: " + teamid + "\n" +
                                    "Amount of participants: " + count + "\n"
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public void sportParticipants() {
        String sql =
                "SELECT s.sport_id, COUNT(m.member_id)\n" +
                        "FROM \"member\" m \n" +
                        "JOIN registration r ON m.member_id = r.member_id\n" +
                        "JOIN team t on t.team_id = r.team_id\n" +
                        "JOIN sport s on s.sport_id = t.sport_id\n" +
                        "GROUP BY s.sport_id";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String sportid = rs.getString("sport_id");
                    int count = rs.getInt("count");
                    System.out.println(
                            "Sport id: " + sportid + "\n" +
                                    "Amount of participants: " + count + "\n"
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public void seperatedByGender() {
        String sql =
                "SELECT gender, COUNT(gender) \n" +
                        "FROM \"member\"\n" +
                        "GROUP BY gender";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String gender = rs.getString("gender");
                    int count = rs.getInt("count");

                    System.out.println(
                            "Sport id: " + gender + "\n" +
                            "Amount of participants: " + count + "\n"
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public void totalSumIncome(){
        String sql =
                "SELECT SUM(price) income FROM registration";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String income = rs.getString("income");
                    
                    System.out.println(
                            "Income: " + income
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }


}

