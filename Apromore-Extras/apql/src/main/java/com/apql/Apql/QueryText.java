package com.apql.Apql;

import com.apql.Apql.controller.ContextController;
import com.apql.Apql.controller.QueryController;
import com.apql.Apql.controller.ViewController;
import com.apql.Apql.highlight.ButtonAction;
import com.apql.Apql.listener.WordListener;
import com.apql.Apql.popup.PopupFrame;
import com.apql.Apql.table.TableProcess;
import com.apql.Apql.table.TableRow;
import com.apql.Apql.table.TableRowFlavor;
import com.apql.Apql.tree.DraggableNodeProcess;
import com.apql.Apql.tree.DraggableNodeTree;
import com.apql.Apql.tree.FolderProcessTree;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;

import javax.swing.tree.TreePath;

public class QueryText extends JTextPane implements DropTargetListener{

	private static final long serialVersionUID = -7719436763743034599L;
    private QueryController queryController=QueryController.getQueryController();
    private ViewController viewController = ViewController.getController();
	public QueryText() {
		setPreferredSize(new Dimension(450, 210));
		QueryController.getQueryController().settextPane(this);
		addKeyListener(new WordListener());
        Keymap parent = this.getKeymap();
        Keymap newmap = JTextComponent.addKeymap("KeymapExampleMap", parent);
        KeyStroke suggest = KeyStroke
                .getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_MASK);
        KeyStroke copy = KeyStroke
                .getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK);
        KeyStroke paste = KeyStroke
                .getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK);
        KeyStroke cut = KeyStroke
                .getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK);
        KeyStroke selectAll = KeyStroke
                .getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK);
        KeyStroke expandCollapse = KeyStroke
                .getKeyStroke(KeyEvent.VK_BACK_SLASH, InputEvent.CTRL_MASK);
        KeyStroke undo = KeyStroke
                .getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK);
        KeyStroke redo = KeyStroke
                .getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK);

        Action actionSuggest = new ButtonAction.CTRLSpace();
        Action actionCopy=new ButtonAction.CTRLC(this);
        Action actionCut=new ButtonAction.CTRLX(this);
        Action actionPaste=new ButtonAction.CTRLV(this);
        Action actionSelect=new ButtonAction.CTRLA(this);
        Action actionExpandCollapse = new ButtonAction.CTRLBack();
        Action actionUndo = new ButtonAction.CTRLZ();
        Action actionRedo = new ButtonAction.CTRLY();

        newmap.addActionForKeyStroke(suggest, actionSuggest);
        newmap.addActionForKeyStroke(copy, actionCopy);
        newmap.addActionForKeyStroke(paste, actionPaste);
        newmap.addActionForKeyStroke(cut, actionCut);
        newmap.addActionForKeyStroke(selectAll, actionSelect);
        newmap.addActionForKeyStroke(expandCollapse, actionExpandCollapse);
        newmap.addActionForKeyStroke(undo, actionUndo);
        newmap.addActionForKeyStroke(redo, actionRedo);
        this.setKeymap(newmap);
        setDropTarget(new DropTarget(this,this));
		addMouseListener(new MouseListener() {


            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {
                int position=queryController.getTextPane().getCaretPosition();
                queryController.setCaretPosition(position);
                System.out.println("position: "+position);
                PopupFrame pf = ViewController.getController().getPopup();
                if (pf != null) {
                    ViewController.getController().setPopupOpen(false);
                    pf.setVisible(false);
                    pf = null;
                    ViewController.getController().setPopup(null);
                }
            }
        });
		setVisible(true);
	}

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent evt) {
        Transferable transferable=evt.getTransferable();
        if(transferable.isDataFlavorSupported(TableRowFlavor.TABLEROWFLAVOUR)){
            evt.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            try {
                TableProcess dragContents = (TableProcess) transferable.getTransferData(TableRowFlavor.TABLEROWFLAVOUR);
                HashSet<String> locationsVersion = new HashSet<>();
                for(TableRow row : dragContents.getRows()) {
                    if (row.isSelected()) {
                        locationsVersion.add(row.toString());
                        row.clearSelection();
                    }
                }
                queryController.setLocations(locationsVersion);
                queryController.addQueryLocation();
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(!queryController.getVersion().equals(ViewController.CHOOSEVERSION)){
            findProcess();
        }
        viewController.getTableProcess().clearSelection();
    }

    private void findProcess(){
        QueryController queryController=QueryController.getQueryController();
        DraggableNodeTree node;
        HashSet<String> locations=new HashSet<String>();

        JTree tree = ViewController.getController().getFolderProcessTree();
        TreePath[] path = tree.getSelectionPaths();

        for(TreePath tp : path) {
            node =(DraggableNodeTree) tp.getLastPathComponent();
            Enumeration e = node.breadthFirstEnumeration();
            while (e.hasMoreElements()) {
                DraggableNodeTree n = (DraggableNodeTree) e.nextElement();

                if (n instanceof DraggableNodeProcess) {
                    DraggableNodeProcess dnp=(DraggableNodeProcess)n;
                    if(queryController.getVersion().equals(ViewController.LATESTVERSION)){
                        locations.add(dnp.getPathNode()+"{LATESTVERSION}");
                    }else if(queryController.getVersion().equals(ViewController.ALLVERSIONS) || queryController.getVersion().equals(ViewController.CHOOSEVERSION)){
                        locations.add(dnp.getPathNode()+"{ALLVERSION}");
                    }
                }
            }
        }
        queryController.setLocations(locations);

        queryController.addQueryLocation();

        tree.clearSelection();
    }


}
