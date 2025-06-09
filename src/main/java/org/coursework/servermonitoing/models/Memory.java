package org.coursework.servermonitoing.models;

public class Memory {
    private final float totalCapacityGB;
    private float usedMemory;

    public Memory(float totalMemory) {
        this.totalCapacityGB = totalMemory;
        if (totalMemory <= 0){
            throw new IllegalArgumentException("totalMemory must be greater than zero");
        }
        this.usedMemory = 0;
    }

    public float getTotalCapacityGB() {
        return totalCapacityGB;
    }

    public float getUsedMemoryGB() {
        return usedMemory;
    }

    public void setUsedMemory(float usedMemory) {
        if (usedMemory < 0){
            throw new IllegalArgumentException("usedMemory must be greater than zero");
        }
        if (usedMemory > this.totalCapacityGB) {
            throw new IllegalArgumentException("Used memory cannot exceed total capacity.");
        }
        this.usedMemory = usedMemory;
    }
}
