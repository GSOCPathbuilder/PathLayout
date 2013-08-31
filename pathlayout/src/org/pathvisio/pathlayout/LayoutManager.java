package org.pathvisio.pathlayout;

import org.pathvisio.core.preferences.Preference;
import org.pathvisio.gui.SwingEngine;
import org.pathvisio.pathlayout.layouts.*;

public class LayoutManager {

	public static enum Layout {
		BALLOON("Balloon","A Layout implementation that assigns positions to vertices using associations with nested circles.","JUNG2.0"),
		FREIN("Fruchtman-Reingold","The Fruchterman-Rheingold algorithm.","JUNG2.0"),
		ISOM("ISOM","Meyer's \"Self-Organizing Map\" layout.","JUNG2.0"),
		KAMKAW("Kamada-Kawai","The Kamada-Kawai algorithm for node layout","JUNG2.0"),
		SPRING("Spring","A simple force-directed spring-embedder","JUNG2.0"),
		PREFUSE("Force-Directed Layout","Force-Directed layout algorithm","Prefuse");
		
		private final String name;
		private final String desc;
		private final String src;
		private boolean selection;
		
		private Layout(String name, String desc, String src){
			this.name = name;
			this.desc = desc;
			this.src = src;
			selection = false;
		}
		
		@Override
		public String toString() {
			return name;
		}
		public String getDescription(){
			return desc;
		}
		public String getSource(){
			return src;
		}
		
		public void useSelection(boolean selection){
			this.selection = selection;
		}
		
		public LayoutAbstract doLayout(SwingEngine se){
			switch (this){
			case BALLOON:
				return new Balloon(se,selection);
			case FREIN:
				return new FruchtRein(se,selection);
			case ISOM:
				return new ISOM(se,selection);
			case KAMKAW:
				return new KamKaw(se,selection);
			case SPRING:
				return new Spring(se,selection);
			case PREFUSE:
				return new Prefuse(se,selection);
			}
			return null;
		}
	
	}
	public static enum PlPreference implements Preference
	{
		PL_LAYOUT_FR_ATTRACTION("0.5"),
		PL_LAYOUT_FR_REPULSION("1"),
		PL_LAYOUT_SPRING_FORCE("0.33"),
		PL_LAYOUT_SPRING_REPULSION("100"),
		PL_LAYOUT_SPRING_STRETCH("0.7");
		
	
		private final String defaultVal;
		
		PlPreference (String _defaultVal) { defaultVal = _defaultVal; }
		
		@Override
		public String getDefault() { return defaultVal; }
	}
}
