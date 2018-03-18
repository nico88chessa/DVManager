package com.dv.webmanager.main;

import java.util.ArrayList;
import java.util.List;

import com.dv.webmanager.core.ApplicationContextAwareImpl;

public class MainCtrl {

    public void init() {

        MainBean bean = ApplicationContextAwareImpl.<MainBean>getBean("mainBean");

//        TicketMapper mapper = ApplicationContextAwareImpl.<TicketMapper>getBean("ticketMapper");
//        List<Machine> tickets = mapper.selectMachine();

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
