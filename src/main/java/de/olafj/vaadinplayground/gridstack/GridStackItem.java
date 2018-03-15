package de.olafj.vaadinplayground.gridstack;

import com.ejt.vaadin.sizereporter.SizeReporter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class GridStackItem extends CustomComponent {

    private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger(0);

    private Label sizeLbl = new Label();
    private Label idLbl = new Label();

    VerticalLayout innerLayout = new VerticalLayout();

    public GridStackItem(Consumer<GridStackItem> onDeleteConsumer) {
        this.setId(INSTANCE_COUNT.getAndIncrement()+ "");
        idLbl.setValue("ID: " + this.getId());

        innerLayout.setSizeFull();
        innerLayout.setSpacing(false);
        innerLayout.setMargin(false);
        setCompositionRoot(innerLayout);

        HorizontalLayout tools = new HorizontalLayout();
        innerLayout.addComponent(tools);

        idLbl.setStyleName(ValoTheme.LABEL_TINY);
        tools.addComponent(idLbl);
        tools.addComponent(sizeLbl);
        sizeLbl.setStyleName(ValoTheme.LABEL_TINY);

        Button removeMe = new Button("x");
        removeMe.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        removeMe.addStyleName(ValoTheme.BUTTON_LINK);
        removeMe.addStyleName(ValoTheme.BUTTON_TINY);
        removeMe.setIcon(FontAwesome.TRASH);
        tools.addComponent(removeMe);

        removeMe.addClickListener(event -> {
            if(onDeleteConsumer != null) {
                onDeleteConsumer.accept(this);
            }
        });

        innerLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setSizeFull();
        addStyleName("grid-item");

        SizeReporter sizeReporter = new SizeReporter(innerLayout);
        sizeReporter.addResizeListener(componentResizeEvent -> {
            sizeLbl.setValue(componentResizeEvent.getWidth() + "x" + componentResizeEvent.getHeight());
        });
    }
}
