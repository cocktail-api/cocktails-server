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
 * IngredientTag
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-13T19:47:25.563+02:00[Europe/Berlin]")
public class IngredientTag   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("tagNames")
  @Valid
  private List<LocalizedString> tagNames = new ArrayList<>();

  public IngredientTag id(Long id) {
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

  public IngredientTag tagNames(List<LocalizedString> tagNames) {
    this.tagNames = tagNames;
    return this;
  }

  public IngredientTag addTagNamesItem(LocalizedString tagNamesItem) {
    this.tagNames.add(tagNamesItem);
    return this;
  }

  /**
   * Get tagNames
   * @return tagNames
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
    public List<LocalizedString> getTagNames() {
    return tagNames;
  }

  public void setTagNames(List<LocalizedString> tagNames) {
    this.tagNames = tagNames;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IngredientTag ingredientTag = (IngredientTag) o;
    return Objects.equals(this.id, ingredientTag.id) &&
        Objects.equals(this.tagNames, ingredientTag.tagNames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tagNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientTag {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tagNames: ").append(toIndentedString(tagNames)).append("\n");
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
