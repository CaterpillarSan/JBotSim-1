package jbotsimx.tools;

import jbotsim.Topology;
import jbotsimx.replay.TraceRecorder;
import jbotsimx.ui.JViewer;

public class JBotSimRecorder {
    public static void main(String[] args) {
        try {
            Topology tp = new Topology();
            String traceFileName = args[0];
            TraceRecorder tr = new TraceRecorder(tp);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        tr.stopAndWrite(traceFileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            tr.start();
            new JViewer(tp);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}