package daos;

import dtos.OrderDTO;
import dtos.SalesReportDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final CustomerDAO customerDAO = new CustomerDAO();

    public List<OrderDTO> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDTO> orders = new ArrayList<>();
            while (resultSet.next()) {
                OrderDTO order = OrderDTO.builder()
                        .orderId(resultSet.getLong("orderId"))
                        .customer(customerDAO.getCustomerById(resultSet.getLong("customerId")))
                        .orderDate(resultSet.getString("orderDate"))
                        .requiredDate(resultSet.getString("requiredDate"))
                        .shippedDate(resultSet.getString("shippedDate"))
                        .freight(resultSet.getDouble("freight"))
                        .shipAddress(resultSet.getString("shipAddress"))
                        .build();
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OrderDTO getOrderById(Long orderId) {
        String sql = "SELECT * FROM Orders WHERE orderId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                OrderDTO order = OrderDTO.builder()
                        .orderId(resultSet.getLong("orderId"))
                        .customer(customerDAO.getCustomerById(resultSet.getLong("customerId")))
                        .orderDate(resultSet.getString("orderDate"))
                        .requiredDate(resultSet.getString("requiredDate"))
                        .shippedDate(resultSet.getString("shippedDate"))
                        .freight(resultSet.getDouble("freight"))
                        .shipAddress(resultSet.getString("shipAddress"))
                        .build();
                return order;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addOrder(OrderDTO order) {
        String sql = "INSERT INTO Orders (customerId, orderDate, requiredDate, shippedDate, freight, shipAddress) VALUES (?, GETDATE(), GETDATE(), GETDATE(), ?, ?)";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, order.getCustomer().getCustomerId());

            preparedStatement.setDouble(2, order.getFreight());
            preparedStatement.setString(3, order.getShipAddress());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public OrderDTO getTheLastOrder() {
        String sql = "SELECT TOP 1 * FROM Orders ORDER BY orderId DESC";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                OrderDTO order = OrderDTO.builder()
                        .orderId(resultSet.getLong("orderId"))
                        .customer(customerDAO.getCustomerById(resultSet.getLong("customerId")))
                        .orderDate(resultSet.getString("orderDate"))
                        .requiredDate(resultSet.getString("requiredDate"))
                        .shippedDate(resultSet.getString("shippedDate"))
                        .freight(resultSet.getDouble("freight"))
                        .shipAddress(resultSet.getString("shipAddress"))
                        .build();
                return order;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateOrder(OrderDTO order) {
        String sql = "UPDATE Orders SET customerId = ?, freight = ?, shipAddress = ? WHERE orderId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, order.getCustomer().getCustomerId());
            preparedStatement.setDouble(2, order.getFreight());
            preparedStatement.setString(3, order.getShipAddress());
            preparedStatement.setLong(4, order.getOrderId());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(Long orderId) {
        String sql = "DELETE FROM Orders WHERE orderId = ?";
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

    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        String sql = "SELECT * FROM Orders WHERE customerId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDTO> orders = null;
            while (resultSet.next()) {
                OrderDTO order = OrderDTO.builder()
                        .orderId(resultSet.getLong("orderId"))
                        .customer(customerDAO.getCustomerById(resultSet.getLong("customerId")))
                        .orderDate(resultSet.getString("orderDate"))
                        .requiredDate(resultSet.getString("requiredDate"))
                        .shippedDate(resultSet.getString("shippedDate"))
                        .freight(resultSet.getDouble("freight"))
                        .shipAddress(resultSet.getString("shipAddress"))
                        .build();
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDTO> getOrdersByDate(String date) {
        String sql = "SELECT * FROM Orders WHERE orderDate = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderDTO> orders = null;
            while (resultSet.next()) {
                OrderDTO order = OrderDTO.builder()
                        .orderId(resultSet.getLong("orderId"))
                        .customer(customerDAO.getCustomerById(resultSet.getLong("customerId")))
                        .orderDate(resultSet.getString("orderDate"))
                        .requiredDate(resultSet.getString("requiredDate"))
                        .shippedDate(resultSet.getString("shippedDate"))
                        .freight(resultSet.getDouble("freight"))
                        .shipAddress(resultSet.getString("shipAddress"))
                        .build();
                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<SalesReportDTO> getSalesReportByPeriod(String startDate, String endDate) {
        String sql = "SELECT Orders.orderId, Orders.orderDate, SUM(OrderDetails.unitPrice * OrderDetails.quantity) as totalSales " +
                "FROM Orders JOIN OrderDetails ON Orders.orderId = OrderDetails.orderId " +
                "WHERE Orders.orderDate BETWEEN ? AND ? " +
                "GROUP BY Orders.orderId, Orders.orderDate " +
                "ORDER BY totalSales DESC";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<SalesReportDTO> salesReports = new ArrayList<>();
            while (resultSet.next()) {
                SalesReportDTO salesReport = new SalesReportDTO(
                        resultSet.getLong("orderId"),
                        resultSet.getString("orderDate"),
                        resultSet.getDouble("totalSales")
                );
                salesReports.add(salesReport);
            }
            return salesReports;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getAllOrders());
    }

}
