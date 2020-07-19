package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	private Map <Integer, Airport> idMap;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		idMap = new HashMap<Integer, Airport>();
		dao = new ExtFlightDelaysDAO();
		this.dao.loadAllAirports(idMap);
	}
	
	public void creaGrafo(int x) {
		// abbiamo creato i vertici
		this.grafo= new SimpleWeightedGraph(DefaultEdge.class);
	// creiamo gli archi 
		for(Rotta r: dao.getRotta(idMap)) {
			if(r.getPesoDistanza()>x) {
				Graphs.addEdge(this.grafo, idMap.get(r.a1), idMap.get(r.a2),r.getPesoDistanza());
			}
		}
		System.out.println(String.format("Grafo creato! # Vertici %d, #archi %d", this.grafo.vertexSet().size(), this.grafo.edgeSet().size()));
		}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}

}
