package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultElementHighlighter implements ElementHighlighter {

    @Override
    public void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'color: navy; background: silver; border: 2px solid navy;');", element);
    }
}
