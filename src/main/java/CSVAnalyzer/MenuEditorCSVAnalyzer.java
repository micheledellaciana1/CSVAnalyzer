package CSVAnalyzer;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import FilteringCore.LogXFilter;
import FilteringCore.LogYFilter;
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
        verbose = false;
    }

    public JMenu BuildMenuFile() {
        JMenu menu = new JMenu("File");

        menu.add(BuildThemeMenu());
        menu.add(BuildExportMenu());
        menu.add(BuildLoadItem());
        menu.add(BuildPropertyChart());

        return menu;
    }

    public JMenu BuildPropertyChart() {
        JMenu menu = new JMenu("Property chart");

        menu.add(BuildSetXAxisName());
        menu.add(BuildSetYAxisName());

        return menu;
    }

    public JMenuItem BuildLoadItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Open") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
                    fileChooser.setDialogTitle("Open CSV file");

                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        final File fileToLoad = fileChooser.getSelectedFile();
                        DataManager DM = new DataManager();
                        String divider = JOptionPane.showInputDialog("Input divider: <divider>");
                        DM.load(fileToLoad.getAbsolutePath(), divider);
                        CSVAnalizerBoard.getInstance().setDataManager(DM);
                    }

                    JOptionPane.showMessageDialog(null,
                            "Found: " + CSVAnalizerBoard.getInstance().getDataManager().getData().size() + " columns",
                            "CSVAnalyzer", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception _e) {
                }
            }
        });

        return item;
    }

    public JMenu BuildDisplayMenu() {
        JMenu menu = new JMenu("Display");
        menu.add(BuildDisplayIJ());
        menu.add(BuildEraseEveryPlot());
        return menu;
    }

    public JMenuItem BuildDisplayIJ() {
        JMenuItem menuItem = new JMenuItem(new AbstractAction("PlotIJ Data") {

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

    public JMenuItem BuildSetXAxisName() {
        JMenuItem menuItem = new JMenuItem(new AbstractAction("Set X axis name") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = JOptionPane.showInputDialog("Input X axis name");
                    _chart.GetChart().getXYPlot().getDomainAxis().setLabel(name);
                } catch (Exception _e) {
                }
            }
        });
        return menuItem;
    }

    public JMenuItem BuildSetYAxisName() {
        JMenuItem menuItem = new JMenuItem(new AbstractAction("Set Y axis name") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = JOptionPane.showInputDialog("Input Y axis name");
                    _chart.GetChart().getXYPlot().getRangeAxis().setLabel(name);
                } catch (Exception _e) {
                }
            }
        });
        return menuItem;
    }

    public JMenuItem BuildEraseEveryPlot() {
        JMenuItem menuItem = new JMenuItem(new AbstractAction("Clear chart") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CSVAnalizerBoard.getInstance().EraseEveryPlot();
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
        menu.add(BuildLogXMenuItem());
        menu.add(BuildLogYMenuItem());
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
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport()
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
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport()
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
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport()
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
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport().addFilter(new derivateOrder1());
                } catch (Exception _e) {
                }
            }
        });

        return item;
    }

    public JMenuItem BuildLogXMenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Log X axis") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport().addFilter(new LogXFilter());
                } catch (Exception _e) {
                }
            }
        });

        return item;
    }

    public JMenuItem BuildLogYMenuItem() {
        JMenuItem item = new JMenuItem(new AbstractAction("Log Y axis") {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport().addFilter(new LogYFilter());
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
                try {
                    CSVAnalizerBoard.getInstance().getSelectedFilteringSupport().removeEveryFilter();
                } catch (Exception _e) {
                }
            }
        });
        return item;
    }

    public JMenu BuildSelectMenu() {
        JMenu menu = new JMenu("Select");

        final JMenuItem menuItem = new JMenuItem(new AbstractAction("Selected <Null>") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String answer = JOptionPane.showInputDialog("Select plot: <Index>");
                    CSVAnalizerBoard.getInstance().setSelectedFilteringSupport(Integer.valueOf(answer));
                } catch (Exception _e) {
                }
            }
        });

        menu.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                try {
                    menuItem.setText("Selected plot: <"
                            + CSVAnalizerBoard.getInstance().getIndexSelectedFilteringSupport() + ">");
                } catch (Exception _e) {
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }

        });

        menu.add(menuItem);
        return menu;
    }
}