package org.biri.gui.web.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LoginImagePanel extends VerticalLayout {

    public LoginImagePanel() {
        Button button = new Button("Click me", e -> Notification.show("Hello"));
        add(button);

        String alt = "DummyImage";
        Image image = new Image("https://dummyimage.com/600x400/000/fff", alt);
        add(image);
    }
}
