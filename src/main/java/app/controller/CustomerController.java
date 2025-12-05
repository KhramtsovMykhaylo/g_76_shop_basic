package app.controller;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;

public class CustomerController
{

    private final CustomerService service = new CustomerService();

//    Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).
    public Customer save(String name) {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

//    Вернуть всех покупателей из базы данных (активных).
    public List<Customer> getAll() {
        return service.getAllActiveCustomer();
    }

//    Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
    public Customer getById(String id) {
        Long numericId = Long.parseLong(id);
        return service.getActiveCustomerById(numericId);
    }

//    Изменить одного покупателя в базе данных по его идентификатору.
    public void update(String id, String newName) {
        Long numericId = Long.parseLong(id);
        service.update(numericId, newName);
    }

//    Удалить покупателя из базы данных по его идентификатору.
    public void deleteById(String id) {
        Long numericId = Long.parseLong(id);
        service.deleteById(numericId);
    }

//    Удалить покупателя из базы данных по его имени.
    public void deleteByName(String name) {
        service.deleteByName(name);
    }

//    Восстановить удалённого покупателя в базе данных по его идентификатору.
    public void restoreById(String id) {
        Long numericId = Long.parseLong(id);
        service.restoreById(numericId);
    }
    
//    Вернуть общее количество покупателей в базе данных (активных).
    public int getCustomersNumber() {
        return service.getActiveCustomerNumber();
    }

//    Вернуть стоимость корзины покупателя по его идентификатору (если он активен).
    public double getCustomersCartTotalCost(String id) {
        Long numericId = Long.parseLong(id);
        return service.getCustomerCartTotalCoast(numericId);
    }

//    Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он активен)
    public double getCustomersCartAveragePrice(String id) {
        Long numericId = Long.parseLong(id);
        return service.getCustomerCartAveragePrice(numericId);
    }

//    Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
//    Удалить товар из корзины покупателя по их идентификаторам
//    Полностью очистить корзину покупателя по его идентификатору (если он активен)
}
