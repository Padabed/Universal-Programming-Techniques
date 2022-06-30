package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IGroupRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private Connection _connection;

    public GroupRepository() {
        try {
            _connection = DriverManager.getConnection(URL, USER, PASSWORD);
            _connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GroupDTO> findByName(String name) {
        List<GroupDTO> groups = new ArrayList<>();
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "SELECT group_id, group_name, group_description " +
                            "FROM utp10.GROUPS " +
                            "WHERE group_name LIKE ?");
            statement.setString(1, name + "%");
            ResultSet result = statement.executeQuery();
            int id;
            String groupName;
            String description;
            while (result.next()) {
                id = result.getInt("group_id");
                groupName = result.getString("group_name");
                description = result.getString("group_description");
                groups.add(new GroupDTO(id, groupName, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public Connection getConnection() {
        return _connection;
    }

    @Override
    public void add(GroupDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "insert into utp10.groups(group_id, group_name, group_description)" +
                            "values (?, ?, ?)");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GroupDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "update utp10.groups " +
                            "set group_name=?, group_description=?" +
                            "where group_id=?");
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getDescription());
            statement.setInt(3, dto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "INSERT INTO utp10.groups (group_id, group_name, group_description) " +
                            "VALUES (?, ?, ?) " +
                            "ON CONFLICT (group_id) DO UPDATE " +
                            "SET group_name=?, group_description=?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.setString(4, dto.getName());
            statement.setString(5, dto.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(GroupDTO dto) {
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "delete from utp10.groups " +
                            "where group_id=?");
            statement.setInt(1, dto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroupDTO findById(int id) {
        GroupDTO group = null;
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "SELECT group_name, group_description " +
                            "FROM utp10.GROUPS WHERE group_id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String groupName;
            String description;
            if (result.next()) {
                groupName = result.getString("group_name");
                description = result.getString("group_description");
                group = new GroupDTO(id, groupName, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public void beginTransaction() {
        try {
            _connection.setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            _connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            _connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            PreparedStatement statement = _connection.prepareStatement("SELECT count(1) from utp10.groups");
            ResultSet result = statement.executeQuery();
            if (result.next())
                count = result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean exists(GroupDTO dto) {
        boolean exists = false;
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "SELECT count(1) from utp10.groups " +
                            "where group_id=? and group_name=? and group_description=?");
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            ResultSet result = statement.executeQuery();
            if (result.next() && result.getInt(1) > 0)
                exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void closeConnection() {
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
