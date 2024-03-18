/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Quang Hieu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private  Long productId;
    private String productName;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private int quantityPerUnit;
    private double unitPrice;
    private String productImage;
}
