package app.truefleet.com.truefleet.Models;

/**
 * Created by Chris Lacy on 3/2/2015.
 */
public class PanelItem {
    private int icon;
    private String title;
    private String counter;

    public PanelItem(int icon, String title, String counter) {
        this.icon = icon;
        this.title = title;
        this.counter = counter;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }
}
