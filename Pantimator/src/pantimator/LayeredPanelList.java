package pantimator;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Created by Mark Williams on 10/7/2014.
 */
public class LayeredPanelList extends ArrayList<JPanel> implements Serializable{
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

    public void add(LayeredPanel panelIn){
        this.alLayeredPanels.add(panelIn);
        this.intNumberOfPanels = this.alLayeredPanels.size();
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

}
