package edu.hw2.task4;

public class CallingInfoUtil {

    private CallingInfoUtil() {

    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            String className = element.getClassName();

            if (!className.equals(CallingInfoUtil.class.getName())) {
                String methodName = element.getMethodName();
                return new CallingInfo(className, methodName);
            }
        }

        return null;
    }
}
