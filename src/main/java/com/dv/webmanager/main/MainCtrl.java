package com.dv.webmanager.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.mybatis.spring.MyBatisSystemException;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;
import com.dv.webmanager.main.Constants.LaserKind;

public class MainCtrl {

    public void init() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

        // attributi pagina gestione macchina
        bean.setNewMachine(new Machine());
        bean.setIpCommandResult(new String());
        this.updateMachineList();

        // attributi pagina dati
        List<String> filtroListaMacchine = new ArrayList<String>();
        for (Machine m: bean.getMachines())
            filtroListaMacchine.add(m.getName());

        List<String> filtroListaLaserKind = new ArrayList<String>();
        for (LaserKind lk : Constants.LaserKind.values())
            filtroListaLaserKind.add(lk.getName());

        bean.setFiltroListaMacchine(filtroListaMacchine);
        bean.setFiltroListaLaserKind(filtroListaLaserKind);
        bean.setFiltroDataA(Calendar.getInstance().getTime());
        bean.setFiltroDataDa(Calendar.getInstance().getTime());
        bean.setFiltroStampaInErrore(false);



    }

    // metodi utilizzati nella pagina di gestione macchine
    public void addNewMachineToDB() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

        Machine newMachine = new Machine();
        newMachine.setName(bean.getNewMachine().getName());
        newMachine.setIp(bean.getNewMachine().getIp());
        newMachine.setLastUpdate(null);

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.insertMachine(newMachine);
        } catch (MyBatisSystemException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(MainBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(MainBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina inserita correttamente.", "") );

        this.updateMachineList();

    }

    public void updateMachineList() {

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        List<Machine> machines = null;
        try {
            machines = mapper.selectMachine();
        } catch (MyBatisSystemException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(MainBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");
        bean.setMachines(machines);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteMachineFromDB() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.deleteMachine(bean.getSelectedMachine());
        } catch (MyBatisSystemException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(MainBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(MainBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina eliminata correttamente.", "") );

        this.updateMachineList();

    }

    public void clearNewMachineValues() {
        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");
        bean.setNewMachine(new Machine());
    }

    public void pingMachine() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");
        bean.setIpCommandResult("");

        String pingCmd = "ping -c4 " + bean.getSelectedMachine().getIp();

        try {

            Process p = Runtime.getRuntime().exec(pingCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                bean.setIpCommandResult(bean.getIpCommandResult()+inputLine);
            in.close();

            bean.setIpCommandResult(bean.getIpCommandResult()+"\n\n");

        } catch (IOException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(MainBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
        }

    }


    // metodi utilizzati nella pagine di ricerca stampe
    public void filtraTicket() {
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");

        TicketFilter ticketFilter = new TicketFilter();

        List<Ticket> tickets = mapper.selectTicket(ticketFilter);

        System.out.println(tickets.size());

    }

}
