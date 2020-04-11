package de.slevermann.cocktails.repositories;

import de.slevermann.cocktails.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i FROM Ingredient i JOIN IngredientName iname ON iname.ingredient.id = i.id AND iname.name = :name AND iname.locale = :locale")
    Ingredient findByNameAndLocale(@Param("name") String name, @Param("locale") String locale);
}
