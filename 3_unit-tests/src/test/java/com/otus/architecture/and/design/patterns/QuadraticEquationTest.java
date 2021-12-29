package com.otus.architecture.and.design.patterns;

import com.otus.architecture.and.design.patterns.exceptions.NotZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuadraticEquationTest {

    private QuadraticEquation quadraticEquation = new QuadraticEquation();

    @Test
    @DisplayName("x^2+1 = 0: корней нет (возвращается пустой массив)")
    void solve$1() {

        // Arrange
        String a = "1";
        String b = "0";
        String c = "1";

        // Act
        double[] result = quadraticEquation.solve(a, b, c);

        // Assert
        assertEquals(0, result.length, "Ожидалось что корней не будет");
    }

    @Test
    @DisplayName("x^2-1 = 0: есть два корня кратности 1 (x1=1, x2=-1)")
    void solve$2() {

        // Arrange
        String a = "1";
        String b = "0";
        String c = "-1";

        // Act
        double[] result = quadraticEquation.solve(a, b, c);

        // Assert
        assertEquals(2, result.length, "Ожидалось 2 корня");
        assertEquals(1, result[0],  "Ожидаемый результат x1 = 1");
        assertEquals(-1, result[1],  "Ожидаемый результат x2 = -1");
    }

    @Test
    @DisplayName("x^2+2x+1 = 0: eсть один корень кратности 2 (x1= x2 = -1)")
    void solve$3() {

        // Arrange
        String a = "1";
        String b = "2";
        String c = "1";

        // Act
        double[] result = quadraticEquation.solve(a, b, c);

        // Assert
        assertEquals(1, result.length, "Ожидался 1 корень");
        assertEquals(-1, result[0] , "Ожидаемый результат x1 = x2 = -1");
    }

    @Test
    @DisplayName("Коэффициент a не может быть равен 0")
    void solve$4() {

        // Arrange discriminant = Math.pow(0.01, 2) - 4 * 0.0002 * 1
        String a = "0";
        String b = "2";
        String c = "1";

        // Act
        // Assert
        assertThrows(NotZeroException.class, () -> quadraticEquation.solve(a, b, c), "Ожидаемый результат: NotZeroException");
    }

    @Test
    @DisplayName("Дискриминант был отличный от нуля, но меньше заданного эпсилон")
    void solve$5() {

        // Arrange Math.pow(2.191, 2) - 4 * 1 * 1.2
        String a = "1";
        String b = "2.191";
        String c = "1.2";

        // Act
        double[] result = quadraticEquation.solve(a, b, c);

        // Assert
        assertEquals(1, result.length, "Ожидался 1 корень");
        assertEquals(-1.0845341439002723, result[0] , "Ожидаемый результат x1 = x2 = -1.0845341439002723");
    }

    @Test
    @DisplayName("Посмотреть какие еще значения могут принимать числа типа double")
    void solve$6() {

        // Arrange Math.pow(2.191, 2) - 4 * 1 * 1.2
        String a = "йцу";
        String b = "!!!миртчртьч";
        String c = "ии34и5ю3и45";

        // Act
        // Assert
        assertThrows(NumberFormatException.class, ()-> quadraticEquation.solve(a, b, c), "Ожидалось исключение NumberFormatException");
    }


}