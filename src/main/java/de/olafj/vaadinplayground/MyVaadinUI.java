package de.olafj.vaadinplayground;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Push
//@PushStateNavigation
@Theme("vaadin-playground")
@SpringUI
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    VerticalLayout screen = new VerticalLayout();
    HorizontalLayout bar = new HorizontalLayout();
    HorizontalLayout content = new HorizontalLayout();


    @Autowired
    SpringViewProvider springViewProvider;

    @Autowired
    SpringNavigator springNavigator;

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }

    @Override
    protected void init(VaadinRequest request) {

        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button navigateToGridStackSample = new Button("GridStack Sample");
        navigateToGridStackSample.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        Button navigateToGridSample = new Button("Grid Sample");
        navigateToGridSample.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        Button navigateToSplitPanelsSample = new Button("SplitPanels Sample");
        navigateToSplitPanelsSample.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        Button navigateToGridlayoutSample = new Button("GridLayout Sample");
        navigateToGridlayoutSample.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        Button workerViewBtn = new Button("Taglist Demo");
        workerViewBtn.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        workerViewBtn.addClickListener(event -> springNavigator.navigateTo("taglist"));

        CssLayout menu = new CssLayout(title, workerViewBtn, navigateToGridStackSample, navigateToGridSample, navigateToSplitPanelsSample, navigateToGridlayoutSample);
        menu.setWidth(172, Unit.PIXELS);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();

        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSpacing(false);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(viewContainer, 1.0f);
        setContent(mainLayout);

        springNavigator.init(this, viewContainer);

        navigateToGridlayoutSample.addClickListener(event -> {
            springNavigator.navigateTo("gridlayoutsample");
        });

        navigateToGridSample.addClickListener(event -> {
            springNavigator.navigateTo("grid");
        });

        navigateToSplitPanelsSample.addClickListener(event -> {
            springNavigator.navigateTo("split");
        });

        navigateToGridStackSample.addClickListener(event -> {
            springNavigator.navigateTo("gridstack");
        });

        navigateToGridSample.setEnabled(false);
        navigateToSplitPanelsSample.setEnabled(false);


    }


}