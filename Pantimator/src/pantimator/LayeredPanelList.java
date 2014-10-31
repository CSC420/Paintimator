package pantimator;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;


public class LayeredPanelList extends ArrayList<LayeredPanel> implements Serializable{
    private static final long serialVersionUID = 000006;

    private ArrayList<LayeredPanel> alLayeredPanels;
    private int intNumberOfPanels;
    private int intSelectedPanel;

    /**
     * Empty Constructor
     */
    LayeredPanelList(){
        alLayeredPanels = new ArrayList<LayeredPanel>();

    }


    public boolean add(LayeredPanel panelIn){
        this.alLayeredPanels.add(panelIn);
        this.intNumberOfPanels = this.alLayeredPanels.size();
        this.intSelectedPanel = this.alLayeredPanels.size() -1;
        return true;
    }

    /**
     *
     */
    public void setSelectedPanel(int selectedPanel){
        this.intSelectedPanel = selectedPanel;

    }

    /**
     *
     */
    public int getIntSelectedPanel(){
        return this.intSelectedPanel;
    }

    public LayeredPanel get(int index){
        this.intSelectedPanel = index;
        return this.alLayeredPanels.get(index);

    }

    public LayeredPanel getPrev(){
        if(this.intSelectedPanel > 0){
           return this.alLayeredPanels.get(this.intSelectedPanel);
        }else{
            this.intSelectedPanel --;
            return this.alLayeredPanels.get(this.intSelectedPanel);
        }
    }

    public LayeredPanel getNext(){
        if(this.intSelectedPanel == (this.intNumberOfPanels - 1 )){
            return this.alLayeredPanels.get(this.intSelectedPanel);
        }else{
            this.intNumberOfPanels ++;
            return this.alLayeredPanels.get(this.intSelectedPanel);
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

}
