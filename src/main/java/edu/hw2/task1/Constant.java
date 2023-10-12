package edu.hw2.task1;

public record Constant(double constant) implements Expr {
    @Override
    public double evaluate() {
        return constant;
    }
}
