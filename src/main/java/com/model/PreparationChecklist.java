package com.model;

import java.util.UUID;

/**
 * PreparationChecklist creates a checklist for users to prepare with
 * @author Fantastic Four
 */
public class PreparationChecklist{
    private UUID owner;
    private Preparation selectedItems;
    private Preparation completedItems;

    /**
     * PreparationChecklist constructor creates the preparation checklist
     * @param owner user owner
     */
    public PreparationChecklist(UUID owner) {
        this.owner = owner.getUuid();
        this.selectedItems = new Preparation();
        this.completedItems = new Preparation();
    }

    /**
     * addToChecklist adds item to checklist
     * @param item task to complete
     * @return boolean if item was properly added to checklist
     */
    public boolean addToChecklist(String item) {
        // add implementation
        return null;
    }

    /**
     * removeFromChecklist removes items from checklist
     * @param item item to remove
     * @return boolean if item was properly removed from checklist
     */
    public boolean removeFromChecklist(String item) {
        // add implementation
        return null;
    }

    /**
     * markAsCompleted marks checklist item as completed
     * @param item item to mark completed
     */
    public void markAsCompleted(Preparation item) {
        // add implementation
    }

    /**
     * markAsIncomplete marks checklist item as incomplete
     * @param item item to mark incomplete
     */
    public void markAsIncomplete(Preparation item) {
        // add implementation
    }

    /**
     * getIncompleteItems returns the incompleted checklist items
     * @return arraylist of the items that have not been completed on the checklist
     */
    public ArrayList<Preparation> getIncompleteItems() {
        // add implementation
        return null;
    }

    /**
     * getOwner returns the owner
     * @return uuid of owner
     */
    public UUID getOwner() {
        return owner;
    }

    /**
     * getSelectedItems returns the selected items
     * @return Preparation items
     */
    public Preparation getSelectedItems() {
        return selectedItems;
    }

    /**
     * getCompletedItems returns the completed items
     * @return Preparation items
     */
    public Preparation getCompletedItems() {
        return completedItems;
    }

    /**
     * calculateCompletionPercentage calculates how many of the tasks have been completed
     * @return completion percentage
     */
    public double calculateCompletionPercentage() {
        // add implementation
        return null;
    }
}