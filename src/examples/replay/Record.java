package examples.replay;

import jbotsim.Topology;
import jbotsim.ui.JViewer;
import jbotsimx.format.xml.XMLTopologyParser;
import jbotsimx.replay.TraceRecorder;

public class Record {
    public static void main(String[] args) {
        try {
            Topology topology = new Topology();
            XMLTopologyParser parser = new XMLTopologyParser(topology);
            parser.parse(Record.class.getResourceAsStream("/examples/replay/network.xml"));
            TraceRecorder recorder = new TraceRecorder(topology);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        recorder.terminate("trace.xml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            recorder.start();

            new JViewer(topology);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
