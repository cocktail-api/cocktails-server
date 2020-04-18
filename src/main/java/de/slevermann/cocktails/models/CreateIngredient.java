package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.models.TranslatedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An ingredient for creation on the server
 */
@ApiModel(description = "An ingredient for creation on the server")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-18T10:28:44.813+02:00[Europe/Berlin]")
public class CreateIngredient   {
  @JsonProperty("typeId")
  private Long typeId = null;

  @JsonProperty("names")
  private TranslatedString names = null;

  @JsonProperty("descriptions")
  private TranslatedString descriptions = null;

  public CreateIngredient typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * Get typeId
   * @return typeId
  **/
  @ApiModelProperty(example = "42", required = true, value = "")
      @NotNull

    public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public CreateIngredient names(TranslatedString names) {
    this.names = names;
    return this;
  }

  /**
   * Get names
   * @return names
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TranslatedString getNames() {
    return names;
  }

  public void setNames(TranslatedString names) {
    this.names = names;
  }

  public CreateIngredient descriptions(TranslatedString descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  /**
   * Get descriptions
   * @return descriptions
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TranslatedString getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(TranslatedString descriptions) {
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
    CreateIngredient createIngredient = (CreateIngredient) o;
    return Objects.equals(this.typeId, createIngredient.typeId) &&
        Objects.equals(this.names, createIngredient.names) &&
        Objects.equals(this.descriptions, createIngredient.descriptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeId, names, descriptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateIngredient {\n");
    
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
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
