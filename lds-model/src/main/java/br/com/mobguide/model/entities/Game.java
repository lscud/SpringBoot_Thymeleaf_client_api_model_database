package br.com.mobguide.model.entities;

import br.com.mobguide.model.enums.GameType;

public class Game extends BaseEntity{

    private int id;

    private String name;

    private double price;

    private GameType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }
}
