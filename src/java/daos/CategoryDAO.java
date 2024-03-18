package daos;

import dtos.CategoryDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<CategoryDTO> getAllCategories() {
        String sql = "SELECT * FROM Categories";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<CategoryDTO> categories = new ArrayList<>();
            while (rs.next()) {
                CategoryDTO category = CategoryDTO.builder()
                        .categoryId(rs.getLong("categoryId"))
                        .categoryName(rs.getString("categoryName"))
                        .description(rs.getString("description"))
                        .build();
                categories.add(category);
            }
            return categories;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CategoryDTO getCategoryById(Long categoryId) {
        String sql = "SELECT * FROM Categories WHERE categoryId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                CategoryDTO category = CategoryDTO.builder()
                        .categoryId(rs.getLong("categoryId"))
                        .categoryName(rs.getString("categoryName"))
                        .description(rs.getString("description"))
                        .build();
                return category;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addCategory(CategoryDTO category) {
        String sql = "INSERT INTO Categories (categoryName, description) VALUES (?, ?)";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getDescription());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(CategoryDTO category) {
        String sql = "UPDATE Categories SET categoryName = ?, description = ? WHERE categoryId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getDescription());
            statement.setLong(3, category.getCategoryId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(Long categoryId) {
        String sql = "DELETE FROM Categories WHERE categoryId = ?";
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, categoryId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryDTO> categories = categoryDAO.getAllCategories();
        for (CategoryDTO category : categories) {
            System.out.println(category);
        }
    }
}
