package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.slevermann.cocktails.models.IngredientData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateIngredient
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-16T17:30:10.709+02:00[Europe/Berlin]")
public class CreateIngredient   {
  @JsonProperty("typeId")
  private Long typeId = null;

  @JsonProperty("data")
  @Valid
  private List<IngredientData> data = new ArrayList<>();

  public CreateIngredient typeId(Long typeId) {
    this.typeId = typeId;
    return this;
  }

  /**
   * Get typeId
   * @return typeId
  **/
  @ApiModelProperty(example = "10", required = true, value = "")
      @NotNull

    public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public CreateIngredient data(List<IngredientData> data) {
    this.data = data;
    return this;
  }

  public CreateIngredient addDataItem(IngredientData dataItem) {
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull
    @Valid
  @Size(min=1)   public List<IngredientData> getData() {
    return data;
  }

  public void setData(List<IngredientData> data) {
    this.data = data;
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
        Objects.equals(this.data, createIngredient.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeId, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateIngredient {\n");
    
    sb.append("    typeId: ").append(toIndentedString(typeId)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
