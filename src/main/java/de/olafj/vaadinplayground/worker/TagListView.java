package de.olafj.vaadinplayground.worker;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import de.olafj.vaadinplayground.tag.TagList;

import java.util.UUID;

@SpringView(name = "taglist")
public class TagListView extends CssLayout implements View {

    public class BeanDemo {

        private Integer anzahl;

        public Integer getAnzahl() {
            return anzahl;
        }

        public void setAnzahl(Integer anzahl) {
            this.anzahl = anzahl;
        }
    }

    final Label pl = new Label("Bitte warten...");

    public class Item {

        public String getItemText() {
            return itemText;
        }

        final String itemText;

        public Item(String itemText) {
            this.itemText = itemText;
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {



        TagList<Item> tl = new TagList<>();
        tl.setItemCaptionGenerator(Item::getItemText);
        tl.setOnDeleteClickListener(item -> {
            Notification.show("Item removed", Notification.Type.TRAY_NOTIFICATION);
        });
        tl.addItem(new Item("Test 1"));
        tl.addItem(new Item("Test 2"));
        tl.addItem(new Item("Test 3"));
        tl.addItem(new Item("Test 4"));

        addComponent(tl);

        Button addItem = new Button("Add");
        addItem.addClickListener(event1 -> {
            tl.addItem(new Item(UUID.randomUUID().toString()));
        });

        addComponent(addItem);


        /*

        Panel p = new Panel("Test");
        p.addStyleName("green");
        p.setContent(new VerticalLayout());
        p.setSizeUndefined();

        setSizeFull();

        addComponent(p);


        Panel p2 = new Panel("Test2");
        p2.addStyleName("green");
        p2.setContent(new VerticalLayout());
        p2.setSizeUndefined();

        addComponent(p2);

*/
        /*
        final WaitDialog wd = new WaitDialog("Bitte warten");
        wd.show();


        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
                getUI().access(() -> wd.setText("Es geht los, schnall dich an!"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10000);
                getUI().access(() -> wd.setText("Ich bin fast fertig... Moment noch."));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(12000);
                getUI().access(() -> wd.hide());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        */

    }
}
