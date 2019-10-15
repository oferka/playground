(() => {

    // only listen for focus events on iOS to figure out keyboard size and compensate
    if (/iPhone|iPod/.test(navigator.userAgent) && !window.MSStream) {
    window.addEventListener('focusin', e => {
        if (/vaadin-text-field|vaadin-combo-box|vaadin-autocomplete/i.test(e.target.nodeName)) {
        compensateForOnScreenKeyboard();
    }
});

    window.addEventListener('focusout', e => {
        if (/vaadin-text-field|vaadin-combo-box|vaadin-autocomplete/i.test(e.target.nodeName)) {
        setTimeout(() => document.body.style.height = '100%', 300);
    }
});
}

function compensateForOnScreenKeyboard() {
    // Compensate for iOS on-screen-keyboard.
    // The keyboard transition/animation takes around 100-300ms
    setTimeout(() => {
        const offset = document.body.getBoundingClientRect();
    document.body.style.height = `calc(100% - ${-offset.top}px)`;
    document.body.scrollTop = 0;
}, 300);
}
})();