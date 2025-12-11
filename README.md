# TermuxOS

TermuxOS is an Android APK that provides a Linux-like operating environment using a Termux-compatible userland, supporting Ubuntu, Debian, and Kali via proot, all without requiring root access.

## Overview

The APK delivers:
- A real terminal experience (bash/zsh)
- Full package management
- Multi-distro support
- A modern UI control layer
- 100% open-source compliance

## Architecture

```
┌───────────────────────────┐
│        Android APK        │
│  (TermuxOS Application)  │
└────────────▲──────────────┘
             │
┌────────────┴──────────────┐
│   Native Android Layer    │
│ (PTY, IO, Storage, IPC)  │
└────────────▲──────────────┘
             │
┌────────────┴──────────────┐
│   Linux Userland Core     │
│ (bash, apt, coreutils)   │
└────────────▲──────────────┘
             │
┌────────────┴──────────────┐
│   proot-distro Manager    │
│ (Ubuntu / Debian / Kali) │
└───────────────────────────┘
```

### Components

1.  **Android Application Layer**: Kotlin/Java UI and logic.
2.  **Terminal Engine**: Handles PTY sessions and shell interaction.
3.  **Linux Userland Bootstrap**: Sets up the minimal filesystem (`usr/`, `bin/`, etc.).
4.  **Proot Manager**: Manages `proot` execution for isolated Linux environments.

## Build Instructions

This project is a standard Android Gradle project.

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 17+
- Android SDK Platform 34

### Building via Command Line

```bash
./gradlew assembleDebug
```

### Building via Android Studio

1. Open Android Studio.
2. Select "Open" and navigate to the project root.
3. Wait for Gradle Sync to complete.
4. Run the `app` configuration.

## Project Structure

- `app/src/main/java/com/termuxos`: Source code
  - `LinuxBootstrap.kt`: Filesystem setup logic.
  - `ProotManager.kt`: Proot command generation.
  - `TerminalEngine.kt`: Terminal emulation interface.
  - `MainActivity.kt`: Main entry point.
- `app/src/main/res`: Resources (Layouts, Strings, Themes).

## Status

Currently, the project contains the core architectural skeleton. The logic for `LinuxBootstrap` and `TerminalEngine` is simulated for demonstration purposes and needs to be replaced with actual JNI/Asset extraction logic in future phases.
