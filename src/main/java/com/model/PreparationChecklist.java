package com.model;

public class PreparationChecklist {
    private UUID owner;
    private Preparation selectedItems;
    private Preparation completedItems;

    public PreparationChecklist(User owner) {
        this.owner = owner.getUuid();
        this.selectedItems = new Preparation();
        this.completedItems = new Preparation();
    }

    public boolean addToChecklist(String item) {
        // add implementation
        return null;
    }

    public boolean removeFromChecklist(String item) {
        // add implementation
        return null;
    }

    public void markAsCompleted(Preparation item) {
        // add implementation
    }

    public void markAsIncomplete(Preparation item) {
        // add implementation
    }

    public ArrayList<Preparation> getIncompleteItems() {
        // add implementation
        return null;
    }

    public UUID getOwner() {
        return owner;
    }

    public Preparation getSelectedItems() {
        return selectedItems;
    }

    public Preparation getCompletedItems() {
        return completedItems;
    }

    public double calculateCompletionPercentage() {
        // add implementation
        return null;
    }
}