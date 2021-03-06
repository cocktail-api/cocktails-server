/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package de.slevermann.cocktails.api;

import de.slevermann.cocktails.dto.SearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-07T18:28:08.273184+02:00[Europe/Berlin]")
@Api(value = "search", description = "the search API")
public interface SearchApi {

    Logger log = LoggerFactory.getLogger(SearchApi.class);

    Optional<ObjectMapper> getObjectMapper();

    Optional<HttpServletRequest> getRequest();

    

    @ApiOperation(value = "Search for ingredients and cocktails", nickname = "search", notes = "", response = SearchResult.class, responseContainer = "List", tags={ "ingredients","cocktails", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = SearchResult.class, responseContainer = "List") })
    @RequestMapping(value = "/search",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<SearchResult>> search(@NotNull @ApiParam(value = "The term to search for", required = true) @Valid @RequestParam(value = "q", required = true) String q);

}
