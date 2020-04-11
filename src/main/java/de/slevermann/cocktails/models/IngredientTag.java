package de.slevermann.cocktails.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ingredient_tag")
public class IngredientTag {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private List<Ingredient> ingredients;
}
