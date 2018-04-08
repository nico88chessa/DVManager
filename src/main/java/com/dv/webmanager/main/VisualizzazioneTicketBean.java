package com.dv.webmanager.main;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.main.Constants.LaserKind;
import com.dv.webmanager.main.Constants.PrintStatus;

public class VisualizzazioneTicketBean {

    // attributi dialog filtro
    private List<Machine> filtroListaMacchine;
    private String filtroNomefile;
    private List<LaserKind> filtroListaLaserKind;
    private java.util.Date filtroDataDa;
    private java.util.Date filtroDataA;
    private List<PrintStatus> filtroListaStatoTicket;
    private int filtroMacchinaSelezionata;
    private int filtroLaserKindSelezionato;
    private int[] filtroStatoTicketSelezionato;


    // attributi lista ticket
    private LazyDataModel<Ticket> listaTicket;
    private Ticket ticketSelezionato;


    // getters e setters
    public List<Machine> getFiltroListaMacchine() {
        return filtroListaMacchine;
    }
    public void setFiltroListaMacchine(List<Machine> filtroListaMacchine) {
        this.filtroListaMacchine = filtroListaMacchine;
    }
    public String getFiltroNomefile() {
        return filtroNomefile;
    }
    public void setFiltroNomefile(String filtroNomefile) {
        this.filtroNomefile = filtroNomefile;
    }
    public List<LaserKind> getFiltroListaLaserKind() {
        return filtroListaLaserKind;
    }
    public void setFiltroListaLaserKind(List<LaserKind> filtroListaLaserKind) {
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
    public List<PrintStatus> getFiltroListaStatoTicket() {
        return filtroListaStatoTicket;
    }
    public void setFiltroListaStatoTicket(List<PrintStatus> filtroListaStatoTicket) {
        this.filtroListaStatoTicket = filtroListaStatoTicket;
    }
    public int getFiltroMacchinaSelezionata() {
        return filtroMacchinaSelezionata;
    }
    public void setFiltroMacchinaSelezionata(int filtroMacchinaSelezionata) {
        this.filtroMacchinaSelezionata = filtroMacchinaSelezionata;
    }
    public int getFiltroLaserKindSelezionato() {
        return filtroLaserKindSelezionato;
    }
    public void setFiltroLaserKindSelezionato(int filtroLaserKindSelezionato) {
        this.filtroLaserKindSelezionato = filtroLaserKindSelezionato;
    }
    public int[] getFiltroStatoTicketSelezionato() {
        return filtroStatoTicketSelezionato;
    }
    public void setFiltroStatoTicketSelezionato(int[] filtroStatoTicketSelezionato) {
        this.filtroStatoTicketSelezionato = filtroStatoTicketSelezionato;
    }
    public LazyDataModel<Ticket> getListaTicket() {
        return listaTicket;
    }
    public void setListaTicket(LazyDataModel<Ticket> listaTicket) {
        this.listaTicket = listaTicket;
    }
    public Ticket getTicketSelezionato() {
        return ticketSelezionato;
    }
    public void setTicketSelezionato(Ticket ticketSelezionato) {
        this.ticketSelezionato = ticketSelezionato;
    }

}
