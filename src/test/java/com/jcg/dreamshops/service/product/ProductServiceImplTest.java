package com.jcg.dreamshops.service.product;

import com.jcg.dreamshops.dto.request.UpdateProductRequest;
import com.jcg.dreamshops.model.Product;
import com.jcg.dreamshops.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @Captor
    private ArgumentCaptor<Product> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = Product.builder()
                .id(1L)
                .name("productName1")
                .brand("productBrand1")
                .description("productDescription1")
                .price(BigDecimal.valueOf(100.0))
                .inventory(5)
                .build();
    }

    @Test
    void updateProduct() {
        UpdateProductRequest request = new UpdateProductRequest(
                "newName",
                "newBrand",
                "newDescription",
                null,
                new BigDecimal("50.00"),
                4
        );
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        doNothing().when(mapper).map(any(UpdateProductRequest.class), any(Product.class));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(request, 1L);

        assertNotNull(updatedProduct);
        verify(productRepository, times(1)).findById(1L);
        verify(mapper, times(1)).map(request, product);
        verify(productRepository, times(1)).save(captor.capture());

        // Verify that the product was correctly mapped to the update request
        Product capturedProduct = captor.getValue();
        assertEquals(request.getName(), capturedProduct.getName());
        assertEquals(request.getBrand(), capturedProduct.getBrand());
        assertEquals(request.getDescription(), capturedProduct.getDescription());
        assertEquals(request.getPrice(), capturedProduct.getPrice());

    }
}