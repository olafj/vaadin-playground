package de.olafj.vaadinplayground.tag;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TagBox<T> extends CustomComponent {

    private final T item;

    protected  final Label textLabel;
    protected final Button deleteButton;

    public TagBox(T item) {
        this.item  = item;

        this.textLabel = new Label();

        Panel p = new Panel();
        p.setStyleName("tag-box");

        HorizontalLayout h = new HorizontalLayout();
        h.setStyleName("tag-box-hlayout");
        h.setMargin(false);
        h.setSpacing(false);

        textLabel.setStyleName(ValoTheme.LABEL_SMALL);
        textLabel.addStyleName("tag-box-text");


        deleteButton = new Button();
        deleteButton.addStyleNames(ValoTheme.BUTTON_ICON_ONLY, ValoTheme.BUTTON_TINY, ValoTheme.BUTTON_BORDERLESS, "tag-box-button");
        deleteButton.setIcon(VaadinIcons.CLOSE_CIRCLE_O);

        h.addComponents(textLabel, deleteButton);
        h.setExpandRatio(textLabel, 1.0f);

        h.setComponentAlignment(textLabel, Alignment.MIDDLE_RIGHT);
        h.setComponentAlignment(deleteButton, Alignment.MIDDLE_RIGHT);

        p.setWidthUndefined();
        p.setContent(h);
        setCompositionRoot(p);
        setWidthUndefined();

    }

    public T getItem() {
        return item;
    }




}
