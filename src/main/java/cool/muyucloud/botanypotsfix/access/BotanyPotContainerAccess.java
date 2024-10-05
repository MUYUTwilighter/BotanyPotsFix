package cool.muyucloud.botanypotsfix.access;

public interface BotanyPotContainerAccess {
    Long getLastUpdate();

    void markUpdate();

    boolean hasGrowthChanged();
}
