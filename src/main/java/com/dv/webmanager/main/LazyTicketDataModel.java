package com.dv.webmanager.main;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;

public class LazyTicketDataModel extends LazyDataModel<Ticket> {

    private static final long serialVersionUID = 1L;

    private List<Ticket> listaTicket;
    private TicketMapper ticketMapper;
    private TicketFilter filtro;

    public LazyTicketDataModel(TicketMapper ticketMapper, TicketFilter filtro) {
        super();
        this.ticketMapper = ticketMapper;
        this.filtro = filtro;
    }

    public List<Ticket> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        listaTicket = ticketMapper.selectTicketLimit(filtro, pageSize, first);
        this.setRowCount(listaTicket.size());
        return listaTicket;
    }

    public List<Ticket> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        this.setRowCount(listaTicket.size());
        return listaTicket;
    }

    public Ticket getRowData(String rowKey) {
        return null;
    }

    public Object getRowKey(Ticket ticket) {
        return null;
    }

}
