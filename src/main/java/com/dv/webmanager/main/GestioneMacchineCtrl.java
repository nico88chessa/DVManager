package com.dv.webmanager.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.mapper.TicketMapper;

public class GestioneMacchineCtrl {

    private static final Logger logger = LoggerFactory.getLogger(GestioneMacchineCtrl.class);

    public void init() {

        logger.trace("Enter");

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");

        // attributi pagina gestione macchina
        bean.setNuovaMacchina(new Machine());
        bean.setIpRisultato(new String());
        this.updateMachineList();

        logger.trace("Exit");

    }

    // metodi utilizzati nella pagina di gestione macchine
    public void addNewMachineToDB() {

        logger.trace("Enter");

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");

        Machine nuovaMacchina = new Machine();
        nuovaMacchina.setName(bean.getNuovaMacchina().getName());
        nuovaMacchina.setIp(bean.getNuovaMacchina().getIp());
        nuovaMacchina.setLastUpdate(null);

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.insertMachine(nuovaMacchina);
        } catch (MyBatisSystemException ex) {

            logger.error(ex.getMessage());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina inserita correttamente.", "") );

        this.updateMachineList();

        logger.trace("Exit");

    }

    public void updateMachineList() {

        logger.trace("Enter");

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        List<Machine> machines = null;
        try {
            machines = mapper.selectMachine();
        } catch (MyBatisSystemException ex) {
            logger.error(ex.getMessage());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        bean.setListaMacchine(machines);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        logger.trace("Exit");

    }

    public void deleteMachineFromDB() {

        logger.trace("Enter");

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.deleteMachine(bean.getMacchinaSelezionata());
        } catch (MyBatisSystemException ex) {
            logger.error(ex.getMessage());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina eliminata correttamente.", "") );

        this.updateMachineList();

        logger.trace("Exit");

    }

    public void modificaMacchinaInDB() {

        logger.trace("Enter");

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
        try {
            mapper.updateMachine(bean.getMacchinaSelezionata());
        } catch (MyBatisSystemException ex) {
            logger.error(ex.getMessage());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
            return;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_INFO, "Macchina modificata correttamente.", "") );

        this.updateMachineList();

        logger.trace("Exit");

    }

    public void clearNewMachineValues() {

        logger.trace("Enter");

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        bean.setNuovaMacchina(new Machine());

        logger.trace("Exit");
    }

    public void pingMachine() {

        logger.trace("Enter");

        GestioneMacchineBean bean = ApplicationContextAwareImpl.<GestioneMacchineBean>getBean("gestioneMacchineBean");
        bean.setIpRisultato("");

        String pingCmd = "ping -c4 " + bean.getMacchinaSelezionata().getIp();

        try {

            Process p = Runtime.getRuntime().exec(pingCmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                bean.setIpRisultato(bean.getIpRisultato()+inputLine);
            in.close();

            bean.setIpRisultato(bean.getIpRisultato()+"\n\n");

        } catch (IOException ex) {
            logger.error(ex.getMessage());
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(GestioneMacchineBean.globalGrowId, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getCause().toString(), "") );
        }

        logger.trace("Exit");

    }

}
