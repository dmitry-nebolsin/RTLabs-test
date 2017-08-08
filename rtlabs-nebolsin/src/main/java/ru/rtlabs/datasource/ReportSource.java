package ru.rtlabs.datasource;

import ru.rtlabs.entity.ReportRow;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public interface ReportSource {
    void populate();

    List<ReportRow> getReportRows();

    TreeMap<Date,Integer> getBarSeries(String type);
}
