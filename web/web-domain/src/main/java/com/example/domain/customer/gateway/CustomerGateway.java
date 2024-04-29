package com.example.domain.customer.gateway;

import com.example.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
