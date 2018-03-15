package de.olafj.vaadinplayground.split;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.themes.ValoTheme;

public abstract class SplittableSplitItem extends SplitItem {


    public SplittableSplitItem(Component container, Boolean firstItem, Integer level) {
        super(container, firstItem, level);

        Button splitButton = new Button("Split H!");

        splitButton.addClickListener(event -> {
            onSplit(this, this.container, this.firstItem, true);
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(false);
        buttons.setMargin(false);
        buttons.setWidth(120, Unit.PIXELS);
        p.addComponent(buttons);
        buttons.addComponent(splitButton);

        Button split2Button = new Button("Split V!");


        split2Button.addClickListener(event -> {
            onSplit(this, this.container, this.firstItem, false);
        });

        buttons.addComponent(split2Button);

        splitButton.setStyleName(ValoTheme.BUTTON_TINY);
        split2Button.setStyleName(ValoTheme.BUTTON_TINY);
    }

    public abstract void onSplit(SplitItem initiator, Component container, Boolean firstItem, Boolean horizontal);
}
