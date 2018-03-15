package de.olafj.vaadinplayground.split;

import com.ejt.vaadin.sizereporter.SizeReporter;
import com.vaadin.pekka.resizablecsslayout.ResizableCssLayout;
import com.vaadin.ui.*;

import java.util.concurrent.atomic.AtomicInteger;

public class SplitItem extends ResizableCssLayout {

    static AtomicInteger INSTANCE_COUNT = new AtomicInteger(0);
    protected Integer level = 0;

    Label sizeLabel = new Label("Unknown");
    Label idLabel = new Label("Unknown");
    Label containerLabel = new Label("Unknown");
    protected VerticalLayout p = new VerticalLayout();

    public void setContainer(Component container) {
        this.container = container;
        //containerLabel.setValue(container.getId());
    }

    public void setFirstItem(Boolean firstItem) {
        this.firstItem = firstItem;
    }

    protected Component container;
    protected Boolean firstItem;

    public SplitItem(Component container, Boolean firstItem, Integer level) {
        this.container = container;
        this.firstItem = firstItem;

        containerLabel.setValue("Level: " + level);

        p.addComponent(idLabel);
        p.addComponent(sizeLabel);
        p.addComponent(containerLabel);
        p.addStyleName("grid-item-content");
        addComponent(p);
        addStyleName("grid-item");

        SizeReporter sizeReporter = new SizeReporter(this);
        sizeReporter.addResizeListener(componentResizeEvent -> {
            sizeLabel.setValue(componentResizeEvent.getWidth() + "x" + componentResizeEvent.getHeight());
        });

        idLabel.setValue(""+ INSTANCE_COUNT.incrementAndGet());

        this.level = level;
    }


    public void setLevel(Integer level) {
        containerLabel.setValue("Level: " + level);
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
