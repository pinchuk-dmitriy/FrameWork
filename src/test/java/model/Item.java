package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.google.common.base.Preconditions.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Item {
    private String name;
    private String size;
    private String color;
    private String cost;

    public Item(String name, String size, String color, String cost){
        checkNotNull(name);
        checkNotNull(size);
        checkNotNull(color);
        checkNotNull(cost);

        this.name = name;
        this.size = size;
        this.color = color;
        this.cost = cost;
    }


    public static Item of(String name, String size, String color, String cost){
        return new Item(name, size, color, cost);
    }
}