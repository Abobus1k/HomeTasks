package edu.hw10.task1;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomObjectGeneratorTest {

    @Test
    public void nextObject()
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException,
        NoSuchFieldException, NoSuchMethodException {
        Map<Class<?>, Supplier<?>> declaredObjects = supplierGenerator();
        TestClass expectedObject = new TestClass("string", 1, (short) 1, 1d, 1L, true, 1, (short) 1, 1, 1);
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(declaredObjects);

        TestClass actualObject = randomObjectGenerator.nextObject(TestClass.class);

        assertEquals(expectedObject, actualObject);
    }

    @Test
    public void nextObjectWithMethod()
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException,
        NoSuchFieldException, NoSuchMethodException {
        Map<Class<?>, Supplier<?>> declaredObjects = supplierGenerator();
        TestClass expectedObject = new TestClass("string", 1, (short) 1, 1d, 1L, true, 1, (short) 1, 1, 1);
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(declaredObjects);

        TestClass actualObject = randomObjectGenerator.nextObject(TestClass.class, "generate");

        assertEquals(expectedObject, actualObject);
    }

    private Map<Class<?>, Supplier<?>> supplierGenerator() {
        Map<Class<?>, Supplier<?>> declaredObjects = new HashMap<>();
        declaredObjects.put(String.class, () -> "string");
        declaredObjects.put(Integer.class, () -> 1);
        declaredObjects.put(Short.class, () -> (short) 1);
        declaredObjects.put(Double.class, () -> 1d);
        declaredObjects.put(Long.class, () -> 1L);
        declaredObjects.put(Float.class, () -> 1f);
        declaredObjects.put(Boolean.class, () -> true);
        declaredObjects.put(int.class, () -> 1);
        declaredObjects.put(float.class, () -> 1f);
        declaredObjects.put(double.class, () -> 1d);
        declaredObjects.put(long.class, () -> 1L);
        declaredObjects.put(short.class, () -> (short) 1);
        return declaredObjects;
    }

    static class TestClass {

        private final String string;
        private final Integer integer;
        private final Short aShort;
        private final Double aDouble;
        private final Long aLong;
        private final Boolean aBoolean;
        private final int anInt;
        private final short anShort;
        double anDouble;

        public String getString() {
            return string;
        }

        public Integer getInteger() {
            return integer;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestClass testClass = (TestClass) o;
            return anInt == testClass.anInt && anShort == testClass.anShort &&
                Double.compare(anDouble, testClass.anDouble) == 0 && anLong == testClass.anLong &&
                Objects.equals(string, testClass.string) && Objects.equals(integer, testClass.integer) &&
                Objects.equals(aShort, testClass.aShort) && Objects.equals(aDouble, testClass.aDouble) &&
                Objects.equals(aLong, testClass.aLong) && Objects.equals(aBoolean, testClass.aBoolean);
        }

        @Override
        public int hashCode() {
            return Objects.hash(string, integer, aShort, aDouble, aLong, aBoolean, anInt, anShort, anDouble, anLong);
        }

        @Override public String toString() {
            return "TestClass{" +
                "string='" + string + '\'' +
                ", integer=" + integer +
                ", aShort=" + aShort +
                ", aDouble=" + aDouble +
                ", aLong=" + aLong +
                ", aBoolean=" + aBoolean +
                ", anInt=" + anInt +
                ", anShort=" + anShort +
                ", anDouble=" + anDouble +
                ", anLong=" + anLong +
                '}';
        }

        public Short getaShort() {
            return aShort;
        }

        public Double getaDouble() {
            return aDouble;
        }

        public Long getaLong() {
            return aLong;
        }

        public Boolean getaBoolean() {
            return aBoolean;
        }

        public int getAnInt() {
            return anInt;
        }

        public short getAnShort() {
            return anShort;
        }

        public double getAnDouble() {
            return anDouble;
        }

        public void setAnDouble(double anDouble) {
            this.anDouble = anDouble;
        }

        public long getAnLong() {
            return anLong;
        }

        public void setAnLong(long anLong) {
            this.anLong = anLong;
        }

        private long anLong;

        private TestClass(
            String string, Integer integer, Short aShort, Double aDouble, Long aLong, Boolean aBoolean, int anInt,
            short anShort, double anDouble, long anLong
        ) {
            this.string = string;
            this.integer = integer;
            this.aShort = aShort;
            this.aDouble = aDouble;
            this.aLong = aLong;
            this.aBoolean = aBoolean;
            this.anInt = anInt;
            this.anShort = anShort;
            this.anDouble = anDouble;
            this.anLong = anLong;
        }

        public static TestClass generate(
            String string, Integer integer, Short aShort, Double aDouble, Long aLong, Boolean aBoolean, int anInt,
            short anShort, double anDouble, long anLong
        ) {
            return new TestClass(string, integer, aShort, aDouble, aLong, aBoolean, anInt, anShort, anDouble, anLong);
        }
    }
}
