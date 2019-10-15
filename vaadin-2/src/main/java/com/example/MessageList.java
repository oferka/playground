package com.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

class MessageList extends Div {

    public MessageList() {
        setWidth("100%");
        addClassName("message-list");
    }

    @Override
    public void add(Component... components) {
        super.add(components);
        components[components.length-1]
                .getElement()
                .callFunction("scrollIntoView");
    }
}
