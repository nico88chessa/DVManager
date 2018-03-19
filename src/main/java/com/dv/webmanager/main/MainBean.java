package com.dv.webmanager.main;

import java.util.Date;
import java.util.List;

public class MainBean {

    private String name;
    private boolean showDBManagement;
    private boolean showUserManagement;
    private boolean showDBData;
    private List<MainBean.Machine> machines;
    private MainBean.Machine selectedMachine;
    private boolean pageLoaded;
    

    

    public boolean isShowDBData() {
        return showDBData;
    }

    public void setShowDBData(boolean showDBData) {
        this.showDBData = showDBData;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isShowDBManagement() {
        return showDBManagement;
    }
    
    public void setShowDBManagement(boolean showDBManagement) {
        this.showDBManagement = showDBManagement;
    }
    
    public boolean isShowUserManagement() {
        return showUserManagement;
    }
    
    public void setShowUserManagement(boolean showUserManagement) {
        this.showUserManagement = showUserManagement;
    }

    public List<MainBean.Machine> getMachines() {
        return machines;
    }
    
    public void setMachines(List<MainBean.Machine> machines) {
        this.machines = machines;
    }
    
    public MainBean.Machine getSelectedMachine() {
        return selectedMachine;
    }

    public void setSelectedMachine(MainBean.Machine selectedMachine) {
        this.selectedMachine = selectedMachine;
        System.out.println("setSelectedMachine called...");
    }

    public boolean isPageLoaded() {
        return pageLoaded;
    }

    public void setPageLoaded(boolean pageLoaded) {
        this.pageLoaded = pageLoaded;
    }



    public static class Machine {
        
        private int id;
        private String ip;
        private Date lastUpdate;
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getIp() {
            return ip;
        }
        
        public void setIp(String ip) {
            this.ip = ip;
        }
        
        public Date getLastUpdate() {
            return lastUpdate;
        }
        
        public void setLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
        }
        
    }

    
}
