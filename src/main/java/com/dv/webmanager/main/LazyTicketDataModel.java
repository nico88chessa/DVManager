package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.MyBatisSystemException;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;
import com.dv.webmanager.main.Constants.LaserKind;
import com.dv.webmanager.main.Constants.PrintStatus;

public class LazyTicketDataModel extends LazyDataModel<WebTicket> {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(LazyTicketDataModel.class);

    private GestioneMacchineBean gestioneMacchinaBean;

    private List<Ticket> listaTicket;
    private TicketMapper ticketMapper;
    private TicketFilter filtro;

    public LazyTicketDataModel(
            GestioneMacchineBean gestioneMacchinaBean,
            TicketMapper ticketMapper,
            TicketFilter filtro) {

        super();
        this.gestioneMacchinaBean = gestioneMacchinaBean;
        this.ticketMapper = ticketMapper;
        this.filtro = filtro;
    }

    public List<WebTicket> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        logger.trace("Enter");

        if (filtro.getMachineList().isEmpty()) {
            this.setRowCount(0);
            return Collections.<WebTicket>emptyList();
        }

        // questa query la faccio sempre se c'e' almeno una macchina in DB

        int ticketCount;
        try {
            ticketCount = ticketMapper.selectTicketCount(filtro);
        } catch (MyBatisSystemException e) {
            logger.error(e.getMessage());
            return Collections.<WebTicket>emptyList();
        }

        this.setRowCount(ticketCount);
        try {
            listaTicket = ticketMapper.selectTicketLimit(filtro, pageSize, first);
        } catch (MyBatisSystemException e) {
            logger.error(e.getMessage());
            return Collections.<WebTicket>emptyList();
        }

        List<Ticket> list2Show = listaTicket;
        List<WebTicket> webTicketList = new ArrayList<WebTicket>();
        for (Ticket t: list2Show)
            webTicketList.add(this.getWebTicketFromTicket(t));

        logger.trace("Exit");

        return webTicketList;

    }

    public WebTicket getRowData(String rowKey) {

        if (rowKey==null || rowKey.isEmpty())
            return new WebTicket();

        for (Ticket ticket: listaTicket)
            if (getTicketKey(ticket).equals(rowKey))
                return getWebTicketFromTicket(ticket);

        return new WebTicket();

    }

    public Object getRowKey(WebTicket ticket) {
        return this.getTicketKey(ticket);
    }

    private String getTicketKey(Ticket ticket) {
        return ticket.getIdM()+"-"+ticket.getIdT();
    }

    private String getTicketKey(WebTicket ticket) {
        return ticket.getIdM()+"-"+ticket.getIdT();
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
