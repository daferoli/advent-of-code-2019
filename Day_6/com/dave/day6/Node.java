package com.dave.day6;

public class Node {
	private Node parent;
	private String name;
	
	public Node(String name) {
		this.setName(name);
		parent = null;
	}
	
	public Node(String name, Node parent) {
		this.setName(name);
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Integer getTotalOrbitCount() {
		if(parent == null) { 
			return 0;
		} else {
			return 1 + parent.getTotalOrbitCount();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
