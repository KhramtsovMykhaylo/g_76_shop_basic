package app.service;

import app.domain.Product;
import app.exception.ProductNotFoundException;
import app.exception.ProductSaveException;
import app.exception.ProductUpdateException;
import app.repository.ProductRepository;

import java.util.List;

/*
Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).
Вернуть все продукты из базы данных (активные).
Вернуть один продукт из базы данных по его идентификатору (если он активен).
Изменить один продукт в базе данных по его идентификатору.
Удалить продукт из базы данных по его идентификатору.
Удалить продукт из базы данных по его наименованию.
Восстановить удалённый продукт в базе данных по его идентификатору.
Вернуть общее количество продуктов в базе данных (активных).
Вернуть суммарную стоимость всех продуктов в базе данных (активных).
Вернуть среднюю стоимость продукта в базе данных (из активных).
 */
public class ProductService {
    private final ProductRepository repository = new ProductRepository();

    //Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).
    public Product save(Product product) {
        if (product == null) {
            throw new ProductSaveException("Продукт не может быть null !");

        }
        String title = product.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new ProductSaveException("Продукт не может быть пустым !");

        }
        if (product.getPrice() < 0) {
            throw new ProductSaveException("Цена продукта не может быть отрицательной!");
        }
        product.setActive(true);
        return repository.save(product);
    }

    //Вернуть все продукты из базы данных (активные).
    public List<Product> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .toList();
    }

    //Вернуть один продукт из базы данных по его идентификатору (если он активен).
    public Product getActiveProductById(Long id) {
        Product product = repository.findById(id);

        if (product == null || !product.isActive()) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    //Изменить один продукт в базе данных по его идентификатору.
    public void update(Long id, double newPrice) {
        if (newPrice < 0) {
            throw new ProductUpdateException("Цена продукта не может быть отрицательной!");
        }
        repository.update(id, newPrice);

    }
    //Удалить продукт из базы данных по его идентификатору.

    public void deleteById(Long id) {
        Product product = getActiveProductById(id);
        product.setActive(false);

    }
    // Удалить продукт из базы данных по его наименованию.

    public void deleteByTitle(String title) {
        getAllActiveProducts()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .forEach(x -> x.setActive(false));
    }

    //Вернуть один продукт из базы данных по его идентификатору (если он активен).
    public void restoreById(Long id) {
        Product product = repository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        product.setActive(true);
    }
    //Вернуть общее количество продуктов в базе данных (активных).

    public int getActiveProductCount() {
        return getAllActiveProducts().size();
    }
//Вернуть среднюю стоимость продукта в базе данных (из активных).
    public double getActiveProductsTotalCoast() {
        /*
        double sum = 0.0;
        for (Product product : getAllActiveProducts()) {
            sum += product.getPrice();
        }
        return sum;

         */
        return getAllActiveProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

    }
    //Вернуть среднюю стоимость продукта в базе данных (из активных).
    public double getActiveProductsAveragePrice(){

        return getAllActiveProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

 /*       int productCount=getActiveProductCount();
        if(productCount==0){
            return 0.0;
        }
        return getActiveProductsTotalCoast()/getActiveProductCount();
        */
    }



}
