package CSVAnalyzer;

import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;

import javax.swing.JMenuBar;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ui.RectangleInsets;

import FilteringCore.filteringSupport;
import core.ChartFrame;
import core.DataManager;
import core.ModChartPanel;

public class CSVAnalizerBoard extends ChartFrame {

    static private CSVAnalizerBoard _instance = new CSVAnalizerBoard(new ModChartPanel(), "CSVAnalyzer");
    private DataManager _dataManager;
    private MenuEditorCSVAnalyzer _menuEditor;
    private ArrayList<filteringSupport> _filteringSupports;
    private ArrayList<String> _LabelDomain;
    private ArrayList<String> _LabelRange;
    private Integer _selectedFilteringSupport = null;

    static public CSVAnalizerBoard getInstance() {
        return _instance;
    }

    private CSVAnalizerBoard(ChartPanel panel, String Title) {
        super(panel, Title);
        _filteringSupports = new ArrayList<filteringSupport>();
        _LabelDomain = new ArrayList<String>();
        _LabelRange = new ArrayList<String>();

        _menuEditor = new MenuEditorCSVAnalyzer(this);
    }

    public void constructMenuBar() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(_menuEditor.BuildMenuFile());
        menubar.add(_menuEditor.BuildDisplayMenu());
        menubar.add(_menuEditor.BuildFilterMenu());
        menubar.add(_menuEditor.BuildSelectMenu());
        setJMenuBar(menubar);
    }

    public ArrayList<filteringSupport> getFilteringSupports() {
        return _filteringSupports;
    }

    public filteringSupport getSelectedFilteringSupport() {
        if (_selectedFilteringSupport == null)
            throw new NullPointerException();
        return _filteringSupports.get(_selectedFilteringSupport);
    }

    public int getIndexSelectedFilteringSupport() {
        if (_selectedFilteringSupport == null)
            throw new NullPointerException();
        return _selectedFilteringSupport;
    }

    public void setSelectedFilteringSupport(int selected) {
        if (selected < _filteringSupports.size()) {
            _selectedFilteringSupport = selected;
            _chart.getXYPlot().getDomainAxis().setLabel(_LabelDomain.get(_selectedFilteringSupport));
            _chart.getXYPlot().getRangeAxis().setLabel(_LabelRange.get(_selectedFilteringSupport));
        } else
            return;
    }

    public void setDataManager(DataManager dataManager) {
        _dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return _dataManager;
    }

    public void EraseEveryPlot() {
        clearData();
        _filteringSupports.clear();
        _LabelRange.clear();
        _LabelDomain.clear();
        _chart.getXYPlot().getDomainAxis().setLabel("X");
        _chart.getXYPlot().getRangeAxis().setLabel("Y");
    }

    public void DisplayIvsJ(int i, int j) {
        if (i >= 0 && j >= 0 && i < _dataManager.getData().size() && j < _dataManager.getData().size()) {
            _filteringSupports.add(new filteringSupport());
            _LabelDomain.add(_dataManager.getLabel(i));
            _LabelRange.add(_dataManager.getLabel(j));

            _selectedFilteringSupport = _filteringSupports.size() - 1;
            getSelectedFilteringSupport().setOriginalData(_dataManager.getData(i), _dataManager.getData(j));
            addSeries(getSelectedFilteringSupport().getFilteredData(),
                    "Plot" + _selectedFilteringSupport + ": " + i + ":" + j);
            _chart.getXYPlot().getDomainAxis().setLabel(_LabelDomain.get(_selectedFilteringSupport));
            _chart.getXYPlot().getRangeAxis().setLabel(_LabelRange.get(_selectedFilteringSupport));
        }
    }
}