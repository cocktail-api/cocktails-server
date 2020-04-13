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
 * IngredientDescription
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-13T19:47:25.563+02:00[Europe/Berlin]")
public class IngredientDescription   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("descriptions")
  @Valid
  private List<LocalizedString> descriptions = new ArrayList<>();

  public IngredientDescription id(Long id) {
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

  public IngredientDescription descriptions(List<LocalizedString> descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  public IngredientDescription addDescriptionsItem(LocalizedString descriptionsItem) {
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
    public List<LocalizedString> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<LocalizedString> descriptions) {
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
    IngredientDescription ingredientDescription = (IngredientDescription) o;
    return Objects.equals(this.id, ingredientDescription.id) &&
        Objects.equals(this.descriptions, ingredientDescription.descriptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descriptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientDescription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
