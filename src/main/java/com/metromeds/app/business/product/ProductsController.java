package com.metromeds.app.business.product;

import com.metromeds.app.handlers.CommandHandler;
import com.metromeds.app.interceptor.response.ApiResponse;
import com.metromeds.app.models.product.Product;
import com.metromeds.app.repository.ProductRepository;
import com.metromeds.app.service.helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductsController implements CommandHandler {
    @Autowired
    ProductRepository productRepository;
    private Map<String, String> params;
    static final String OPERATION = "operation";

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity execute(String method, Map<String, String> parms) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        this.params = parms;
        ProductsController controller = new ProductsController(this.productRepository);
        controller.params = this.params;
        Method invokeCommand = this.getClass().getMethod(method);
        Object result = invokeCommand.invoke(controller);
        ApiResponse response = new ApiResponse();
        return response.setServletResponse(result);
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        if (this.params.containsKey("id")) {
            products.add(productById(Integer.parseInt(this.params.get("id"))));
        } else {
            productRepository.findAll().forEach(products::add);
        }
        return products;
    }

    public Product productById(Integer id) {
        return productRepository.findOne(id);
    }

    public ResponseEntity postProducts() throws IllegalAccessException, NoSuchFieldException, ParseException {
        Product product = new Product();
        product = (Product) helper.getVariables(product,this.params);
        productRepository.save(product);
        return new ResponseEntity("Successfully Saved Product", HttpStatus.OK);
    }

    public ResponseEntity deleteProducts() {
        if (this.params.containsKey("id")) {
            productRepository.delete(Integer.parseInt(this.params.get("id")));
            return new ResponseEntity("Product Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity("Failed to delete product", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
