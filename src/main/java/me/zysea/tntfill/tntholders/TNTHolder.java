package me.zysea.tntfill.tntholders;

public abstract class TNTHolder {

    private int amount = 0;

    public abstract boolean remove(int amount);
    public abstract boolean add(int amount);
    public abstract int count();

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int incrementAmount(){
        return amount++;
    }

    public int decrementAmount(){
        return amount--;
    }
}
