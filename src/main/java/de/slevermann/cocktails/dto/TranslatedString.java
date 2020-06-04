package de.slevermann.cocktails.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A translated string
 */
@ApiModel(description = "A translated string")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-04T17:53:46.915060+02:00[Europe/Berlin]")
public class TranslatedString   {
  @JsonProperty("language")
  private String language = null;

  @JsonProperty("translation")
  private String translation = null;

  public TranslatedString language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Get language
   * @return language
  **/
  @ApiModelProperty(example = "de", required = true, value = "")
      @NotNull

    public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public TranslatedString translation(String translation) {
    this.translation = translation;
    return this;
  }

  /**
   * Get translation
   * @return translation
  **/
  @ApiModelProperty(example = "Limettensaft", required = true, value = "")
      @NotNull

    public String getTranslation() {
    return translation;
  }

  public void setTranslation(String translation) {
    this.translation = translation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TranslatedString translatedString = (TranslatedString) o;
    return Objects.equals(this.language, translatedString.language) &&
        Objects.equals(this.translation, translatedString.translation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(language, translation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TranslatedString {\n");
    
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    translation: ").append(toIndentedString(translation)).append("\n");
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
