package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetIngredient
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-16T17:30:10.709+02:00[Europe/Berlin]")
public class GetIngredient   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("language")
  private String language = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("typeId")
  private Long typeId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  public GetIngredient id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * The database ID of the locale
   * @return id
  **/
  @ApiModelProperty(example = "10", required = true, value = "The database ID of the locale")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GetIngredient language(String language) {
    this.language = language;
    return this;
  }

  /**
   * A 2-letter language designator so that a language-country tuple like de-DE can be formed
   * @return language
  **/
  @ApiModelProperty(example = "de", required = true, value = "A 2-letter language designator so that a language-country tuple like de-DE can be formed")
      @NotNull

  @Size(min=2,max=2)   public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public GetIngredient country(String country) {
    this.country = country;
    return this;
  }

  /**
   * A 2-letter region designator so that a language-country tuple like de-DE can be formed
   * @return country
  **/
  @ApiModelProperty(example = "DE", required = true, value = "A 2-letter region designator so that a language-country tuple like de-DE can be formed")
      @NotNull

  @Size(min=2,max=2)   public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public GetIngredient type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "Hard Liquor", required = true, value = "")
      @NotNull

    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public GetIngredient typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * Get typeId
   * @return typeId
  **/
  @ApiModelProperty(example = "5", required = true, value = "")
      @NotNull

    public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public GetIngredient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "White rum", required = true, value = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GetIngredient description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "A high proof spirit made from sugar cane", required = true, value = "")
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
    GetIngredient getIngredient = (GetIngredient) o;
    return Objects.equals(this.id, getIngredient.id) &&
        Objects.equals(this.language, getIngredient.language) &&
        Objects.equals(this.country, getIngredient.country) &&
        Objects.equals(this.type, getIngredient.type) &&
        Objects.equals(this.typeId, getIngredient.typeId) &&
        Objects.equals(this.name, getIngredient.name) &&
        Objects.equals(this.description, getIngredient.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, language, country, type, typeId, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetIngredient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
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
