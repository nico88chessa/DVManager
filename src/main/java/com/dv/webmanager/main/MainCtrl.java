package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.List;

import com.dv.webmanager.core.ApplicationContextAwareImpl;
import com.dv.webmanager.db.bean.Machine;
import com.dv.webmanager.db.mapper.TicketMapper;

public class MainCtrl {

    public void init() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

//        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
//        List<Machine> tickets = mapper.selectMachine();

        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");

        bean.setPageLoaded(true);

        Machine machine = new Machine();
        machine.setIp("192.168.1.254");
        machine.setLastUpdate(null);
        machine.setName("prova");

        mapper.insertMachine(machine);

        bean.setShowDBManagement(false);
        bean.setShowUserManagement(false);
        bean.setShowDBData(true);

        List<MainBean.Machine> machines = new ArrayList<MainBean.Machine>();
        for (int i=0; i<20; i++) {
            MainBean.Machine temp = new MainBean.Machine();
            temp.setId(i+1);
            temp.setIp("192.168.1."+(i+1));
            temp.setLastUpdate(new java.util.Date());
            machines.add(temp);
        }
        bean.setMachines(machines);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addNewMachineToDB() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

        String name = bean.getName();
        System.out.print(name);

        // inserire la macchine nel DB

    }

    public void showDBManagement() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

        bean.setShowDBManagement(true);
        bean.setShowUserManagement(false);
        bean.setShowDBData(false);

    }

    public void showUserManagement() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

        bean.setShowDBManagement(false);
        bean.setShowUserManagement(true);
        bean.setShowDBData(false);

    }

    public void showDBData() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

        bean.setShowDBManagement(false);
        bean.setShowUserManagement(false);
        bean.setShowDBData(true);

    }

}
