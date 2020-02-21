package org.geneontology.obographs.model.axiom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geneontology.obographs.model.Edge;
import org.geneontology.obographs.model.Meta;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This combined ObjectPropertyDomain, ObjectPropertyRange, and some AllValuesFrom expressions into a single convenience structure
 * 
 * 
 * @author cjm
 *
 */
@JsonDeserialize(builder = DomainRangeAxiom.Builder.class)
public class DomainRangeAxiom extends AbstractAxiom {

    private DomainRangeAxiom(Builder builder) {
        super(builder);
        predicateId = builder.predicateId;
        domainClassIds = builder.domainClassIds;
        rangeClassIds = builder.rangeClassIds;
        allValuesFromEdges = builder.allValuesFromEdges;
    }

    private final String predicateId;
    private final Set<String> domainClassIds;
    private final Set<String> rangeClassIds;
    private final Set<Edge> allValuesFromEdges;


    


    /**
     * @return the predicateId
     */
    public String getPredicateId() {
        return predicateId;
    }




    /**
     * For multiple domains, this is treated as intersection
     * 
     * @return the domainClassIds
     */
    public Set<String> getDomainClassIds() {
        return domainClassIds;
    }




    /**
    * For multiple ranges, this is treated as intersection
     * 
     * @return the rangeClassIds
     */
    public Set<String> getRangeClassIds() {
        return rangeClassIds;
    }


    /**
     * Set of edges representing `X SubClassOf P only Y` axioms.
     * 
     * Note that these are not in the main graph.edges object, as the edge
     * graph is intended to be an existential graph. Most applications that do
     * not perform a reasoning function have no use for universal axioms.
     * 
     * @return the allValuesFromEdges
     */
    public Set<Edge> getAllValuesFromEdges() {
        return allValuesFromEdges;
    }

    public static class Builder extends AbstractAxiom.Builder {

        @JsonProperty
        private String predicateId;
        @JsonProperty
        private Set<String> domainClassIds;
        @JsonProperty
        private Set<String> rangeClassIds;
        @JsonProperty
        private Meta meta;
        @JsonProperty
        private Set<Edge> allValuesFromEdges;

        public Builder predicateId(String predicateId) {
            this.predicateId = predicateId;
            return this;
        }
        public String predicateId() {
            return predicateId;
        }

        public Builder domainClassId(Set<String> domainClassId) {
            this.domainClassIds = domainClassIds;
            return this;
        }
        public Builder domainClassId(String domainClassId) {
            this.domainClassIds = Collections.singleton(domainClassId);
            return this;
        }
        public Builder addDomainClassId(String domainClassId) {
            if (domainClassIds == null)
                domainClassIds = new HashSet<>();
            this.domainClassIds.add(domainClassId);
            return this;
        }
 
        public Builder rangeClassIds(Set<String> rangeClassIds) {
            this.rangeClassIds = rangeClassIds;
            return this;
        }
        public Builder rangeClassId(String rangeClassId) {
            this.rangeClassIds = Collections.singleton(rangeClassId);
            return this;
        }
        public Builder addRangeClassId(String rangeClassId) {
            if (rangeClassIds == null)
                rangeClassIds = new HashSet<>();
            this.rangeClassIds.add(rangeClassId);
            return this;
        }
 
        public Builder addAllValuesFrom(Edge edge) {
            if (allValuesFromEdges == null)
                allValuesFromEdges = new HashSet<>();
            this.allValuesFromEdges.add(edge);
            return this;
        }

        @JsonCreator
        public DomainRangeAxiom build() {
            return new DomainRangeAxiom(this);
        }

    }


}
