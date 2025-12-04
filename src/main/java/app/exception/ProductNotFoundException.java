package app.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {

        super(String.format("Продукт с идентификатором %d не найден",id));
    }
}
