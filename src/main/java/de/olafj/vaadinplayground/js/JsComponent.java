package de.olafj.vaadinplayground.js;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import de.olafj.vaadinplayground.VaadinPlaygroundApplication;
import elemental.json.JsonArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class JsComponent extends CustomComponent {

    public JsComponent() {

        try {
            CustomLayout layout  = new CustomLayout(VaadinPlaygroundApplication.class.getResourceAsStream("/static/webcam.html"));
            setCompositionRoot(layout);

            JavaScript.getCurrent().addFunction("de.olafj.vaadinplayground.js.myfunc",
                    new JavaScriptFunction() {
                        @Override
                        public void call(JsonArray arguments) {
                            String message = arguments.getString(0);
                            Notification.show(message);
                        }
                    });

            /*
            Link link = new Link("Send Message", new ExternalResource(
                    "javascript:de.olafj.vaadinplayground.js.myfunc(callbackFunc());"));


            layout.addComponent(link, "okbutton");
            */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
