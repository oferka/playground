package org.biri.gui.web.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "login2")
public class LoginView2 extends VerticalLayout {

    public LoginView2() {
        Button button = new Button("Click me2", e -> Notification.show("Hello2"));
        add(button);
    }
}
