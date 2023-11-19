package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PasswordCracker {
    private static final int MAX_PASSWORD_LENGTH = 4;
    private final Map<String, String> passwordDatabase;
    private static final ConcurrentHashMap<String, String> cracked = new ConcurrentHashMap<>();
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public PasswordCracker(Map<String, String> passwordDatabase) {
        this.passwordDatabase = passwordDatabase;
    }

    public static void main(String[] args) {

        PasswordCracker passwordCracker = new PasswordCracker(fillPasswordDatabase());

        int numThreads = 6;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long startTime1 = System.currentTimeMillis();
        passwordCracker.multiThreadedPasswordCrack(executorService, numThreads);
        executorService.shutdown();
        long endTime1 = System.currentTimeMillis();

        System.out.println("Multi-threaded execution time: " + (endTime1 - startTime1) + " ms");

        System.out.println(cracked);
    }

    private static Map<String, String> fillPasswordDatabase() {
        Map<String, String> ans = new HashMap<>();
        ans.put("763fb1b0349eb7e8b1a95d27acd87583", "a.v.petrov");
        ans.put("0745064918b49693cca64d6b6a13d28a", "a.aa.aaa");
        ans.put("21c2e59531c8710156d34a3c30ac81d5", "b.bb.bbb");

        return ans;
    }

    public void multiThreadedPasswordCrack(ExecutorService executorService, int numThreads) {
        List<Future<Void>> futures = new ArrayList<>();
        int part = (int) Math.floor((double) CHARACTERS.length() / numThreads);
        for (int i = 0; i < numThreads; i++) {
            Callable<Void> task;
            if (i == numThreads - 1) {
                task = createPasswordCrackTask(part * i, CHARACTERS.length() - 1);
            }
            else {
                task = createPasswordCrackTask(part * i, part * (i + 1) - 1);
            }
            futures.add(executorService.submit(task));
        }

        for (Future<Void> future : futures) {
            try {
                future.get(); // Ждем завершения всех задач
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public void singleThreadedPasswordCrack() {
        PasswordGenerator generator = new PasswordGenerator(MAX_PASSWORD_LENGTH);
        String nextPassword;

        for (int i = 0; i < MAX_PASSWORD_LENGTH; i++) {
            while ((nextPassword = generator.nextPassword()) != null) {
                String hash = md5(nextPassword);
                if (passwordDatabase.containsKey(hash)) {
                    String username = passwordDatabase.get(hash);
                    cracked.put(username, nextPassword);
                }
            }
            generator = new PasswordGenerator(MAX_PASSWORD_LENGTH - i - 1);
        }
    }

    private Callable<Void> createPasswordCrackTask(int from, int to) {
        return () -> {
            ConcurrentPasswordGenerator generator = new ConcurrentPasswordGenerator(MAX_PASSWORD_LENGTH, from, to);
            String nextPassword;

            for (int i = 0; i < MAX_PASSWORD_LENGTH; i++) {
                while ((nextPassword = generator.nextPassword()) != null) {
                    String hash = md5(nextPassword);
                    if (passwordDatabase.containsKey(hash)) {
                        String username = passwordDatabase.get(hash);
                        cracked.put(username, nextPassword);
                    }
                }
                generator = new ConcurrentPasswordGenerator(MAX_PASSWORD_LENGTH - i - 1, from, to);
            }

            return null;
        };
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PasswordGenerator {
        private final int[] indices;

        public PasswordGenerator(int passwordLength) {
            indices = new int[passwordLength];
        }

        public String nextPassword() {
            if (indices[0] == CHARACTERS.length() - 1) {
                return null;
            }

            StringBuilder password = new StringBuilder();
            for (int index : indices) {
                password.append(CHARACTERS.charAt(index));
            }

            incrementIndices();
            return password.toString();
        }

        private void incrementIndices() {
            for (int i = indices.length - 1; i >= 0; i--) {
                indices[i]++;
                if (indices[i] == CHARACTERS.length()) {
                    indices[i] = 0;
                } else {
                    break;
                }
            }
        }
    }

    private static class ConcurrentPasswordGenerator {
        private final int[] indices;
        private final int from;
        private final int to;

        public ConcurrentPasswordGenerator(int passwordLength, int from, int to) {
            indices = new int[passwordLength];
            this.from = from;
            this.to = to;
        }

        public String nextPassword() {
            if (indices[0] == CHARACTERS.length() - 1) {
                return null;
            }

            StringBuilder password = new StringBuilder();
            for (int index : indices) {
                password.append(CHARACTERS.charAt(index));
            }

            incrementIndices();
            return password.toString();
        }

        private void incrementIndices() {
            for (int i = indices.length - 1; i >= 0; i--) {
                indices[i]++;

                if (indices.length == 1) {
                    if (indices[i] >= to) {
                        indices[i] = CHARACTERS.length() - 1;
                    } else {
                        break;
                    }
                }

                else if (i == indices.length - 1) {
                    if (indices[i] >= to) {
                        indices[i] = from;
                    } else {
                        break;
                    }
                }
                else {
                    if (indices[i] == CHARACTERS.length()) {
                        indices[i] = 0;
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
