package com.bookmyshowspring.demo.repository.mongo;

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
public class CustomerMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Customer saveCustomer(Customer customer) {
        return mongoTemplate.save(customer);
    }


    public Optional<Customer> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, Customer.class));
    }


    public List<Customer> findAll() {
        return mongoTemplate.findAll(Customer.class);
    }


    public void updateCustomer(String id, Customer customer) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update()
                .set("name", customer.getName())
                .set("email", customer.getEmail())
                .set("phoneNumber", customer.getPhoneNumber());
        mongoTemplate.updateFirst(query, update, Customer.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Customer.class);
    }
}
