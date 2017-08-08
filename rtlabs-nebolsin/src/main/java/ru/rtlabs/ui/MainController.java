package ru.rtlabs.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rtlabs.datasource.ReportSource;
import ru.rtlabs.entity.ReportRow;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @Autowired private ReportSource reportSource;

    @FXML private TableView<ReportRow> table;
    @FXML private Button populate;
    @FXML private BarChart<String,Number> chart;

    private ObservableList<ReportRow> tableData;

    @FXML
    public void initialize() {
    }


    @PostConstruct
    public void init() {
        List<ReportRow> rows = reportSource.getReportRows();
        if (rows != null)
            tableData = FXCollections.observableArrayList(rows);

        TableColumn<ReportRow, String> claimIdColumn = new TableColumn<>("Номер заявки");
        claimIdColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("orderNumber"));

        TableColumn<ReportRow, String> dateColumn = new TableColumn<>("Дата");
        dateColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("date"));

        TableColumn<ReportRow, String> formCodeColumn = new TableColumn<>("Код формы");
        formCodeColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("formCode"));

        TableColumn<ReportRow, String> statusColumn = new TableColumn<>("Статус");
        statusColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("status"));

        TableColumn<ReportRow, String> fioColumn = new TableColumn<>("ФИО заявителя");
        fioColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("fio"));

        TableColumn<ReportRow, String> typeColumn = new TableColumn<>("Тип заявителя");
        typeColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("type"));

        TableColumn<ReportRow, String> serviceNameColumn = new TableColumn<>("Название услуги");
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("serviceName"));

        TableColumn<ReportRow, String> subserviceNameColumn = new TableColumn<>("Название подуслуги");
        subserviceNameColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("subserviceName"));

        TableColumn<ReportRow, String> enterpriseNameColumn = new TableColumn<>("Название ведомства");
        enterpriseNameColumn.setCellValueFactory(new PropertyValueFactory<ReportRow,String>("enterpriseName"));

        table.getColumns().setAll(claimIdColumn, dateColumn, formCodeColumn, statusColumn, fioColumn, typeColumn, serviceNameColumn, subserviceNameColumn, enterpriseNameColumn);

        if (tableData != null)
            table.setItems(tableData);

        chart.getXAxis().setLabel("Дата");
        chart.getYAxis().setLabel("Количество");

        refreshChartData("ИП");
    }

    /**
     * Обновление данных в чарте, тип клиента как параметр
     */
    private void refreshChartData(String type){
        XYChart.Series series = new XYChart.Series();

        series.setName(type);

        TreeMap<Date,Integer> barSeries = reportSource.getBarSeries(type);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        for (Map.Entry entry : barSeries.entrySet()) {
            series.getData().add(new XYChart.Data<>(df.format(entry.getKey()), entry.getValue()));
        }

        chart.getData().clear();

        chart.getData().addAll(series);
    }


    /**
     * Обновление
     */
    @FXML
    public void populateBase(ActionEvent actionEvent) {
        reportSource.populate();
        populate.setText("База заполнена.");
        List<ReportRow> rows = reportSource.getReportRows();
        if (rows != null)
            tableData = FXCollections.observableArrayList(rows);
        table.setItems(tableData);
        refreshChartData("ИП");
    }

    @FXML
    public void refreshChartIP(ActionEvent actionEvent) {
        refreshChartData("ИП");
    }

    @FXML
    public void refreshChartUL(ActionEvent actionEvent) {
        refreshChartData("ЮЛ");
    }

    @FXML
    public void refreshChartFL(ActionEvent actionEvent) {
        refreshChartData("ФЛ");
    }
}
