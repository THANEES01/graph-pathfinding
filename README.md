# graph-pathfinding

  This is a Java Swing application that visualizes path finding in a graph using depth-first search (DFS). Here is an explanation of what the code does:

- It first defines a Graph class that represents a graph data structure. The graph is stored as 
  an adjacency list, which is a list of lists. Each inner list represents the adjacent vertices 
  for a particular vertex.
- The Graph class has methods like addEdge() to add edges, findPathDFS() to find a path between 
  two vertices using DFS, and getters for the vertices and adjacency list.
- The PathFindingApp class extends JFrame and is the main Swing application. It creates a sample 
  graph and adds Swing components like JTextFields, JButton, JLabel, and JTextArea to the frame.
- When the "Find Path" button is clicked, it calls findPathDFS() on the graph instance to find a 
  path between the source and destination cities entered by the user. It then displays this path.
- The getCityName() method maps vertex numbers to city names for display.
  printGraph() displays a representation of the graph in the text area when the app starts.

To run this, you need:
- JDK 8 or higher installed to compile and run Java code
- The Swing framework, which is included in the JDK
