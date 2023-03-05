package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    //todo toString() change into this
    IMMUNE, // an enum to identify that an object is immune to any attack.
    HOSTILE, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    CATCHABLE, // Whether an actor can be caught or not
    HASPOKEMON, // Used to check if pokeballs have a pokemon in them
    FIGHTTYPE, // Fight type pokemon
    TELEPORTABLE, // For the door ground to alert the player that they can teleport from this ground
    DESTROY,// an enum to identify that a ground is going to be destroyed this night/day
    EXPAND,// an enum to identify that a ground is going to be expanded this night/day
    SLIP,//an enum to identify that an actor that can slip over when trying to stand on the ice
    ENABLEFLY,//Wall enable fly
    FLYABLE,//Defined flyable actor
    UNEXPANDABLE,//WALLS AND FLOORS CANNOT BE EXPANED by things like lava
    DROPCANDY,// drop a candy in day and night effect
    FIRETICK,//check if pokemon can use fire
    HASSPECIALATTACK// if a pokemon has special attack
}
