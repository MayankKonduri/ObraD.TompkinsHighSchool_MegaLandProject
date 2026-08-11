# Megaland Game Simulation

A networked Java adaptation of the *Megaland* board game.

`Java Swing` · `TCP sockets` · `2–5 players` · `25 classes` · `~8,000 lines`

Obra D. Tompkins High School

---

## Overview

This project is a Java-based simulation of the *Megaland* board game, developed
by our team to bring the game's mechanics and challenges to a digital format.
*Megaland* is a game of exploration, risk-taking, and resource gathering, where
players venture through a dangerous landscape to collect treasures, avoid
hazards, and build their own prosperous domain.

It runs as a client–server desktop application: one player hosts a game, the
others connect over the local network, and the full session — character
selection, card draws, hazard resolution, building purchases, and chat — is
synchronized across every client.

**Team:** Mayank Konduri · Nischal Konduri · Ayan Gupta

---

## Gameplay Rules

The simulation is built on the official rules of *Megaland*:

**Resource gathering.** Players explore various areas, gathering resources to be
used for purchases or advancement.

**Risk management.** Players must decide when to withdraw from exploration to
avoid losing resources to hazards.

**Building and scoring.** Gathered resources allow players to build
establishments that score points, progressing towards victory.

For the complete rules, see the
[rules document](https://docs.google.com/document/d/16IWZxScFI1Z-VdZrBNeJG5rALevHiWSy55krg7O0iKY/edit?usp=sharing)
or the illustrated rulebook bundled in `Assets/Rules/`.

---

## Features

**Exploration mechanics.** Players venture into different areas, encountering
random events and collecting resources. Level cards carry a skull count, a
treasure-chest flag, a jump flag, and a trade option.

**Hazard encounters.** Players face hazards, adding an element of risk where
resources may be lost if players overextend. A drawn card's skull count is
subtracted directly from the player's hearts, which start at 4.

**Building system.** Resources can be spent on structures, adding strategic
depth. Each building card carries a cost, plus star-card and night-card flags
that affect scoring.

**Score tracking.** Scores are computed from collected resources and constructed
buildings, resolving to a win/lose screen at the end of the session.

**In-game chat.** A dedicated chat panel relays messages through the host to
every connected client during play.

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

Communication uses prefix-tagged strings sent over `ObjectOutputStream`.
`CommandFromServer` defines 21 message types and `CommandFromClient` defines 16,
each as a `public static final String` constant with a matching `notify_*()`
helper — for example `START_GAME:`, `CHARACTER_SELECTION:`, and
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
└── out/                            # committed build output — see Known Issues
```

---

## Running the Game

**Requirements:** JDK 8 or newer with Swing. No external libraries or build tool
— the project is configured for IntelliJ IDEA via the included `.idea/` and
`.iml` files.

> [!IMPORTANT]
> Classes are declared `package Project.src;`, and images are loaded with paths
> relative to `Project/src/Images/`. The working directory must be the
> repository root — not `Project/` — or every image fails to load.

**IntelliJ IDEA**

1. Open the repository root as a project.
2. Run `FrameMain` (or `Frame`).
3. Confirm the working directory in the run configuration is the repository root.

**Command line**

```bash
# from the repository root
javac -d build Project/src/*.java
java -cp build Project.src.FrameMain
```

### Playing

1. **One player hosts.** Choose *Host*, enter a name, pick 2–5 players. The
   server binds to port 12345.
2. **Everyone else joins.** Choose *Connect* and enter the host's local IP.
3. Pick characters, select cards, and play. The host must stay connected — a
   `HOST_DISCONNECTED` broadcast ends the session for everyone.

> [!NOTE]
> Port 12345 is hardcoded in `HostPanel` and `ClientMain`. All players must be
> on the same local network, and the host's firewall must allow inbound
> connections on that port.
