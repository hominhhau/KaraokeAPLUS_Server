package gui.model;

import javax.swing.Icon;

/**
 * @author HO MINH HAU
 */
public class ModelMenu {

    public Icon getIcon() {
        return icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public String[] getSubMenu() {
        return subMenu;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setSubMenu(String[] subMenu) {
        this.subMenu = subMenu;
    }

    public ModelMenu(Icon icon, String menuName, String... subMenu) {
        this.icon = icon;
        this.menuName = menuName;
        this.subMenu = subMenu;
    }

    public ModelMenu() {

    }

    private Icon icon;
    private String menuName;
    private String subMenu[];

}
