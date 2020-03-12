package org.geneontology.obographs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.geneontology.obographs.model.meta.*;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A holder for metadata
 *
 * The information in a Meta object consists sets of {@link PropertyValue} objects,
 * which associate the Meta object holder with some value via some property.
 *
 * The set of PropertyValue objects can be partitioned into two subsets:
 *
 *  1. PropertyValues corresponding to a specific explicitly modeled property type (e.g synonym)
 *  2. generic {@link BasicPropertyValue}s - anything property not explicitly modeled
 *
 *
 *
 * @author cjm
 *
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Value.Immutable
public abstract class AbstractMeta {

    @JsonProperty
    @Nullable
    public abstract DefinitionPropertyValue getDefinition();

    @JsonProperty
    @Value.NaturalOrder
    public abstract List<String> getComments();

    @JsonProperty
    @Value.NaturalOrder
    public abstract List<String> getSubsets();

    @JsonProperty
    @Value.NaturalOrder
    public abstract List<SynonymPropertyValue> getSynonyms();

    @JsonProperty
    @Value.NaturalOrder
    public abstract List<XrefPropertyValue> getXrefs();

    @JsonIgnore
    @Value.Default
    public List<String> getXrefsValues() {
        return getXrefs().stream().map(XrefPropertyValue::getVal).collect(Collectors.toList());
    }

    @JsonProperty
    @Value.NaturalOrder
    public abstract List<BasicPropertyValue> getBasicPropertyValues();

    @JsonProperty
    @Value.Default
    public String getVersion(){
        return "";
    }

    @JsonProperty
    @Value.Default
    public boolean getDeprecated() {
        return false;
    }

}
