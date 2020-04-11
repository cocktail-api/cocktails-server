package de.slevermann.cocktails.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient_name")
public class IngredientName {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "locale")
    private String locale;

    @EqualsAndHashCode.Exclude
    @JoinColumn(nullable = false, name = "ingredient_id")
    @JsonManagedReference
    @ManyToOne
    @ToString.Exclude
    private Ingredient ingredient;
}
