package intf.dream.bee.count.service.spring;

import intf.dream.bee.count.service.BeeCountListService;

public abstract class AbstractBeeCountListService implements BeeCountListService
{
    protected BeeCountListService beeCountListService;
    
    public AbstractBeeCountListService(BeeCountListService beeCountListService)
    {
        this.beeCountListService = beeCountListService;
    }

}

