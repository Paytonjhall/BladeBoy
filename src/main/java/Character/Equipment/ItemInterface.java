package Character.Equipment;

public interface ItemInterface {


    void setName(String name);
    void setValue(int value);
    void setDescription(String desc);

    String getName();
    int getValue();
    String getDescription();
    String hoverString();
}
