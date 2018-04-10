package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;
import com.dv.webmanager.main.Constants.LaserKind;
import com.dv.webmanager.main.Constants.PrintStatus;

public class VisualizzazioneTicketCtrl {

    private static final int NO_MACHINE = -1;

    public void init() {
        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");
        GestioneMacchineBean gestioneMacchinaBean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");

        this.aggiornaListaMacchineFiltro();

        List<LaserKind> listaLaserKind = new ArrayList<LaserKind>();
        for (LaserKind item: Constants.LaserKind.values()) {
            if (item == LaserKind.NONE)
                continue;
            listaLaserKind.add(item);
        }

        List<PrintStatus> listaTicketStatus = new ArrayList<PrintStatus>();
        for (PrintStatus item: Constants.PrintStatus.values()) {
            if (item == PrintStatus.NONE)
                continue;
            listaTicketStatus.add(item);
        }

        visualizzazioneTicketBean.setFiltroNomefile(null);
        visualizzazioneTicketBean.setFiltroListaLaserKind(listaLaserKind);
        visualizzazioneTicketBean.setFiltroDataDa(null);
        visualizzazioneTicketBean.setFiltroDataA(null);
        visualizzazioneTicketBean.setFiltroListaStatoTicket(listaTicketStatus);

        visualizzazioneTicketBean.setFiltroMacchinaSelezionata(NO_MACHINE);
        visualizzazioneTicketBean.setFiltroLaserKindSelezionato(LaserKind.NONE.getCode());

        visualizzazioneTicketBean.setFiltroStatoTicketSelezionato(new int[]{});
        visualizzazioneTicketBean.setRighePerPagina(20);

        // inizialmente faccio vedere la lista dei ticket non filtrata
        TicketFilter filtro = new TicketFilter();
        filtro.setIdMachine(NO_MACHINE);
        filtro.setFilename(null);
        filtro.setDateStart(null);
        filtro.setDateEnd(null);
        filtro.setLaserKind(LaserKind.NONE.getCode());
        filtro.setPrintStatus(null);
        LazyTicketDataModel lazyTicketList = new LazyTicketDataModel(gestioneMacchinaBean, mapper, filtro);
        visualizzazioneTicketBean.setListaTicket(lazyTicketList);

    }

    public void aggiornaListaMacchineFiltro() {
        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");
        GestioneMacchineBean gestioneMacchineBean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");

        List<Machine> listaMacchine = new ArrayList<Machine>();
        listaMacchine.addAll(gestioneMacchineBean.getListaMacchine());
        visualizzazioneTicketBean.setFiltroListaMacchine(listaMacchine);

    }

    public void filtraTicket() {

        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");
        GestioneMacchineBean gestioneMacchinaBean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");

        TicketFilter filtro = new TicketFilter();
        filtro.setIdMachine(visualizzazioneTicketBean.getFiltroMacchinaSelezionata());

        String nomefile = visualizzazioneTicketBean.getFiltroNomefile();
        if (nomefile!=null && !nomefile.isEmpty())
            filtro.setFilename(nomefile);
        else
            filtro.setFilename(null);
        filtro.setDateStart(visualizzazioneTicketBean.getFiltroDataDa());
        filtro.setDateEnd(visualizzazioneTicketBean.getFiltroDataA());
        filtro.setLaserKind(visualizzazioneTicketBean.getFiltroLaserKindSelezionato());
        filtro.setPrintStatus(visualizzazioneTicketBean.getFiltroStatoTicketSelezionato());

        LazyTicketDataModel lazyTicketList = new LazyTicketDataModel(gestioneMacchinaBean, mapper, filtro);

        visualizzazioneTicketBean.setListaTicket(lazyTicketList);

        // qui resetto l'indice della tabella a 0 (faccio partire la tabella dall'inizio)
        UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
        DataTable dataTable;
        dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("listaTicketForm:tabellaTicketDT");
        dataTable.reset();
    }

}