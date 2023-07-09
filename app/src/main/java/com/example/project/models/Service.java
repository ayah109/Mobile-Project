package com.example.project.models;

    // Define the Service class to hold the service details
    public class Service {
        private String name;
        private double cost;

        public Service(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }
    }
