package de.olafj.vaadinplayground.gridstack;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.alump.gridstack.GridStackLayout;
import org.vaadin.alump.gridstack.client.GwtGridStack;

import javax.annotation.PostConstruct;

@SpringView(name = "gridstack")
public class GridStackView extends VerticalLayout implements View {

    private GridStackLayout gridStackLayout;
    private Button createNewWGridStackItem = new Button("Neue Kachel");
    private Label infoLbl = new Label();
    private Label saveLbl = new Label();

    @PostConstruct
    void init() {
        setWidth(800, Unit.PIXELS);
        setHeight(600, Unit.PIXELS);
        gridStackLayout = new GridStackLayout(5, 4);
        gridStackLayout.setSizeFull();

        String info = "Raster (800x600) mit 5 Spalten und 4 Zeilen, Kacheln können mehrere Spalten und Zeilen überspannen. " +
                "Die Kacheln können durch das Icon in der oberen linken Ecke geschoben  und durch das Icon in der Ecken unten rechts verkleinert, -größert werden. " +
                "Die Icons zum Schieben und Vergrößern werden beim Darüberfahren mit der Maus eingeblendet. Über das kleine Papierkorb-Icon kann eine Kachel entfernt werden.";

        infoLbl.setContentMode(ContentMode.HTML);
        infoLbl.setValue(info);
        infoLbl.setWidth(600, Unit.PIXELS);
        addComponent(infoLbl);
        HorizontalLayout tools = new HorizontalLayout();
        tools.addComponent(createNewWGridStackItem);

        tools.addComponent(saveLbl);

        addComponent(tools);
        addComponent(gridStackLayout);
        setExpandRatio(gridStackLayout, 1.0f);
        gridStackLayout.addStyleName("grid-area");
        gridStackLayout.setCellHeight(150);

        createNewWGridStackItem.addStyleName(ValoTheme.BUTTON_SMALL);
        createNewWGridStackItem.addClickListener(event -> {
           GridStackItem gsi = new GridStackItem(this::onItemDeleted);
           gridStackLayout.addComponent(gsi);
           createNewWGridStackItem.setEnabled(false);
        });

        gridStackLayout.addGridStackMoveListener(events -> {
            createNewWGridStackItem.setEnabled(isSpaceLeft());
            updateSaveModus();
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        gridStackLayout.removeAllComponents();

        gridStackLayout.addComponent(new GridStackItem(this::onItemDeleted), 0, 0, 3, 2);
        gridStackLayout.addComponent(new GridStackItem(this::onItemDeleted), 4, 0, 2, 4);

        gridStackLayout.addComponent(new GridStackItem(this::onItemDeleted), 0, 2, 2, 1);
        gridStackLayout.addComponent(new GridStackItem(this::onItemDeleted), 2, 2, 1, 2);
        /*
        gridStackLayout.addComponent(new GridStackItem(gridStackLayout), 3, 3, 1, 2);
        gridStackLayout.addComponent(new GridStackItem(gridStackLayout), 4, 3, 2, 2);
        */

    }

    public boolean isSpaceLeft() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if(gridStackLayout.isAreaEmpty(i, j, 1,1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void onItemDeleted(GridStackItem gridStackItem) {
        gridStackLayout.removeComponent(gridStackItem);
        createNewWGridStackItem.setEnabled(isSpaceLeft());
        updateSaveModus();
    }

    private void updateSaveModus() {
        if(isSpaceLeft()) {
            saveLbl.setValue("Speichern nicht möglich. Weiße Flächen!");
            saveLbl.setStyleName(ValoTheme.LABEL_FAILURE);
        } else {
            saveLbl.setValue("Speichern möglich. Alles gefüllt!");
            saveLbl.setStyleName(ValoTheme.LABEL_SUCCESS);
        }
    }
}
