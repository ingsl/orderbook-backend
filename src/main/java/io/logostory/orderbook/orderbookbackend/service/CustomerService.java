package io.logostory.orderbook.orderbookbackend.service;

import com.querydsl.core.BooleanBuilder;
import io.logostory.orderbook.orderbookbackend.domain.Customer;
import io.logostory.orderbook.orderbookbackend.domain.QCustomer;
import io.logostory.orderbook.orderbookbackend.domain.dto.CustomerDTO;
import io.logostory.orderbook.orderbookbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO find(Long id) {
        Optional<Customer> dto = customerRepository.findById(id);
        return CustomerDTO.of(dto.orElseGet(Customer::new));
    }

    public Long save(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(Customer.of(customerDTO));
        return customer.getCustomerId();
    }

    public boolean update(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(Customer.of(customerDTO));
        if(customer.getCustomerId() == customerDTO.getCustomerId()){
            return true;
        }
        return false;
    }


    public void delete(Long id) {
        customerRepository.deleteById(id);
    }


    private BooleanBuilder writeBuilder(long customerId) {
        QCustomer qCustomer = QCustomer.customer;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCustomer.customerId.eq(customerId));

        return builder;
    }

}
