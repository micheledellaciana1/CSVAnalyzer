package CSVAnalyzer;

import javax.swing.JMenuBar;

import org.jfree.chart.ChartPanel;

import FilteringCore.filteringSupport;
import core.ChartFrame;
import core.DataManager;
import core.ModChartPanel;

public class CSVAnalizerBoard extends ChartFrame {

    static private CSVAnalizerBoard _instance = new CSVAnalizerBoard(new ModChartPanel(), "CSVAnalyzer");
    private DataManager _dataManager;
    private MenuEditorCSVAnalyzer _menuEditor;
    private filteringSupport _filteringSupport;

    static public CSVAnalizerBoard getInstance() {
        return _instance;
    }

    private CSVAnalizerBoard(ChartPanel panel, String Title) {
        super(panel, Title);
        _filteringSupport = new filteringSupport();
        _menuEditor = new MenuEditorCSVAnalyzer(this);
    }

    public void constructMenuBar() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(_menuEditor.BuildMenuFile());
        menubar.add(_menuEditor.BuildDisplayMenu());
        menubar.add(_menuEditor.BuildFilterMenu());
        setJMenuBar(menubar);
    }

    public filteringSupport getFilteringSupport() {
        return _filteringSupport;
    }

    public void setDataManager(DataManager dataManager) {
        _dataManager = dataManager;
    }

    public void DisplayIvsJ(int i, int j) {
        if (i >= 0 && j >= 0 && i < _dataManager.getData().size() && j < _dataManager.getData().size()) {
            clearData();
            _filteringSupport.removeEveryFilter();
            _filteringSupport.setOriginalData(_dataManager.getData(i), _dataManager.getData(j));
            addSeries(_filteringSupport.getFilteredData(), "Filtered:" + i + ":" + j);
            _chart.getXYPlot().getDomainAxis().setLabel(_dataManager.getLabel(i));
            _chart.getXYPlot().getRangeAxis().setLabel(_dataManager.getLabel(j));
        }
    }

    public void DisplayOriginal() {
        addSeries(_filteringSupport.getOriginalData(), "Original");
    }
}