package de.slevermann.cocktails.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Locale;

@Data
@Entity
@Table(name = "ingredient_name")
public class IngredientName {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "locale")
    private Locale locale;

    @EqualsAndHashCode.Exclude
    @JoinColumn(nullable = false)
    @JsonManagedReference
    @ManyToOne
    @ToString.Exclude
    private Ingredient ingredient;
}
