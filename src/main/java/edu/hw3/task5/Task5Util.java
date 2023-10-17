package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;

public class Task5Util {

    private Task5Util() {}

    public static List<Contact> func(List<Contact> contactList, String order) {
        if (contactList == null || contactList.isEmpty()) {
            return new ArrayList<>();
        }

        boolean reverseOrder;

        reverseOrder = order.equals("DESC");

        List<Contact> sortedContactList = new ArrayList<>(contactList);

        sortedContactList.sort((o1, o2) -> {
            String firstContact = o1.getSurname();
            String secondContact = o2.getSurname();

            if (firstContact == null) {
                firstContact = o1.getName();
            }

            if (secondContact == null) {
                secondContact = o2.getName();
            }

            return reverseOrder ? -firstContact.compareTo(secondContact) : firstContact.compareTo(secondContact);
        });
        return sortedContactList;
    }
}
