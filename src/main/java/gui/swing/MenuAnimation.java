package gui.swing;

import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Lớp MenuAnimation giúp thực hiện hiệu ứng mở/đóng menu dựa trên MigLayout.
 *
 * @author HO MINH HAU
 */
public class MenuAnimation {
    /**
     * Khởi tạo một MenuAnimation với MigLayout, một thành phần (component) và thời gian hiệu ứng.
     *
     * @param layout    MigLayout sẽ sử dụng cho menu.
     * @param component Thành phần menu.
     * @param duration  Thời gian của hiệu ứng (milliseconds).
     */
    public MenuAnimation(MigLayout layout, Component component, int duration) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimaotor(component, duration);
    }

    /**
     * Khởi tạo một MenuAnimation với MigLayout và một thành phần (component).
     * Mặc định thời gian hiệu ứng là 200 milliseconds.
     *
     * @param layout    MigLayout sẽ sử dụng cho menu.
     * @param component Thành phần menu.
     */

    public MenuAnimation(MigLayout layout, Component component) {
        this.layout = layout;
        this.menuItem = (MenuItem) component;
        initAnimaotor(component, 200);
    }

    private void initAnimaotor(Component component, int duration) {
        int height = component.getPreferredSize().height;
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float h;
                if (open) {
                    h = 40 + ((height - 40) * fraction);
                    menuItem.setAlpha(fraction);
                } else {
                    h = 40 + ((height - 40) * (1f - fraction));
                    menuItem.setAlpha(1f - fraction);
                }
                layout.setComponentConstraints(menuItem, "h " + h + "!");

                component.revalidate();
                component.repaint();
            }

        };
        animator = new Animator(duration, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
    }

    /**
     * Mở menu với hiệu ứng.
     */
    public void openMenu() {
        open = true;
        animator.start();

    }

    /**
     * Đóng menu với hiệu ứng.
     */
    public void closeMenu() {
        open = false;
        animator.start();

    }

    private final MigLayout layout;
    private final MenuItem menuItem;
    private Animator animator;
    private boolean open;

}
