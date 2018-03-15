package de.olafj.vaadinplayground.js;

import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import de.olafj.vaadinplayground.VaadinPlaygroundApplication;
import elemental.json.JsonArray;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

@SpringView(name = "jsinclude")
public class JsIncludeView extends VerticalLayout implements View {

    @PostConstruct
    public void init() {

        ExternalResource resource = null;

            resource = new ExternalResource("/webcam2.html");
            BrowserFrame frame = new BrowserFrame();
            frame.setWidth(800, Unit.PIXELS);
            frame.setHeight(700, Unit.PIXELS);
            frame.setSource(resource);
            addComponentsAndExpand(frame);

        JavaScript.getCurrent().addFunction("de_olafj_vaadinplayground_js_myfunc",
                new JavaScriptFunction() {
                    @Override
                    public void call(JsonArray arguments) {
                            String message = arguments.getString(0);
                        //double width = arguments.getNumber(1);
                        //double height = arguments.getNumber(2);
                        String[] base64Image = message.split(";");
                        byte[] imageBytes = Base64.getDecoder().decode(base64Image[1].split(",")[1]);

                        try(FileOutputStream fileOutputStream = new FileOutputStream("/Users/ola/Desktop/test.png")) {
                            fileOutputStream.write(imageBytes);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        JavaScript.getCurrent().execute("window.de_olafj_vaadinplayground_js_myfunc = de_olafj_vaadinplayground_js_myfunc;");


    }

}
