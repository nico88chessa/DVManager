
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

    // questi sono per la dialog dei filtri
    private List<String> filtroListaMacchine;
    private String filtroNomefile;
    private List<String> filtroListaLaserKind;
    private java.util.Date filtroDataDa;
    private java.util.Date filtroDataA;
    private boolean filtroStampaInErrore;
    // questi sono per la dialog dei filtri (campi selezionati)
    private String filtroMacchinaSelezionata;
    private int filtroLaserKindSelezionato;


    // getters and setters
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
    public List<String> getFiltroListaMacchine() {
        return filtroListaMacchine;
    }
    public void setFiltroListaMacchine(List<String> filtroListaMacchine) {
        this.filtroListaMacchine = filtroListaMacchine;
    }
    public String getFiltroNomefile() {
        return filtroNomefile;
    }
    public void setFiltroNomefile(String filtroNomefile) {
        this.filtroNomefile = filtroNomefile;
    }
    public List<String> getFiltroListaLaserKind() {
        return filtroListaLaserKind;
    }
    public void setFiltroListaLaserKind(List<String> filtroListaLaserKind) {
        this.filtroListaLaserKind = filtroListaLaserKind;
    }
    public java.util.Date getFiltroDataDa() {
        return filtroDataDa;
    }
    public void setFiltroDataDa(java.util.Date filtroDataDa) {
        this.filtroDataDa = filtroDataDa;
    }
    public java.util.Date getFiltroDataA() {
        return filtroDataA;
    }
    public void setFiltroDataA(java.util.Date filtroDataA) {
        this.filtroDataA = filtroDataA;
    }
    public boolean isFiltroStampaInErrore() {
        return filtroStampaInErrore;
    }
    public void setFiltroStampaInErrore(boolean filtroStampaInErrore) {
        this.filtroStampaInErrore = filtroStampaInErrore;
    }
    public String getFiltroMacchinaSelezionata() {
        return filtroMacchinaSelezionata;
    }
    public void setFiltroMacchinaSelezionata(String filtroMacchinaSelezionata) {
        this.filtroMacchinaSelezionata = filtroMacchinaSelezionata;
    }
    public int getFiltroLaserKindSelezionato() {
        return filtroLaserKindSelezionato;
    }
    public void setFiltroLaserKindSelezionato(int filtroLaserKindSelezionato) {
        this.filtroLaserKindSelezionato = filtroLaserKindSelezionato;
    }

}
