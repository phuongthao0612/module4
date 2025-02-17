package com.example.thi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT")
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 50, message = "Tên sản phẩm phải từ 5 đến 50 ký tự")
    @Column(name = "product_name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 100000, message = "Giá sản phẩm tối thiểu phải là 100,000 VND")
    @Column(name = "product_price", nullable = false)
    private Double price;

    @NotBlank(message = "Trạng thái không được để trống")
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255)")
    private String status;

    @NotNull(message = "Loại sản phẩm không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;
}
