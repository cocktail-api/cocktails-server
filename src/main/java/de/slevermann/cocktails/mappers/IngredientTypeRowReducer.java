package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.IngredientTypeName;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

public class IngredientTypeRowReducer implements LinkedHashMapRowReducer<Long, IngredientType> {
    @Override
    public void accumulate(Map<Long, IngredientType> container, RowView rowView) {
        IngredientType t = container.computeIfAbsent(rowView.getColumn("id", Long.class), id ->
                rowView.getRow(IngredientType.class));
        if (rowView.getColumn("ingredient_type_id", Long.class) != null) {
            t.getNames().add(rowView.getRow(IngredientTypeName.class));
        }
    }
}
