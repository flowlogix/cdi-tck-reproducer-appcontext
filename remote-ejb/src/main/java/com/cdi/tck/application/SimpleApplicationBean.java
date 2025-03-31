package com.cdi.tck.application;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleApplicationBean {
    private double id = Math.random();

    public double getId() {
        return id;
    }
}
