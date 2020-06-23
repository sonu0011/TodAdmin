package com.adios.ediostoiadmin.data.modal;

import java.util.ArrayList;

public class CustomerOrders  {
    private ArrayList<CustomerOrder> customerOrders;

    public CustomerOrders(ArrayList<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public ArrayList<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }
    public void showItems(){
        for (int i = 0; i < customerOrders.size(); i++) {
            System.out.println(customerOrders.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "CustomerOrders{" +
                "customerOrders=" + customerOrders +
                '}';
    }
}
