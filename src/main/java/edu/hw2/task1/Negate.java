package edu.hw2.task1;

public record Negate(Expr constant) implements Expr {

    @Override
    public double evaluate() {
        return -constant.evaluate();
    }
}
