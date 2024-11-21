package hr.algebra.juristiq.models;

import java.util.List;

public class CostCalculation {

    private List<CostItem> costItems;
    private double totalCost;

    public CostCalculation(List<CostItem> costItems, double totalCost) {
        this.costItems = costItems;
        this.totalCost = totalCost;
    }

    // Getteri i setteri
}

