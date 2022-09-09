package me.zysea.tntfill.tntholders;

import me.zysea.factions.faction.Faction;

public class TNTBankWrapper extends TNTHolder {

    private Faction faction;

    public TNTBankWrapper(Faction faction){
        this.faction = faction;
    }


    @Override
    public boolean remove(int amount) {
        if(!faction.getProperties().containsKey("tnt"))
            return false;

        int current = faction.getProperties().getInt("tnt");
        if(current-amount <= 0)
            return false;

        faction.getProperties().setInt("tnt", current-amount);
        return true;
    }

    @Override
    public boolean add(int amount) {
        if(!faction.getProperties().containsKey("tnt"))
            faction.getProperties().setInt("tnt", amount);
        else {
            int current = faction.getProperties().getInt("tnt");
            faction.getProperties().setInt("tnt", current + amount);
        }
        return true;
    }

    @Override
    public int count() {
        if(!faction.getProperties().containsKey("tnt"))
            return 0;

        int amount = faction.getProperties().getInt("tnt");
        setAmount(amount);

        return amount;
    }

    public Faction getFaction() {
        return faction;
    }
}
