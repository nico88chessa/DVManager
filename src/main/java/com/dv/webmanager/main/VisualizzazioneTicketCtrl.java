package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.List;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;
import com.dv.webmanager.main.Constants.LaserKind;
import com.dv.webmanager.main.Constants.PrintStatus;

public class VisualizzazioneTicketCtrl {

    public void init() {
        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");

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

        visualizzazioneTicketBean.setFiltroMacchinaSelezionata(-1);
        visualizzazioneTicketBean.setFiltroLaserKindSelezionato(LaserKind.NONE.getCode());

        visualizzazioneTicketBean.setFiltroStatoTicketSelezionato(new int[]{});

        filtraTicket();

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
    }

}
