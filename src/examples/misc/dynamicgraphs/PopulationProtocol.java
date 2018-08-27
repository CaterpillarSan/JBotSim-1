package examples.misc.dynamicgraphs;

import jbotsim.Link;
import jbotsim.PRNG;
import jbotsim.Topology;
import jbotsim.event.ClockListener;
import jbotsimx.ui.JViewer;

import java.util.List;

/**
 * Created by acasteig on 17/03/15.
 */
public class PopulationProtocol implements ClockListener{
    Topology topology;

    public PopulationProtocol(Topology topology) {
        this.topology = topology;
        topology.addClockListener(this, 10);
    }

    @Override
    public void onClock() {
        List<Link> links = topology.getLinks();

        for (Link link : links)
            link.setWidth(1);
        if (links.size()>0) {
            Link link = links.get(PRNG.nextInt(links.size()));
            link.setWidth(4);
            interact(link);
        }
    }

    private void interact(Link link){

    }
    public static void main(String[] args) {
        Topology tp = new Topology();
        new PopulationProtocol(tp);
        new JViewer(tp);
    }
}
