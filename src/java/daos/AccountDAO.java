package daos;

import dtos.AccountDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public List<AccountDTO> getAllAccounts() {
        String sql = "SELECT * FROM Account";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<AccountDTO> accounts = new ArrayList<>();
            while (rs.next()) {
                AccountDTO account = AccountDTO.builder()
                        .accountId(rs.getString("accountId"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .fullName(rs.getString("fullName"))
                        .type(rs.getInt("type"))
                        .customerId(rs.getLong("customerId"))
                        .build();
                accounts.add(account);
            }
            return accounts;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccountDTO getAccountById(String accountId) {
        String sql = "SELECT * FROM Account WHERE accountId = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                AccountDTO account = AccountDTO.builder()
                        .accountId(rs.getString("accountId"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .fullName(rs.getString("fullName"))
                        .type(rs.getInt("type"))
                        .customerId(rs.getLong("customerId"))
                        .build();
                return account;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccountDTO getAccountByUsername(String username) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                AccountDTO account = AccountDTO.builder()
                        .accountId(rs.getString("accountId"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .fullName(rs.getString("fullName"))
                        .type(rs.getInt("type"))
                        .customerId(rs.getLong("customerId"))
                        .build();
                return account;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createAccount(AccountDTO account) {
        String sql = "INSERT INTO Account(accountId, username, password, fullName, type) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, account.getAccountId());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getFullName());
            statement.setInt(5, account.getType());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(AccountDTO account) {
        String sql = "UPDATE Account SET username = ?, password = ?, fullName = ?, type = ? WHERE accountId = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullName());
            statement.setInt(4, account.getType());
            statement.setString(5, account.getAccountId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(String accountId) {
        String sql = "DELETE FROM Account WHERE accountId = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, accountId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AccountDTO login(String accountId, String password) {
        String sql = "SELECT * FROM Account WHERE accountId = ? AND password = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, accountId);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                AccountDTO account = AccountDTO.builder()
                        .accountId(rs.getString("accountId"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .fullName(rs.getString("fullName"))
                        .type(rs.getInt("type"))
                        .customerId(rs.getLong("customerId"))
                        .build();
                return account;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccountDTO getAccountByCustomerId(Long customerId) {
        String sql = "SELECT * FROM Account WHERE customerId = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, customerId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                AccountDTO account = AccountDTO.builder()
                        .accountId(rs.getString("accountId"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .fullName(rs.getString("fullName"))
                        .type(rs.getInt("type"))
                        .build();
                return account;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        List<AccountDTO> accounts = accountDAO.getAllAccounts();
        for (AccountDTO account : accounts) {
            System.out.println(account);
        }
    }
}
