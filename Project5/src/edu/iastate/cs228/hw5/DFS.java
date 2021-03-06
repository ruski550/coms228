package edu.iastate.cs228.hw5;
/*
 *  @author
 *
 */

import java.util.HashMap;
import java.util.Iterator;

public class DFS
{
    // This method creates a color map and a pred map (see lecture code file DiGraph.java)
    // and an empty stack of type LinkedStack<V>. It colors each vertex green
    // and sets the value of each vertex to null in the pred map (see lecture code).
    // Then as long as there is a green vertex w left, the method calls visitDFS()
    // on the vertex w along with other parameters.
    // If visitDFS() returns false, then this method returns null.
    // Otherwise, it returns the stack containing the list of all vertices in
    // a topological order, which is produced by the visitDFS() method.
    //
    // If aGraph is null, then it throws IllegalArgumentException.

    public static <V> LinkedStack<V> depthFirstSearch(DiGraph<V> aGraph)
    { // TODO
    	
    	//TODO color all vertex green
    	//for each vertex check if green, if so  create linkedstack, create hashmap color, and create hashmap pred? and call visitDFS on it.
    	
    	
    	   // It visits the vertices of a graph
        // in depth-first traversal, produces a depth-first forest, and
        // generates a list of vertices in a topological order.
       
    	//public static <V> void depthFirstSearch(DiGraph<V> aGraph)
        //{
    	
    		if (aGraph == null) {throw new IllegalArgumentException();}
    			
          HashMap<V, String> color = new HashMap<V, String>();
          HashMap<V, V> pred = new HashMap<V, V>();
          LinkedStack<V> topoOrder = new LinkedStack<V>();
//          System.out.println("Initialization");
//           System.out.println();
          for ( V w : aGraph.vertices() )
            {
              color.put(w, "green"); // unreached
              pred.put(w, null);
//              System.out.println("Vertex: " + w + "  Color: " + color.get(w) + "  Pred: null");
            }

//          System.out.println();
          for ( V w : aGraph.vertices() )
            if ( color.get(w).equals( "green" ) )
    	{
              if (visitDFS(aGraph, w, color, pred, topoOrder)) {
            	  return topoOrder;
              }
//              if (!visitDFS(aGraph, w, color, pred, topoOrder)) {return null;}
          //    if (visitDFS(aGraph, w, color, pred, topoOrder) == false) {return null;}
//              System.out.println();
    	}

//          System.out.println("\nDFS Forest");  // This display part is omitted in class.
//          for ( V w : aGraph.vertices() )
//           if ( pred.get(w) == null )
//             System.out.println( "The root of a DFS tree: " + w.toString() );
//           else 
//             System.out.println( "Tree edge: "
//    	       + pred.get(w).toString()
//    	       + "->" +  w.toString() );

//          System.out.println( "Topological Sorting:");
//          while ( ! topoOrder.isEmpty() ) {
//             System.out.print(" " +  topoOrder.pop().toString() );
//          System.out.println();
        //}
          
         
    
    	return null;
    }

    // This method implements an iterative depth-first search algorithm for
    // checking if the given graph is acyclic (has no cycles) and if so,
    // generates a stack (named topoOrder) of all vertices in a topological order
    // and returns true. Otherwise, it returns false. An iterative depth-first 
    // search algorithm is given in lecuture for an undirected graph. Here, you need
    // to modify the algorithm to make it work for a directed graph. Note that
    // the edge iterator citer should be changed from type Iterator<V> to
    // type Iterator<Edge<V, Integer>>, and that citer.next().getVertex(),
    // instead of citer.next(), gives the vertex w. If the vertex w is red,
    // then the graph has a cycle. So the method returns false.
    // Whenever a vertex is colored black, the vertex is pushed onto the stack
    // topoOrder. If the graph has no cycles (the execution reaches the end of the method),
    // then the method returns true.

    @SuppressWarnings("unchecked")
	protected static <V> boolean visitDFS(DiGraph<V> aGraph, V s,
            HashMap<V, String> color, HashMap<V, V> pred, LinkedStack<V> topoOrder)
    { // TODO
    	//TODO Color vertex black to indicate that it has been visited/added
    	//TODO check all edges that lead away from this vertex (ie probably call visitDFS on them or helper method)
    	
    	// color.put(s, "red"); // reached but not processed
    	 
    	   LinkedStack<V> nodestack = new LinkedStack<V>();
    	   LinkedStack<Iterator<V>> edgestack = new LinkedStack<Iterator<V>>();
    	   Iterator<V> siter = (Iterator<V>) aGraph.adjacentTo(s).iterator();
    	   nodestack.push(s);
    	   edgestack.push(siter);
    	   while ( ! nodestack.isEmpty() )
    	   { V c = nodestack.peek();
    	     Iterator<Edge<V,Integer>> citer =(Iterator<Edge<V, Integer>>) edgestack.peek();
    	     if ( citer.hasNext() )
    		{ V w = citer.next().getVertex();
    		  	if(color.get(w).equals("red")) {return false;}
    		  	if ( color.get(w).equals( "green" ) )  
    			  
    		  { color.put(w, "red"); // reached but not processed
    		    pred.put(w, c);
    	         Iterator<Edge<V,Integer>> witer = aGraph.adjacentTo(w).iterator();
    	         nodestack.push(w);
    	         edgestack.push((Iterator<V>) witer);
//    	         return false;
    		  }
    		}
    		else
    		{ color.put(c, "black"); // processed
    		topoOrder.push(c);  
    		nodestack.pop(); // vertex c is removed
    	       edgestack.pop(); // its edge iterator is removed
    		}
    	   }
    	   for (V z : aGraph.vertices()) {
    		   if (color.get(z).equals("black")) {
    			   continue;
    		   }
    		   else {
    			   return false;
    		   }
    	   }
    	   
    	return true;
    	
    }     
    
    
   
    
    
    
    
    //Week 12 stuff
 // It visits the vertices of a graph
    // in depth-first traversal, and produces a depth-first forest.
    /* An iterative depth-first search that is supposed to produce
    the same depth-first search tree as the recursive depth-first search. */
 //TODO Make this work on a directed list
//    private static <V> void visitDFS(DiGraph<V> aGraph, V s,
//         HashMap<V, String> color, HashMap<V, V> pred)
// {
//  // color.put(s, "red"); // reached but not processed
// 
//   LinkedStack<V> nodestack = new LinkedStack<V>();
//   LinkedStack<Iterator<Edge<V,Integer>>> edgestack = new LinkedStack<Iterator<Edge<V,Integer>>>();
//   Iterator<Edge<V,Integer>> siter = aGraph.adjacentTo(s).iterator();
//   nodestack.push(s);
//   edgestack.push(siter);
//   while ( ! nodestack.isEmpty() )
//   { V c = nodestack.peek();
//     Iterator<Edge<V,Integer>> citer = edgestack.peek();
//     if ( citer.hasNext() )
//	{ V w = citer.next().getVertex();
//	  if ( color.get(w).equals( "green" ) )
//	  { color.put(w, "red"); // reached but not processed
//	    pred.put(w, c);
//         Iterator<Edge<V,Integer>> witer = aGraph.adjacentTo(w).iterator();
//         nodestack.push(w);
//         edgestack.push(witer);
//	  }
//	}
//	else
//	{ color.put(c, "black"); // processed
//	topoOrder.push(c);  
//	nodestack.pop(); // vertex c is removed
//       edgestack.pop(); // its edge iterator is removed
//	}
//   }
// }

    
}
