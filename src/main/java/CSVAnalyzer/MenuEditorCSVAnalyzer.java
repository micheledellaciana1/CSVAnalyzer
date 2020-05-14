package CSVAnalyzer;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import FilteringCore.derivateOrder1;
import FilteringCore.noiseRductionMedian;
import FilteringCore.noiseReductionOrder0;
import FilteringCore.noiseReductionOrder1;
import core.ChartFrame;
import core.DataManager;
import core.MenuEditorChartFrame;

public class MenuEditorCSVAnalyzer extends MenuEditorChartFrame {

    public MenuEditorCSVAnalyzer(ChartFrame chart) {
        super(chart);
    }

    public JMenu BuildMenuFile() {
        JMenu menu = new JMenu("File");

        menu.add(BuildExportMenu());
        menu.add(BuildLoadItem());

        return menu;
    }

    public JMenuItem BuildLoadItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Open") {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
                fileChooser.setDialogTitle("Open CSV file");

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    final File fileToLoad = fileChooser.getSelectedFile();
                    DataManager DM = new DataManager();
                    String divider = JOptionPane.showInputDialog("Input divider: <divider>");
                    DM.load(fileToLoad.getAbsolutePath(), divider);
                    CSVAnalizerBoard.getInstance().setDataManager(DM);
                    CSVAnalizerBoard.getInstance().DisplayIvsJ(0, 1);
                }
            }
        });

        return item;
    }

    public JMenu BuildDisplayMenu() {
        JMenu menu = new JMenu("Display");
        menu.add(BuildDisplayIJ());
        return menu;
    }

    public JMenuItem BuildDisplayIJ() {
        JMenuItem menuItem = new JMenuItem(new AbstractAction("DisplayData") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String answer = JOptionPane.showInputDialog("Input: <X index> <Y index>");
                    String splitted[] = answer.split(" ");
                    CSVAnalizerBoard.getInstance().DisplayIvsJ(Integer.valueOf(splitted[0]),
                            Integer.valueOf(splitted[1]));
                } catch (Exception _e) {
                }
            }
        });
        return menuItem;
    }

    public JMenu BuildFilterMenu() {
        JMenu menu = new JMenu("Filter");
        menu.add(BuildMenuNoiseReduction());
        menu.add(BuildDerivateOrder1MenuItem());
        menu.add(BuildResetFilterMenuItem());
        return menu;
    }

    public JMenu BuildMenuNoiseReduction() {
        JMenu menu = new JMenu("Noise Reduction");
        menu.add(BuildBoxCarOrder0MenuItem());
        menu.add(BuildBoxCarOrder1MenuItem());
        menu.add(BuildMedianMenuItem());
        return menu;
    }

    public JMenuItem BuildBoxCarOrder0MenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Add BoxCar Order0") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String answer = JOptionPane.showInputDialog("Input: <Half BoxSize>");
                    CSVAnalizerBoard.getInstance().getFilteringSupport()
                            .addFilter(new noiseReductionOrder0(Integer.valueOf(answer)));
                } catch (Exception _e) {
                }
            }
        });
        return item;
    }

    public JMenuItem BuildBoxCarOrder1MenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Add BoxCar Order1") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String answer = JOptionPane.showInputDialog("Input: <Half BoxSize>");
                    CSVAnalizerBoard.getInstance().getFilteringSupport()
                            .addFilter(new noiseReductionOrder1(Integer.valueOf(answer)));
                } catch (Exception _e) {
                }
            }
        });
        return item;
    }

    public JMenuItem BuildMedianMenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Median") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String answer = JOptionPane.showInputDialog("Input: <Half BoxSize>");
                    CSVAnalizerBoard.getInstance().getFilteringSupport()
                            .addFilter(new noiseRductionMedian(Integer.valueOf(answer)));
                } catch (Exception _e) {
                }
            }
        });
        return item;
    }

    public JMenuItem BuildDerivateOrder1MenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Derivate order1") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CSVAnalizerBoard.getInstance().getFilteringSupport().addFilter(new derivateOrder1());
                } catch (Exception _e) {
                }
            }
        });
        return item;
    }

    public JMenuItem BuildResetFilterMenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Erase Filters") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CSVAnalizerBoard.getInstance().getFilteringSupport().removeEveryFilter();
            }
        });
        return item;
    }
}