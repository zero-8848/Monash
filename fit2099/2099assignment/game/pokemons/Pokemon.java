package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.newactions.*;
import game.pokeballs.GreatBall;
import game.pokeballs.MasterBall;
import game.pokeballs.Pokeball;
import game.AffectionManager;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * Abstract base class representing the pokemons in the world
 *
 */
public abstract class Pokemon extends Actor {

    /**
     * The action list
     */
    private ActionList actions = new ActionList();

    /**
     * Pokemons favourite action 
     */
    private FavouriteAction favouriteAction;//change into fav

    /**
     * The behaviour tree map that is used to sort the behaviours
     */
    private final TreeMap<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * The Pokemons special attack
     */
    private List<BackupWeapons> specialAttacks = new ArrayList<>();

    /**
     * The hash map that relates the pokemons to their favourite action
     */
    private HashMap<String, FavouriteAction> actionMap = new HashMap<>();

    /**
     * Random number used for the random attack
     */
    private Random random = new Random();

    /**
     * Constructor
     * @param name pokemon name
     * @param displayChar display character
     * @param hitPoints the health points
     * @param specialAttack the pokemons special attack
     */
    public Pokemon(String name, char displayChar, int hitPoints, BackupWeapons specialAttack) {
        super(name, displayChar, hitPoints);
        this.addCapability(AffectionLevel.NEUTRAL);
        this.specialAttacks.add(specialAttack);
        this.favouriteAction = this.getActionMap(name);
        AffectionManager.getInstance().registerPokemon(this);

        // Add behaviours that are common to all pokemons
        this.addBehaviour(10,  new WanderBehaviour());
        this.addBehaviour(1, new AttackBehaviour());

    }

    /**
     * Get the a random attack
     * @return the pokemons special attack
     */
    public BackupWeapons getSpecialAttack(){
        return this.specialAttacks.get(random.nextInt(this.specialAttacks.size()));
    }

    /**
     * Get the pokemons possible special attacks
     * @return the pokemons possible special attacks
     */
    public List<BackupWeapons> getPossibleAttacks(){
        return this.specialAttacks;
    }

    /**
     * The logic so that next pokemon has all the previous pokemons weapons
     * @param specialAttacks
     */
    public void setWeapons(List<BackupWeapons> specialAttacks){
        for (BackupWeapons weapon: specialAttacks){
                this.getPossibleAttacks().add(weapon);
        }
    }

    /**
     * @param name the pokemons name
     * @return FavouriteAction the pokemons favourite action
     */
    public FavouriteAction getActionMap(String name){
        return actionMap.get(name);
    }
    
    /**
     * Set the hashMap values to be used later
     */
    public void setFavouriteAction(FavouriteAction favouriteAction) {
        this.favouriteAction = favouriteAction;
    }

    /**
     * Serves to get the pokemons favourite action
     * @return the pokemons favourite action
     */
    public Action getFavouriteAction(){
        return favouriteAction;
        }

    /**
     *
     * @param otherActor the Actor that might be performing attack/player that trying to catch pokemon
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {



        // IF the other actor is not immune do the following
        if (!(otherActor.hasCapability(Status.IMMUNE))){

            // Add the attack action 
            actions.add(new AttackAction(otherActor, direction));

            // Set a boolean true which assumes that the special attack is not in the pokemons inventory
            Boolean specialAttackInInventory = false;

            // Check to the see if the special attack is in the pokemons inventory
            // If so set the boolean to true
            for (Item item : this.getInventory()){
                if (item.toString() == this.getSpecialAttack().toString()){
                    specialAttackInInventory = true;
                }
        }

        // Call this method to determine whether the special attack should be added, kept or removed from the pokemons inventory
        weaponActivationCheck(otherActor, specialAttackInInventory, map);;
        }
        
        // Add the three favourite actions
        actions.add(new ChestPoundingAction(this));
        actions.add(new DancingAction(this));
        actions.add(new SingingAction(this));
        actions.add(new PlayAction(this));
//        actions.add(new )
      // actions.add(new CatchAction(this, ));
        // Have this line as the regurlar pokeball is not part of the inventory
        if (this.hasCapability(Status.CATCHABLE)){
            // Base action for catching a pokemon with the basic ball
            actions.add(new CatchAction(this, new Pokeball()));

            // If the actor has master or great ball in their inventory add them to the allowable actions
            for (int i = 0; i < otherActor.getInventory().size(); i++){
                Item item = otherActor.getInventory().get(i);

                // Check to see if the item is a MasterBall or a GreatBall
                if(item.toString().contains("Ball")){

                    // If the player has a pokeball and it has a pokemon in it then dont do anything
                    if (item.hasCapability(Status.HASPOKEMON)){}

                    // The steps required if the ball is a GreatBall
                    else if (item.toString() == "GreatBall"){
                        GreatBall newGreatBall = new GreatBall();
                        otherActor.removeItemFromInventory(item);
                        otherActor.addItemToInventory(newGreatBall);
                        actions.add(new CatchAction(this, newGreatBall));
                    }

                    // The steps required if the ball is a MasterBall
                    else if (item.toString() == "MasterBall" ){
                        MasterBall newMasterBall = new MasterBall();
                        otherActor.removeItemFromInventory(item);
                        otherActor.addItemToInventory(newMasterBall);
                        actions.add(new CatchAction(this, newMasterBall));
                    }
                }
            }
        }

        return actions;
    }

    /**
     * To check to see if a pokemon should use the special attack 
     * @param otherActor the other pokemon that is being attacked
     * @param inInventory boolean that states whether the special attack is in the inventory
     * @param map current game map
     */
    public void weaponActivationCheck(Actor otherActor, Boolean inInventory, GameMap map){
        // The location of the ground
        Location currentLocation = map.locationOf(this);
        // Check to see if the ground is the same type as the pokemon
        boolean sameGroundType = ElementsHelper.hasAnySimilarElements(this, currentLocation.getGround().findCapabilitiesByType(Element.class));
        // Check to see if the other actor is off the same type or not
        boolean enemyDifferentType = !(ElementsHelper.hasAnySimilarElements(this, otherActor.findCapabilitiesByType(Element.class)));

        BackupWeapons possibleWeapon = this.getSpecialAttack();

        // If it is standing on the same ground element or the enemy is off different type
        if ((sameGroundType || enemyDifferentType)){
            toggleWeapon(true, possibleWeapon);
        }

    }

    /**
     * handles the turn
     * @param actions the action
     * @param lastAction the most recent action
     * @param map the game map
     * @param display how we display the messages
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Check if theres fire on this ground
        // If so need to get damaged by the fire
        for (Item item :map.locationOf(this).getItems()){
            if (item.hasCapability(Status.FIRETICK) && !(this.hasCapability(Element.FIRE))){
                display.println(this + " was " + item.asWeapon().verb());
                this.hurt(item.asWeapon().damage());
            }
        }

        for (Behaviour behaviour : this.getBehaviour().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * To add and remove the special weapon from the pokemons inventory
     * @param isEquipping to determine if the weapon is to be added or removed
     */
    public void toggleWeapon(boolean isEquipping, BackupWeapons currentWeapon) {

        // if true add to inventory
        if (isEquipping == true){ 
            this.addItemToInventory(currentWeapon);
       }

   }

    /**
     * Add the follow behaviour as number 3 as the seocnd most important
     * @param subject the trainer that the pokemon will follow
     */
    public void addFollowBehaviour(Actor subject){
        this.addBehaviour(3, new FollowBehaviour(subject));
    }

   /**
    * Setter for new behaviours
    * @param key location in the hashmap
    * @param behaviour the new behaviour
    */
   public void addBehaviour(int key, Behaviour behaviour){
       behaviours.put(key, behaviour);
   }

   /**
    * Getter for the behaviour
    * @return the list of behaviours
    */
   public TreeMap<Integer, Behaviour> getBehaviour(){
       return behaviours;
   }

   /**
    * @return the string representation of the pokemons
    */
   @Override
   public String toString() {
       return name + printHp() + AffectionManager.getInstance().printAffectionPoints(this);
   }




}
