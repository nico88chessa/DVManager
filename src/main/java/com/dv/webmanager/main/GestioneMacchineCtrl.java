package com.dv.webmanager.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.mybatis.spring.MyBatisSystemException;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.bean.Ticket;
import com.dv.webmanager.db.bean.TicketFilter;
import com.dv.webmanager.db.mapper.TicketMapper;

public class GestioneMacchineCtrl {

    public void init() {

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");

        // attributi pagina gestione macchina
        bean.setNuovaMacchina(new Machine());
        bean.setIpRisultato(new String());
        this.updateMachineList();

    }

    // metodi utilizzati nella pagina di gestione macchine
    public void addNewMachineToDB() {

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");

        Machine nuovaMacchina = new Machine();
        nuovaMacchina.setName(bean.getNuovaMacchina().getName());
        nuovaMacchina.setIp(bean.getNuovaMacchina().getIp());
        nuovaMacchina.setLastUpdate(null);

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.insertMachine(nuovaMacchina);
        } catch (MyBatisSystemException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina inserita correttamente.", "") );

        this.updateMachineList();

    }

    public void updateMachineList() {

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        List<Machine> machines = null;
        try {
            machines = mapper.selectMachine();
        } catch (MyBatisSystemException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        bean.setListaMacchine(machines);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteMachineFromDB() {

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.deleteMachine(bean.getMacchinaSelezionata());
        } catch (MyBatisSystemException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina eliminata correttamente.", "") );

        this.updateMachineList();

    }

    public void clearNewMachineValues() {
        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        bean.setNuovaMacchina(new Machine());
    }

    public void pingMachine() {

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        bean.setIpRisultato("");

        String pingCmd = "ping -c4 " + bean.getMacchinaSelezionata().getIp();

        try {

            Process p = Runtime.getRuntime().exec(pingCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                bean.setIpRisultato(bean.getIpRisultato()+inputLine);
            in.close();

            bean.setIpRisultato(bean.getIpRisultato()+"\n\n");

        } catch (IOException ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
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
