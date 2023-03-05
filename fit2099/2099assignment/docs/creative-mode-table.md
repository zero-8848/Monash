# Requirement 3

**Title**:Player special features:Fly,Ice and play

**Description**:
In this requirement we will focus on the player(Ash):

1. Fly:
   The player can fly over the wall at certain turns. Certain turns include:
   
   1. During night except for the first turn of each night. (For example, the player can fly in turns 6th, and 7th, but the player cannot fly on the 5th turn).
   
   2. the First turn of each day except for turn 0 (e.g. the player can fly on turn 10th but cannot fly on turn 0).
   In a nutshell, the player can enter the wall in turns:

6th,7th,8th,9th,10th

16th,17th,18th,19th,20th

26th,27th,28th,29th,30th

Etc.

The flying feature will be disabled in other turns.

2. Ice

There is a new ground type called ice, the display char is "?".
If the player tries to stand on the ice, he will slip onto an exit ground of the ice that can be entered by the player (For example, dirt can be entered by the Player, but the wall cannot be entered by the player if the player is not flying). The slip will happen even if the actor is flying.

For example :

In the 6th turn, when the Player is flying

#*#

#?#

#@#

If the Player tries to stand on the ice,
He will slip to the north of the ice automatically:

#@#

#?#

#*#


Ice cannot be converted when other elements are expanding.

For other actors like Pokemon, they can stand on the ice with no slip.(i.e. The slip will only affect the actor)

3.Play Action

There is a new action called PlayAction. This action should become available to the player when it stands adjacent to a Pokemon.
The Play action has a 50% chance to increase the affection points of the target pokemon by 20 points.



**Explanation why it adheres to SOLID principles** (WHY):

1. Fly means enable the wall can be entered by Player in certain time.So the logic of checking if an actor can enter the  is implemented in Wall class instead of Player class.This adheres to **single responsibility principle** as the enter-enabling feature is the responsibility of the Wall class but not thePlayer class.

2. Instead of creating a new time period and making the Player can fly in the new time period, we choose to use the existing day and night effect to ensure the player can fly in certain turns. The dayEffect in Wall is used to disable the fly-in feature and nightEffect is used to enable it (i.e. remove/add capability of ENABLEFLY Status). This approach adheres to **Liskov Substitution Principle** as all classes/methods implementing the TimePerception interface have functionalities related to day and night time perception. Every class that implements dayEffect must have some functionality inside a nightEffect at the same time. Also, all these functionalities are related to day/night time perception. As the Wall class implements TimePerception, we should be able to replace TimeTercerption with Wall(and all other Classes that implement TimePerception) without disrupting the behaviour of our program.This also indicates this feature adheres to **Liskov substitution principle**.

3. Adding Ice ground extends Ground class, but there’s no need to modify code inside the abstract Ground class. This approach adheres to the Open Closed principle as it is open for extension but closed for modification.

4. Adding PlayAction extends the Action class but does not modify code inside the abstract Action class. This approach adheres to the Open Closed principle as it is open for extension but closed for modification.


| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| ----------------------------------------------------------------------------------------------------------------------- |------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | We use two classes from the engine package: Action and Ground. PlayAction extends Action, Ice extend Ground.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | We use two existing features:TimePerception and AffectionManager.<br/>The Fly functionality uses TimePerception's day and night feature(req5 A1&2) to ensure the fly only happens during night except the first turn of each night and first turn of day expect turn 0.Our approach is the Wall class implements TimePerception and add/remove fly capabilities in the overrided dayEffect()/nightEffect() methods.This is an functionality from req5 in last assignment<br/>The play action uses AffectionManager feature to increase the affections points.The increaseAffection() method is called in execute() method of PlayAction class to increase target pokemon's affection Points.This is a feature in req4 of A1&2.                                                                       |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | The fly Feature uses TimePerception interface.Wall implements Timeperception interface.It implements dayEffect() and nightEffect() methods to make sure the fly will happen in certain time.Our approach is We add ENABLEFLY capability to the Wall in nightEffect() and remove it by dayEffect().                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| Must use existing or create new capabilities                                                                            | There are 3 new capabilities been created in this requirement.They are SLIP,ENABLEFLY,FLYABLE in Status enum class.<br/> ENABLEFLY is added to the Wall class to make the wall accept Player to fly into(make sure player can enter the wall when he is flying).We add it to the Wall in nightEffect() and remove it by dayEffect().<br/>SLIP is an enum to identify that an actor that can slip over when trying to stand on the ice.We add it to the Player in player's constructor and using if statement to check if an actor has this capability in the tick() method of ice.<br/>  The capability used by Ice class is  UNEXPANDABLE. We added it to Ice objects in the constructor of Ice class.This approach can make sure Ice ground cannot be converted when other elements are expanding. |
