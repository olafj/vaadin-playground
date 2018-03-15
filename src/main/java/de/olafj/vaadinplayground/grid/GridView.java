package de.olafj.vaadinplayground.grid;

import com.vaadin.navigator.View;
import com.vaadin.pekka.resizablecsslayout.ResizableCssLayout;
import com.vaadin.pekka.resizablecsslayout.client.ResizeLocation;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringView(name = "grid")
public class GridView extends Panel implements View {

    final GridLayout gridLayout = new GridLayout(4,3);
    final GridItemCollection gridItems = new GridItemCollection();

    public GridView() {
    }

    @PostConstruct
    protected void init() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setMargin(true);
        setContent(layout);

        gridLayout.setWidth(480, Sizeable.Unit.PIXELS);

        gridLayout.setSpacing(false);
        gridLayout.setMargin(false);
/*
    gridLayout.setColumnExpandRatio(0, 0.25f);
    gridLayout.setColumnExpandRatio(1, 0.25f);
    gridLayout.setColumnExpandRatio(2, 0.25f);
    gridLayout.setColumnExpandRatio(3, 0.25f);
*/
        layout.addComponent(gridLayout);



        GridItem gridItem1 = new GridItem(new GridCoords(0,0,1,0), 120, 90,4, 3);
        gridItems.addItem(gridItem1);

        GridItem gridItem2 = new GridItem(new GridCoords(2,0,3,0),120, 90, 4, 3);
        gridItems.addItem(gridItem2);

        GridItem gridItem3 = new GridItem(new GridCoords(0,1,3,1),120, 90, 4, 3);
        gridItems.addItem(gridItem3);

        GridItem gridItem4 = new GridItem(new GridCoords(0,2,0,2),120, 90, 4, 3);
        gridItems.addItem(gridItem4);

        GridItem gridItem5 = new GridItem(new GridCoords(1,2,2,2),120, 90, 4, 3);
        gridItems.addItem(gridItem5);

        GridItem gridItem6 = new GridItem(new GridCoords(3,2,3,2),120, 90, 4, 3);
        gridItems.addItem(gridItem6);

        gridItems.stream().forEach(gridItem -> {
            gridItem.addResizeListener(resizeListener);
            gridLayout.addComponent(gridItem, gridItem.getGridCoords().getX1(), gridItem.getGridCoords().getY1(), gridItem.getGridCoords().getX2(), gridItem.getGridCoords().getY2());
        });

    }

    private ResizableCssLayout.ResizeListener resizeListener = new ResizableCssLayout.ResizeListener() {

        @Override
        public void resizeStart(ResizableCssLayout.ResizeStartEvent event) {
            GridItem resizedItem = (GridItem) event.getComponent();
            resizedItem.resizeStart();
        }

        @Override
        public void resizeEnd(ResizableCssLayout.ResizeEndEvent event) {
            GridItem resizedItem = (GridItem) event.getComponent();
            float wDiff = event.getWidth() - resizedItem.getOldWidth();
            ResizeLocation resizedLocation = resizedItem.getLatestResizeLocation();
            List<GridItem> others =  gridItems.stream().filter(gridItem -> gridItem != resizedItem).collect(Collectors.toList());
            others.stream().filter(gridItem -> gridItem.isInRowOf(resizedItem)).forEach(otherItem -> {
                if(resizedLocation == ResizeLocation.RIGHT && otherItem.isRightNeighborOf(resizedItem)) {
                    otherItem.setWidth(otherItem.getWidth() - wDiff, Unit.PIXELS);
                }
                if(resizedLocation == ResizeLocation.LEFT && otherItem.isLeftNeighborOf(resizedItem)) {
                    otherItem.setWidth(otherItem.getWidth() - wDiff, Unit.PIXELS);
                }
            });
        }

        @Override
        public void resizeCancel(ResizableCssLayout.ResizeCancelEvent event) {

        }
    };

}
