/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnv.listener;

import java.util.HashMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.Map;
import javax.servlet.ServletContext;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //lay context
        ServletContext context = sce.getServletContext();

        Map<String, String> listResource = new HashMap<>();
        String roadMapPath = context.getRealPath("/WEB-INF/roadMap.txt");
        File f = new File(roadMapPath);

        //load file va nap vao map
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String[] split;
            String action;
            String resource;
            String record;
            while ((record = br.readLine()) != null) {
                split = record.split("=");
                action = split[0];
                resource = split[1];
                context.log( action + "===" + resource);
               
                listResource.put(action, resource);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException ex) {
            context.log(ex.getMessage());

        } catch (IOException ex) {
            context.log(ex.getMessage());

        } catch (Exception ex) {
            context.log(ex.getMessage());
        }
        

        if (listResource.isEmpty()) {
            context.log(getClass() + "File Road Map is empty.Check again!");
        }

        // luu attribute
        context.setAttribute("ROAD_MAP", listResource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
