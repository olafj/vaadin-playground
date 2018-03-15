package de.olafj.vaadinplayground.worker;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitDialog extends Window {

    private static Logger log = LoggerFactory.getLogger(WaitDialog.class);
    public static String WAIT_DIALOG_CSS_CLASSNAME = "waitdialog-content";

    final Label progressText = new Label();
    final ProgressBar progressBar = new ProgressBar();

    public WaitDialog(String text) {
        this(text, ContentMode.TEXT);
    }

    public WaitDialog(String text, ContentMode contentMode) {

        progressBar.setIndeterminate(true);

        CssLayout layout = new CssLayout(progressBar, progressText);
        layout.setStyleName(WAIT_DIALOG_CSS_CLASSNAME);

        setModal(true);
        setResizable(false);
        setClosable(false);
        setContent(layout);
        setSizeUndefined();

        setVisible(false);
        setText(text, contentMode);
    }

    public void show() {
        UI ui = UI.getCurrent();
        ui.addWindow(this);
        center();
        setVisible(true);
    }

    public void hide() {
        this.close();
    }

    public void setText(String text, ContentMode contentMode) {
        this.progressText.setContentMode(contentMode);
        this.progressText.setValue(text);
    }

    public void setText(String text) {
        this.progressText.setContentMode(ContentMode.TEXT);
        this.progressText.setValue(text);
    }
}
