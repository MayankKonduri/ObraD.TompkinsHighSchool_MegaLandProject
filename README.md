<div align="center">

# 🏰 Megaland Game Simulation

### A Networked Java Adaptation of the *Megaland* Board Game

<p>
  <img src="https://img.shields.io/badge/Java-Swing-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Networking-TCP_Sockets-4A90D9?style=for-the-badge" alt="Sockets" />
  <img src="https://img.shields.io/badge/IntelliJ-IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white" alt="IntelliJ" />
</p>

<p>
  <img src="https://img.shields.io/badge/players-2_to_5-blue?style=flat-square" alt="Players" />
  <img src="https://img.shields.io/badge/classes-25-informational?style=flat-square" alt="Classes" />
  <img src="https://img.shields.io/badge/lines-~8,000-green?style=flat-square" alt="LOC" />
  <img src="https://img.shields.io/badge/mode-host_%2F_join_LAN-orange?style=flat-square" alt="Mode" />
</p>

<sub>Obra D. Tompkins High School</sub>

</div>

---

## Overview

This project is a Java-based simulation of the *Megaland* board game, developed
by our team to bring the game's exciting mechanics and challenges to a digital
format. *Megaland* is a game of exploration, risk-taking, and resource
gathering, where players venture through a dangerous landscape to collect
treasures, avoid hazards, and build their own prosperous domain.

It runs as a **client–server desktop application**: one player hosts a game, the
others connect over the local network, and the full session — character
selection, card draws, hazard resolution, building purchases, and chat — is
synchronized across every client.

## Team Members

<div align="center">

| Member |
|:---|
| **Mayank Konduri** |
| **Nischal Konduri** |
| **Ayan Gupta** |

</div>

---

## Gameplay Rules

Our Java simulation is built based on the official rules of *Megaland*. The game
mechanics include:

- **Resource Gathering** — Players explore various areas, gathering resources to
  be used for purchases or advancement.
- **Risk Management** — Players must decide when to withdraw from exploration to
  avoid losing resources due to hazards.
- **Building and Scoring** — Gathered resources allow players to build
  establishments that score points, progressing towards victory.

For full gameplay rules, refer to the official
[Rules Document](https://docs.google.com/document/d/16IWZxScFI1Z-VdZrBNeJG5rALevHiWSy55krg7O0iKY/edit?usp=sharing),
or the illustrated rulebook bundled in `Assets/Rules/`.

---

## Features

<table>
<tr>
<td width="55"><h3 align="center">🗺️</h3></td>
<td>

**Exploration Mechanics**

Players venture into different areas, encountering random events and collecting
resources. Level cards carry a skull count, a treasure-chest flag, a jump flag,
and a trade option.

</td>
</tr>
<tr>
<td width="55"><h3 align="center">💀</h3></td>
<td>

**Hazard Encounters**

Players face hazards, adding an element of risk where resources may be lost if
players overextend. A drawn card's skull count is subtracted directly from the
player's hearts, which start at **4**.

</td>
</tr>
<tr>
<td width="55"><h3 align="center">🏗️</h3></td>
<td>

**Building System**

Resources can be used to build various structures, adding strategic depth. Each
building card carries a cost, plus star-card and night-card flags that affect
scoring.

</td>
</tr>
<tr>
<td width="55"><h3 align="center">🏆</h3></td>
<td>

**Score Tracking**

The game tracks scores based on players' collected resources and constructed
buildings, resolving to a win/lose screen at the end of the session.

</td>
</tr>
<tr>
<td width="55"><h3 align="center">💬</h3></td>
<td>

**In-Game Chat**

A dedicated chat panel relays messages through the host to every connected
client during play.

</td>
</tr>
</table>

---

## Architecture

One player runs the server; everyone else connects as a client. All game state
changes route through the host, which broadcasts them outward.

```
                    ┌──────────────────────┐
                    │   LoadingPanel       │  entry screen
                    └──────────┬───────────┘
                               ▼
              ┌────────────────┴────────────────┐
              ▼                                 ▼
      ┌───────────────┐                 ┌───────────────┐
      │   HostPanel   │                 │ ConnectPanel  │
      │  starts the   │                 │  joins by IP  │
      │    server     │                 │               │
      └───────┬───────┘                 └───────┬───────┘
              │                                 │
              ▼                                 ▼
      ┌───────────────┐    TCP :12345   ┌───────────────┐
      │  ServerMain   │◄───────────────►│  ClientMain   │
      │               │                 │               │
      │ ServerListener│  one thread     │ClientListener │
      │  per client   │  per connection │               │
      └───────┬───────┘                 └───────┬───────┘
              │                                 │
              │   CommandFromServer (21 msgs)   │
              │   CommandFromClient  (16 msgs)  │
              │   prefix-tagged strings over    │
              │   ObjectOutputStream            │
              ▼                                 ▼
      ┌─────────────────────────────────────────────────┐
      │  CharacterSelectPanel → CardSelectPanel         │
      │            → GamePanel → EndGamePanel           │
      └─────────────────────────────────────────────────┘
```

### Protocol

Communication uses **prefix-tagged strings** sent over `ObjectOutputStream`.
`CommandFromServer` defines 21 message types and `CommandFromClient` defines 16,
each as a `public static final String` constant with a matching `notify_*()`
helper — for example `START_GAME:`, `CHARACTER_SELECTION:`,
`HOST_DISCONNECTED:`. Keeping every wire format in two files means the client
and server can't silently drift apart.

---

## Repository Layout

```text
.
├── Project/
│   ├── src/                        # all 25 Java classes
│   │   ├── FrameMain.java          #   entry point
│   │   ├── Frame.java              #   top-level JFrame (1920×1040 in a scroll pane)
│   │   │
│   │   ├── ServerMain.java         #   host: accept loop, broadcast
│   │   ├── ServerListener.java     #   host: one thread per connected client
│   │   ├── ClientMain.java         #   client: connection + send
│   │   ├── ClientListener.java     #   client: inbound message dispatch
│   │   ├── CommandFromServer.java  #   21 server→client message types
│   │   ├── CommandFromClient.java  #   16 client→server message types
│   │   │
│   │   ├── Player.java             #   coins, hearts, jumps, owned cards
│   │   ├── LevelCard.java          #   skulls, treasure chest, jump, trade
│   │   ├── BuildingCards.java      #   cost, star card, night card
│   │   ├── TreasureCard.java
│   │   │
│   │   ├── LoadingPanel.java       #   ── UI flow ──
│   │   ├── HostPanel.java          #   host setup, 2–5 players
│   │   ├── ConnectPanel.java       #   join by IP
│   │   ├── WaitingForHostPanel.java
│   │   ├── CharacterSelectPanel.java
│   │   ├── CardSelectPanel.java
│   │   ├── GamePanel.java          #   main game loop (~2,900 lines)
│   │   ├── ChatPanel.java
│   │   ├── RulesPanel.java
│   │   ├── InGameRulesPanel.java
│   │   ├── EndGamePanel.java
│   │   └── WinningClass.java
│   │
│   └── src/Images/                 # 86 UI images (~63 MB)
│
├── Assets/
│   ├── Art/                        # card art archives (~44 MB)
│   ├── Models/                     # 3D character cards
│   └── Rules/                      # rulebook PDF + archive
│
├── Class Diagrams/                 # UML, week 1
├── Storyboards/                    # design storyboards
└── out/                            # ⚠ committed build output — see Known Issues
```

---

## Running the Game

**Requirements:** JDK 8 or newer with Swing. No external libraries or build tool
— the project is configured for IntelliJ IDEA via the included `.idea/` and
`.iml` files.

> [!IMPORTANT]
> Classes are declared `package Project.src;`, and images are loaded with paths
> relative to `Project/src/Images/`. **The working directory must be the
> repository root** — not `Project/` — or every image fails to load.

<details open>
<summary><b>IntelliJ IDEA</b></summary>

<br>

1. Open the repository root as a project.
2. Run `FrameMain` (or `Frame`).
3. Confirm the working directory in the run configuration is the repository root.

</details>

<details>
<summary><b>Command line</b></summary>

<br>

```bash
# from the repository root
javac -d build Project/src/*.java
java -cp build Project.src.FrameMain
```

</details>

### Playing

1. **One player hosts.** Choose *Host*, enter a name, pick 2–5 players. The
   server binds to port **12345**.
2. **Everyone else joins.** Choose *Connect* and enter the host's local IP.
3. Pick characters, select cards, and play. The host must stay connected — a
   `HOST_DISCONNECTED` broadcast ends the session for everyone.

> [!NOTE]
> Port `12345` is hardcoded in `HostPanel` and `ClientMain`. All players must be
> on the same local network, and the host's firewall must allow inbound
> connections on that port.

---

## ⚠️ Known Issues

Observations from reading the code. Nothing here has been changed.

> [!CAUTION]
> **The repository is roughly twice the size it needs to be.** `out/` contains a
> complete copy of the project — every source file, all 86 images, and all
> `Assets/` archives — alongside 76 compiled `.class` files. It accounts for
> **130 MB of the 260 MB total**.
>
> A `.gitignore` listing `out/` does exist, but it sits in `Project/`, so it
> never applies to the `out/` directory at the repository root. Moving that file
> to the root and running:
> ```bash
> git rm -r --cached out
> ```
> would halve the clone size. Note that this only stops tracking it going
> forward — the objects stay in history unless the history is rewritten.

> [!WARNING]
> **Windows-only image paths in `InGameRulesPanel`.** Rulebook images are loaded
> with hardcoded backslashes:
> ```java
> ImageIO.read(new File("Project\\src\\Images\\megaland+rulebook_ver+03-01.png"));
> ```
> Backslash is not a path separator on macOS or Linux, so the whole string is
> read as one filename and the load fails. Other classes get this right with
> `File.separator` — applying the same approach here, or better,
> `getClass().getResource("/Images/...")`, would make the rules screen work
> everywhere and survive being packaged into a JAR.

> [!NOTE]
> **`Player` ignores the hearts value passed to its constructor.** The parameter
> is assigned, then immediately overwritten on the last line:
> ```java
> this.playerHearts = playerHearts;
> ...
> this.playerHearts = 4;   // ← every player starts at 4 regardless
> ```
> If 4 is the intended starting value, dropping the parameter and declaring
> `private static final int STARTING_HEARTS = 4;` would say so clearly.

> [!NOTE]
> **`LevelCard.setLevelCardName()` does nothing.** It takes no argument and
> assigns the field to itself:
> ```java
> public void setLevelCardName(){ this.levelCardName = levelCardName; }
> ```
> Should be `setLevelCardName(String levelCardName)`. Harmless today because
> nothing calls it, but it will silently no-op the first time someone does.

> [!NOTE]
> **Two entry points with different threading behaviour.** `FrameMain.main()`
> calls `new Frame()` directly, while `Frame.main()` wraps it in
> `SwingUtilities.invokeLater`. Only the second is correct — Swing components
> should be created on the Event Dispatch Thread. Since `FrameMain` is the
> documented entry point, it's the one that should use `invokeLater`.

> [!NOTE]
> **`UnsafeTreasures.java` is an empty class** — twelve imports, a class
> declaration, and no body. Either unfinished or superseded.

> [!NOTE]
> **`GamePanel.java` is ~2,900 lines**, more than a third of the codebase, and
> holds turn flow, card resolution, scoring, and rendering together. Splitting
> the rules logic out from the drawing code would make the game loop testable
> independently of the UI — a natural next step rather than a defect.

> [!NOTE]
> **Windows and IDE artifacts are tracked.** 26 `desktop.ini` files, `.idea/`
> directories at two levels, and `untitled/.idea/workspace.xml`. Adding these to
> a root `.gitignore` keeps the tree clean:
> ```gitignore
> out/
> desktop.ini
> .idea/
> *.iml
> *.class
> .DS_Store
> ```

> [!NOTE]
> **Fixed 1920×1040 window.** `Frame` sets that preferred size directly, so on a
> smaller display the layout relies on the surrounding `JScrollPane` rather than
> adapting. Worth knowing before a demo on unfamiliar hardware.

---

<div align="center">
<sub>Built with Java Swing · Obra D. Tompkins High School</sub>
</div>
