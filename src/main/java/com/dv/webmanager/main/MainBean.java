
package com.dv.webmanager.main;

import java.util.List;

import com.dv.webmanager.db.bean.Machine;

public class MainBean {

    public static final String globalGrowId = "globalGrow";

    // attributi per la pagina di gestione macchine
    private List<Machine> machines;
    private Machine newMachine;
    private Machine selectedMachine;
    private String ipCommandResult;

    // attributi per la pagina di ricerca ticket
    private List<String> ricercaListaMacchine;
    private String ricercaMacchinaSelezionata;
    private List<String> ricercaListaTipoLaser;
    private String ricercaLaserSelezionato;
    private java.util.Date ricercaDataInizioStampa;
    private java.util.Date ricercaDataFineStampa;
    private boolean ricercaIsStampaInErrore;

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

}
