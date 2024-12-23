import java.util.*;

// Tracks global game state
// Useful for implementing state-based behavior (ex: see something new on second visit to room)

public class GameState {
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<Item>();
    List<String> tips = new ArrayList<>();
    Map<String, Room> rooms; // global list of rooms
    Map<String, Item> items; // global list of known items
    boolean pet = false;
    boolean ate = false;
    boolean app = false;
    boolean wardenRoom = false;

    // update state and check for winning condition
    public String update() {
        if (room.name.equals("Warden's Room")) {
            if ((inventory.contains(items.get("Green Frog"))) && (inventory.contains(items.get("Rose")))
                    && (inventory.contains(items.get("Veggie Pizza")))) {
                if (pet && ate && app) {
                    String finaltext = """
                            You hear The Warden chuckle a little, and then hear its voice once more in your mind.
                            \"I see you've managed to find the items I seek, and taken the time to enjoy them and relax a little. I believe you are
                            ready to return to the real world and ace your upcoming finals. Don't forget to take the time to relax every once in a while.\"
                            You feel yourself blackout, and you awake in your bed at home, relaxed and ready for your finals.
                            """;
                    finished = true;
                    return finaltext;

                } else {
                    String rejectText = """
                            The Warden grunts, and you feel as though you see his glowing blue eyes roll. In your mind, he says \"You've brought me the items I seek, but you've
                            rushed through it! Take the time to truly appreciate these items, and your time here. Only then will I allow you to leave.\"
                            """;
                    return rejectText;
                }
            } else {
                if (!wardenRoom) {
                    String wardenSpeech = """
                            You look around this strangely dark room, and can barely make out an elegent throne at the back of the room, and a shadowy figure sits upon it.
                            A pair of glowing blue eyes stare at you from the throne. You hear this figure speak, but not with your ears. It's almost as if it's talking to you
                            through your mind. \"I am The Warden, and this is my realm. I've brought you here to distract you from your finals back home for a while, and to play a game.
                            I desire three items which can be found somewhere in this building. Bring them to me, and I shall allow you to return to your world. The key to the rest
                            of the complex is on the floor in front of you.\"
                            """;
                }
            }
        }
        return "";
    }

    public GameState(String name) {
        this.name = name;
        finished = false;
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        room = rooms.get("Starting Room");
        inventory.add(items.get("book"));
    }
}
