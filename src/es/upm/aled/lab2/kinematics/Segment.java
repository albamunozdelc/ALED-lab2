package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;


/**
 * Class representing one segment in the kinematics that paints the exoskeleton bones on
 * the screen. Each segment is identified by it length and angle. Every Segment has a List of their
 * children Segments; those it's connected to.
 * 
 * @author albamunozdelc
 */

// TODO: Implemente la clase
//esta clase debe tener toda la info de la que disponenmos para cada segmento
//no necesario pos ini del segmento
public class Segment {
	private double length;
	private double angle;
	private List<Segment> children;
	

	/**
	 * Builds a new Segment by its length and its angle relative to the parent
	 * 
	 * @param length
	 * @param angle
	 */
	public Segment(double length, double angle) {
		this.length = length;
		this.angle = angle;
		this.children = new ArrayList<>();
	}
	
	
	/**
	 * Returns the length of the segment
	 * 
	 * @return The length of the segment
	 */
	public double getLength() {
		return length;
	}
	
	
	/**
	 * Returns the angle between segment child segment and parent segment
	 * 
	 * @return Value of the angle
	 */
	public double getAngle() {
		return angle;
	}
	
	
	/**
	 * Returns the Segments this one is related to
	 * 
	 * @return A List of all the children Segments.
	 */
	public List<Segment> getChildren() {
		return children;
	}
	
	
	/** Set the angle of one Segments
	 * 
	 * @param angle
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	
	/** Adds a new Segment to the List of Segments this ione is connected to. Each Segments 
	 * can only appear as a child once
	 * 
	 * @param child
	 */
	public void addChild(Segment child) {
		if (!children.contains(child))
			children.add(child);
	}

}
