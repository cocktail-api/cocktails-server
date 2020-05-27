package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An ingredient in a single language
 */
@ApiModel(description = "An ingredient in a single language")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-26T13:26:24.736404+02:00[Europe/Berlin]")
public class LocalizedIngredient   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("typeId")
  private UUID typeId = null;

  @JsonProperty("typeLanguage")
  private String typeLanguage = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("language")
  private String language = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  public LocalizedIngredient id(UUID id) {
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

  public LocalizedIngredient typeId(UUID typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * Get typeId
   * @return typeId
  **/
  @ApiModelProperty(example = "d61c2b3a-09e3-4305-8f2b-24079ab52e8d", required = true, value = "")
      @NotNull

    @Valid
    public UUID getTypeId() {
    return typeId;
  }

  public void setTypeId(UUID typeId) {
    this.typeId = typeId;
  }

  public LocalizedIngredient typeLanguage(String typeLanguage) {
    this.typeLanguage = typeLanguage;
    return this;
  }

  /**
   * Get typeLanguage
   * @return typeLanguage
  **/
  @ApiModelProperty(example = "de", required = true, value = "")
      @NotNull

    public String getTypeLanguage() {
    return typeLanguage;
  }

  public void setTypeLanguage(String typeLanguage) {
    this.typeLanguage = typeLanguage;
  }

  public LocalizedIngredient type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "Alkohol", required = true, value = "")
      @NotNull

    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public LocalizedIngredient language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Get language
   * @return language
  **/
  @ApiModelProperty(example = "de", required = true, value = "")
      @NotNull

    public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public LocalizedIngredient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Weißer Rum", required = true, value = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalizedIngredient description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Ein hochprozentiges Destillat aus Zuckerrohr", required = true, value = "")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalizedIngredient localizedIngredient = (LocalizedIngredient) o;
    return Objects.equals(this.id, localizedIngredient.id) &&
        Objects.equals(this.typeId, localizedIngredient.typeId) &&
        Objects.equals(this.typeLanguage, localizedIngredient.typeLanguage) &&
        Objects.equals(this.type, localizedIngredient.type) &&
        Objects.equals(this.language, localizedIngredient.language) &&
        Objects.equals(this.name, localizedIngredient.name) &&
        Objects.equals(this.description, localizedIngredient.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeId, typeLanguage, type, language, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalizedIngredient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    typeLanguage: ").append(toIndentedString(typeLanguage)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
