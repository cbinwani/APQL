package com.apql.Apql;

import com.apql.Apql.controller.QueryController;
import com.apql.Apql.controller.ViewController;
import org.apromore.model.UserType;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class APQLApplet extends JApplet {
	private static final long serialVersionUID = 8477785817089483519L;


  	public void init() {
		super.init();
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                private Main m;
                private QueryText query;
                private UserType user;
                private ViewController controller;
                private QueryController queryController;

                public void run(){
                    controller=ViewController.getController();
                    queryController=QueryController.getQueryController();
//                    controller.clear();
//                    queryController.clearQueryController();
                    controller.setUsername(APQLApplet.this.getParameter("user"));
                    controller.setIdSession(APQLApplet.this.getParameter("idSession"));
                    controller.setApplet(APQLApplet.this);
                    m=new Main();
                    APQLApplet.this.add(m);
                    APQLApplet.this.setMinimumSize(new Dimension(1000, 500));
                    try {
                        UIManager.setLookAndFeel(new ToolTipLookAndFeel());
                    }catch(Exception ex){

                    }
                }
            });
        }catch (Exception ex){

        }

//            try
//
//            {
//
//            }
//
//            catch(
//            Exception ex
//            )
//
//            {
//                System.err.println("ToolTipLookAndFeel exception!");
//                System.err.println(ex.getMessage());
//            }
//
//            setMinimumSize(new Dimension(1000, 500)
//
//            );
//
//            setLocation(200,200);
//
//            m=new
//
//            Main();
//
//            add(m);

        }

    public void destroy(){
        super.destroy();
    }

    class ToolTipLookAndFeel extends MetalLookAndFeel
	{
	    protected void initSystemColorDefaults(UIDefaults table)
	    {        
	        super.initSystemColorDefaults(table);        
	        table.put("info", new ColorUIResource(255, 247, 200));    
	    }

	    protected void initComponentDefaults(UIDefaults table) {
	        super.initComponentDefaults(table);

	    Border border = BorderFactory.createLineBorder(new Color(76,79,83));
	    table.put("ToolTip.border", border);
	    }
	}

}
