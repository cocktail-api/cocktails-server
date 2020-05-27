package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.dbmodels.DbCreateIngredient;
import de.slevermann.cocktails.dbmodels.DbIngredient;
import de.slevermann.cocktails.dbmodels.DbUserInfo;
import de.slevermann.cocktails.models.CreateIngredient;
import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.TranslatedString;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class IngredientMapper {

    private final UserInfoMapper userInfoMapper;

    private final IngredientTypeMapper ingredientTypeMapper;

    private final TranslatedStringMapper translatedStringMapper;

    public IngredientMapper(UserInfoMapper userInfoMapper, IngredientTypeMapper ingredientTypeMapper, TranslatedStringMapper translatedStringMapper) {
        this.userInfoMapper = userInfoMapper;
        this.ingredientTypeMapper = ingredientTypeMapper;
        this.translatedStringMapper = translatedStringMapper;
    }

    public Ingredient dbIngredientToIngredient(DbIngredient dbIngredient) {
        Ingredient ingredient = new Ingredient()
                .id(dbIngredient.getUuid())
                .type(ingredientTypeMapper.dbIngredientTypetoIngredientType(dbIngredient.getType()))
                .names(dbIngredient.getNames().entrySet().stream()
                        .map(e -> new TranslatedString()
                                .language(e.getKey())
                                .translation(e.getValue()))
                        .collect(Collectors.toList()))
                .descriptions(dbIngredient.getDescriptions().entrySet().stream()
                        .map(e -> new TranslatedString()
                                .language(e.getKey())
                                .translation(e.getValue()))
                        .collect(Collectors.toList()))
                ._public(dbIngredient.isPublic());
        DbUserInfo userInfo = dbIngredient.getUserInfo();
        if (userInfo != null) {
            ingredient.owner(userInfoMapper.dbUserInfoToUserInfo(userInfo));
        }
        return ingredient;
    }

    public DbCreateIngredient createIngredientToDbCreateIngredient(CreateIngredient ingredient) {
        return DbCreateIngredient.builder()
                .typeId(ingredient.getTypeId())
                .names(translatedStringMapper.translatedStringsToHashMap(ingredient.getNames()))
                .descriptions(translatedStringMapper.translatedStringsToHashMap(ingredient.getDescriptions()))
                .build();
    }
}
