package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;
import com.dv.webmanager.main.Constants.LaserKind;
import com.dv.webmanager.main.Constants.PrintStatus;

public class VisualizzazioneTicketCtrl {

    private static final int NO_MACHINE = -1;

    public void init() {
        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");
        GestioneMacchineBean gestioneMacchinaBean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");


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

    }

    public void aggiornaListaMacchineFiltro() {
        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");
        GestioneMacchineBean gestioneMacchinaBean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");

        List<Machine> listaMacchine = new ArrayList<Machine>();
        listaMacchine.addAll(gestioneMacchinaBean.getListaMacchine());
        visualizzazioneTicketBean.setFiltroListaMacchine(listaMacchine);

        // inizialmente faccio vedere la lista dei ticket non filtrata
        TicketFilter filtro = new TicketFilter();
        filtro.setMachineList(visualizzazioneTicketBean.getFiltroListaMacchine());
        filtro.setIdMachine(NO_MACHINE);
        filtro.setFilename(null);
        filtro.setDateStart(null);
        filtro.setDateEnd(null);
        filtro.setLaserKind(LaserKind.NONE.getCode());
        filtro.setPrintStatus(null);
        LazyTicketDataModel lazyTicketList = new LazyTicketDataModel(gestioneMacchinaBean, mapper, filtro);
        visualizzazioneTicketBean.setListaTicket(lazyTicketList);

        // Ticket selezionato vuoto
        visualizzazioneTicketBean.setTicketSelezionato(new WebTicket());
    }

    public void filtraTicket() {

        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");
        GestioneMacchineBean gestioneMacchinaBean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");

        TicketFilter filtro = new TicketFilter();
        filtro.setMachineList(visualizzazioneTicketBean.getFiltroListaMacchine());
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
        DataTable dataTable = (DataTable) root.findComponent("listaTicketForm:tabellaTicketDT");
        dataTable.reset();
    }

    public void aggiornaNoteTicket() {

        VisualizzazioneTicketBean visualizzazioneTicketBean = ApplicationContextAwareImpl.<VisualizzazioneTicketBean>getBean("visualizzazioneTicketBean");

        WebTicket ticketSelezionato = visualizzazioneTicketBean.getTicketSelezionato();
        if (ticketSelezionato == null)
            return;

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        Ticket ticket = new Ticket();

        ticket.setIdM(ticketSelezionato.getIdM());
        ticket.setIdT(ticketSelezionato.getIdT());
        ticket.setQueueActivated(ticketSelezionato.getQueueActivated());
        ticket.setQueuePosition(ticketSelezionato.getQueuePosition());
        ticket.setWorkId(ticketSelezionato.getWorkId());
        ticket.setFileName(ticketSelezionato.getFileName());
        ticket.setFileLength(ticketSelezionato.getFileLength());
        ticket.setFileHeight(ticketSelezionato.getFileHeight());
        ticket.setFilePixelX(ticketSelezionato.getFilePixelX());
        ticket.setFilePixelY(ticketSelezionato.getFilePixelY());
        ticket.setFileResolutionX(ticketSelezionato.getFileResolutionX());
        ticket.setFileResolutionY(ticketSelezionato.getFileResolutionY());
        ticket.setFileTotalpixelX(ticketSelezionato.getFileTotalpixelX());
        ticket.setFileTotalpixelY(ticketSelezionato.getFileTotalpixelY());
        ticket.setFileTotalsizex(ticketSelezionato.getFileTotalsizex());
        ticket.setFileTotalsizey(ticketSelezionato.getFileTotalsizey());
        ticket.setCylinderLength(ticketSelezionato.getCylinderLength());
        ticket.setCylinderDiameter(ticketSelezionato.getCylinderDiameter());
        ticket.setCylinderStartAt(ticketSelezionato.getCylinderStartAt());
        ticket.setCylinderCrossAt(ticketSelezionato.getCylinderCrossAt());
        ticket.setLaserKind(ticketSelezionato.getLaserKind().getCode());
        ticket.setLaserSources(ticketSelezionato.getLaserSources());
        ticket.setLaserMinPower(ticketSelezionato.getLaserMinPower());
        ticket.setLaserMaxPower(ticketSelezionato.getLaserMaxPower());
        ticket.setLaserShots(ticketSelezionato.getLaserShots());
        ticket.setLaserPeriod(ticketSelezionato.getLaserPeriod());
        ticket.setSetupCalibration(ticketSelezionato.getSetupCalibration());
        ticket.setSetupOffsetX(ticketSelezionato.getSetupOffsetX());
        ticket.setSetupOffsetY(ticketSelezionato.getSetupOffsetY());
        ticket.setSetupZPresent(ticketSelezionato.getSetupZPresent());
        ticket.setSetupZPosition(ticketSelezionato.getSetupZPosition());
        ticket.setSetupFocalPresent(ticketSelezionato.getSetupFocalPresent());
        ticket.setSetupFocalPosition(ticketSelezionato.getSetupFocalPosition());
        ticket.setSetupSpeed(ticketSelezionato.getSetupSpeed());
        ticket.setSetupEngravingMode(ticketSelezionato.getSetupEngravingMode());
        ticket.setPrintStartAt(ticketSelezionato.getPrintStartAt());
        ticket.setPrintStopAt(ticketSelezionato.getPrintStopAt());
        ticket.setPrintStatus(ticketSelezionato.getPrintStatus().getCode());
        ticket.setPrintError(ticketSelezionato.getPrintError());
        ticket.setNotes(ticketSelezionato.getNotes());

        mapper.updateNoteTicket(ticket);

    }

}