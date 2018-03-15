package de.olafj.vaadinplayground;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@SpringView(name = "")
public class DefaultView extends VerticalLayout implements View {


    @PostConstruct
    void init() {
        addComponent(new Label("Hello"));
    }

}
