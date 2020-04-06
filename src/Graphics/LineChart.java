package Graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

import static Graphics.Dataset.*;


public class LineChart extends ApplicationFrame {
    private static final long serialVersionUID = 1L;
    private String description;
    private double[] f;
    private double[] x;
    private double[] y;
    XYSeriesCollection seriesNewDataset = new XYSeriesCollection();
    XYSeriesCollection firstSeriesDataset = new XYSeriesCollection();

    public LineChart(final String title, double[] f, double[] x,double[] y,String description) {
        super(title);
        this.description=description;
        this.f=f;
        this.x=x;
        this.y=y;
        start();
    }

    private void start() {
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));
        setContentPane(chartPanel);
    }


    private JFreeChart createChart() {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                description,
                null,                        // x axis label
                null,                        // y axis label
                null,                        // data
                PlotOrientation.VERTICAL,
                true,                        // include legend
                false,                       // tooltips
                false                        // urls
        );

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(159, 190, 237));

        plot.setDomainGridlinePaint(Color.gray);//сетка
        plot.setRangeGridlinePaint(Color.gray);

        // Определение отступа меток делений
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        // Скрытие осевых линий и меток делений
        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);    // осевая линия

        // Настройка NumberAxis
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYSeries seriesNew = new XYSeries(description);
        XYSeries firstSeries = new XYSeries("Табличная функция");
        for (int i = 0; i < f.length; i++) {
            seriesNew.add(x[i], f[i]);// График апроксимирующей функции
            firstSeries.add(x[i],y[i]);
        }
        seriesNewDataset.addSeries(seriesNew);
        XYDataset newDataset = seriesNewDataset;

        // Точки набора данных
        firstSeriesDataset.addSeries(firstSeries);
        XYDataset firstDataset = firstSeriesDataset;

        // Настройка XYSplineRenderer
        // Precision: the number of line segments between 2 points [default: 5]
        XYSplineRenderer r0 = new XYSplineRenderer();
        //характеристики графика аппроксимации
        r0.setPrecision(8);
        r0.setSeriesShapesVisible(0, true); //точки на графике
        r0.setSeriesPaint(0, Color.blue);
        XYSplineRenderer r1 = new XYSplineRenderer();
        //характеристики табличной функции
        r1.setSeriesPaint(0, Color.red);
        r1.setSeriesShapesVisible(0, true);
        r1.setSeriesLinesVisible (0, false);

        plot.setDataset(0, newDataset);
        plot.setDataset(1, firstDataset);

        // Подключение Spline Renderer к набору данных
        plot.setRenderer(0, r0);
        plot.setRenderer(1, r1);

        return chart;
    }

}