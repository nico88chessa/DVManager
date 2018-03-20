
package com.dv.webmanager.main;

import java.util.List;

import com.dv.webmanager.db.bean.Machine;

public class MainBean {
    
    public static final String globalGrowId = "globalGrow";
    public static int ipPingTimes = 4;

    private List<Machine> machines;
    private Machine newMachine;
    private Machine selectedMachine;
    
    private String ipCommandResult;
    private int ipPingCounter;
    private boolean pingStop;
    
    public List<Machine> getMachines() {
        return machines;
    }
    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
    public Machine getNewMachine() {
        return newMachine;
    }
    public void setNewMachine(Machine newMachine) {
        this.newMachine = newMachine;
    }
    public Machine getSelectedMachine() {
        return selectedMachine;
    }
    public void setSelectedMachine(Machine selectedMachine) {
        this.selectedMachine = selectedMachine;
    }
    public String getIpCommandResult() {
        return ipCommandResult;
    }
    public void setIpCommandResult(String ipCommandResult) {
        this.ipCommandResult = ipCommandResult;
    }
    public int getIpPingCounter() {
        return ipPingCounter;
    }
    public void setIpPingCounter(int ipPingCounter) {
        this.ipPingCounter = ipPingCounter;
    }
    public boolean getPingStop() {
        return pingStop;
    }
    public void setPingStop(boolean pingStop) {
        this.pingStop = pingStop;
    }

}
