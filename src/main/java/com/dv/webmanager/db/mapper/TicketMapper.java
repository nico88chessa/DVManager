package com.dv.webmanager.db.mapper;

import java.util.List;

import org.mybatis.spring.MyBatisSystemException;

import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;

public interface TicketMapper {
 
    public List<Ticket> selectAllTickets() throws MyBatisSystemException;
    
    public List<Machine> selectMachine() throws MyBatisSystemException;

    public void insertMachine(Machine machine) throws MyBatisSystemException;
    
    public void deleteMachine(Machine machine) throws MyBatisSystemException;
    
}
