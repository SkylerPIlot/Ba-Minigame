<img src="https://oldschool.runescape.wiki/images/thumb/e/e9/Penance_Queen.png/1280px-Penance_Queen.png?56343" width="200">

https://runelite.net/plugin-hub/show/ba-minigame

# Ba-minigame

Includes features to enhance the barbarian assault minigame gameplay experience.

The list `Green egg, Red egg, Blue egg, Hammer, Logs, Yellow egg, Crackers, Tofu, Worms` is automatically appended to the Ground Items plugin hidden list,
and then removed when the feature is turned off. If any of the items were previously on the list, they will not be removed.

The features from the standard Barbarian Assault plugin are automatically turned off, and then restored when this plugin is turned off.

## Ba minigame Configuration
![options](https://i.imgur.com/PwCSWRA.gif)

### Chat colors
Enable game chat colors on messages announced by this plugin

### Swap lobby ladder
Swap `Climb-down` with `Quick-start` on the wave lobby ladders

### Swap Commander Connad
Swap `Talk-to` with `Get-rewards` on Commander Connad

## In-game

### Display wave completed icon
Displays a checkmark over your role icon when all the NPCs for your role are dead 

### Display wave timer
Displays time elapsed inside the wave (see `Time Units` setting to change display mode)

### Display call change timer	
Shows time until next call change, looping every 30 seconds

![wave+call change timer](https://i.imgur.com/6UI8wUY.gif)

### Hide teammate role
Hides the line underneath the call showing which team member you're calling to.

### Time units
Controls time precision for wave/round times and penance deaths. Note: this does not affect the call change timer.

### Call change flash color
Changes the default flash color on a call change. Set a completely transparent color to disable the flash.

### Death times
Shows the time all penance monsters of a certain type are killed in an info box, the chat, or both

![](https://user-images.githubusercontent.com/17231890/94433907-e42ced00-0190-11eb-842f-ece6f7a00924.png)

![](https://user-images.githubusercontent.com/17231890/94433913-e4c58380-0190-11eb-95e9-8dd92e6fc89b.png)

### Death messages color
Recolors the penance death message relevant to the current role

### Display cannon eggs
Displays the amount of each type of egg loaded in cannon hoppers

![](https://user-images.githubusercontent.com/17231890/94430484-e771aa00-018b-11eb-8686-95e727445809.png)

### Inventory highlight
Changes the styles of all inventory highlights. If set to disabled, other inventory highlight settings will not display anything.

![](https://user-images.githubusercontent.com/17231890/94434338-8947c580-0191-11eb-9a22-1f4a46f1e689.png) ![](https://user-images.githubusercontent.com/17231890/94433736-ac25aa00-0190-11eb-8595-306a8a7156ac.png)

### Ground items highlight
Show ground item highlights

### Ground tiles highlight
Configures whether to highlight tiles containing ground items

### Call correction
Displays a game chat message warning if your teammate corrects their call

![call correction](https://i.imgur.com/zbp13wx.png)

## Attacker

![](https://i.imgur.com/Mf7GdeM.gif)

### Highlight arrows
Highlight arrows called by your teammate

![](https://user-images.githubusercontent.com/17231890/94431162-d4130e80-018c-11eb-89cc-11ddce11fda1.png)

### Highlight arrow color
Configures the color to highlight the called arrows

### Highlight attack style
Highlights the melee attack style called by your teammate

![](https://user-images.githubusercontent.com/17231890/94430848-71ba0e00-018c-11eb-8640-cbcaf1c56b55.png)

### Highlight attack style color
Configures the color to highlight the attack style

### Show runner tick timer
While playing as attacker, displays a 9 tick timer synchronized with runner movements

## Defender

![](https://i.imgur.com/xY0eDJq.gif)

### Highlight called bait
Highlights bait called by your teammate

![](https://user-images.githubusercontent.com/17231890/94431031-b34ab900-018c-11eb-89f5-23f659cd7fd4.png)

### Called bait color
Color to highlight the bait called by your teammate

### Highlight ground bait
Highlight bait dropped on the ground

### Ground bait color
Color to highlight the bait dropped on the ground

### Highlight ground logs/hammer
Highlight logs and hammer on the ground

### Ground logs/hammer color
Color to highlight the logs and hammer on the ground

### Show runner tick timer
While playing as defender, displays a 9 tick timer synchronized with runner movements

![runner tick timer](https://user-images.githubusercontent.com/17231890/94431310-03298000-018d-11eb-8e22-9e91fee8b060.png)

### Highlight broken traps
Highlight tiles of broken traps when penance runners are not dead

![broken trap](https://i.imgur.com/WGMnbdY.png)

### Broken traps color
The tile color of highlighted broken traps

### Broken traps opacity
The opacity of highlighted broken traps

### Broken traps border
Border width of highlighted broken traps

### Hide attack options
While playing as defender, hides attack options on penance monsters

## Collector

The below gif loops between the available configurations:
1. Highlight called only
2. Highlight all
3. Highlight none
4. Highlight disabled (which restores the highlighting from the ground items plugin)
![](https://i.imgur.com/LrFuK8U.gif)

### Show number of eggs collected
Displays number of eggs collected this wave next to the call change timer

![](https://user-images.githubusercontent.com/17231890/94431354-150b2300-018d-11eb-9f2f-5ffbd85b3478.png)

### Highlight eggs
Highlight egg colors on the ground: only for the called egg, for all eggs, or for none

### Menu highlight mode
Configures what to highlight in right-click menu

### Swap collection bag
Swap `Look-in` with `Empty` on the collection bag

### Swap collector horn
Swap `Use` with `Tell-defensive` on the collector horn

### Swap collector eggs
Swap `Use` with `Destroy` on green/blue/red eggs

### Show runner tick timer
While playing as collector, displays a 9 tick timer synchronized with runner movements

### Hide attack options
While playing as collector, hides attack options on penance monsters

## Healer

![](https://i.imgur.com/yTHoLcU.gif)

### Highlight called poison
Highlights poison food called by your teammate

![](https://user-images.githubusercontent.com/17231890/94431667-7e8b3180-018d-11eb-92db-80e1555bb81d.png)

### Called poison color
Configures the color to highlight the correct poison food

### Highlight incorrect notification
Highlights incorrect poison chat notification

![](https://user-images.githubusercontent.com/17231890/94433689-9d3ef780-0190-11eb-917f-913d46083048.png)

### Notification color
Recolors the incorrect poison notification message

### Show number of hitpoints healed
Displays number of hitpoints healed this wave next to the call change timer

![](https://user-images.githubusercontent.com/17231890/94431476-3ff57700-018d-11eb-9cca-a321189abd2b.png)

### Show teammate health bars
Displays a health bar where a teammate's remaining health is located

![](https://user-images.githubusercontent.com/17231890/94446578-bc458580-01a0-11eb-83fc-f517bea2dc3a.png)

### Health bars transparency
Transparency level of the teammate health bar

### Hide teammates health
Hides teammates health overlay

### Teammates health hotkey
When teammates health is hidden, pressing this hotkey shows it temporarily

### Show runner tick timer
While playing as healer, Displays a 9 tick timer synchronized with runner movements

### Swap healer spring
Swap `Drink-from` with `Take-from` on the healer spring

### Hide attack options
While playing as healer, hides attack options on penance monsters

## Post-game

### Duration
Displays duration after each wave and/or round, units controlled by `In-game:Time units` setting

![](https://user-images.githubusercontent.com/17231890/94430377-b3968480-018b-11eb-8699-e69e985fbf41.png)

### Reward points
Gives summary of reward points in the chat after each wave and/or round

![](https://user-images.githubusercontent.com/17231890/94748664-b6ef6300-0379-11eb-9ba0-3565392de1b1.png)

### Rewards breakdown
Gives summary of advanced points breakdown in the chat after each wave and/or round

![](https://user-images.githubusercontent.com/17231890/94748663-b656cc80-0379-11eb-96bb-8774e8bb493a.png)

## Points

### Display at
Set when to display role points overlay

![role points](https://i.imgur.com/OOMvTOV.png)

### Points mode
Set how to display role points

### Points counter
Set which role points counter to display in the overlay: the current total, points earned this round, or points earned this session

### Points tracking
Determines whose points will be tracked in the points overlay

### Reset round and session points
Change the value in this checkbox to manually reset accrued points in this round and session

### Session reset time
The session will automatically reset after the given time, during which no waves are completed

### Diary bonus
Include Kandarin hard diary +10% point bonus when displaying role points 
