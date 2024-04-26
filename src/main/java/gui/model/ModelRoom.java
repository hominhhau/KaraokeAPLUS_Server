
package gui.model;

import javax.swing.Icon;

/**
 * @author HO MINH HAU
 */
public class ModelRoom {

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Icon getIcon() {
        return icon;
    }

    public ModelRoom(String roomId, String roomName, Icon icon, String maddp, String makh) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.icon = icon;
        this.maddp = maddp;
        this.makh = makh;

    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelRoom() {
    }

    public String getMaddp() {
        return maddp;
    }

    public void setMaddp(String maddp) {
        this.maddp = maddp;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }


    String roomId;
    String roomName;
    Icon icon;

    String maddp;
    String makh;

}
