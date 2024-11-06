package se.dzmitry.projekt2Game3.model;

public abstract class Entity {

    private String role;
    private int health;
    protected int damage;


    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;

    }


    public void takeHit(int damage) {
        this.health -= damage;
    }

    public void punch(Entity toPunch) {
        toPunch.takeHit(this.damage);
    }

    public boolean isAlive() {
        if (health > 0) {
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }


    public String getRole() {
        return role;
    }

}


