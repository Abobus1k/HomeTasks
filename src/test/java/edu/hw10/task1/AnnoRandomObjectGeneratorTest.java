package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnnoRandomObjectGeneratorTest {
    @Test
    public void nextObjectGenerate()
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException,
        NoSuchFieldException, NoSuchMethodException {
        Map<Class<?>, Supplier<?>> declaredObjects = supplierGenerator();
        TestClass expectedObject = new TestClass("string", 4, 2, -1, 1);
        AnnoRandomObjectGenerator randomObjectGenerator = new AnnoRandomObjectGenerator(
            declaredObjects, getComparableClasses());

        TestClass
            actualObject = randomObjectGenerator.nextObject(TestClass.class);

        assertEquals(expectedObject, actualObject);
    }

    private Map<Class<?>, Supplier<?>> supplierGenerator() {
        Map<Class<?>, Supplier<?>> declaredObjects = new HashMap<>();
        declaredObjects.put(String.class, () -> "string");
        declaredObjects.put(Integer.class, () -> 1);
        return declaredObjects;
    }

    private List<Class<?>> getComparableClasses() {
        List<Class<?>> declaredObjects = new ArrayList<>();
        declaredObjects.add(Integer.class);
        declaredObjects.add(int.class);
        return declaredObjects;
    }

    @ParameterizedTest
    @ValueSource(strings = {"minMaxGenerator", "Generator"})
    public void nextObjectThrow(String nameOfGeneratorMethod) {
        AnnoRandomObjectGenerator randomObjectGenerator = new AnnoRandomObjectGenerator(
            supplierGenerator(), getComparableClasses());

        assertThrows(
            IllegalArgumentException.class,
            () -> randomObjectGenerator.nextObject(TestClass.class, nameOfGeneratorMethod)
        );

    }

    static class TestClass {

        private final String string;

        public String getString() {
            return string;
        }

        public Integer getX1() {
            return x1;
        }

        public Integer getX2() {
            return x2;
        }

        public Integer getX3() {
            return x3;
        }

        public Integer getX4() {
            return x4;
        }

        private final Integer x1;
        private final Integer x2;
        private final Integer x3;
        private final Integer x4;

        public TestClass(
            @NotNull String string,
            @NotNull @Min(4) Integer x1,
            @Max(2) Integer x2,
            @Min(-1) @Max(7) Integer x3,
            @Min(1) @Max(2) Integer x4
        ) {
            this.string = string;
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestClass testClass = (TestClass) o;
            return Objects.equals(string, testClass.string) && Objects.equals(x1, testClass.x1) &&
                Objects.equals(x2, testClass.x2) && Objects.equals(x3, testClass.x3) &&
                Objects.equals(x4, testClass.x4);
        }

        @Override
        public int hashCode() {
            return Objects.hash(string, x1, x2, x3, x4);
        }

        @Override public String toString() {
            return "TestClass{" +
                "string='" + string + '\'' +
                ", integer=" + x1 +
                ", integer1=" + x2 +
                ", integer2=" + x3 +
                ", integer3=" + x4 +
                '}';
        }

        public static TestClass Generator(
            @NotNull @Min(2) String string,
            @NotNull @Min(4) Integer x1,
            @Max(2) Integer x2,
            @Min(-1) @Max(7) Integer x3,
            @Min(1) @Max(2) Integer x4
        ) {
            return new TestClass(string, x1, x2, x3, x4);

        }

        public static TestClass minMaxGenerator(
            @NotNull String string,
            @NotNull @Min(5) Integer x1,
            @Max(3) Integer x2,
            @Min(3) @Max(1) Integer x3,
            @Min(2) @Max(2) Integer x4
        ) {
            return new TestClass(string, x1, x2, x3, x4);

        }
    }
}
