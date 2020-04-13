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
 * IngredientName
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-13T19:47:25.563+02:00[Europe/Berlin]")
public class IngredientName   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("names")
  @Valid
  private List<LocalizedString> names = new ArrayList<>();

  public IngredientName id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "20", value = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public IngredientName names(List<LocalizedString> names) {
    this.names = names;
    return this;
  }

  public IngredientName addNamesItem(LocalizedString namesItem) {
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
    public List<LocalizedString> getNames() {
    return names;
  }

  public void setNames(List<LocalizedString> names) {
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
    IngredientName ingredientName = (IngredientName) o;
    return Objects.equals(this.id, ingredientName.id) &&
        Objects.equals(this.names, ingredientName.names);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, names);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientName {\n");
    
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
