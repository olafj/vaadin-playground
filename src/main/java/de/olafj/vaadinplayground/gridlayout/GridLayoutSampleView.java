package de.olafj.vaadinplayground.gridlayout;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@SpringView(name = "gridlayoutsample")
public class GridLayoutSampleView extends VerticalLayout implements View {


    @PostConstruct
    void init() {
/*
        GridLayout gl = new GridLayout(2, 1);
        gl.setColumnExpandRatio(0, 1.0f);
        //gl.setWidth(600, Unit.PIXELS);
        gl.setWidth(100, Unit.PERCENTAGE);

        Label l = new Label(VaadinIcons.FILE.getHtml() + "&nbsp;&nbsp;" + "Test.txt", ContentMode.HTML);
        gl.addComponent(l, 0, 0);
        l.setWidth(100, Unit.PERCENTAGE);

        Button b = new Button();
        b.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        b.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        b.setIcon(VaadinIcons.EYE);

        gl.addComponent(b,1,0);

        addComponent(gl);
*/
        TreeGrid<String> treeGrid = new TreeGrid<>();
        Grid.Column<String, GridLayout> nameColumn = treeGrid.addComponentColumn(text -> {

            GridLayout gl = new GridLayout(2, 1);
            gl.setColumnExpandRatio(0, 1.0f);
            gl.setWidth(1000, Unit.PIXELS);

            //gl.setSizeFull();

            Label l = new Label(VaadinIcons.FILE.getHtml() + "&nbsp;&nbsp;" + text, ContentMode.HTML);
            gl.addComponent(l, 0, 0);
            l.setWidth(500, Unit.PIXELS);

            Button b = new Button();
            b.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
            b.setStyleName(ValoTheme.BUTTON_BORDERLESS);
            b.setIcon(VaadinIcons.EYE);

            gl.addComponent(b,1,0);

            return gl;
        });
        nameColumn.setExpandRatio(1);
        nameColumn.setMinimumWidth(400);
        treeGrid.setWidth(100, Unit.PERCENTAGE);
        treeGrid.setFrozenColumnCount(1);

        treeGrid.setItems("Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello ", "World");
        addComponent(treeGrid);
    }

}
