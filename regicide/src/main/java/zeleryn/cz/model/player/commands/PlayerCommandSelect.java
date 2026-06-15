package zeleryn.cz.model.player.commands;

public class PlayerCommandSelect extends PlayerCommand{
    private final int selectIndex;

    public PlayerCommandSelect(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public int getSelectIndex() {
        return selectIndex;
    }
}
