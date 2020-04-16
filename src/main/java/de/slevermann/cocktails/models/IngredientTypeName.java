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
 * IngredientTypeName
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-16T17:30:10.709+02:00[Europe/Berlin]")
public class IngredientTypeName   {
  @JsonProperty("language")
  private String language = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("name")
  private String name = null;

  public IngredientTypeName language(String language) {
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

  public IngredientTypeName country(String country) {
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

  public IngredientTypeName name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Hard Liquor", required = true, value = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IngredientTypeName ingredientTypeName = (IngredientTypeName) o;
    return Objects.equals(this.language, ingredientTypeName.language) &&
        Objects.equals(this.country, ingredientTypeName.country) &&
        Objects.equals(this.name, ingredientTypeName.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(language, country, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientTypeName {\n");
    
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
