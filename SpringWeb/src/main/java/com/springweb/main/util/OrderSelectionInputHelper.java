package com.springweb.main.util;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderSelectionInputHelper {

    private final String countSelectionInput;

    public OrderSelectionInputHelper(String countSelectionInput) {
        this.countSelectionInput = countSelectionInput;
    }

    public Map<Long, Integer> getComponentOrders(String componentName) {
        Map<Long, Integer> selectionOrders = new HashMap<>();

        String[] array = countSelectionInput.split(",");

        for (String item: array) {
            if (item.contains(componentName + "_")) {
                long id = Long.parseLong(item.split("_")[1]);
                int count = Integer.parseInt(item.split("_")[2]);
                selectionOrders.put(id, count);
            }
        }
        return selectionOrders;

    }
}
