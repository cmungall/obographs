package org.geneontology.obographs.model.axiom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geneontology.obographs.model.Edge;
import org.immutables.value.Value;

import java.util.SortedSet;

/**
 * This combined ObjectPropertyDomain, ObjectPropertyRange, and some AllValuesFrom expressions into a single convenience structure
 *
 * @author cjm
 */
@JsonSerialize(as = DomainRangeAxiom.class)
@JsonDeserialize(as = DomainRangeAxiom.class)
@Value.Immutable
public abstract class AbstractDomainRangeAxiom implements Axiom {

    /**
     * @return the predicateId
     */
    @JsonProperty
    public abstract String getPredicateId();

    /**
     * For multiple domains, this is treated as intersection
     *
     * @return the domainClassIds
     */
    @JsonProperty
    @Value.NaturalOrder
    public abstract SortedSet<String> getDomainClassIds();

    /**
     * For multiple ranges, this is treated as intersection
     *
     * @return the rangeClassIds
     */
    @JsonProperty
    @Value.NaturalOrder
    public abstract SortedSet<String> getRangeClassIds();

    /**
     * Set of edges representing `X SubClassOf P only Y` axioms.
     * <p>
     * Note that these are not in the main graph.edges object, as the edge
     * graph is intended to be an existential graph. Most applications that do
     * not perform a reasoning function have no use for universal axioms.
     *
     * @return the allValuesFromEdges
     */
    @JsonProperty
    @Value.NaturalOrder
    public abstract SortedSet<Edge> getAllValuesFromEdges();
}
