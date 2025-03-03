package com.bookmyshowspring.demo.repository;

import com.bookmyshowspring.demo.models.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Customer saveCustomer(Customer customer) {
        return mongoTemplate.insert(customer);
    }


    public Optional<Customer> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Customer.class));
    }


    public List<Customer> findAll() {
        return mongoTemplate.findAll(Customer.class);
    }


    public void updateCustomer(String id, Customer customer) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("name", customer.getName())
                .set("email", customer.getEmail())
                .set("phoneNumber", customer.getPhoneNumber());
        mongoTemplate.updateFirst(query, update, Customer.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Customer.class);
    }
}
