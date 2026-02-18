
package core;


public class Player {
    public CardStack stack = new CardStack(false);
    private String name = "default";

    /**
     * @return returns the player's name.
     */
    public String getName(){
        return name;
    }

    /**
     * @param the name of the new player.
     */
    public Player(String newName ){
        this.name = newName;
    }

    // This doesn't work with my IDE issues.
//    public boolean equals(Object object) {
//        if (object == null || getClass() != object.getClass()) return false;
//        if (!super.equals(object)) return false;
//        Player player = (Player) object;
//        return java.util.Objects.equals(stack, player.stack) && java.util.Objects.equals(name, player.name);
//    }
//
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), stack, name);
//    }
}

// END ------------------------------------------------------------------------------- 