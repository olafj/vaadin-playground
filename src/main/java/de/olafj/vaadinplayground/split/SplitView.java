package de.olafj.vaadinplayground.split;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicInteger;

@SpringView(name="split")
public class SplitView extends VerticalLayout implements View {

    public static final int DISPLAY_WIDTH = 800;
    public static final int DISPLAY_HEIGHT = 600;

    public static final Integer NUMBER_ROWS = 6;

    public static AtomicInteger SPLIT_PANEL_COUNT = new AtomicInteger(0);

    public SplitView() {
    }

    @PostConstruct
    void init() {

        VerticalSplitPanel vSplit1 = new VerticalSplitPanel();
        vSplit1.setId("" + SPLIT_PANEL_COUNT.getAndIncrement());
        vSplit1.setSplitPosition(50, Unit.PERCENTAGE);
        vSplit1.setSizeFull();
        addComponentsAndExpand(vSplit1);
        setWidth(DISPLAY_WIDTH, Unit.PIXELS);
        setHeight(DISPLAY_HEIGHT, Unit.PIXELS);
        setMargin(false);
        setSpacing(false);

        vSplit1.setFirstComponent(newSplitItem(vSplit1, true, 0));
        vSplit1.setSecondComponent(newSplitItem(vSplit1, false, 0));

        vSplit1.setMinSplitPosition(16, Unit.PERCENTAGE);
        vSplit1.setMaxSplitPosition(84, Unit.PERCENTAGE);

    }

    public Component newSplitPanel(boolean horizontal, SplitItem initiator) {
        Integer level = ((SplittableSplitItem) initiator).getLevel();
        float minSplit = minSplit(level);
        float maxSplit = maxSplit(level);
        if(horizontal) {
            HorizontalSplitPanel hSplit = new HorizontalSplitPanel();
            hSplit.setId("" + SPLIT_PANEL_COUNT.getAndIncrement());
            hSplit.setSplitPosition(50, Unit.PERCENTAGE);
            hSplit.setFirstComponent(initiator);
            initiator.setContainer(hSplit);
            ((SplittableSplitItem) initiator).setLevel(level+1);
            initiator.setFirstItem(true);
            hSplit.setSizeFull();
            hSplit.setMinSplitPosition(minSplit, Unit.PERCENTAGE);
            hSplit.setMaxSplitPosition(maxSplit, Unit.PERCENTAGE);
            return hSplit;
        } else {
            VerticalSplitPanel vSplit1 = new VerticalSplitPanel();
            vSplit1.setId("" + SPLIT_PANEL_COUNT.getAndIncrement());
            vSplit1.setSplitPosition(50, Unit.PERCENTAGE);
            vSplit1.setFirstComponent(initiator);
            initiator.setContainer(vSplit1);
            ((SplittableSplitItem) initiator).setLevel(level+1);
            initiator.setFirstItem(true);
            vSplit1.setSizeFull();
            vSplit1.setMinSplitPosition(minSplit, Unit.PERCENTAGE);
            vSplit1.setMaxSplitPosition(maxSplit, Unit.PERCENTAGE);
            return vSplit1;
        }
    }

    public SplitItem newSplitItem(Component container, Boolean firstItem, Integer level) {

        final SplittableSplitItem splitItem = new SplittableSplitItem(container, firstItem, level) {
            @Override
            public void onSplit(SplitItem initiator, Component container, Boolean firstItem, Boolean horizontal) {
                Component splitPanel = newSplitPanel(horizontal, initiator);
                SplittableSplitItem newItem = (SplittableSplitItem) newSplitItem(splitPanel, false, initiator.getLevel());

                if(splitPanel instanceof VerticalSplitPanel) {
                    VerticalSplitPanel v = (VerticalSplitPanel) splitPanel;
                    v.setSecondComponent(newItem);
                } else {
                    HorizontalSplitPanel h = (HorizontalSplitPanel) splitPanel;
                    h.setSecondComponent(newItem);
                }

                if(container instanceof HorizontalSplitPanel) {
                    if(firstItem) {
                        ((HorizontalSplitPanel) container).setFirstComponent(splitPanel);
                    } else {
                        ((HorizontalSplitPanel) container).setSecondComponent(splitPanel);
                    }
                } else {
                    if(firstItem) {
                        ((VerticalSplitPanel) container).setFirstComponent(splitPanel);
                    } else {
                        ((VerticalSplitPanel) container).setSecondComponent(splitPanel);
                    }
                }
            }
        };

        return splitItem;

    }

    public static float minSplit(Integer level) {
        return new BigDecimal(1).divide(new BigDecimal(NUMBER_ROWS-level), MathContext.DECIMAL32).floatValue() * 100.0f;
    }

    public static float maxSplit(Integer level) {
        return new BigDecimal((NUMBER_ROWS-level)-1).divide(new BigDecimal(NUMBER_ROWS-level), MathContext.DECIMAL32).floatValue() * 100.0f;
    }
}
