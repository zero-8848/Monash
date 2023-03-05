package game;

/**
 * different affection levels
 */
public enum AffectionLevel {
    DISLIKE,// Dislike affection level,cannot be fixed
    NEUTRAL,//can be fixed but only catch by masterball
    CURIOUS,//curious about actor, is willing to be captured by a Greatball.
    LIKE,//is willing to be captured with Pokeball
    FOLLOW//Pokemon will follow the trainer/player around
}
