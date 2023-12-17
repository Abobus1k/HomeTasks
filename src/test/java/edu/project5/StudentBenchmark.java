package edu.project5;

import org.openjdk.jmh.annotations.*;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StudentBenchmark {
    public record Student(String name, String surname) { }

    private final Student student = new Student("John", "Doe");
    private final Method method;
    private final MethodHandle methodHandle;
    private final StudentNameGetter getter;

    public StudentBenchmark() {
        Method tmpMethod;
        MethodHandle tmpMethodHandle;
        StudentNameGetter tmpGetter;

        try {
            tmpMethod = Student.class.getMethod("name");
        } catch (Throwable e) {
            tmpMethod = null;
        }

        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            tmpMethodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));
        } catch (Throwable e) {
            tmpMethodHandle = null;
        }


        try {
            var caller = MethodHandles.lookup();
            var factoryType = MethodType.methodType(StudentNameGetter.class);
            var interfaceMethodType = MethodType.methodType(String.class, Student.class);
            var implementation = caller.findVirtual(Student.class, "name", MethodType.methodType(String.class));
            var dynamic = MethodType.methodType(String.class, Student.class);

            tmpGetter = (StudentNameGetter) LambdaMetafactory.metafactory(
                caller,
                "get",
                factoryType,
                interfaceMethodType,
                implementation,
                dynamic
            ).getTarget().invokeExact();
        } catch (Throwable e) {
            tmpGetter = null;
        }

        method = tmpMethod;
        methodHandle = tmpMethodHandle;
        getter = tmpGetter;
    }

    @Benchmark
    public String directAccess() {
        return student.name();
    }

    @Benchmark
    public String reflectMethod() throws Exception {
        return (String) method.invoke(student);
    }

    @Benchmark
    public String methodHandles() throws Throwable {
        return (String) methodHandle.invoke(student);
    }


    @Benchmark
    public String lambdaMetafactory() {
        return getter.get(student);
    }

    public interface StudentNameGetter {
        String get(Student student);
    }
}
