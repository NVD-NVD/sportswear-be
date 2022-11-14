package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.controllers.ProductController;
import com.ute.sportswearbe.controllers.UserController;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Value("${host}")
    private String host;

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getContainingClass() == ProductController.class || returnType.getContainingClass() == UserController.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof User){
            ((User) body).setAvatar(host + ((User) body).getAvatar());
        }
        if (body instanceof Product){
            ((Product) body).setImages(instanceOfProduct((Product) body));
        }
        if (body instanceof List && ((List<?>) body).size() > 0) {
            if (((List<?>) body).get(0) instanceof User){
                for (int i = 0; i < ((List<?>) body).size(); i++) {
                    User user = ((List<User>) body).get(i);
                    user.setAvatar(host + user.getAvatar());
                    ((List<User>) body).set(i, user);
                }
            }
            if (((List<?>) body).get(0) instanceof Product){
                for (int i = 0; i < ((List<Product>) body).size(); i++) {
                    Product product = (Product) ((List<?>) body).get(i);
                    product.setImages(instanceOfProduct(product));
                    ((List<Product>) body).set(i, product);
                }
            }
        }
        return body;
    }
    private List<String> instanceOfProduct(Product product){
        List<String> imgs = product.getImages();
        List<String> link = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            link.add(host + imgs.get(i));
        }
        return link;
    }
}
