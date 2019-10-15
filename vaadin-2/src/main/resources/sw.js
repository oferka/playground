const version = 2;
const cacheName = 'vaadin-cache-v' + version;
const staticAssets = [
    './offline.html',
    './frontend/images/icon.png'
];

self.addEventListener('install', evt => {
    evt.waitUntil(updateCache());
});


self.addEventListener('fetch', async evt => {
    const request = evt.request;

    if(acceptsHTML(request)){
        evt.respondWith(networkFirst(request));
    } else {
        if (request.cache === 'only-if-cached' && request.mode !== 'same-origin') {
            return;
        }
        evt.respondWith(cacheFirst(request));
    }
});

async function updateCache() {
    const cache = await caches.open(cacheName);
    cache.addAll(staticAssets);
}

function acceptsHTML(request) {
    return request.headers.get('Accept')
        .split(',')
        .some(type => type === 'text/html');
}

async function cacheFirst(request) {
    const cache = await caches.open(cacheName);
    const cached = await cache.match(request);

    return cached || fetch(request);
}

async function networkFirst(request) {
    try {
        const fresh = await fetch(request);
        return fresh;
    } catch {
        const cache = await caches.open(cacheName);
        return await cache.match('./offline.html');
    }
}