import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        List<Double> y1Values = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Integer> bstTest = new BST<>();
        Random r = new Random();
        for (int i = 1;i<=5000;i++){
            int randomItem = r.nextInt(100000);
            bstTest.add(randomItem);
            double thisY1 = bstTest.AverageDepth();
            y1Values.add(thisY1);
            xValues.add(i);
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("number of items").yAxisTitle("average depth").build();
        chart.addSeries("random BST", xValues, y1Values);
        chart.addSeries("optimal BST", xValues, y2Values);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Integer> bstTest = new BST<>();
        Random r = new Random();
        for (int i=0;i<5000;i++){
            int randomItem = r.nextInt(100000);
            bstTest.add(randomItem);
        }
        for (int i=0;i<500000;i++){
            int randomItem = r.nextInt(100000000);
            bstTest.deleteTakingSuccessor(bstTest.getRandomKey());
            bstTest.add(randomItem);
            yValues.add(bstTest.AverageDepth());
            xValues.add(i);
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("number of operations").yAxisTitle("average depth").build();
        chart.addSeries("asymmetric Hibbard deletion", xValues, yValues);

        new SwingWrapper(chart).displayChart();


    }

    public static void experiment3() {
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Integer> bstTest = new BST<>();
        Random r = new Random();
        for (int i=0;i<5000;i++){
            int randomItem = r.nextInt(100000);
            bstTest.add(randomItem);
        }
        for (int i=0;i<500000;i++){
            int randomItem = r.nextInt(100000000);
            bstTest.deleteTakingRandom(bstTest.getRandomKey());
            bstTest.add(randomItem);
            yValues.add(bstTest.AverageDepth());
            xValues.add(i);
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("number of operations").yAxisTitle("average depth").build();
        chart.addSeries("symmetric Hibbard deletion", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        //experiment1();
        //experiment2();
        experiment3();
    }
}
