package daos;

import dtos.SupplierDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    public List<SupplierDTO> getAllSuppliers() {
        String sql = "SELECT * FROM Suppliers";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<SupplierDTO> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                SupplierDTO supplier = SupplierDTO.builder()
                        .supplierId(resultSet.getLong("supplierId"))
                        .companyName(resultSet.getString("companyName"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                suppliers.add(supplier);
            }
            return suppliers;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SupplierDTO getSupplierById(Long supplierId) {
        String sql = "SELECT * FROM Suppliers WHERE supplierId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, supplierId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                SupplierDTO supplier = SupplierDTO.builder()
                        .supplierId(resultSet.getLong("supplierId"))
                        .companyName(resultSet.getString("companyName"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                return supplier;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addSupplier(SupplierDTO supplier) {
        String sql = "INSERT INTO Suppliers (companyName, address, phone) VALUES (?, ?, ?)";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, supplier.getCompanyName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getPhone());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(SupplierDTO supplier) {
        String sql = "UPDATE Suppliers SET companyName = ?, address = ?, phone = ? WHERE supplierId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, supplier.getCompanyName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getPhone());
            preparedStatement.setLong(4, supplier.getSupplierId());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(Long supplierId) {
        String sql = "DELETE FROM Suppliers WHERE supplierId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, supplierId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SupplierDTO> searchSuppliers(String search) {
        String sql = "SELECT * FROM Suppliers WHERE companyName LIKE ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<SupplierDTO> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                SupplierDTO supplier = SupplierDTO.builder()
                        .supplierId(resultSet.getLong("supplierId"))
                        .companyName(resultSet.getString("companyName"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .build();
                suppliers.add(supplier);
            }
            return suppliers;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isSupplierExists(Long supplierId) {
        String sql = "SELECT * FROM Suppliers WHERE supplierId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, supplierId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SupplierDAO supplierDAO = new SupplierDAO();
        List<SupplierDTO> suppliers = supplierDAO.getAllSuppliers();
        for (SupplierDTO supplier : suppliers) {
            System.out.println(supplier);
        }
    }
}
