package com.apql.Apql;


import com.apql.Apql.controller.QueryController;
import com.apql.Apql.controller.ServiceController;
import com.apql.Apql.controller.ViewController;
import com.apql.Apql.highlight.Highlight;
import com.apql.Apql.highlight.Keywords;
import com.apql.Apql.history.QueueHistory;
import com.apql.Apql.listener.CommandListener;
import com.apql.Apql.table.TableProcess;
import com.apql.Apql.tree.FPTreeSelectionListener;
import com.apql.Apql.tree.FolderProcessRenderer;
import com.apql.Apql.tree.FolderProcessTree;
import org.apromore.manager.client.ManagerService;
import org.apromore.model.*;

import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class Main extends JPanel {

    private int fromPane = 40;
    private SpringLayout layout;
    private JButton ok;
    private JButton clear;
    private JButton showTableIds;
    private JScrollPane scrollError;
    private JScrollPane scrollQuery;
    private JScrollPane scrollVariables;
    private JScrollPane scrollTree;
    private JScrollPane scrollResume;
    private QueryText query;
    private VariableText variables;
    private JTextPane error;
    private ViewController viewController;
    private QueryController queryController;
    private UserType user;
    private ManagerService manager;
    private JLabel folders;
    private JLabel variablesLabel;
    private JLabel queryLabel;
    private JLabel errorLog;
    private JRadioButton allVersion;
    private JRadioButton latestVersion;
    private JRadioButton chooseVersion;
    private CommandListener command;
    private JMenuBar menuBar;
    private JMenu loadSave;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem loadWebDav;
    private JMenuItem saveWebDav;
    private Icon expandIcon;
    private JPanel treeVersionPanel;
    private JTextField nameQuery;
    private JLabel nameQueryLabel;
    private JSplitPane splitPane;
    private TableProcess tableProcess;
    private JButton expand;
    private JButton collapse;

    public Main() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        viewController=ViewController.getController();
        queryController=QueryController.getQueryController();
        queryController.clearQueryController();
        setPreferredSize(new Dimension(1000, 500));
        this.layout = new SpringLayout();

        query = new QueryText();
        variables = new VariableText();
        error = new JTextPane();
        error.setEditable(false);
        error.setPreferredSize(new Dimension(150, 150));
        Keywords.createList();
        QueueHistory queueHistory=new QueueHistory(20);


        manager = ServiceController.getManagerService();

        user = manager.readUserByUsername(viewController.getUsername());
        command=new CommandListener(user);

        FolderProcessTree fpt = new FolderProcessTree();

        DefaultTreeModel modelTree=new DefaultTreeModel(fpt.createTree());

        fpt.setModel(modelTree);
        fpt.setDragEnabled(true);
        fpt.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        fpt.setCellRenderer(new FolderProcessRenderer());
//        fpt.setPreferredSize(new Dimension(250, 400));
        fpt.addTreeSelectionListener(new FPTreeSelectionListener());

        folders = new JLabel("<html><b>Folders</b></html>");
        variablesLabel = new JLabel("<html><b>Variables</b></html>");
        queryLabel = new JLabel("<html><b>Query</b></html>");
        errorLog = new JLabel("<html><b>Log</b></html>");

        scrollError = new JScrollPane(error);
        scrollQuery = new JScrollPane(query);
        scrollVariables = new JScrollPane(variables);
        scrollTree = new JScrollPane(fpt);
//        scrollTree.setMaximumSize(new Dimension(250, 600));
        scrollError.setPreferredSize(new Dimension(200, 100));

        tableProcess=new TableProcess();
//        tableProcess.setPreferredSize(new Dimension(250,200));
//        tableProcess.setMaximumSize(new Dimension(250,200));
        tableProcess.setHeader(133,35," Branch", " Version", " Last Update");
        tableProcess.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,scrollTree,tableProcess);
        splitPane.setMaximumSize(new Dimension(400, 600));
        splitPane.setPreferredSize(new Dimension(400, 600));
        splitPane.setMinimumSize(new Dimension(400, 600));
        splitPane.setBackground(Color.WHITE.brighter());
        splitPane.setDividerLocation(300);

        allVersion =new JRadioButton(ViewController.ALLVERSIONS);
        latestVersion =new JRadioButton(ViewController.LATESTVERSION);
        chooseVersion = new JRadioButton(ViewController.CHOOSEVERSION);
        latestVersion.setSelected(true);

        nameQuery=new JTextField();
        nameQuery.setPreferredSize(new Dimension(300,25));
        nameQuery.setMinimumSize(new Dimension(300, 25));
        nameQuery.setMaximumSize(new Dimension(300, 25));
        nameQueryLabel=new JLabel("<html><b>Query name</b></html>");

        viewController.setTreeVersionPanel(treeVersionPanel);
        viewController.setFolderProcessTree(fpt);
        viewController.setAllVersion(allVersion);
        viewController.setLatestVersion(latestVersion);
        viewController.setChooseVersion(chooseVersion);
        viewController.setUserID(user.getId());
        viewController.setNameQuery(nameQuery);
        viewController.setTableProcess(tableProcess);
        queryController.setErrorPane(error);
        queryController.setVersion(ViewController.LATESTVERSION);
        queryController.settextPane(query);
        queryController.setQueueHistory(queueHistory);

        menuBar=new JMenuBar();

        loadSave=new JMenu(ViewController.FILE);
        load=new JMenuItem(ViewController.LOAD);
        save=new JMenuItem(ViewController.SAVE);

        loadSave.add(load);
        loadSave.add(save);
        menuBar.add(loadSave);

        expandIcon=new ImageIcon("/icons/expand.png");
//        viewController.setMainPanel(this);

        //FOLDERS LABEL
        layout.putConstraint(SpringLayout.NORTH, folders, 25,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, folders, 10, SpringLayout.WEST,
                this);
        //TREE FOLDER
        layout.putConstraint(SpringLayout.NORTH, splitPane, 5,
                SpringLayout.SOUTH, folders);
        layout.putConstraint(SpringLayout.WEST, splitPane, 10, SpringLayout.WEST,
                this);

        buildTextAreas();

        buildButton();

        buildRadioButton();

        //LOG LABEL
        layout.putConstraint(SpringLayout.NORTH, errorLog, 5,
                SpringLayout.SOUTH, scrollQuery);
        layout.putConstraint(SpringLayout.WEST, errorLog, 25,
                SpringLayout.EAST, showTableIds);

        layout.putConstraint(SpringLayout.NORTH, scrollError, 5, SpringLayout.SOUTH,
                errorLog);
        layout.putConstraint(SpringLayout.WEST, scrollError, 25, SpringLayout.EAST, showTableIds);
        layout.putConstraint(SpringLayout.EAST, scrollQuery, 0, SpringLayout.EAST, scrollError);

        this.setLayout(layout);

        this.add(scrollVariables);
        this.add(scrollQuery);
        this.add(scrollError);
        this.add(splitPane);
        this.add(ok);
        this.add(clear);
        this.add(showTableIds);
        this.add(expand);
        this.add(collapse);
        this.add(folders);
        this.add(variablesLabel);
        this.add(queryLabel);
        this.add(errorLog);
        this.add(menuBar);
        this.add(allVersion);
        this.add(latestVersion);
        this.add(chooseVersion);
        this.add(nameQuery);
        this.add(nameQueryLabel);
    }

    private void buildRadioButton(){
        layout.putConstraint(SpringLayout.NORTH, allVersion, 5,
                SpringLayout.SOUTH, expand);
        layout.putConstraint(SpringLayout.WEST, allVersion, 0, SpringLayout.WEST,
                ok);
        layout.putConstraint(SpringLayout.NORTH, latestVersion, 5,
                SpringLayout.SOUTH, expand);
        layout.putConstraint(SpringLayout.WEST, latestVersion, 0, SpringLayout.WEST,
                clear);
        layout.putConstraint(SpringLayout.NORTH, chooseVersion, 5,
                SpringLayout.SOUTH, expand);
        layout.putConstraint(SpringLayout.WEST, chooseVersion, 0, SpringLayout.WEST,
                showTableIds);
//        layout.putConstraint(SpringLayout.NORTH, nameQuery, 10, SpringLayout.SOUTH,
//                allVersion);
//        layout.putConstraint(SpringLayout.WEST, nameQuery, 0, SpringLayout.WEST,
//                allVersion);
    }

    private void buildTextAreas(){
        layout.putConstraint(SpringLayout.NORTH, nameQueryLabel, 25,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameQueryLabel, 25,
                SpringLayout.EAST, splitPane);

        layout.putConstraint(SpringLayout.NORTH, nameQuery, 25,
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nameQuery, 10,
                SpringLayout.EAST, nameQueryLabel);

        //VARIABLE LABEL
        layout.putConstraint(SpringLayout.NORTH, variablesLabel, 25,
                SpringLayout.NORTH, nameQueryLabel);
        layout.putConstraint(SpringLayout.WEST, variablesLabel, 25,
                SpringLayout.EAST, splitPane);
        //VARIABLE TEXT
        layout.putConstraint(SpringLayout.NORTH, scrollVariables, 5,
                SpringLayout.SOUTH, variablesLabel);
        layout.putConstraint(SpringLayout.WEST, scrollVariables, 25,
                SpringLayout.EAST, splitPane);
        layout.putConstraint(SpringLayout.EAST, this, 10,
                SpringLayout.EAST, scrollVariables);
        //QUERY LABEL
        layout.putConstraint(SpringLayout.NORTH, queryLabel, 5,
                SpringLayout.SOUTH, scrollVariables);
        layout.putConstraint(SpringLayout.WEST, queryLabel, 25,
                SpringLayout.EAST, splitPane);

        //QUERY TEXT
        layout.putConstraint(SpringLayout.NORTH, scrollQuery, 5,
                SpringLayout.SOUTH, queryLabel);
        layout.putConstraint(SpringLayout.WEST, scrollQuery, 25,
                SpringLayout.EAST, splitPane);
        layout.putConstraint(SpringLayout.EAST, scrollVariables, 0,
                SpringLayout.EAST, scrollQuery);
    }

    private void buildButton() {
        Image okImg = null;
        Image cancelImg = null;
        Image loadSaveImg = null;
        try {
            okImg = ImageIO.read(getClass().getResource("/icons/ok.png"));
            cancelImg = ImageIO.read(getClass().getResource("/icons/cancel2.png"));
            loadSaveImg = ImageIO.read(getClass().getResource("/icons/folder24.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ok = new JButton(ViewController.OK);
        ok.setToolTipText("<html>run query</html>");
        ok.setIcon(new ImageIcon(okImg));

        this.clear = new JButton(ViewController.CLEAR);
        clear.setToolTipText("<html>clear text</html>");
        clear.setIcon(new ImageIcon(cancelImg));

        this.showTableIds=new JButton(ViewController.QUERIEDMODELS);
        showTableIds.setToolTipText("<html>show queried process models</html>");
        showTableIds.setIcon(new ImageIcon(loadSaveImg));

        this.expand = new JButton(ViewController.EXPAND);
        expand.setToolTipText("<html>expand</html>");


        this.collapse = new JButton(ViewController.COLLAPSE);
        collapse.setToolTipText("<html>collapse</html>");


        this.ok.setPreferredSize(new Dimension(100, 30));
        this.clear.setPreferredSize(new Dimension(100, 30));
        this.save.setPreferredSize(new Dimension(100, 30));
        this.load.setPreferredSize(new Dimension(100, 30));
        this.showTableIds.setPreferredSize(new Dimension(100, 30));
        this.expand.setPreferredSize(new Dimension(100,30));
        this.collapse.setPreferredSize(new Dimension(100,30));

        this.showTableIds.addActionListener(command);

        this.ok.addActionListener(command);

        this.clear.addActionListener(command);

        this.load.addActionListener(command);

        this.allVersion.addActionListener(command);

        this.latestVersion.addActionListener(command);

        this.chooseVersion.addActionListener(command);

        this.save.addActionListener(command);

        this.expand.addActionListener(command);

        this.collapse.addActionListener(command);

        layout.putConstraint(SpringLayout.WEST, ok, 25, SpringLayout.EAST,
                splitPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 25,
                SpringLayout.SOUTH, scrollQuery);

        layout.putConstraint(SpringLayout.WEST, clear, 10,
                SpringLayout.EAST, ok);
        layout.putConstraint(SpringLayout.NORTH, clear, 25,
                SpringLayout.SOUTH, scrollQuery);

        layout.putConstraint(SpringLayout.WEST, showTableIds, 10,
                SpringLayout.EAST,
                clear);
        layout.putConstraint(SpringLayout.NORTH, showTableIds, 25,
                SpringLayout.SOUTH, scrollQuery);

        layout.putConstraint(SpringLayout.WEST, expand, 50,
                SpringLayout.WEST,
                ok);
        layout.putConstraint(SpringLayout.NORTH, expand, 18,
                SpringLayout.SOUTH, ok);

        layout.putConstraint(SpringLayout.WEST, collapse, 50,
                SpringLayout.WEST,
                clear);
        layout.putConstraint(SpringLayout.NORTH, collapse, 18,
                SpringLayout.SOUTH, ok);

    }
}
