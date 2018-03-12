package com.dv.webmanager.main;

public class MainData {

    private String name;
    private boolean renderDBManagement;
    private boolean renderUserManagement;
    
    
    public boolean getRenderUserManagement() {
        return renderUserManagement;
    }

    public void setRenderUserManagement(boolean renderUserManagement) {
        this.renderUserManagement = renderUserManagement;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean getRenderDBManagement() {
        return renderDBManagement;
    }
    
    public void setRenderDBManagement(boolean renderDBManagement) {
        this.renderDBManagement = renderDBManagement;
    }
    

}
