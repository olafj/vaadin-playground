package de.olafj.vaadinplayground.grid;

import com.vaadin.pekka.resizablecsslayout.ResizableCssLayout;
import com.vaadin.pekka.resizablecsslayout.client.ResizeLocation;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class GridItem extends ResizableCssLayout {

    private float oldWidth = 0f;

    private float oldHeight = 0f;

    private GridCoords gridCoords;

    Label label = new Label();

    public GridItem(GridCoords gridCoords, int singleColWidth, int singleRowHeight, int gridCols, int gridRows, Component... children) {
        super(children);
        this.gridCoords = gridCoords;

        setWidth(gridCoords.colSpan() * singleColWidth, Unit.PIXELS);
        setHeight(gridCoords.rowSpan() * singleRowHeight, Unit.PIXELS);

        Panel p = new Panel();
        p.setSizeFull();
        setWidth(gridCoords.colSpan() * singleColWidth, Unit.PIXELS);
        setHeight(gridCoords.rowSpan() * singleRowHeight, Unit.PIXELS);

        p.setContent(label);
        p.addStyleName("grid-item-content");
        addComponent(p);

        addStyleName("grid-item");

        if(gridCoords.startsOnX(0) && gridCoords.endsOnX(gridCols-1)) {
            setResizable(false);
            return;
        } else if(gridCoords.startsOnX(0)) {
            setResizable(true);
            setResizeLocations(ResizeLocation.RIGHT);
            return;
        } else if(gridCoords.endsOnX(gridCols-1)) {
            setResizable(true);
            setResizeLocations(ResizeLocation.LEFT);
        } else {
            setResizable(true);
            setResizeLocations(ResizeLocation.LEFT, ResizeLocation.RIGHT);
        }
    }

    public GridCoords getGridCoords() {
        return gridCoords;
    }

    public void setGridCoords(GridCoords gridCoords) {
        this.gridCoords = gridCoords;
    }

    public float getOldWidth() {
        return oldWidth;
    }

    public float getOldHeight() {
        return oldHeight;
    }

    public void resizeStart() {
        oldWidth = this.getWidth();
        oldHeight = this.getHeight();
    }

    public boolean startsOnX(int x) {
        return gridCoords.startsOnX(x);
    }

    public boolean endsOnX(int x) {
        return gridCoords.endsOnX(x);
    }

    public boolean startsOnY(int y) {
        return gridCoords.startsOnY(y);
    }

    public boolean endsOnY(int y) {
        return gridCoords.endsOnY(y);
    }

    public boolean isInRowOf(GridItem other) {
        return gridCoords.isInRowOf(other.getGridCoords());
    }

    public boolean notInRowOf(GridItem other) {
        return gridCoords.notInRowOf(other.getGridCoords());
    }

    public boolean sameRightAlignment(GridItem other) {
        return gridCoords.sameRightAlignment(other.getGridCoords());
    }

    public boolean sameLeftAlignment(GridItem other) {
        return gridCoords.sameLeftAlignment(other.getGridCoords());
    }

    public boolean isRightNeighborOf(GridItem other) {
        return gridCoords.isRightNeighborOf(other.getGridCoords());
    }

    public boolean isLeftNeighborOf(GridItem other) {
        return gridCoords.isLeftNeighborOf(other.getGridCoords());
    }

    @Override
    public void setWidth(float width, Unit unit) {
        super.setWidth(width, unit);
        label.setValue("w="+width);
    }
}
