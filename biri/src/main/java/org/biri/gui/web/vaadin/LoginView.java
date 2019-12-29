package org.biri.gui.web.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        add(new LoginImagePanel());
    }
}
