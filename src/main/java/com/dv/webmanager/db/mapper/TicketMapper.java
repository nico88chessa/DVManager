package com.dv.webmanager.db.mapper;

import java.util.List;

import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;

public interface TicketMapper {
 
    public List<Ticket> selectAllTickets();
    
    public List<Machine> selectMachine();

    
}
