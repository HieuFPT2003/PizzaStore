package daos;

import dtos.ProductDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final SupplierDAO supplierDAO = new SupplierDAO();
    public List<ProductDTO> getAllProducts() {
        String sql = "SELECT * FROM Products ORDER BY productId DESC";  // DESC: descending order
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDTO> products = new ArrayList<>();
            while (resultSet.next()) {
                ProductDTO product = ProductDTO.builder()
                        .productId(resultSet.getLong("productId"))
                        .productName(resultSet.getString("productName"))
                        .supplier(supplierDAO.getSupplierById(resultSet.getLong("supplierId")))
                        .category(categoryDAO.getCategoryById(resultSet.getLong("categoryId")))
                        .quantityPerUnit(resultSet.getInt("quantityPerUnit"))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .productImage(resultSet.getString("productImage"))
                        .build();
                products.add(product);
            }
            return products;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProductDTO getProductById(Long productId) {
        String sql = "SELECT * FROM Products WHERE productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProductDTO product = ProductDTO.builder()
                        .productId(resultSet.getLong("productId"))
                        .productName(resultSet.getString("productName"))
                        .supplier(supplierDAO.getSupplierById(resultSet.getLong("supplierId")))
                        .category(categoryDAO.getCategoryById(resultSet.getLong("categoryId")))
                        .quantityPerUnit(resultSet.getInt("quantityPerUnit"))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .productImage(resultSet.getString("productImage"))
                        .build();
                return product;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addProduct(ProductDTO product) {
        String sql = "INSERT INTO Products (productName, supplierId, categoryId, quantityPerUnit, unitPrice, productImage) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setLong(2, product.getSupplier().getSupplierId());
            preparedStatement.setLong(3, product.getCategory().getCategoryId());
            preparedStatement.setInt(4, product.getQuantityPerUnit());
            preparedStatement.setDouble(5, product.getUnitPrice());
            preparedStatement.setString(6, product.getProductImage());
            return preparedStatement.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(ProductDTO product) {
        String sql = "UPDATE Products SET productName = ?, supplierId = ?, categoryId = ?, quantityPerUnit = ?, unitPrice = ?, productImage = ? WHERE productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setLong(2, product.getSupplier().getSupplierId());
            preparedStatement.setLong(3, product.getCategory().getCategoryId());
            preparedStatement.setInt(4, product.getQuantityPerUnit());
            preparedStatement.setDouble(5, product.getUnitPrice());
            preparedStatement.setString(6, product.getProductImage());
            preparedStatement.setLong(7, product.getProductId());
            return preparedStatement.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(Long productId) {
        String sql = "DELETE FROM Products WHERE productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, productId);
            return preparedStatement.executeUpdate() > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductDTO> getProductByProductName(String productName) {
        String sql = "SELECT * FROM Products WHERE productName LIKE ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + productName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDTO> products = new ArrayList<>();
            while (resultSet.next()) {
                ProductDTO product = ProductDTO.builder()
                        .productId(resultSet.getLong("productId"))
                        .productName(resultSet.getString("productName"))
                        .supplier(supplierDAO.getSupplierById(resultSet.getLong("supplierId")))
                        .category(categoryDAO.getCategoryById(resultSet.getLong("categoryId")))
                        .quantityPerUnit(resultSet.getInt("quantityPerUnit"))
                        .quantityPerUnit(resultSet.getInt("quantityPerUnit"))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .productImage(resultSet.getString("productImage"))
                        .build();
                products.add(product);
            }
            return products;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<ProductDTO> searchProductByIdOrNameOrUnitPrice(String keyword) {
        String sql = "SELECT * FROM Products WHERE productId LIKE ? OR productName LIKE ? OR unitPrice LIKE ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDTO> products = new ArrayList<>();
            while (resultSet.next()) {
                ProductDTO product = ProductDTO.builder()
                        .productId(resultSet.getLong("productId"))
                        .productName(resultSet.getString("productName"))
                        .supplier(supplierDAO.getSupplierById(resultSet.getLong("supplierId")))
                        .category(categoryDAO.getCategoryById(resultSet.getLong("categoryId")))
                        .quantityPerUnit(resultSet.getInt("quantityPerUnit"))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .productImage(resultSet.getString("productImage"))
                        .build();
                products.add(product);
            }
            return products;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }


        public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

    }
}
