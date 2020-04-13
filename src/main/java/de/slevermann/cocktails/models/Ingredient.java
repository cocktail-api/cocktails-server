package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.models.IngredientDescription;
import de.slevermann.cocktails.models.IngredientName;
import de.slevermann.cocktails.models.IngredientTag;
import de.slevermann.cocktails.models.IngredientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ingredient
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-13T17:00:55.672+02:00[Europe/Berlin]")
public class Ingredient   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("type")
  private IngredientType type = null;

  @JsonProperty("names")
  @Valid
  private List<IngredientName> names = new ArrayList<>();

  @JsonProperty("descriptions")
  @Valid
  private List<IngredientDescription> descriptions = new ArrayList<>();

  @JsonProperty("tags")
  @Valid
  private List<IngredientTag> tags = null;

  public Ingredient id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "10", value = "")
  
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

  public Ingredient names(List<IngredientName> names) {
    this.names = names;
    return this;
  }

  public Ingredient addNamesItem(IngredientName namesItem) {
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
  @Size(min=1)   public List<IngredientName> getNames() {
    return names;
  }

  public void setNames(List<IngredientName> names) {
    this.names = names;
  }

  public Ingredient descriptions(List<IngredientDescription> descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  public Ingredient addDescriptionsItem(IngredientDescription descriptionsItem) {
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
  @Size(min=1)   public List<IngredientDescription> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<IngredientDescription> descriptions) {
    this.descriptions = descriptions;
  }

  public Ingredient tags(List<IngredientTag> tags) {
    this.tags = tags;
    return this;
  }

  public Ingredient addTagsItem(IngredientTag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<IngredientTag> getTags() {
    return tags;
  }

  public void setTags(List<IngredientTag> tags) {
    this.tags = tags;
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
        Objects.equals(this.descriptions, ingredient.descriptions) &&
        Objects.equals(this.tags, ingredient.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, names, descriptions, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
    sb.append("    descriptions: ").append(toIndentedString(descriptions)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
