package model;

import java.io.Serializable;

public class SaveResult implements Serializable {
    private int simId;
    private String simName;
    private String saveDate;
    private String simCom;
    
    public SaveResult() {}
    public SaveResult(String simName) {
        this.simName = simName;
    }
    public SaveResult(String simName, String saveDate, String simCom) {
        this.simName = simName;
        this.saveDate = saveDate;
        this.simCom = simCom;
    }
    public SaveResult(int simId, String simName, String saveDate, String simCom) {
        this.simId = simId;
        this.simName = simName;
        this.saveDate = saveDate;
        this.simCom = simCom;
    }
    public int getSimId() { return simId; }
    public String getSimName() { return simName; }
    public String getSaveDate() { return saveDate; }
    public String getSimCom() { return simCom; }
}