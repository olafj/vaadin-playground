package de.olafj.vaadinplayground;

import com.vaadin.server.*;
import com.vaadin.spring.server.SpringVaadinServlet;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@Component("vaadinServlet")
@WebServlet(value = "/*", asyncSupported = true)
public class VaadinServlet extends SpringVaadinServlet {

    private static final Logger log = LoggerFactory.getLogger(VaadinServlet.class);

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener(this::sessionInit);
    }

    public final void sessionInit(final SessionInitEvent event)
            throws ServiceException {

        log.debug("initialize custom facets of vaadin-session.");

        event.getSession().addBootstrapListener(new BootstrapListener() {

            @Override
            public void modifyBootstrapPage(final BootstrapPageResponse response) {

                log.debug("Try to append meta-tags...");

                final Element head = response.getDocument().head();

                log.debug("Adding viewport-meta-information: width=device-width, initial-scale=1");
                head.appendElement("meta")
                        .attr("name", "viewport")
                        .attr("content","width=device-width, initial-scale=1");

            }

            @Override
            public void modifyBootstrapFragment(
                    final BootstrapFragmentResponse response) {
            }
        });
    }



}