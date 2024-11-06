package se.dzmitry.projekt2Game3.model;

public class Resident extends Entity {

    public Resident(String role, int health, int damage) {
        super(role, health, damage);
    }

    public void setDamage(int damage) {
        //resident.setDamage(resident.getDamage() + 3);
        this.damage = damage;
    }
}
