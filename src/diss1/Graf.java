/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diss1;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.IAxis.AxisTitle;
import info.monitorenter.gui.chart.IAxisScalePolicy;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.axis.scalepolicy.AxisScalePolicyManualTicks;
import info.monitorenter.gui.chart.labelformatters.LabelFormatterNumber;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author davidecek
 */
public class Graf {

    private ITrace2D trace;
    private Chart2D chart;
    private JFrame frame;

    public Graf() {
        trace = new Trace2DSimple();
    }

    public void configure(String framename, int fwidth, int fheight, int xpos, int ypos) {
        try {
            chart = new Chart2D();
            trace.setColor(Color.red);
            trace.setName("1. strategia");
            frame = new JFrame(framename);
            IAxis<IAxisScalePolicy> xAxis = (IAxis<IAxisScalePolicy>) chart.getAxisX();           
            xAxis.setFormatter(new LabelFormatterNumber(new DecimalFormat("###k")));
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.getContentPane().add(chart);
            frame.setSize(fwidth, fheight);
            frame.setLocation(xpos, ypos);

        } catch (Throwable f) {
            System.exit(0);
        }
    }

    public void trace() throws InterruptedException {
        frame.setVisible(true);
        chart.addTrace(trace);

    }

    public void addPoint(double X, double Y) throws InterruptedException {
        trace.addPoint(X, Y);
    }

    public void createBarChart(int pocetParkovacichMiest, int[] vysledky, int xpos, int ypos) {
        chart = new Chart2D();
        ITrace2D trace = new Trace2DSimple();
        trace.setTracePainter(new info.monitorenter.gui.chart.traces.painters.TracePainterVerticalBar(chart));
        chart.addTrace(trace);
        trace.setColor(Color.red);
        for (int i = 0; i < pocetParkovacichMiest + 1; i++) {
            trace.addPoint(i, vysledky[i]);
        }
        IAxis<IAxisScalePolicy> xAxis = (IAxis<IAxisScalePolicy>) chart.getAxisX();
        xAxis.setAxisScalePolicy(new AxisScalePolicyManualTicks());
        xAxis.setMajorTickSpacing(pocetParkovacichMiest);
        // xAxis.getMaxValue();                
        xAxis.setMinorTickSpacing(5);
        xAxis.setAxisTitle(new AxisTitle("Pocetnosti zaparkovani"));
        xAxis.setStartMajorTick(true);
        JFrame frame = new JFrame("Pocetnosti");
        frame.getContentPane().add(chart);         
        frame.setSize(600, 400);
        frame.setLocation(xpos, ypos);
        frame.addWindowListener(
                new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        frame.setVisible(true);

    }

}
