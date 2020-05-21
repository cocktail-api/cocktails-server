package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An ingredient, with all languages
 */
@ApiModel(description = "An ingredient, with all languages")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T21:13:28.957416+02:00[Europe/Berlin]")
public class Ingredient   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("type")
  private IngredientType type = null;

  @JsonProperty("names")
  @Valid
  private List<TranslatedString> names = new ArrayList<>();

  @JsonProperty("descriptions")
  @Valid
  private List<TranslatedString> descriptions = new ArrayList<>();

  public Ingredient id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1337", required = true, value = "")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Ingredient type(IngredientType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public IngredientType getType() {
    return type;
  }

  public void setType(IngredientType type) {
    this.type = type;
  }

  public Ingredient names(List<TranslatedString> names) {
    this.names = names;
    return this;
  }

  public Ingredient addNamesItem(TranslatedString namesItem) {
    this.names.add(namesItem);
    return this;
  }

  /**
   * Get names
   * @return names
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<TranslatedString> getNames() {
    return names;
  }

  public void setNames(List<TranslatedString> names) {
    this.names = names;
  }

  public Ingredient descriptions(List<TranslatedString> descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  public Ingredient addDescriptionsItem(TranslatedString descriptionsItem) {
    this.descriptions.add(descriptionsItem);
    return this;
  }

  /**
   * Get descriptions
   * @return descriptions
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<TranslatedString> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<TranslatedString> descriptions) {
    this.descriptions = descriptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient ingredient = (Ingredient) o;
    return Objects.equals(this.id, ingredient.id) &&
        Objects.equals(this.type, ingredient.type) &&
        Objects.equals(this.names, ingredient.names) &&
        Objects.equals(this.descriptions, ingredient.descriptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, names, descriptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
    sb.append("    descriptions: ").append(toIndentedString(descriptions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
