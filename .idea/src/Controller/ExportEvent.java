package Controller;

public class ExportEvent {


    private String exportType;

    public ExportEvent(String exportType) {
        this.exportType = exportType;
    }

    public String getExportType() {
        return exportType;
    }
}