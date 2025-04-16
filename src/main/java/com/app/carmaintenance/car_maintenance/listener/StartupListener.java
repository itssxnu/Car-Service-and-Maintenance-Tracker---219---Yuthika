package com.app.carmaintenance.car_maintenance.listener;

import com.app.carmaintenance.car_maintenance.util.Config;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Config.init(sce.getServletContext());
        System.out.println("Application started. Data directories initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
