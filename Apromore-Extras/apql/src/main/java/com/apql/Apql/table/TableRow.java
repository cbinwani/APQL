package com.apql.Apql.table;

import com.apql.Apql.controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by corno on 8/08/2014.
 */
public class TableRow extends JPanel implements MouseListener, DragSourceListener, DragGestureListener, Comparable<TableRow> {
    private List<JLabel> columns;
    private int defaultWidth;
    private int defaultHeight;
    private boolean selected =false;
    private String pathNode;
    private Color background;
    private DragSource dragSource;
    private TableProcess table;
    private String id;

    public TableRow(){}

    public TableRow(TableProcess table,int defaultWidth, int defaultHeight, Color background,String pathNode,String id, String... columns){
        this.columns=new LinkedList<JLabel>();
        this.pathNode=pathNode;
        this.table=table;
        this.id=id;

        this.defaultWidth=defaultWidth;
        this.defaultHeight=defaultHeight;
        this.background=background;
        setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        if(table.isSelectRow()) {
            addMouseListener(this);
            this.dragSource=new DragSource();
            dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
        }
        JLabel labelColumns;
        for(String str : columns){
            labelColumns=new JLabel(str);
            labelColumns.setOpaque(true);
            buildLabel(defaultWidth, defaultHeight, labelColumns);
            this.columns.add(labelColumns);
            add(labelColumns);
        }
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(background);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        revalidate();
        repaint();
    }

    private void buildLabel(int widthHeader, int heightHeader, JLabel label){
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setBackground(background);
        label.setPreferredSize(new Dimension(widthHeader, heightHeader));
        label.setMaximumSize(new Dimension(widthHeader, heightHeader));
        label.setMinimumSize(new Dimension(widthHeader, heightHeader));
    }

    public List<JLabel> getLabelsRow(){
        return columns;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected=selected;
        if(!selected){
            this.setBackground(new Color(146, 239, 237));
        }else{
            this.setBackground(background);
        }
    }

    public void clearSelection(){
        selected=false;
        super.setBackground(background);
        revalidate();
        repaint();
    }

    @Override
    public void setBackground(Color color){
        super.setBackground(color);
        if(columns!=null && !columns.isEmpty())
            for(JLabel label : columns)
                label.setBackground(color);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click row: "+((TableRow)e.getSource()).toString());
        TableRow row;
        if(!selected){
            row=((TableRow)e.getSource());
            row.setBackground(new Color(146, 239, 237));
            for(JLabel label : columns){
                label.setBackground(new Color(146, 239, 237));
                label.revalidate();
                label.repaint();
            }
            row.revalidate();
            row.repaint();
            selected =true;
        }else{
            row=((TableRow)e.getSource());
            row.setBackground(background);
            for(JLabel label : columns){
                label.setBackground(background);
                label.revalidate();
                label.repaint();
            }
            row.revalidate();
            row.repaint();
            selected =false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        TableRow row=((TableRow)e.getSource());
        if(!selected) {
            for(JLabel label : columns){
                label.setBackground(new Color(146, 239, 237));
                label.revalidate();
                label.repaint();
            }
            row.setBackground(new Color(177, 243, 241));
            row.revalidate();
            row.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        TableRow row=((TableRow)e.getSource());
        if(!selected) {
            row.setBackground(background);
            for(JLabel label : columns){
                label.setBackground(background);
                label.revalidate();
                label.repaint();
            }
            row.revalidate();
            row.repaint();
        }
    }

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(pathNode+"{"+id+"/"+columns.get(1).getText()+"/"+columns.get(0).getText()+"}");
        return sb.toString();
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        if(dragSource==null)
            return;
        Transferable transferable = new TransferableRows((TableProcess)getParent().getParent().getParent().getParent());
        dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {

    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragExit(DragSourceEvent dse) {

    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {

    }

    @Override
    public int compareTo(TableRow row) {
        int mode=table.getOrderMode();
        return columns.get(mode).getText().compareTo(row.getLabelsRow().get(mode).getText());
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(50, 50);
        float[] dist = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
        LinearGradientPaint p =
                new LinearGradientPaint(start, end, dist, colors);
    }
}
