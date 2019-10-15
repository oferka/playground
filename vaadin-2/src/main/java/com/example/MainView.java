package com.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import org.vaadin.marcus.shortcut.Shortcut;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Push
@Route(EMPTY)
@StyleSheet("styles/styles.css")
@JavaScript("src/ios-keyboard-fix.js")
@BodySize()
class MainView extends VerticalLayout implements PageConfigurator {

    private final UnicastProcessor<ChatMessage> messagePublisher;

    private MessageList messageList;
    private final Flux<ChatMessage> messages;
    private String username;
    private Disposable messageSubscription;

    public MainView(Flux<ChatMessage> messages, UnicastProcessor<ChatMessage> messagePublisher) {
        this.messages = messages;
        this.messagePublisher = messagePublisher;

        addClassName("main-view");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        var header = new H1("Vaadin Chat");
        header.getElement().getThemeList().add("dark");
        add(header);

        askUsername();
    }

    private void askUsername() {
        var usernameLayout = new HorizontalLayout();
        var usernameField = new TextField();
        usernameField.setValue("Vaadin User");
        usernameField.focus();

        var startButton = new Button("Start chatting", click -> {
            username = usernameField.getValue();
            remove(usernameLayout);
            showChat();
        });

        usernameLayout.add(usernameField, startButton);
        Shortcut.add(usernameLayout, Key.ENTER, startButton::click);
        add(usernameLayout);
    }

    private void showChat() {
        messageList = new MessageList();
        add(messageList, createInputLayout());
        expand(messageList);

        messageSubscription = messages.subscribe(message -> getUI().ifPresent(ui -> ui.access(() -> {
            messageList.add(new MessageLayout(message));
        })));

        getUI().ifPresent(ui -> ui.addDetachListener(event -> {
            messageSubscription.dispose();
        }));
    }

    private HorizontalLayout createInputLayout() {
        var inputLayout = new HorizontalLayout();
        var messageField = new TextField();
        var sendButton = new Button("Send");

        inputLayout.setWidth("100%");
        inputLayout.add(messageField, sendButton);
        inputLayout.expand(messageField);

        messageField.setPlaceholder("Type something ...");
        Shortcut.add(messageField, Key.ENTER, sendButton::click);

        sendButton.getElement().getThemeList().add("primary");
        sendButton.addClickListener(click -> {
            if (messageField.getValue().isEmpty())
                return;
            messagePublisher.onNext(new ChatMessage(username, messageField.getValue()));
            messageField.clear();
            messageField.focus();
        });
        messageField.focus();

        return inputLayout;
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addLink(InitialPageSettings.Position.PREPEND, "manifest", "manifest.webmanifest");
        settings.addLink(InitialPageSettings.Position.PREPEND, "icon", "favicon.png");
        settings.addInlineWithContents(
                "window.addEventListener('load', function() { " + "  if('serviceWorker' in navigator) {"
                        + "    navigator.serviceWorker.register('./sw.js');" + "  }" + "});",
                InitialPageSettings.WrapMode.JAVASCRIPT);
    }

}
