package com.otus.architecture.and.design.patterns;

import com.otus.architecture.and.design.patterns.exceptions.IncorrectValueException;
import com.otus.architecture.and.design.patterns.exceptions.NotZeroException;
import com.otus.architecture.and.design.patterns.exceptions.UnknownException;
import org.apache.commons.math3.util.Precision;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class QuadraticEquation {

    /**
     * Пусть дано квадратное уравнение ax^2 + bx + c = 0.
     * Тогда дискриминант — это просто число D = b2 − 4ac.
     */
    public double[] solve(double a, double b, double c) {

        Predicate<Double> isNumberZero = (dob) -> Precision.equals(dob, 0, 0.001d);
        BiPredicate<Double, Double> greaterThan = (first, second) -> (first - second) > 0.001d;
        Predicate<Double> isIncorrectValue = (doubleVal) -> Double.isInfinite(doubleVal) || Double.isNaN(doubleVal);


        if (isIncorrectValue.test(a)) {
            throw new IncorrectValueException("Передано не корретное значение для коэфицента a=" + a);
        } else if (isIncorrectValue.test(b)) {
            throw new IncorrectValueException("Передано не корретное значение для коэфицента b=" + b);
        } else if (isIncorrectValue.test(c)) {
            throw new IncorrectValueException("Передано не корретное значение для коэфицента c=" + c);
        }

        if (isNumberZero.test(a)) {
            throw new NotZeroException("Коэффицент а не может быть равен 0");
        }

        double discriminant = Math.pow(b, 2) - 4 * a * c;
        if (isNumberZero.test(discriminant)) {
            double[] result = new double[1];
            result[0] = (-b + Math.sqrt(discriminant)) / 2 * a;
            return result;
        } else if (greaterThan.test(0d, discriminant)) {
            return new double[0];
        } else if (greaterThan.test(discriminant, 0d)) {
            double[] result = new double[2];
            result[0] = (-b + Math.sqrt(discriminant)) / 2 * a;
            result[1] = (-b - Math.sqrt(discriminant)) / 2 * a;
            return result;
        }

        String errorMessage = String.format("Неожиданный результат для: a=%s; b=%s; c=%s;", a, b, c);
        throw new UnknownException(errorMessage);
    }
}
