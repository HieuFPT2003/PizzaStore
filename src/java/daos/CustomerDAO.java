package daos;

import dtos.CustomerDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public List<CustomerDTO> getAllCustomers() {
        String sql = "SELECT * FROM Customers";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CustomerDTO> customers = new ArrayList<>();
            while (resultSet.next()) {
                CustomerDTO customer = CustomerDTO.builder()
                        .customerId(resultSet.getLong("customerId"))
                        .password(resultSet.getString("password"))
                        .contactName(resultSet.getString("contactName"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                customers.add(customer);
            }
            return customers;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerDTO getCustomerById(Long customerId) {
        String sql = "SELECT * FROM Customers WHERE customerId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CustomerDTO customer = CustomerDTO.builder()
                        .customerId(resultSet.getLong("customerId"))
                        .password(resultSet.getString("password"))
                        .contactName(resultSet.getString("contactName"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                return customer;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addCustomer(CustomerDTO customer) {
        String sql = "INSERT INTO Customers (password, contactName, address, phone) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getPassword());
            preparedStatement.setString(2, customer.getContactName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(CustomerDTO customer) {
        String sql = "UPDATE Customers SET password = ?, contactName = ?, address = ?, phone = ? WHERE customerId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getPassword());
            preparedStatement.setString(2, customer.getContactName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setLong(5, customer.getCustomerId());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(Long customerId) {
        String sql = "DELETE FROM Customers WHERE customerId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customerId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CustomerDTO findOneByPhone(String phone) {
        String sql = "SELECT * FROM Customers WHERE phone = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CustomerDTO customer = CustomerDTO.builder()
                        .customerId(resultSet.getLong("customerId"))
                        .password(resultSet.getString("password"))
                        .contactName(resultSet.getString("contactName"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                return customer;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        List<CustomerDTO> customers = customerDAO.getAllCustomers();
        for (CustomerDTO customer : customers) {
            System.out.println(customer);
        }

    }
}
