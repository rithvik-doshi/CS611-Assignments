# Game Instructions and Information

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

Good luck, and have fun!