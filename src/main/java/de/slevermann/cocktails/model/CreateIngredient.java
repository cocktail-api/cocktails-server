package de.slevermann.cocktails.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.model.TranslatedString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An ingredient for creation on the server
 */
@ApiModel(description = "An ingredient for creation on the server")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-27T22:58:48.812615+02:00[Europe/Berlin]")
public class CreateIngredient   {
  @JsonProperty("typeId")
  private UUID typeId = null;

  @JsonProperty("names")
  @Valid
  private List<TranslatedString> names = new ArrayList<>();

  @JsonProperty("descriptions")
  @Valid
  private List<TranslatedString> descriptions = new ArrayList<>();

  public CreateIngredient typeId(UUID typeId) {
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

  public CreateIngredient names(List<TranslatedString> names) {
    this.names = names;
    return this;
  }

  public CreateIngredient addNamesItem(TranslatedString namesItem) {
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

  public CreateIngredient descriptions(List<TranslatedString> descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  public CreateIngredient addDescriptionsItem(TranslatedString descriptionsItem) {
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
