package com.apql.Apql.listener;

import com.apql.Apql.controller.QueryController;
import com.apql.Apql.controller.ServiceController;
import com.apql.Apql.controller.ViewController;
import com.apql.Apql.highlight.ButtonAction;
import com.apql.Apql.highlight.Highlight;
import com.apql.Apql.progressBar.ProgressBarDialog;
import com.apql.Apql.table.TableProcess;
import org.apromore.filestore.client.DavFileSystemView;
import org.apromore.manager.client.ManagerService;
import org.apromore.model.Detail;
import org.apromore.model.ResultPQL;
import org.apromore.model.UserType;
import org.apromore.portal.client.PortalService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by corno on 26/07/2014.
 */
public class CommandListener implements ActionListener {
    private UserType user;
    private ManagerService manager;
    private PortalService portal;
    private QueryController queryController=QueryController.getQueryController();
    private ViewController viewController=ViewController.getController();

    public CommandListener(UserType user){
        this.user=user;
        this.manager= ServiceController.getManagerService();
        this.portal=ServiceController.getPortalService();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton radioButton;
        JButton button;
        JMenuItem menuItem;
        if(e.getSource() instanceof JRadioButton){
            radioButton=((JRadioButton)e.getSource());
            if(radioButton==viewController.getRadioButton(ViewController.ALLVERSIONS) && (viewController.getRadioButton(ViewController.LATESTVERSION).isSelected() || viewController.getRadioButton(ViewController.CHOOSEVERSION).isSelected())) {
                queryController.setVersion(ViewController.ALLVERSIONS);
                viewController.getRadioButton(ViewController.LATESTVERSION).setSelected(false);
                viewController.getRadioButton(ViewController.CHOOSEVERSION).setSelected(false);
                viewController.getFolderProcessTree().getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
                viewController.getFolderProcessTree().clearSelection();
                viewController.getTableProcess().cleanRows();
                viewController.getTableProcess().revalidate();
            }else if(radioButton==viewController.getRadioButton(ViewController.LATESTVERSION) && (viewController.getRadioButton(ViewController.ALLVERSIONS).isSelected() || viewController.getRadioButton(ViewController.CHOOSEVERSION).isSelected())) {
                queryController.setVersion(ViewController.LATESTVERSION);
                viewController.getRadioButton(ViewController.ALLVERSIONS).setSelected(false);
                viewController.getRadioButton(ViewController.CHOOSEVERSION).setSelected(false);
                viewController.getFolderProcessTree().getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
                viewController.getFolderProcessTree().clearSelection();
                viewController.getTableProcess().cleanRows();
                viewController.getTableProcess().revalidate();
            }else if(radioButton==viewController.getRadioButton(ViewController.CHOOSEVERSION) && (viewController.getRadioButton(ViewController.ALLVERSIONS).isSelected() || viewController.getRadioButton(ViewController.LATESTVERSION).isSelected())) {
                queryController.setVersion(ViewController.CHOOSEVERSION);
                viewController.getRadioButton(ViewController.ALLVERSIONS).setSelected(false);
                viewController.getRadioButton(ViewController.LATESTVERSION).setSelected(false);
                viewController.getFolderProcessTree().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                viewController.getFolderProcessTree().clearSelection();
                viewController.getTableProcess().cleanRows();
            }


        }else if(e.getSource() instanceof JButton){
            System.out.println("BUTTON");
            button = ((JButton)e.getSource());
            if(button.getText().equals(ViewController.OK)){
                try {

                    JDialog frame = new JDialog();
                    frame.setTitle("Query is running");
                    frame.setLocation(queryController.getTextPane().getLocationOnScreen());
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                    //Create and set up the content pane.
                    ProgressBarDialog newContentPane = new ProgressBarDialog(frame);
                    newContentPane.setOpaque(true); //content panes must be opaque
                    frame.setContentPane(newContentPane);

                    //Display the window.
                    frame.pack();
                    frame.setVisible(true);

                    long startTime=System.currentTimeMillis();
                    String variables = queryController.getVariablePane().getText();
                    String query = ButtonAction.CTRLBack.getQuery();


                    LinkedList<String> idsNets = new LinkedList<>(queryController.getIdNets());
                    List<String> results = manager.runAPQLExpression(variables + " " + query, idsNets, user.getId());
                    long endTime=System.currentTimeMillis();

                    if (!results.isEmpty() && !results.get(0).matches("([0-9]+[/]([0-9]+([.][0-9]+){1,2})[/][a-zA-Z0-9]+[;]?)+")) {
//                        frame.setModal(false);
                        newContentPane.setProgress(100);

                        JTextPane errorPane = queryController.getErrorPane();
                        errorPane.setText("");
                        StringBuilder sb = new StringBuilder();
                        for (String error : results) {
                            sb.append(error + "\n");
                        }
                        errorPane.setText(sb.toString());
                    } else if (!results.isEmpty() && results.get(0).matches("([0-9]+[/]([0-9]+([.][0-9]+){1,2})[/][a-zA-Z0-9]+[;]?)+") || results.isEmpty()) {
                        queryController.getErrorPane().setText("Query successfull in "+(endTime-startTime)+" msec;");
                        List<Detail> details = manager.getDetails();
                        System.out.println(details);
                        List<ResultPQL> pqlResults=queryController.buildResults(results);
                        portal.addNewTab(pqlResults,user.getId(),details,variables+"\n\n"+query,viewController.getNameQuery().getText());
                        newContentPane.setProgress(100);
                    }

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }else if(button.getText().equals(ViewController.CLEAR)) {
                queryController.getTextPane().setText("");
                queryController.getVariablePane().setText("");
                ButtonAction.CTRLBack.setExpand(true);
            }else if(button.getText().equals(ViewController.QUERIEDMODELS)){
                JDialog window = new JDialog();
                window.setTitle("Queried Process Models");
                window.setPreferredSize(new Dimension(800, 600));
                window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                window.setModal(true);
                window.setResizable(false);
                window.setLocation(queryController.getVariablePane().getLocationOnScreen());
                TableProcess queriedModels = new TableProcess(false);
                queriedModels.setHeader(160,35,"Name", "Id", "Original language", "Version", "Branch");
                queriedModels.setRows(160,35,queryController.getIdNets());
                JScrollPane pane=new JScrollPane(queriedModels);
                window.add(pane);
                window.pack();
                window.setVisible(true);
            }else if(button.getText().equals(ViewController.EXPAND)){
                System.out.println("ELSE expand EC");
                String query=queryController.getTextPane().getText();
                StringBuilder locations=new StringBuilder();
                ButtonAction.CTRLBack.setExpand(true);
                for(String str : ButtonAction.CTRLBack.getLocationInQuery()){
                    locations.append("\""+str+"\", ");
                }
                locations.delete(locations.length()-2,locations.length()-1);
                Highlight.getHighlight().highlight(queryController.getBeforeLoc()+" "+locations.toString()+" "+queryController.getAfterLoc());
            }else if(button.getText().equals(ViewController.COLLAPSE)){
                String query=queryController.getTextPane().getText();
                System.out.println("IF expand EC");
                ButtonAction.CTRLBack.setExpand(false);
                ButtonAction.CTRLBack.setQuery(query);
                ButtonAction.CTRLBack.setLocationInQuery(queryController.keepLocationInQuery());
                Highlight.getHighlight().highlight(queryController.getBeforeLoc()+" + "+queryController.getAfterLoc());
            }
        }else if(e.getSource() instanceof JMenuItem){
            menuItem = ((JMenuItem) e.getSource());
            if (menuItem.getText().equals(ViewController.LOAD)) {
                AccessController.doPrivileged(new PrivilegedAction() {//fornisce accesso privilegiato al filesystem
                    public Object run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            JFileChooser chooser = new JFileChooser();
//                            JFileChooser chooser = new JFileChooser(new DavFileSystemView(ServiceController.getFileStoreService()));
                            chooser.setDialogTitle("Load Query");
                            chooser.setFileFilter(new FileNameExtensionFilter("QUERY PQL", "pql"));
                            if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                                System.out.println("FILE: "+chooser.getSelectedFile());
                                File file = chooser.getSelectedFile();
                                Document doc = Jsoup.parse(file, "UTF-8");
                                Elements variables = doc.getElementsByTag("variables");
                                Elements query = doc.getElementsByTag("query");
                                queryController.getVariablePane().setText(variables.text());
                                Highlight.getHighlight().highlight(query.text());
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return null;
                    }
                });
            }else if(menuItem.getText().equals(ViewController.SAVE)){
                AccessController.doPrivileged(new PrivilegedAction() {//fornisce accesso privilegiato al filesystem
                    public Object run() {
                        PrintWriter pw;
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            JFileChooser chooser = new JFileChooser();
                            chooser.setDialogTitle("Save Query");
                            chooser.setFileFilter(new FileNameExtensionFilter("QUERY PQL", "pql"));
                            chooser.setSelectedFile(new File(viewController.getNameQuery().getText()));
                            if (chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                                System.out.println("FILE: "+chooser.getSelectedFile());
                                File file = new File(chooser.getSelectedFile().getAbsolutePath()+".pql");
                                pw = new PrintWriter(file);
                                pw.println("<variables>"+queryController.getVariablePane().getText()+"</variables>");
                                pw.println("<query>"+queryController.getTextPane().getText()+"</query>");
                                pw.close();
                            }

                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        return null;
                    }
                });
            }
        }

    }

    private void removeScrollFromPanel(){
        JPanel treeVersionPanel=viewController.getTreeVersionPanel();
        treeVersionPanel.setVisible(false);
        treeVersionPanel.remove(viewController.getResumeScroll());
        treeVersionPanel.setVisible(true);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.

    }
}
