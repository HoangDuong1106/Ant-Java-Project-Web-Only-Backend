/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.listener;

import duongmh.constraint.Constraint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author admin
 */
public class RoadMapServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

// create map 
        Map<String, String> roadmap = new HashMap<String, String>();

        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/") + "WEB-INF\\" + Constraint.ROAD_MAP_TXT_FILE_NAME;
        System.out.println("real " + realPath);
        FileReader fr = null;
        BufferedReader bf = null;
        // read file and load to map 
        try {
            File f = new File(realPath);
            if (!f.exists()) {
//                    context.log("File Not Found Or Wrong File Path");
                return;
            }
            fr = new FileReader(f);
            bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, Constraint.TOKEN_SPLIT);
                String name = stk.nextToken();
                String value = stk.nextToken();
                // add into RoadMap
                roadmap.put(name, value);
            }
            context.setAttribute(Constraint.ROAD_MAP_ATTRIBUTE_NAME, roadmap);
//            HashMap<String, String> map = (HashMap<String, String>) context.getAttribute("ROADMAP");
//            for (String key : map.keySet()) {
//                System.out.println(key + " : " + map.get(key));
//            }

        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            context.removeAttribute(Constraint.ROAD_MAP_ATTRIBUTE_NAME);
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
        }
    }

}
