package pantimator;

import java.io.Serializable;
import java.util.ArrayList;

public class LayeredPanelList extends ArrayList<LayeredPanel> implements Serializable{
    private static final long serialVersionUID = 000006;
    private ArrayList<LayeredPanel> alLayeredPanels;
    private int intNumberOfPanels = 0;
    private int intSelectedPanel = 0;

    LayeredPanelList(){
        alLayeredPanels = new ArrayList<LayeredPanel>();
    }

    public boolean add(LayeredPanel panelIn){
        alLayeredPanels.add(panelIn);
        intSelectedPanel = intNumberOfPanels;
        intNumberOfPanels ++;
        
        return true;
    }
    

    public void setSelectedPanel(int selectedPanel){
        this.intSelectedPanel = selectedPanel;
    }

    public int getIntSelectedPanel(){
        return this.intSelectedPanel;
    }

    public LayeredPanel get(int index){
        this.intSelectedPanel = index;
        return this.alLayeredPanels.get(index);

    }

    public LayeredPanel getPrev(){

        if(this.intSelectedPanel > 0){
        	 intSelectedPanel --;
        	 return this.getSelected();
        }else{
        	return this.getSelected();
        }
    }

    public LayeredPanel getNext(){
        if(this.intSelectedPanel == (this.intNumberOfPanels - 1 )){
        	return this.getSelected();
        }else{
            this.intSelectedPanel ++;
            return this.getSelected();
        }
    }

    public LayeredPanel getFirst(){
        this.intSelectedPanel = 0;
        return this.alLayeredPanels.get(this.intSelectedPanel);
    }

    public LayeredPanel getLast(){
        this.intSelectedPanel = this.intNumberOfPanels - 1;
        return this.alLayeredPanels.get(this.intSelectedPanel);
    }

    public LayeredPanel getSelected(){
        return  this.alLayeredPanels.get(this.intSelectedPanel);
    }
    
    public int getSize(){
    	return this.alLayeredPanels.size();
    }
    
    public ArrayList<LayeredPanel> getArray() {
    	return this.alLayeredPanels;
    }

}
