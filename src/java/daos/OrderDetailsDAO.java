package daos;

import dtos.OrderDTO;
import dtos.OrderDetailsDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO {
    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();
    public List<OrderDetailsDTO> getAllOrdersDetails() {
        String sql = "SELECT * FROM OrderDetails";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList =  new ArrayList<>();
            while (resultSet.next()) {
                OrderDetailsDTO orderDetail = OrderDetailsDTO.builder()
                        .order(orderDAO.getOrderById(resultSet.getLong("orderId")))
                        .product(productDAO.getProductById(resultSet.getLong("productId")))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .quantity(resultSet.getInt("quantity"))
                        .build();
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderDetailsDTO getOrderDetailsById(Long orderId, Long productId) {
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ? AND productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                OrderDetailsDTO orderDetail = OrderDetailsDTO.builder()
                        .order(orderDAO.getOrderById(resultSet.getLong("orderId")))
                        .product(productDAO.getProductById(resultSet.getLong("productId")))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .quantity(resultSet.getInt("quantity"))
                        .build();
                return orderDetail;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDetailsDTO> getOrderDetailsByCustomerId(Long customerId) {
        String sql = "SELECT * FROM OrderDetails WHERE orderId IN (SELECT orderId FROM Orders WHERE customerId = ? )";

        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList =  new ArrayList<>();
            while (resultSet.next()) {
                OrderDetailsDTO orderDetail = OrderDetailsDTO.builder()
                        .order(orderDAO.getOrderById(resultSet.getLong("orderId")))
                        .product(productDAO.getProductById(resultSet.getLong("productId")))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .quantity(resultSet.getInt("quantity"))
                        .build();
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDetailsDTO> getOrderDetailsByOrderId(Long orderId) {
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList =  new ArrayList<>();
            while (resultSet.next()) {
                OrderDetailsDTO orderDetail = OrderDetailsDTO.builder()
                        .order(orderDAO.getOrderById(resultSet.getLong("orderId")))
                        .product(productDAO.getProductById(resultSet.getLong("productId")))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .quantity(resultSet.getInt("quantity"))
                        .build();
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDetailsDTO> getOrderDetailsByProductId(Long productId) {
        String sql = "SELECT * FROM OrderDetails WHERE productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDetailsDTO> ordersDetailsList =  new ArrayList<>();
            while (resultSet.next()) {
                OrderDetailsDTO orderDetail = OrderDetailsDTO.builder()
                        .order(orderDAO.getOrderById(resultSet.getLong("orderId")))
                        .product(productDAO.getProductById(resultSet.getLong("productId")))
                        .unitPrice(resultSet.getDouble("unitPrice"))
                        .quantity(resultSet.getInt("quantity"))
                        .build();
                ordersDetailsList.add(orderDetail);
            }
            return ordersDetailsList;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addOrderDetails(OrderDetailsDTO orderDetails) {
        String sql = "INSERT INTO OrderDetails (orderId, productId, unitPrice, quantity) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderDetails.getOrder().getOrderId());
            preparedStatement.setLong(2, orderDetails.getProduct().getProductId());
            preparedStatement.setDouble(3, orderDetails.getUnitPrice());
            preparedStatement.setInt(4, orderDetails.getQuantity());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderDetails(OrderDetailsDTO orderDetails) {
        String sql = "UPDATE OrderDetails SET unitPrice = ?, quantity = ? WHERE orderId = ? AND productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, orderDetails.getUnitPrice());
            preparedStatement.setInt(2, orderDetails.getQuantity());
            preparedStatement.setLong(3, orderDetails.getOrder().getOrderId());
            preparedStatement.setLong(4, orderDetails.getProduct().getProductId());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetails(Long orderId, Long productId) {
        String sql = "DELETE FROM OrderDetails WHERE orderId = ? AND productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetailsByOrderId(Long orderId) {
        String sql = "DELETE FROM OrderDetails WHERE orderId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetailsByProductId(Long productId) {
        String sql = "DELETE FROM OrderDetails WHERE productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, productId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetailsByOrderIdAndProductId(Long orderId, Long productId) {
        String sql = "DELETE FROM OrderDetails WHERE orderId = ? AND productId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        List<OrderDetailsDTO> ordersDetailsList = orderDetailsDAO.getAllOrdersDetails();
        for (OrderDetailsDTO orderDetails : ordersDetailsList) {
            System.out.println(orderDetails);
        }
    }
}
