package org.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * ComparableWeightedEdge
 */
public class ComparableWeightedEdge extends DefaultWeightedEdge implements Comparable<ComparableWeightedEdge> {
    private static final long serialVersionUID = 8487584995698618886L;

    ComparableWeightedEdge() {
        super();
    }

    @Override
    public int compareTo(ComparableWeightedEdge other) {
        return (Double.valueOf(this.getWeight()).compareTo(Double.valueOf(other.getWeight())));
    }
}