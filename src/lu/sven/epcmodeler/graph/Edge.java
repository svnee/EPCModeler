package lu.sven.epcmodeler.graph;

public class Edge {
	
	private String id;
	private String timestamp;
	
	public Edge(String id, String ts){
		this.id=id;
		this.setTimestamp(ts);
	}

	public String toString() {
		return "";
	}
	
	public boolean equals(Object o) {
		if(o instanceof Edge){
			Edge e = (Edge)o;
			return e.id.equals(id);
		}
		return false;
		
	}
	
	public String getID(){
		return id;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return timestamp;
	}
}

