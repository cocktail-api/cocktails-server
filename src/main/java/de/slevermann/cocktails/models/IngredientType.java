package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.models.LocalizedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IngredientType
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-13T19:47:25.563+02:00[Europe/Berlin]")
public class IngredientType   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("typeNames")
  @Valid
  private List<LocalizedString> typeNames = new ArrayList<>();

  public IngredientType id(Long id) {
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

  public IngredientType typeNames(List<LocalizedString> typeNames) {
    this.typeNames = typeNames;
    return this;
  }

  public IngredientType addTypeNamesItem(LocalizedString typeNamesItem) {
    this.typeNames.add(typeNamesItem);
    return this;
  }

  /**
   * Get typeNames
   * @return typeNames
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<LocalizedString> getTypeNames() {
    return typeNames;
  }

  public void setTypeNames(List<LocalizedString> typeNames) {
    this.typeNames = typeNames;
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
        Objects.equals(this.typeNames, ingredientType.typeNames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    typeNames: ").append(toIndentedString(typeNames)).append("\n");
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
