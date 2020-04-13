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
 * LocalizedString
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-13T19:47:25.563+02:00[Europe/Berlin]")
public class LocalizedString   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("locale")
  private String locale = null;

  @JsonProperty("string")
  private String string = null;

  public LocalizedString id(Long id) {
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

  public LocalizedString locale(String locale) {
    this.locale = locale;
    return this;
  }

  /**
   * Get locale
   * @return locale
  **/
  @ApiModelProperty(example = "de-DE", required = true, value = "")
      @NotNull

    public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public LocalizedString string(String string) {
    this.string = string;
    return this;
  }

  /**
   * Get string
   * @return string
  **/
  @ApiModelProperty(example = "Wodka", required = true, value = "")
      @NotNull

    public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalizedString localizedString = (LocalizedString) o;
    return Objects.equals(this.id, localizedString.id) &&
        Objects.equals(this.locale, localizedString.locale) &&
        Objects.equals(this.string, localizedString.string);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, locale, string);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocalizedString {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    string: ").append(toIndentedString(string)).append("\n");
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
