
# NeuroVerse Plugin SDK

This repository provides the official SDK and documentation to build custom **NeuroVerse plugins** — standalone APKs that extend the capabilities of the NeuroVerse AI assistant on Android.

**Note:** This is **not** the main NeuroVerse app. It contains everything you need to create, package, and distribute your own plugins for NeuroVerse.

---

## 🔍 What Is the Plugin SDK?

- A development kit for third-party Android developers.
- Enables full Android API support within plugins.
- Dynamically loaded by the NeuroVerse host via `DexClassLoader`.
- Lightweight: Your plugin ships as a single ZIP containing the APK and a `manifest.json`.

---

## 🚀 Quick Start

1. **Clone this repository** or download the `plugin-api.jar` from [Releases].
2. **Create a new Android project** in Android Studio.
3. Copy `plugin-api.jar` into your module's `libs/` folder.
4. Extend the `Plugin` base class from `com.dark.plugin_api.info.Plugin`.
5. Override the lifecycle and AI hooks:
    - `getName()`
    - `onStart()` / `onStop()`
    - `submitAiRequest(prompt: String)`
    - `onAiResponse(response: JSONObject)`
6. Build your APK (`plugin.apk`).
7. Create a `manifest.json` describing your plugin.
8. Zip both `plugin.apk` + `manifest.json` → `MyPlugin.zip`.
9. Import the ZIP in NeuroVerse via the Plugin Manager.

---

## 📦 Example Plugin Structure

```

MyPlugin.zip
├── plugin.apk      # Your compiled plugin APK
└── manifest.json   # Metadata for NeuroVerse to load your plugin

````

### Sample `manifest.json`
```json
{
  "name": "PluginExample",
  "description": "An example plugin for launching apps by name.",
  "main": "com.test.plugin_example.PluginExample",
  "plugin-api-version": "1.0.0",
  "permissions": ["LAUNCH_APP"],
  "version": "1.0.0",
  "author": "YOUR NAME"
}
````

---

## 🛠 Plugin 1.0.0 Support

In **v1.0.0**, the Plugin SDK provides:

* **Base `Plugin` class** with lifecycle hooks:

    * `onStart()` / `onStop()` for initialization and cleanup.
    * `submitAiRequest(prompt: String)`: Build a JSON payload to forward prompts to NeuroVerse AI.
    * `onAiResponse(response: JSONObject)`: Receive AI responses and render a `ViewGroup` UI.
* **Manifest metadata**:

    * `name`, `description`, `main`, and `permissions` fields.
* **Dynamic loading** via `DexClassLoader` sandboxed in a read-only APK copy.
* **Full Android API access** (e.g., UI, network, database libraries).

---

## 🚧 Roadmap: Next Release

Coming soon in **v1.1.0+**:

* **Asynchronous APIs**: Non-blocking AI request/response handlers.
* **Resource support**: Explicit handling of plugin assets (layouts, drawables).
* **Enhanced security**: Permission scoping and sandbox-level controls.
* **Plugin configuration UI**: Standardized preference screens for user settings.
* **Template generator**: CLI tool to scaffold a new plugin project.

---

## 🤖 How AI Works

### In Your Plugin

1. **`submitAiRequest(prompt)`**

    * Called by NeuroVerse when your plugin needs AI input.
    * Return a `JSONObject` with a `messages` array:

      ```kotlin
      JSONObject().apply {
        put("messages", JSONArray().apply {
          put(JSONObject().apply {
            put("role", "system")
            put("content", "You are an AI agent plugin")
          })
          put(JSONObject().apply {
            put("role", "user")
            put("content", prompt)
          })
        })
      }
      ```
2. **`onAiResponse(response)`**

    * Receives the raw JSON from NeuroVerse AI.
    * Return a `ViewGroup` to display any UI (e.g., TextViews, RecyclerViews).

### In NeuroVerse Host

* NeuroVerse routes user prompts to the active plugin via `submitAiRequest`.
* The host aggregates messages and forwards them to the OpenRouter AI engine.
* AI responses are delivered back into `onAiResponse` for UI rendering.

---

## 🏗 Contributing & Support

* **Issues & PRs**: Submit on GitHub — we welcome bug reports and improvements.
* **Discussions**: Join the NeuroVerse community forum.
* **Feature Requests**: Comment on existing issues or open new ones.

---

> **Tip:** Keep your manifest permissions minimal and test your plugin thoroughly in a fresh NeuroVerse install.

[Releases]: https://github.com/your-org/neuroverse-plugin-sdk/releases


