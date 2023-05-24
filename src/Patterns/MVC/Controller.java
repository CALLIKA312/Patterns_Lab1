package Patterns.MVC;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.text.DecimalFormat;

public class Controller {
    public TableView<MyTableRow> tableView;
    public TableColumn<MyTableRow, String> xTableColumn;
    public TableColumn<MyTableRow, String> yTableColumn;
    public LineChart<Number, Number> lineChart;
    public Button submitButton;
    public TextArea minXTextArea;
    public TextArea maxXTextArea;
    public DecimalFormat decimalFormat = new DecimalFormat("#.###");
    public TextArea newValueTextArea;


    @FXML
    public void initialize() {
        XYChart<Number, Number> chart = lineChart;
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        chart.getData().add(series);

        tableView.setEditable(true);

        xTableColumn.setCellValueFactory(new PropertyValueFactory<>("xValue"));
        xTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        xTableColumn.setOnEditCommit(t -> {
                    if (t.getNewValue().isEmpty() || t.getNewValue().equals("")) {
                        MyTableRow myTableRow = tableView.getSelectionModel().getSelectedItem();
                        tableView.getItems().remove(myTableRow);
                    } else {
                        try {
                            double number = Double.parseDouble(t.getNewValue());
                            System.out.println("Valid number entered: " + number);
                        } catch (NumberFormatException e) {
                            tableView.refresh();
                            showErrorDialog("Invalid Number", "Please enter a valid number.");
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setxValue(t.getNewValue());
                    }
                    tableChartUpdate();
                    tableFulfill(lineChart.getData());
                }
        );
        yTableColumn.setEditable(false);
        yTableColumn.setCellValueFactory(new PropertyValueFactory<>("yValue"));


    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void update() {
        butChartUpdate();
        tableFulfill(lineChart.getData());
    }


    private void tableChartUpdate() {
        ObservableList<MyTableRow> ol = tableView.getItems();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<Number, Number>> data = series.getData();
        for (MyTableRow row : ol) {
            if (row.getxValue().isEmpty()) {
                System.out.println(row.getyValue());
                continue;
            }
            data.add(new XYChart.Data<>(
                    Double.parseDouble(
                            row.getxValue().replace(',', '.')),
                    Math.sin(Double.parseDouble(
                            row.getxValue().replace(',', '.')))
            ));
            System.out.println(data.get(data.size() - 1));
        }
        if (!lineChart.getData().isEmpty()) lineChart.getData().clear();
        lineChart.getData().add(series);
    }


    private void butChartUpdate() {
        double minX = Double.parseDouble(minXTextArea.getText());
        double maxX = Double.parseDouble(maxXTextArea.getText());
        int ySize = (int) (((maxX - minX) / 0.1) + 1);
        double curX = minX;

        var xAxis = new NumberAxis();
        xAxis.setLabel("X");
        var yAxis = new NumberAxis();
        yAxis.setLabel("Y");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("sin");
        ObservableList<XYChart.Data<Number, Number>> data = series.getData();

        for (int i = 0; i < ySize; i++) {
            data.add(new XYChart.Data<>(curX, Math.sin(curX)));
            curX += 0.1;
        }
        if (!lineChart.getData().isEmpty()) lineChart.getData().clear();
        lineChart.getData().add(series);
    }


    private void tableFulfill(ObservableList<XYChart.Series<Number, Number>> series) {
        System.out.println("Start table fill");
        ObservableList<MyTableRow> tableData = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<Number, Number>> data = series.get(0).getData();
        for (XYChart.Data<Number, Number> value : data) {
            tableData.add(new MyTableRow(decimalFormat.format(value.getXValue().doubleValue()), decimalFormat.format(value.getYValue().doubleValue())));
        }
        tableView.getItems().clear();
        tableView.getItems().addAll(tableData);

    }

    public void addNewValue() {
        System.out.println("123123");
        if (newValueTextArea.getText().isEmpty()) return;
        tableView.getItems().add(new MyTableRow(newValueTextArea.getText(), ""));
        newValueTextArea.clear();
        tableChartUpdate();
        tableFulfill(lineChart.getData());
    }

    public static class MyTableRow {
        private final SimpleStringProperty xValue;
        private final SimpleStringProperty yValue;

        public MyTableRow(String xValue, String yValue) {
            this.xValue = new SimpleStringProperty(xValue);
            this.yValue = new SimpleStringProperty(yValue);
        }

        public String getxValue() {
            return xValue.get();
        }

        public SimpleStringProperty xValueProperty() {
            return xValue;
        }

        public void setxValue(String xValue) {
            this.xValue.set(xValue);
        }

        public String getyValue() {
            return yValue.get();
        }

        public SimpleStringProperty yValueProperty() {
            return yValue;
        }

        public void setyValue(String yValue) {
            this.yValue.set(yValue);
        }
    }
}
