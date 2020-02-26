package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class DefaultElementHighlighter implements ElementHighlighter {

    @Autowired
    private ElementHighlighterConfiguration elementHighlighterConfiguration;

    @Override
    public void highlight(WebDriver driver, WebElement element) {
        log.debug("Element {} highlighting started", element.getText());
        if(elementHighlighterConfiguration.isEnabled()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = format(
                    "arguments[0].setAttribute('style', 'color: %s; background: %s; border: %s;');",
                    elementHighlighterConfiguration.getColor(),
                    elementHighlighterConfiguration.getBackground(),
                    elementHighlighterConfiguration.getBorder()
            );
            js.executeScript(script, element);
        }
        log.debug("Element {} highlighting completed", element.getText());
    }
}
