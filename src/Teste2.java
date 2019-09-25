import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

/**
 * @author ges
 * @author kwalrath
 */
/* MenuLayoutDemo.java requires no other files. */

public class Teste2 {
  public JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
    //menuBar.setLayout(new BoxLayout, BoxLayout.);
    //menuBar.setLayout(new GridLayout(1,1,1,1));
  //  menuBar.setSize(100, 100);
    //menuBar.setBackground(Color.gray);
    menuBar.add(createMenu("Professores cadastrado"));
    menuBar.add(createMenu("Menu 2"));
    menuBar.add(createMenu("Menu 3"));

    menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
    return menuBar;
  }

  // used by createMenuBar
  public JMenu createMenu(String title) {
    JMenu m = new HorizontalMenu(title);
    return m;
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("MenuLayoutDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create and set up the content pane.
    Teste2 demo = new Teste2();
    Container contentPane = frame.getContentPane();
    contentPane.setBackground(Color.WHITE); // contrasting bg
    contentPane.add(demo.createMenuBar(), BorderLayout.LINE_START);

    // Display the window.
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  class HorizontalMenu extends JMenu {
    HorizontalMenu(String label) {
      super(label);
      JPopupMenu pm = getPopupMenu();
      pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
    }

    public Dimension getMinimumSize() {
      return getPreferredSize();
    }

    public Dimension getMaximumSize() {
      return getPreferredSize();
    }

    public void setPopupMenuVisible(boolean b) {
      boolean isVisible = isPopupMenuVisible();
      if (b != isVisible) {
        if ((b == true) && isShowing()) {
          // Set location of popupMenu (pulldown or pullright).
          // Perhaps this should be dictated by L&F.
          int x = 0;
          int y = 0;
          Container parent = getParent();
          if (parent instanceof JPopupMenu) {
            x = 0;
            y = getHeight();
          } else {
            x = getWidth();
            y = 0;
          }
          getPopupMenu().show(this, x, y);
        } else {
          getPopupMenu().setVisible(false);
        }
      }
    }
  }
}