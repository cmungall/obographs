package org.geneontology.obographs.model.axiom;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.geneontology.obographs.io.OgJsonGenerator;
import org.geneontology.obographs.io.OgYamlGenerator;
import org.geneontology.obographs.model.Edge;
import org.geneontology.obographs.model.EdgeTest;
import org.geneontology.obographs.model.Graph;
import org.geneontology.obographs.model.GraphDocument;
import org.geneontology.obographs.model.GraphDocumentTest;
import org.geneontology.obographs.model.GraphTest;
import org.geneontology.obographs.model.Node;
import org.geneontology.obographs.model.NodeTest;
import org.geneontology.obographs.model.axiom.EquivalentNodesSet;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class EquivalentNodesSetTest {

    @Test
    public void test() throws JsonProcessingException {
        GraphDocument d = buildGD();
        Graph g = d.getGraphs().get(0);


        assertEquals(1, g.getNodes().size());
        assertEquals(1, g.getEdges().size());
        assertEquals(1, d.getGraphs().size());

        System.out.println(OgJsonGenerator.render(d));
        System.out.println(OgYamlGenerator.render(d));
    }
    
    public static GraphDocument buildGD() {
        Graph g = buildGraph();
        List<Graph> graphs =  (List<Graph>) Collections.singletonList(g);

        Map<Object,Object> context = new HashMap<>();
        context.put("GO", "http://purl.obolibrary.org/obo/GO_");

        GraphDocument d = new GraphDocument.Builder().
                context(context).
                graphs(graphs).build();
        return d;
    }



    public static Graph buildGraph() {
        Node n = NodeTest.build();
        Edge e = EdgeTest.build();
        
        List<Node> nodes = (List<Node>) Collections.singletonList(n);
        List<Edge> edges = (List<Edge>) Collections.singletonList(e);
        List<EquivalentNodesSet> enss = Collections.singletonList(buildENS());
        Graph g = new Graph.Builder().nodes(nodes ).edges(edges).equivalentNodesSet(enss).build();
        return g;
    }
    
    public static EquivalentNodesSet buildENS() {
        String[] ids = {"X:1", "X:2"};
        Set<String> nodeIds = new HashSet<>(
                Arrays.asList(ids));
        return new EquivalentNodesSet.Builder().nodeIds(nodeIds).representativeNodeId(ids[0]).build();
        
    }


}
