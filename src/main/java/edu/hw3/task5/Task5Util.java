package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;

public class Task5Util {

    private Task5Util() {
    }

    public static List<Contact> func(List<String> contactList, String order) {
        if (contactList == null || contactList.isEmpty()) {
            return new ArrayList<>();
        }

        boolean reverseOrder;

        reverseOrder = order.equals("DESC");

        List<Contact> contactLst = parseContacts(contactList);

        List<Contact> sortedContactList = new ArrayList<>(contactLst);

        sortedContactList.sort((o1, o2) -> {
            String firstContact = o1.surname();
            String secondContact = o2.surname();

            if (firstContact == null) {
                firstContact = o1.name();
            }

            if (secondContact == null) {
                secondContact = o2.name();
            }

            return reverseOrder ? -firstContact.compareTo(secondContact) : firstContact.compareTo(secondContact);
        });
        return sortedContactList;
    }

    public static List<Contact> parseContacts(List<String> contactList) {
        List<Contact> res = new ArrayList<>();
        for (String string : contactList) {
            String[] tmpFullName = string.split(" ");
            if (tmpFullName.length == 1) {
                res.add(new Contact(tmpFullName[0]));
            } else {
                res.add(new Contact(tmpFullName[0], tmpFullName[1]));
            }
        }
        return res;
    }
}
