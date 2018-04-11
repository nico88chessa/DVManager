package com.dv.webmanager.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.MyBatisSystemException;

import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;

public interface TicketMapper {

    public void insertMachine(Machine machine) throws MyBatisSystemException;

    public void deleteMachine(Machine machine) throws MyBatisSystemException;

    public void updateNoteTicket(Ticket ticket) throws MyBatisSystemException;

    public List<Machine> selectMachine() throws MyBatisSystemException;

    public int selectMachineCount() throws MyBatisSystemException;

    public int selectTicketCount(TicketFilter ticketFilter) throws MyBatisSystemException;

    public List<Ticket> selectTicket(TicketFilter ticketFilter) throws MyBatisSystemException;

    public List<Ticket> selectTicketLimit(
            @Param("ticketFilter") TicketFilter ticketFilter,
            @Param("count") int count,
            @Param("offset") int offset) throws MyBatisSystemException;


}
