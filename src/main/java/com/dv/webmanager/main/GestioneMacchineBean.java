
package com.dv.webmanager.main;

import java.util.List;

import com.dv.webmanager.db.bean.Machine;

public class GestioneMacchineBean {

    public static final String globalGrowId = "globalGrow";

    // attributi per la pagina di gestione macchine
    private List<Machine> listaMacchine;
    private Machine nuovaMacchina;
    private Machine macchinaSelezionata;
    private String ipRisultato;


    // getters and setters
    public List<Machine> getListaMacchine() {
        return listaMacchine;
    }
    public void setListaMacchine(List<Machine> listaMacchine) {
        this.listaMacchine = listaMacchine;
    }
    public Machine getNuovaMacchina() {
        return nuovaMacchina;
    }
    public void setNuovaMacchina(Machine nuovaMacchina) {
        this.nuovaMacchina = nuovaMacchina;
    }
    public Machine getMacchinaSelezionata() {
        return macchinaSelezionata;
    }
    public void setMacchinaSelezionata(Machine macchinaSelezionata) {
        this.macchinaSelezionata = macchinaSelezionata;
    }
    public String getIpRisultato() {
        return ipRisultato;
    }
    public void setIpRisultato(String ipRisultato) {
        this.ipRisultato = ipRisultato;
    }




}
