package de.slevermann.cocktails.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.dto.IngredientType;
import de.slevermann.cocktails.dto.TranslatedString;
import de.slevermann.cocktails.dto.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An ingredient, with all languages
 */
@ApiModel(description = "An ingredient, with all languages")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-06T10:48:38.119443+02:00[Europe/Berlin]")
public class Ingredient   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("type")
  private IngredientType type = null;

  @JsonProperty("names")
  @Valid
  private List<TranslatedString> names = new ArrayList<>();

  @JsonProperty("descriptions")
  @Valid
  private List<TranslatedString> descriptions = new ArrayList<>();

  @JsonProperty("owner")
  private UserInfo owner = null;

  @JsonProperty("published")
  private Boolean published = null;

  public Ingredient id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "d61c2b3a-09e3-4305-8f2b-24079ab52e8d", required = true, value = "")
      @NotNull

    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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

  public Ingredient owner(UserInfo owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public UserInfo getOwner() {
    return owner;
  }

  public void setOwner(UserInfo owner) {
    this.owner = owner;
  }

  public Ingredient published(Boolean published) {
    this.published = published;
    return this;
  }

  /**
   * Get published
   * @return published
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Boolean isPublished() {
    return published;
  }

  public void setPublished(Boolean published) {
    this.published = published;
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
        Objects.equals(this.owner, ingredient.owner) &&
        Objects.equals(this.published, ingredient.published);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, names, descriptions, owner, published);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
    sb.append("    descriptions: ").append(toIndentedString(descriptions)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    published: ").append(toIndentedString(published)).append("\n");
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
