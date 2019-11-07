package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

public final class Storage {

    private static List<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void updateCustomer(Customer customer) {
        int index = customers.indexOf(customer);
        customers.set(index, customer);
    }

    public static List<Customer> getAllCustomers() {
        return customers;
    }

    public static void clear() {
        customers = new ArrayList<>();
    }

    public static int size() {
        return customers.size();
    }

    public static  void delete(Customer customer){
        customers.remove(customer);
    }

    public static void fakeData() {
        clear();
        customers.add(new Customer("nguyen van A", "012345678", "abc@def.ghk", "abcdef", R.drawable.wolf_profile));
        customers.add(new Customer("nguyen van B", "012345678", "abc@def.ghk", "abcdef", R.drawable.wolf_profile));
        customers.add(new Customer("nguyen van C", "012345678", "abc@def.ghk", "abcdef", R.drawable.wolf_profile));
        customers.add(new Customer("nguyen van D", "012345678", "abc@def.ghk", "abcdef", R.drawable.wolf_profile));
        customers.add(new Customer("nguyen van E", "012345678", "abc@def.ghk", "abcdef", R.drawable.wolf_profile));
    }
}
