package de.slevermann.cocktails.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<IngredientName> names;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private IngredientType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ingredient_ingredient_tag",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<IngredientTag> tags;
}
