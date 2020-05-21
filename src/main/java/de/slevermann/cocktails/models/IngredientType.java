package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.models.TranslatedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An ingredient type, with all languages
 */
@ApiModel(description = "An ingredient type, with all languages")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T21:13:28.957416+02:00[Europe/Berlin]")
public class IngredientType   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("names")
  @Valid
  private List<TranslatedString> names = new ArrayList<>();

  public IngredientType id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "15", required = true, value = "")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public IngredientType names(List<TranslatedString> names) {
    this.names = names;
    return this;
  }

  public IngredientType addNamesItem(TranslatedString namesItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IngredientType ingredientType = (IngredientType) o;
    return Objects.equals(this.id, ingredientType.id) &&
        Objects.equals(this.names, ingredientType.names);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, names);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
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
