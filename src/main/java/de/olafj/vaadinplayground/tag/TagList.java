package de.olafj.vaadinplayground.tag;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TagList<T> extends CustomComponent {

    final Map<T, TagBox<T>> items = new HashMap<>();
    final CssLayout innerLayout = new CssLayout();

    private ItemCaptionProvider<T> itemCaptionGenerator = null;
    private Consumer<T> onDeleteClickListener = null;

    public TagList() {
        innerLayout.setSizeFull();
        setCompositionRoot(innerLayout);
    }

    public void setItemCaptionGenerator(ItemCaptionProvider<T> supplier) {
        this.itemCaptionGenerator = supplier;
    }

    public void setOnDeleteClickListener(Consumer<T> consumer) {
        this.onDeleteClickListener = consumer;
    }

    public void removeItem(T item) {
        this.innerLayout.removeComponent(this.items.get(item));
        this.items.remove(item);
    }

    public void addItem(T item) {
        TagBox<T> tagBox = createBox(item);
        this.items.put(item, tagBox);
        this.innerLayout.addComponent(tagBox);
    }

    public Collection<T> getItems() {
        return Collections.unmodifiableSet(this.items.keySet());
    }

    private TagBox<T> createBox(T item) {
        TagBox<T> tagBox = new TagBox<>(item);
        if(this.itemCaptionGenerator != null) {
            tagBox.textLabel.setValue(this.itemCaptionGenerator.provideValue(item));
        } else {
            tagBox.textLabel.setValue(item.toString());
        }

        tagBox.deleteButton.addClickListener(event -> {
            removeItem(tagBox.getItem());
            this.onDeleteClickListener.accept(tagBox.getItem());
        });
        return tagBox;
    }


    @FunctionalInterface
    public interface ItemCaptionProvider<T> {
        String provideValue(T pojo);
    }
}
