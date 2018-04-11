package com.dv.webmanager.db.bean;

import java.util.List;

public class TicketFilter {

    private List<Machine> machineList;
    private int idMachine;
    private String filename;
    private java.util.Date dateStart;
    private java.util.Date dateEnd;
    private int laserKind;
    private int[] printStatus;

    public List<Machine> getMachineList() {
        return machineList;
    }
    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
    }
    public int getIdMachine() {
        return idMachine;
    }
    public void setIdMachine(int idMachine) {
        this.idMachine = idMachine;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public java.util.Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(java.util.Date dateStart) {
        this.dateStart = dateStart;
    }
    public java.util.Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(java.util.Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public int getLaserKind() {
        return laserKind;
    }
    public void setLaserKind(int laserKind) {
        this.laserKind = laserKind;
    }
    public int[] getPrintStatus() {
        return printStatus;
    }
    public void setPrintStatus(int[] printStatus) {
        this.printStatus = printStatus;
    }

}
