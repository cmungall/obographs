package org.geneontology.obographs.core.model.axiom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ComparisonChain;
import org.immutables.value.Value;

import java.util.List;

/**
 * Corresponds to an axiom of the form C = X1 and ... and Xn,
 * Where X_i is either a named class or OWL Restriction
 * 
 * 
 * @author cjm
 *
 */
@JsonPropertyOrder({"definedClassId", "genusIds", "restrictions", "meta"})
@Value.Immutable
public abstract class AbstractLogicalDefinitionAxiom implements Axiom, Comparable<AbstractLogicalDefinitionAxiom> {

    /**
     * @return the representativeNodeId
     */
    @JsonProperty
    public abstract String getDefinedClassId();

    /**
     * @return the nodeIds
     */
    @JsonProperty
    public abstract List<String> getGenusIds();

    /**
     * @return the restrictions
     */
    @JsonProperty
    public abstract List<ExistentialRestrictionExpression> getRestrictions();

    @Override
    public int compareTo(AbstractLogicalDefinitionAxiom o) {
        return ComparisonChain.start()
                .compare(this.getDefinedClassId(), o.getDefinedClassId())
                .result();
    }
}
