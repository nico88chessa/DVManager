package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;
import com.dv.webmanager.main.Constants.LaserKind;
import com.dv.webmanager.main.Constants.PrintStatus;

public class LazyTicketDataModel extends LazyDataModel<WebTicket> {

    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE_BUFFER = 3;

    private GestioneMacchineBean gestioneMacchinaBean;

    private List<Ticket> listaTicket;
    private TicketMapper ticketMapper;
    private TicketFilter filtro;

    private int lastDBPageSize; // dimensione di pagina
    private int lastDBStartIndex; // indice di partenza query db
    private int lastDBBufferSize; // dimensione buffer query

    public LazyTicketDataModel(
            GestioneMacchineBean gestioneMacchinaBean,
            TicketMapper ticketMapper,
            TicketFilter filtro) {

        super();
        this.gestioneMacchinaBean = gestioneMacchinaBean;
        this.ticketMapper = ticketMapper;
        this.filtro = filtro;
        this.lastDBPageSize = -1;
        this.lastDBStartIndex = -1;
        this.lastDBBufferSize = 0;

    }

    public List<WebTicket> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        // questa query la faccio sempre
        int ticketCount = ticketMapper.selectTicketCount(filtro);
        this.setRowCount(ticketCount);

        if ( (this.lastDBPageSize != pageSize) ||                           // se ho cambiato dimensione della pagina
                (first >= (lastDBStartIndex + this.lastDBPageSize*(PAGE_SIZE_BUFFER)) ) ||
                (first < lastDBStartIndex) ) {  // oppure l'indice della pagina non e' compreso nel buffer

            // questo serve per gestire il caso di filtraggio quando ho gia' cambiato pagina
            if (this.lastDBPageSize == -1)
                first = 0;

            // faccio la query a DB
            this.lastDBPageSize = pageSize;
            this.lastDBBufferSize = this.lastDBPageSize * PAGE_SIZE_BUFFER;
            this.lastDBStartIndex = first/lastDBBufferSize * lastDBBufferSize;

            listaTicket = ticketMapper.selectTicketLimit(filtro, this.lastDBBufferSize, lastDBStartIndex);

        }

        int sublistIndexFrom = first % this.lastDBBufferSize;
        int sublistIndexTo = sublistIndexFrom + this.lastDBPageSize;

        sublistIndexTo = Integer.min(sublistIndexTo, listaTicket.size());

        List<Ticket> list2Show = listaTicket.subList(sublistIndexFrom, sublistIndexTo);

        List<WebTicket> webTicketList = new ArrayList<WebTicket>();
        for (Ticket t: list2Show)
            webTicketList.add(this.getWebTicketFromTicket(t));

        return webTicketList;

    }

    public WebTicket getRowData(String rowKey) {
        return null;
    }

    public Object getRowKey(WebTicket ticket) {
        return null;
    }

    private WebTicket getWebTicketFromTicket(Ticket ticket) {
        WebTicket webTicket = new WebTicket();

        webTicket.setIdM(ticket.getIdM());
        webTicket.setMachineName("");
        for (Machine m: gestioneMacchinaBean.getListaMacchine())
            if (m.getId() == ticket.getIdM())
                webTicket.setMachineName(m.getName());

        webTicket.setIdT(ticket.getIdT());
        webTicket.setQueueActivated(ticket.getQueueActivated());
        webTicket.setQueuePosition(ticket.getQueuePosition());
        webTicket.setWorkId(ticket.getWorkId());
        webTicket.setFileName(ticket.getFileName());
        webTicket.setFileLength(ticket.getFileLength());
        webTicket.setFileHeight(ticket.getFileHeight());
        webTicket.setFilePixelX(ticket.getFilePixelX());
        webTicket.setFilePixelY(ticket.getFilePixelY());
        webTicket.setFileResolutionX(ticket.getFileResolutionX());
        webTicket.setFileResolutionY(ticket.getFileResolutionY());
        webTicket.setFileTotalpixelX(ticket.getFileTotalpixelX());
        webTicket.setFileTotalpixelY(ticket.getFileTotalpixelY());
        webTicket.setFileTotalsizex(ticket.getFileTotalsizex());
        webTicket.setFileTotalsizey(ticket.getFileTotalsizey());
        webTicket.setCylinderLength(ticket.getCylinderLength());
        webTicket.setCylinderDiameter(ticket.getCylinderDiameter());
        webTicket.setCylinderStartAt(ticket.getCylinderStartAt());
        webTicket.setCylinderCrossAt(ticket.getCylinderCrossAt());

        webTicket.setLaserKind(LaserKind.valueFromInt(ticket.getLaserKind()));

        webTicket.setLaserSources(ticket.getLaserSources());
        webTicket.setLaserMinPower(ticket.getLaserMinPower());
        webTicket.setLaserMaxPower(ticket.getLaserMaxPower());
        webTicket.setLaserShots(ticket.getLaserShots());
        webTicket.setLaserPeriod(ticket.getLaserPeriod());
        webTicket.setSetupCalibration(ticket.getSetupCalibration());
        webTicket.setSetupOffsetX(ticket.getSetupOffsetX());
        webTicket.setSetupOffsetY(ticket.getSetupOffsetY());
        webTicket.setSetupZPresent(ticket.getSetupZPresent());
        webTicket.setSetupZPosition(ticket.getSetupZPosition());
        webTicket.setSetupFocalPresent(ticket.getSetupFocalPresent());
        webTicket.setSetupFocalPosition(ticket.getSetupFocalPosition());
        webTicket.setSetupSpeed(ticket.getSetupSpeed());
        webTicket.setSetupEngravingMode(ticket.getSetupEngravingMode());
        webTicket.setPrintStartAt(ticket.getPrintStartAt());
        webTicket.setPrintStopAt(ticket.getPrintStopAt());

        webTicket.setPrintStatus(PrintStatus.valueFromInt(ticket.getPrintStatus()));

        webTicket.setPrintError(ticket.getPrintError());
        webTicket.setNotes(ticket.getNotes());


        return webTicket;
    }

}
