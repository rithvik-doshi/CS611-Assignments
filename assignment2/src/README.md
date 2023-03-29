# CS611 - Assignment 3

## Legends: Monsters and Heroes

- Rithvik Doshi
- [rithvik@bu.edu](mailto:rithvik@bu.edu)
- U98773385

Files
- data - contains object attributes
  - Armory.txt
  - Dragons.txt
  - Exoskeletons.txt
  - FireSpells.txt
  - IceSpells.txt
  - LightningSpells.txt
  - Paladins.txt
  - Potions.txt
  - Sorcerers.txt
  - Spirits.txt
  - Warriors.txt
  - Weaponry.txt
- UML.png - UML diagram of project
- Accessible.java - interface for accessible objects
- Color.java
- Consumable.java - interface for consumable objects
- DataList.java - class to store data
- DataLoader.java - class to load data
- DataMap.java - class to store data
- GameEngine.java - class to run game
- Item.java - class to store item data
  - Armor.java
  - Potion.java (consumable)
  - Spell.java (consumable)
  - Weapon.java
- Legend.java
  - Hero.java
  - Monster.java
- LegendStatus.java - enum for legend status
- LMH - Game object
- Main.java - main class
- Map.java - class to store map data
- Space.java - abstract space object
  - Common.java (accessible)
  - Inaccessible.java
  - Market.java (accessible)

### Java Dependencies:
- Java 8
  - java.io.BufferedReader
  - java.io.FileReader
  - java.io.IOException
  - java.util.stream.Collectors
  - java.util.ArrayList
  - java.io.File
  - java.io.FileNotFoundException
  - java.nio.file.Path
  - java.nio.file.Paths
  - java.util.Scanner
  - java.util.Collections 
  - java.util.TreeMap

## Game Instructions and Information

### 1. The world of Legends: Monsters, and Heroes
The world of the game is represented by a fixed, square grid of spaces. The grid contains three types of spaces:
- Inaccessible spaces, which the heroes cannot enter (marked by an X)
- Market spaces, where items can be bought or sold (marked by an M)
- Common spaces, where battles have a chance of occurring (marked by an empty yellow space)

Every time the heroes visit a common tile, there is a chance that they will engage in a battle with the monsters.

Use W/A/S/D to move the heroes around the map. The heroes can only move one space at a time. The heroes can only move to a space that is adjacent to their current position.

Use I to check information about your entire hero party.

The game will progress until all players are dead. If you wish to quit, press Q in the main menu. You cannot flee a battle or quit the game from the market, so be wary of these situations!

### 2. Battle
The battle pits the party of heroes against a group of monsters. A battle consists of multiple rounds, where the heroes and monsters each make moves. The fight ends when the HP of either all the monsters or all the heroes is zero. If the heroes win the fight, they earn money and experience, potentially leveling up. If the monsters win the fight, the game is over.

The heroes move first in each round. During the heroes’ turn, the player chooses for each hero whether they will do one of the following:
- Attack, using the hero’s equipped weapon
- Cast a spell from the hero’s inventory
- Use a potion from the hero’s inventory
- Equip a weapon or piece of armor

In addition, at any time during the heroes’ turn, the player can display the statistics of a hero or a monster. This will not consume the turn
The heroes win the battle if they defeat all the monsters. If the heroes win, they gain experience and money, and all fainted heroes are revived.

When it is your turn in the battle, select an option from the menu. You will not be able to attack if you do not have a weapon equipped. You will not be able to cast a spell if you do not have any spells in your inventory. You will not be able to use a potion if you do not have any potions in your inventory. You will not be able to equip a weapon or armor if you do not have any in your inventory.

### 3. Heroes and Monsters
At the beginning of the game, up to three players can select a hero from the provided list. That will be your character for the rest of the game.

Each hero has a name, a level with experience points, hit points, mana points, a strength, dexterity and agility value, starting money and an inventory that can hold up to 10 items.

In addition to this, you can choose heroes of different classes. The classes are:
- Warriors, who are given higher strength and agility.
- Sorcerers, who are given higher dexterity and agility.
- Paladins, who are given higher strength and dexterity.

During your travels, you may come across a group of monsters. A monster has a name, a level, hit points, a damage, defense and dodge value.

You can read up on the kinds of monsters in the provided text files. There are three kinds of monsters:
- Dragons, who are given higher damage
- Exoskeletons, who are given higher defense
- Spirits, who are given higher dodge

### 4. The Market

Heroes can buy or sell items at any market on the map. To access the market, your hero party has to be located on a market space. To access the market, press 'M' on the home screen at the beginning of the turn.

Follow the market instructions to buy or sell an item. You can only buy or sell one item per turn. You can only buy items that you have enough money for. You can only sell items that you have in your inventory. If you sell an item, you will get back half of its purchase price as money.

### 5. Items

There are four kinds of items in the game:

1. Weapons: used to attack a monster.
2. Armor: used to defend against an attack from a monster.
3. Potions: used to restore a certain hero statistic.
4. Spells: consume mana points and are used to attack a monster and reduce a certain monster statistic.
   1. Ice spells reduce the defense of the target
   2. Fire spells reduce the defense of the target
   3. Lightning spells reduce the dodge chance of the target

Keep in mind that in order to use a weapon or armor, you must equip it at the beginning of a battle.
Potions and spells are consumable items. Potions can only be drunk once, and spells can only be cast three times. Make sure that you have enough MP to cast a spell, as it costs a certain amount to be able to cast each time!

These items can be bought at a market. For some items, you may have to be at a certain level to be able to use them, so be careful!

### 6. Stat Calculations

The following are the formulas used to calculate the statistics of a hero or monster:

Spell damage = `spell_base_damage + (hero dexterity/10000) * spell_base_damage`

Starting HP for Legend: `100 * starting level`

At every new level, Hero's HP is set to `100 * level` and MP is doubled

Hero's attack damage (with a weapon, hero cannot attack without a weapon) = `(strength + weapon damage) * 0.1`

Legend dodge chance = `(1 - Math.pow(dodgeFactor, legend dodgeAmt))` (dodgeFactor is 0.99 for heroes, 0.999 for monsters)

Exp needed to level up: `10 * current level`

Players and monsters never regain health/mp during a battle.

Hero gold gain = `100 * max level of monsters defeated`

Hero exp gain = `size of monster party + level of monsters defeated * 10`

Hero hp/mp gain = `baseHP or baseMP stat * 1.1`

Monster level/abilities scale to the highest level among the hero party.

The party size is between 1-3 players.

## Notes

1. Files to be parsed should be stored in ConfigFiles, for parser class to read class
2. Bonus features/implementations:
   1. \# of  monsters that are generated is random, but will usually be close to the \# of players playing. This is given by the formula:
      1. ``Math.random() < (0.8 - (Math.pow(0.4, heroes.size())))``
   2. Use of color to highlight important text and information.
      1. Created a class to encapsulate color codes for ANSI escape sequences.
      2. Created a static method that takes in a string and a color, and returns a string with the color code at the beginning and the reset code at the end.
   3. Use of interfaces to specify specialized behavior for certain classes.
      1. Consumable interface for potions and spells
      2. Accessible interface for market and common spaces
3. 

## How to compile and run

1. Make sure that the text configfiles are located in ``src/data/`` and all other files in ``src``.
2. Run Main.java using javac
3. Enjoy!

## Input/Output Example

```
/Users/rithvikdoshi/Library/Java/JavaVirtualMachines/openjdk-19.0.2/Contents/Home/bin/java -javaagent:/Users/rithvikdoshi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/223.8617.56/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=50757:/Users/rithvikdoshi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/223.8617.56/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/rithvikdoshi/Documents/BU/JuniorSpring/611/homework/2/assignment2/out/production/assignment2 Main
Welcome to Legends: Monsters and Heroes!

Do you want to see the instructions? (Y/N)
1. Y
2. N
Choose an option: 2
Select a map to play on: 
Generating map...
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Confirm this map? (1 for Yes, 2 for No)
1. Y
2. N
Choose an option: 1
Choose the number of heroes to embark on a quest
Enter a number between 1 and 3: 2
Paladins:
	Amaryllis_Astra:
		agility: 500
		dexterity: 500
		mana: 500
		starting experience: 5
		starting money: 2500
		strength: 500
	Caliber_Heist:
		agility: 400
		dexterity: 400
		mana: 400
		starting experience: 8
		starting money: 2500
		strength: 400
	Garl_Glittergold:
		agility: 500
		dexterity: 400
		mana: 100
		starting experience: 5
		starting money: 2500
		strength: 600
	Parzival:
		agility: 650
		dexterity: 700
		mana: 300
		starting experience: 7
		starting money: 2500
		strength: 750
	Sehanine_Moonbow:
		agility: 700
		dexterity: 700
		mana: 300
		starting experience: 7
		starting money: 2500
		strength: 750
	Skoraeus_Stonebones:
		agility: 600
		dexterity: 350
		mana: 250
		starting experience: 4
		starting money: 2500
		strength: 650
Sorcerers:
	Kalabar:
		agility: 400
		dexterity: 600
		mana: 800
		starting experience: 6
		starting money: 2500
		strength: 850
	Reign_Havoc:
		agility: 800
		dexterity: 800
		mana: 800
		starting experience: 8
		starting money: 2500
		strength: 800
	Reverie_Ashels:
		agility: 700
		dexterity: 400
		mana: 900
		starting experience: 7
		starting money: 2500
		strength: 800
	Rillifane_Rallathil:
		agility: 450
		dexterity: 500
		mana: 1300
		starting experience: 9
		starting money: 2500
		strength: 750
	Segojan_Earthcaller:
		agility: 500
		dexterity: 650
		mana: 900
		starting experience: 5
		starting money: 2500
		strength: 800
	Skye_Soar:
		agility: 400
		dexterity: 500
		mana: 1000
		starting experience: 5
		starting money: 2500
		strength: 700
Warriors:
	Eunoia_Cyn:
		agility: 800
		dexterity: 600
		mana: 400
		starting experience: 6
		starting money: 2500
		strength: 700
	Flandal_Steelskin:
		agility: 650
		dexterity: 700
		mana: 200
		starting experience: 7
		starting money: 2500
		strength: 750
	Gaerdal_Ironhand:
		agility: 500
		dexterity: 600
		mana: 100
		starting experience: 7
		starting money: 1354
		strength: 700
	Muamman_Duathall:
		agility: 500
		dexterity: 750
		mana: 300
		starting experience: 6
		starting money: 2546
		strength: 900
	Sehanine_Monnbow:
		agility: 800
		dexterity: 500
		mana: 600
		starting experience: 8
		starting money: 2500
		strength: 700
	Undefeated_Yoj:
		agility: 400
		dexterity: 700
		mana: 400
		starting experience: 7
		starting money: 2500
		strength: 800

Hero 1, choose your class and hero!
1. Paladins
2. Sorcerers
3. Warriors
Choose an option: 1
1. Amaryllis_Astra
2. Caliber_Heist
3. Garl_Glittergold
4. Parzival
5. Sehanine_Moonbow
6. Skoraeus_Stonebones
Choose an option: 4
You have chosen: Parzival
Hero 2, choose your class and hero!
1. Paladins
2. Sorcerers
3. Warriors
Choose an option: 3
1. Eunoia_Cyn
2. Flandal_Steelskin
3. Gaerdal_Ironhand
4. Muamman_Duathall
5. Sehanine_Monnbow
6. Undefeated_Yoj
Choose an option: 5
You have chosen: Sehanine_Monnbow
Market!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| H |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info, M for market: m
Armory:
	Breastplate:
		cost: 350
		damage reduction: 600
		required level: 3
	Full_Body_Armor:
		cost: 1000
		damage reduction: 1100
		required level: 8
	Guardian_Angel:
		cost: 1000
		damage reduction: 1000
		required level: 10
	Platinum_Shield:
		cost: 150
		damage reduction: 200
		required level: 1
	Wizard_Shield:
		cost: 1200
		damage reduction: 1500
		required level: 10
FireSpells:
	Breath_of_Fire:
		cost: 350
		damage: 450
		mana cost: 100
		required level: 1
	Flame_Tornado:
		cost: 700
		damage: 850
		mana cost: 300
		required level: 4
	Heat_Wave:
		cost: 450
		damage: 600
		mana cost: 150
		required level: 2
	Hell_Storm:
		cost: 600
		damage: 950
		mana cost: 600
		required level: 3
	Lava_Comet:
		cost: 800
		damage: 1000
		mana cost: 550
		required level: 7
IceSpells:
	Arctic_Storm:
		cost: 700
		damage: 800
		mana cost: 300
		required level: 6
	Frost_Blizzard:
		cost: 750
		damage: 850
		mana cost: 350
		required level: 5
	Ice_Blade:
		cost: 250
		damage: 450
		mana cost: 100
		required level: 1
	Snow_Cannon:
		cost: 500
		damage: 650
		mana cost: 250
		required level: 2
LightningSpells:
	Electric_Arrows:
		cost: 550
		damage: 650
		mana cost: 200
		required level: 5
	Lightning_Dagger:
		cost: 400
		damage: 500
		mana cost: 150
		required level: 1
	Spark_Needles:
		cost: 500
		damage: 600
		mana cost: 200
		required level: 2
	Thunder_Blast:
		cost: 750
		damage: 950
		mana cost: 400
		required level: 4
Potions:
	Ambrosia:
		attribute affected: All_Health/Mana/Strength/Dexterity/Defense/Agility
		attribute increase: 150
		cost: 1000
		required level: 8
	Healing_Potion:
		attribute affected: Health
		attribute increase: 100
		cost: 250
		required level: 1
	Luck_Elixir:
		attribute affected: Agility
		attribute increase: 65
		cost: 500
		required level: 4
	Magic_Potion:
		attribute affected: Mana
		attribute increase: 100
		cost: 350
		required level: 2
	Mermaid_Tears:
		attribute affected: Health/Mana/Strength/Agility
		attribute increase: 100
		cost: 850
		required level: 5
	Strength_Potion:
		attribute affected: Strength
		attribute increase: 75
		cost: 200
		required level: 1
Weaponry:
	Axe:
		cost: 550
		damage: 850
		required hands: 1
		required level: 5
	Bow:
		cost: 300
		damage: 500
		required hands: 2
		required level: 2
	Dagger:
		cost: 200
		damage: 250
		required hands: 1
		required level: 1
	Scythe:
		cost: 1000
		damage: 1100
		required hands: 2
		required level: 6
	Sword:
		cost: 500
		damage: 800
		required hands: 1
		required level: 1
	TSwords:
		cost: 1400
		damage: 1600
		required hands: 2
		required level: 8

-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 2500
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (0/10):
Empty!
-----------------------------------------

Parzival, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 2
Select an item to buy: 
1. Axe
2. Bow
3. Dagger
4. Scythe
5. Sword
6. TSwords
Choose an option: 4
You have selected: Scythe
Buy Scythe for 1000? Parzival has 2500 gold.
1. Yes
2. No
Choose an option: 1
Successfully bought Scythe
-----------------------------------------
Name: Sehanine_Monnbow
Level: 8
HP: 800
Class: Warriors
MP: 600
Strength: 840
Agility: 960
Dexterity: 500
Money: 2500
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (0/10):
Empty!
-----------------------------------------

Sehanine_Monnbow, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 2
Select an item to buy: 
1. Axe
2. Bow
3. Dagger
4. Scythe
5. Sword
6. TSwords
Choose an option: 6
You have selected: TSwords
Buy TSwords for 1400? Sehanine_Monnbow has 2500 gold.
1. Yes
2. No
Choose an option: 1
Successfully bought TSwords
Party of heroes, would you like to continue shopping?
1. Yes
2. No
Choose an option: 1
Armory:
	Breastplate:
		cost: 350
		damage reduction: 600
		required level: 3
	Full_Body_Armor:
		cost: 1000
		damage reduction: 1100
		required level: 8
	Guardian_Angel:
		cost: 1000
		damage reduction: 1000
		required level: 10
	Platinum_Shield:
		cost: 150
		damage reduction: 200
		required level: 1
	Wizard_Shield:
		cost: 1200
		damage reduction: 1500
		required level: 10
FireSpells:
	Breath_of_Fire:
		cost: 350
		damage: 450
		mana cost: 100
		required level: 1
	Flame_Tornado:
		cost: 700
		damage: 850
		mana cost: 300
		required level: 4
	Heat_Wave:
		cost: 450
		damage: 600
		mana cost: 150
		required level: 2
	Hell_Storm:
		cost: 600
		damage: 950
		mana cost: 600
		required level: 3
	Lava_Comet:
		cost: 800
		damage: 1000
		mana cost: 550
		required level: 7
IceSpells:
	Arctic_Storm:
		cost: 700
		damage: 800
		mana cost: 300
		required level: 6
	Frost_Blizzard:
		cost: 750
		damage: 850
		mana cost: 350
		required level: 5
	Ice_Blade:
		cost: 250
		damage: 450
		mana cost: 100
		required level: 1
	Snow_Cannon:
		cost: 500
		damage: 650
		mana cost: 250
		required level: 2
LightningSpells:
	Electric_Arrows:
		cost: 550
		damage: 650
		mana cost: 200
		required level: 5
	Lightning_Dagger:
		cost: 400
		damage: 500
		mana cost: 150
		required level: 1
	Spark_Needles:
		cost: 500
		damage: 600
		mana cost: 200
		required level: 2
	Thunder_Blast:
		cost: 750
		damage: 950
		mana cost: 400
		required level: 4
Potions:
	Ambrosia:
		attribute affected: All_Health/Mana/Strength/Dexterity/Defense/Agility
		attribute increase: 150
		cost: 1000
		required level: 8
	Healing_Potion:
		attribute affected: Health
		attribute increase: 100
		cost: 250
		required level: 1
	Luck_Elixir:
		attribute affected: Agility
		attribute increase: 65
		cost: 500
		required level: 4
	Magic_Potion:
		attribute affected: Mana
		attribute increase: 100
		cost: 350
		required level: 2
	Mermaid_Tears:
		attribute affected: Health/Mana/Strength/Agility
		attribute increase: 100
		cost: 850
		required level: 5
	Strength_Potion:
		attribute affected: Strength
		attribute increase: 75
		cost: 200
		required level: 1
Weaponry:
	Axe:
		cost: 550
		damage: 850
		required hands: 1
		required level: 5
	Bow:
		cost: 300
		damage: 500
		required hands: 2
		required level: 2
	Dagger:
		cost: 200
		damage: 250
		required hands: 1
		required level: 1
	Scythe:
		cost: 1000
		damage: 1100
		required hands: 2
		required level: 6
	Sword:
		cost: 500
		damage: 800
		required hands: 1
		required level: 1
	TSwords:
		cost: 1400
		damage: 1600
		required hands: 2
		required level: 8

-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 1500
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (1/10):
Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100

-----------------------------------------

Parzival, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 3
Select an item to buy: 
1. Breastplate
2. Full_Body_Armor
3. Guardian_Angel
4. Platinum_Shield
5. Wizard_Shield
Choose an option: 3
You have selected: Guardian_Angel
Buy Guardian_Angel for 1000? Parzival has 1500 gold.
1. Yes
2. No
Choose an option: 1
You are not high enough level to buy this item!
Failed to buy Guardian_Angel
-----------------------------------------
Name: Sehanine_Monnbow
Level: 8
HP: 800
Class: Warriors
MP: 600
Strength: 840
Agility: 960
Dexterity: 500
Money: 1100
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (1/10):
TSwords:
	Purchase Price: 1400
	Level: 8
	Damage: 1600

-----------------------------------------

Sehanine_Monnbow, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 6
Select an item to buy: 
1. Electric_Arrows
2. Lightning_Dagger
3. Spark_Needles
4. Thunder_Blast
Choose an option: 2
You have selected: Lightning_Dagger
Buy Lightning_Dagger for 400? Sehanine_Monnbow has 1100 gold.
1. Yes
2. No
Choose an option: 1
Successfully bought Lightning_Dagger
Party of heroes, would you like to continue shopping?
1. Yes
2. No
Choose an option: 1
Armory:
	Breastplate:
		cost: 350
		damage reduction: 600
		required level: 3
	Full_Body_Armor:
		cost: 1000
		damage reduction: 1100
		required level: 8
	Guardian_Angel:
		cost: 1000
		damage reduction: 1000
		required level: 10
	Platinum_Shield:
		cost: 150
		damage reduction: 200
		required level: 1
	Wizard_Shield:
		cost: 1200
		damage reduction: 1500
		required level: 10
FireSpells:
	Breath_of_Fire:
		cost: 350
		damage: 450
		mana cost: 100
		required level: 1
	Flame_Tornado:
		cost: 700
		damage: 850
		mana cost: 300
		required level: 4
	Heat_Wave:
		cost: 450
		damage: 600
		mana cost: 150
		required level: 2
	Hell_Storm:
		cost: 600
		damage: 950
		mana cost: 600
		required level: 3
	Lava_Comet:
		cost: 800
		damage: 1000
		mana cost: 550
		required level: 7
IceSpells:
	Arctic_Storm:
		cost: 700
		damage: 800
		mana cost: 300
		required level: 6
	Frost_Blizzard:
		cost: 750
		damage: 850
		mana cost: 350
		required level: 5
	Ice_Blade:
		cost: 250
		damage: 450
		mana cost: 100
		required level: 1
	Snow_Cannon:
		cost: 500
		damage: 650
		mana cost: 250
		required level: 2
LightningSpells:
	Electric_Arrows:
		cost: 550
		damage: 650
		mana cost: 200
		required level: 5
	Lightning_Dagger:
		cost: 400
		damage: 500
		mana cost: 150
		required level: 1
	Spark_Needles:
		cost: 500
		damage: 600
		mana cost: 200
		required level: 2
	Thunder_Blast:
		cost: 750
		damage: 950
		mana cost: 400
		required level: 4
Potions:
	Ambrosia:
		attribute affected: All_Health/Mana/Strength/Dexterity/Defense/Agility
		attribute increase: 150
		cost: 1000
		required level: 8
	Healing_Potion:
		attribute affected: Health
		attribute increase: 100
		cost: 250
		required level: 1
	Luck_Elixir:
		attribute affected: Agility
		attribute increase: 65
		cost: 500
		required level: 4
	Magic_Potion:
		attribute affected: Mana
		attribute increase: 100
		cost: 350
		required level: 2
	Mermaid_Tears:
		attribute affected: Health/Mana/Strength/Agility
		attribute increase: 100
		cost: 850
		required level: 5
	Strength_Potion:
		attribute affected: Strength
		attribute increase: 75
		cost: 200
		required level: 1
Weaponry:
	Axe:
		cost: 550
		damage: 850
		required hands: 1
		required level: 5
	Bow:
		cost: 300
		damage: 500
		required hands: 2
		required level: 2
	Dagger:
		cost: 200
		damage: 250
		required hands: 1
		required level: 1
	Scythe:
		cost: 1000
		damage: 1100
		required hands: 2
		required level: 6
	Sword:
		cost: 500
		damage: 800
		required hands: 1
		required level: 1
	TSwords:
		cost: 1400
		damage: 1600
		required hands: 2
		required level: 8

-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 1500
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (1/10):
Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100

-----------------------------------------

Parzival, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 3
Select an item to buy: 
1. Breastplate
2. Full_Body_Armor
3. Guardian_Angel
4. Platinum_Shield
5. Wizard_Shield
Choose an option: 2
You have selected: Full_Body_Armor
Buy Full_Body_Armor for 1000? Parzival has 1500 gold.
1. Yes
2. No
Choose an option: 1
You are not high enough level to buy this item!
Failed to buy Full_Body_Armor
-----------------------------------------
Name: Sehanine_Monnbow
Level: 8
HP: 800
Class: Warriors
MP: 600
Strength: 840
Agility: 960
Dexterity: 500
Money: 700
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (2/10):
TSwords:
	Purchase Price: 1400
	Level: 8
	Damage: 1600
Lightning_Dagger:
	Purchase Price: 400
	Level: 1
	Type: Lightning
	Mana Cost: 150
	Uses left: 3
	Damage: 500

-----------------------------------------

Sehanine_Monnbow, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 1
Select an item to buy: 
1. Ambrosia
2. Healing_Potion
3. Luck_Elixir
4. Magic_Potion
5. Mermaid_Tears
6. Strength_Potion
Choose an option: 2
You have selected: Healing_Potion
Buy Healing_Potion for 250? Sehanine_Monnbow has 700 gold.
1. Yes
2. No
Choose an option: 1
Successfully bought Healing_Potion
Party of heroes, would you like to continue shopping?
1. Yes
2. No
Choose an option: 1
Armory:
	Breastplate:
		cost: 350
		damage reduction: 600
		required level: 3
	Full_Body_Armor:
		cost: 1000
		damage reduction: 1100
		required level: 8
	Guardian_Angel:
		cost: 1000
		damage reduction: 1000
		required level: 10
	Platinum_Shield:
		cost: 150
		damage reduction: 200
		required level: 1
	Wizard_Shield:
		cost: 1200
		damage reduction: 1500
		required level: 10
FireSpells:
	Breath_of_Fire:
		cost: 350
		damage: 450
		mana cost: 100
		required level: 1
	Flame_Tornado:
		cost: 700
		damage: 850
		mana cost: 300
		required level: 4
	Heat_Wave:
		cost: 450
		damage: 600
		mana cost: 150
		required level: 2
	Hell_Storm:
		cost: 600
		damage: 950
		mana cost: 600
		required level: 3
	Lava_Comet:
		cost: 800
		damage: 1000
		mana cost: 550
		required level: 7
IceSpells:
	Arctic_Storm:
		cost: 700
		damage: 800
		mana cost: 300
		required level: 6
	Frost_Blizzard:
		cost: 750
		damage: 850
		mana cost: 350
		required level: 5
	Ice_Blade:
		cost: 250
		damage: 450
		mana cost: 100
		required level: 1
	Snow_Cannon:
		cost: 500
		damage: 650
		mana cost: 250
		required level: 2
LightningSpells:
	Electric_Arrows:
		cost: 550
		damage: 650
		mana cost: 200
		required level: 5
	Lightning_Dagger:
		cost: 400
		damage: 500
		mana cost: 150
		required level: 1
	Spark_Needles:
		cost: 500
		damage: 600
		mana cost: 200
		required level: 2
	Thunder_Blast:
		cost: 750
		damage: 950
		mana cost: 400
		required level: 4
Potions:
	Ambrosia:
		attribute affected: All_Health/Mana/Strength/Dexterity/Defense/Agility
		attribute increase: 150
		cost: 1000
		required level: 8
	Healing_Potion:
		attribute affected: Health
		attribute increase: 100
		cost: 250
		required level: 1
	Luck_Elixir:
		attribute affected: Agility
		attribute increase: 65
		cost: 500
		required level: 4
	Magic_Potion:
		attribute affected: Mana
		attribute increase: 100
		cost: 350
		required level: 2
	Mermaid_Tears:
		attribute affected: Health/Mana/Strength/Agility
		attribute increase: 100
		cost: 850
		required level: 5
	Strength_Potion:
		attribute affected: Strength
		attribute increase: 75
		cost: 200
		required level: 1
Weaponry:
	Axe:
		cost: 550
		damage: 850
		required hands: 1
		required level: 5
	Bow:
		cost: 300
		damage: 500
		required hands: 2
		required level: 2
	Dagger:
		cost: 200
		damage: 250
		required hands: 1
		required level: 1
	Scythe:
		cost: 1000
		damage: 1100
		required hands: 2
		required level: 6
	Sword:
		cost: 500
		damage: 800
		required hands: 1
		required level: 1
	TSwords:
		cost: 1400
		damage: 1600
		required hands: 2
		required level: 8

-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 1500
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (1/10):
Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100

-----------------------------------------

Parzival, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 3
Select an item to buy: 
1. Breastplate
2. Full_Body_Armor
3. Guardian_Angel
4. Platinum_Shield
5. Wizard_Shield
Choose an option: 1
You have selected: Breastplate
Buy Breastplate for 350? Parzival has 1500 gold.
1. Yes
2. No
Choose an option: 1
Successfully bought Breastplate
-----------------------------------------
Name: Sehanine_Monnbow
Level: 8
HP: 800
Class: Warriors
MP: 600
Strength: 840
Agility: 960
Dexterity: 500
Money: 450
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (3/10):
TSwords:
	Purchase Price: 1400
	Level: 8
	Damage: 1600
Lightning_Dagger:
	Purchase Price: 400
	Level: 1
	Type: Lightning
	Mana Cost: 150
	Uses left: 3
	Damage: 500
Healing_Potion:
	Purchase Price: 250
	Level: 1
	Effect: Health
	Change: 100

-----------------------------------------

Sehanine_Monnbow, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 3
Party of heroes, would you like to continue shopping?
1. Yes
2. No
Choose an option: 1
Armory:
	Breastplate:
		cost: 350
		damage reduction: 600
		required level: 3
	Full_Body_Armor:
		cost: 1000
		damage reduction: 1100
		required level: 8
	Guardian_Angel:
		cost: 1000
		damage reduction: 1000
		required level: 10
	Platinum_Shield:
		cost: 150
		damage reduction: 200
		required level: 1
	Wizard_Shield:
		cost: 1200
		damage reduction: 1500
		required level: 10
FireSpells:
	Breath_of_Fire:
		cost: 350
		damage: 450
		mana cost: 100
		required level: 1
	Flame_Tornado:
		cost: 700
		damage: 850
		mana cost: 300
		required level: 4
	Heat_Wave:
		cost: 450
		damage: 600
		mana cost: 150
		required level: 2
	Hell_Storm:
		cost: 600
		damage: 950
		mana cost: 600
		required level: 3
	Lava_Comet:
		cost: 800
		damage: 1000
		mana cost: 550
		required level: 7
IceSpells:
	Arctic_Storm:
		cost: 700
		damage: 800
		mana cost: 300
		required level: 6
	Frost_Blizzard:
		cost: 750
		damage: 850
		mana cost: 350
		required level: 5
	Ice_Blade:
		cost: 250
		damage: 450
		mana cost: 100
		required level: 1
	Snow_Cannon:
		cost: 500
		damage: 650
		mana cost: 250
		required level: 2
LightningSpells:
	Electric_Arrows:
		cost: 550
		damage: 650
		mana cost: 200
		required level: 5
	Lightning_Dagger:
		cost: 400
		damage: 500
		mana cost: 150
		required level: 1
	Spark_Needles:
		cost: 500
		damage: 600
		mana cost: 200
		required level: 2
	Thunder_Blast:
		cost: 750
		damage: 950
		mana cost: 400
		required level: 4
Potions:
	Ambrosia:
		attribute affected: All_Health/Mana/Strength/Dexterity/Defense/Agility
		attribute increase: 150
		cost: 1000
		required level: 8
	Healing_Potion:
		attribute affected: Health
		attribute increase: 100
		cost: 250
		required level: 1
	Luck_Elixir:
		attribute affected: Agility
		attribute increase: 65
		cost: 500
		required level: 4
	Magic_Potion:
		attribute affected: Mana
		attribute increase: 100
		cost: 350
		required level: 2
	Mermaid_Tears:
		attribute affected: Health/Mana/Strength/Agility
		attribute increase: 100
		cost: 850
		required level: 5
	Strength_Potion:
		attribute affected: Strength
		attribute increase: 75
		cost: 200
		required level: 1
Weaponry:
	Axe:
		cost: 550
		damage: 850
		required hands: 1
		required level: 5
	Bow:
		cost: 300
		damage: 500
		required hands: 2
		required level: 2
	Dagger:
		cost: 200
		damage: 250
		required hands: 1
		required level: 1
	Scythe:
		cost: 1000
		damage: 1100
		required hands: 2
		required level: 6
	Sword:
		cost: 500
		damage: 800
		required hands: 1
		required level: 1
	TSwords:
		cost: 1400
		damage: 1600
		required hands: 2
		required level: 8

-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 1150
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (2/10):
Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100
Breastplate:
	Purchase Price: 350
	Level: 3
	Defense: 600

-----------------------------------------

Parzival, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 1
Select an item class to buy from: 
1. Potions
2. Weaponry
3. Armory
4. FireSpells
5. IceSpells
6. LightningSpells
Choose an option: 1
Select an item to buy: 
1. Ambrosia
2. Healing_Potion
3. Luck_Elixir
4. Magic_Potion
5. Mermaid_Tears
6. Strength_Potion
Choose an option: 5
You have selected: Mermaid_Tears
Buy Mermaid_Tears for 850? Parzival has 1150 gold.
1. Yes
2. No
Choose an option: 1
Successfully bought Mermaid_Tears
-----------------------------------------
Name: Sehanine_Monnbow
Level: 8
HP: 800
Class: Warriors
MP: 600
Strength: 840
Agility: 960
Dexterity: 500
Money: 450
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (3/10):
TSwords:
	Purchase Price: 1400
	Level: 8
	Damage: 1600
Lightning_Dagger:
	Purchase Price: 400
	Level: 1
	Type: Lightning
	Mana Cost: 150
	Uses left: 3
	Damage: 500
Healing_Potion:
	Purchase Price: 250
	Level: 1
	Effect: Health
	Change: 100

-----------------------------------------

Sehanine_Monnbow, would you like to buy or sell items?
1. Buy
2. Sell
3. Skip
Choose an option: 3
Party of heroes, would you like to continue shopping?
1. Yes
2. No
Choose an option: 2
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| H |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info, M for market: w
Market!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| H |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info, M for market: i
Hero Information:
-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 300
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (3/10):
Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100
Breastplate:
	Purchase Price: 350
	Level: 3
	Defense: 600
Mermaid_Tears:
	Purchase Price: 850
	Level: 5
	Effect: Health/Mana/Strength/Agility
	Change: 100

-----------------------------------------

-----------------------------------------
Name: Sehanine_Monnbow
Level: 8
HP: 800
Class: Warriors
MP: 600
Strength: 840
Agility: 960
Dexterity: 500
Money: 450
-----------------------------------------
Weapon: Not Equipped
Armor: Not Equipped
-----------------------------------------
Inventory (3/10):
TSwords:
	Purchase Price: 1400
	Level: 8
	Damage: 1600
Lightning_Dagger:
	Purchase Price: 400
	Level: 1
	Type: Lightning
	Mana Cost: 150
	Uses left: 3
	Damage: 500
Healing_Potion:
	Purchase Price: 250
	Level: 1
	Effect: Health
	Change: 100

-----------------------------------------


+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| H |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info, M for market: d
No battle!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M | H |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: w
No battle!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   | H | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: d
You cannot access this space
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   | H | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: w
No battle!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   | H |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: a
No battle!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
| H |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: a
You cannot access this space
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
| H |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: s
Battle!
Monsters:
-----------------------------------------
Name: St-Shargaas
Level: 8
HP: 500
Class: Exoskeletons
Damage: 880
Defense: 1248
Dodge: 88
-----------------------------------------


Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
You don't have a weapon equipped!
Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100

1. Scythe
Choose an option: 1
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
You don't have a weapon equipped!
TSwords:
	Purchase Price: 1400
	Level: 8
	Damage: 1600

1. TSwords
Choose an option: 1
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 5
1. Heroes
2. Monsters
Choose an option: 1
1. Parzival
2. Sehanine_Monnbow
Choose an option: 1
-----------------------------------------
Name: Parzival
Level: 7
HP: 700
Class: Paladins
MP: 300
Strength: 900
Agility: 650
Dexterity: 840
Money: 300
-----------------------------------------
Weapon: Scythe:
	Purchase Price: 1000
	Level: 6
	Damage: 1100
Armor: Not Equipped
-----------------------------------------
Inventory (2/10):
Breastplate:
	Purchase Price: 350
	Level: 3
	Defense: 600
Mermaid_Tears:
	Purchase Price: 850
	Level: 5
	Effect: Health/Mana/Strength/Agility
	Change: 100

-----------------------------------------

Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 5
1. Heroes
2. Monsters
Choose an option: 2
1. St-Shargaas
Choose an option: 2
Invalid entry.
1. St-Shargaas
Choose an option: 1
-----------------------------------------
Name: St-Shargaas
Level: 8
HP: 500
Class: Exoskeletons
Damage: 880
Defense: 1248
Dodge: 88
-----------------------------------------

Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Parzival dealt 32 damage to St-Shargaas!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow dealt 39 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 2
You have no spells to cast!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 2
1. Lightning_Dagger
Choose an option: 1
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow cast Lightning_Dagger on St-Shargaas!
St-Shargaas's dodge went down by 70!
Sehanine_Monnbow dealt 80 damage to St-Shargaas!
2 uses left of Lightning_Dagger
Monster St-Shargaas's turn: 
Sehanine_Monnbow dodged the attack!
St-Shargaas dealt 0 damage to Sehanine_Monnbow!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 3
1. Mermaid_Tears
Choose an option: 1
Parzival healed by 100 health!
Parzival restored 100 mana!
Parzival increased strength by 100!
Parzival increased agility by 100!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 3
1. Healing_Potion
Choose an option: 1
Sehanine_Monnbow healed by 100 health!
Monster St-Shargaas's turn: 
Sehanine_Monnbow dodged the attack!
St-Shargaas dealt 0 damage to Sehanine_Monnbow!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Parzival dealt 33 damage to St-Shargaas!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow dealt 39 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 4
1. Weapon
2. Armor
Choose an option: 2
Breastplate:
	Purchase Price: 350
	Level: 3
	Defense: 600

1. Breastplate
Choose an option: 1
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow dealt 39 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
St-Shargaas dodged the attack!
Parzival dealt 0 damage to St-Shargaas!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow dealt 39 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Sehanine_Monnbow dodged the attack!
St-Shargaas dealt 0 damage to Sehanine_Monnbow!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Parzival dealt 33 damage to St-Shargaas!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow dealt 39 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Parzival dealt 33 damage to St-Shargaas!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
St-Shargaas dodged the attack!
Sehanine_Monnbow dealt 0 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Parzival dealt 33 damage to St-Shargaas!
Hero Sehanine_Monnbow's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Sehanine_Monnbow dealt 39 damage to St-Shargaas!
Monster St-Shargaas's turn: 
Parzival dodged the attack!
St-Shargaas dealt 0 damage to Parzival!
Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 5
1. Heroes
2. Monsters
Choose an option: 2
1. St-Shargaas
Choose an option: 1
-----------------------------------------
Name: St-Shargaas
Level: 8
HP: 22
Class: Exoskeletons
Damage: 880
Defense: 1248
Dodge: 70
-----------------------------------------

Hero Parzival's turn: 
1. Attack with weapon
2. Cast a spell
3. Use a potion
4. Equip weapon/armor
5. Info
Choose an option: 1
Choose a monster to attack:
1. St-Shargaas
Choose an option: 1
Parzival dealt 33 damage to St-Shargaas!
St-Shargaas has been defeated!
Battle over!
Heroes won!
Parzival now has 1100 gold!
Parzival has leveled up to level 8!
Parzival has gained 80 experience points! (10/80) Level 8
Parzival now has 800 HP!
Parzival now has 1460 MP!
Sehanine_Monnbow now has 1250 gold!
Sehanine_Monnbow has leveled up to level 9!
Sehanine_Monnbow has gained 80 experience points! (0/90) Level 9
Sehanine_Monnbow now has 900 HP!
Sehanine_Monnbow now has 2220 MP!
+---+---+---+---+---+---+---+---+
| M |   | M | X |   | X |   | X |
+---+---+---+---+---+---+---+---+
|   |   | M |   |   | X | X |   |
+---+---+---+---+---+---+---+---+
|   | X | X | X |   | X |   | M |
+---+---+---+---+---+---+---+---+
| X |   |   | M | M |   | X | X |
+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+
| H |   | X |   | X |   |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   | X | M |   |
+---+---+---+---+---+---+---+---+
| M |   | M |   | M | X |   |   |
+---+---+---+---+---+---+---+---+
Make a move: WASD to move, Q to quit, I for info: q
Thanks for playing!

Process finished with exit code 0

```