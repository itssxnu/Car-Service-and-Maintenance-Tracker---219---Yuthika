package com.app.carmaintenance.car_maintenance.model;

import java.util.*;

public class ServiceHistory {
    private Node head;

    private class Node {
        ServiceRecord data;
        Node next;
        Node(ServiceRecord data) {
            this.data = data;
        }
    }

    public void add(ServiceRecord record) {
        Node newNode = new Node(record);
        newNode.next = head;
        head = newNode;
    }

    public List<ServiceRecord> getAll() {
        List<ServiceRecord> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public void selectionSortByDate() {
        for (Node i = head; i != null; i = i.next) {
            Node min = i;
            for (Node j = i.next; j != null; j = j.next) {
                if (j.data.getDate().compareTo(min.data.getDate()) < 0) {
                    min = j;
                }
            }
            // Swap
            if (min != i) {
                ServiceRecord temp = i.data;
                i.data = min.data;
                min.data = temp;
            }
        }
    }
}

