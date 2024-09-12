package models

class ListId     // Constructor
    (// setters
    // getters
    // fields for id object
    var id: Int, var listId: Int, var name: Int
) {

    override fun toString(): String {
        return "{" +
                " id: " + id +
                ", listId: " + listId +
                ", name: '" + "Item " + name + "'" +
                "}"
    }
}