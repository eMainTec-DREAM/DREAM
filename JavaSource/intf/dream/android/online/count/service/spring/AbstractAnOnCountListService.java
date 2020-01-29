package intf.dream.android.online.count.service.spring;

import intf.dream.android.online.count.service.AnOnCountListService;

public abstract class AbstractAnOnCountListService implements AnOnCountListService
{
    protected AnOnCountListService anOnCountListService;
    
    public AbstractAnOnCountListService(AnOnCountListService anOnCountListService)
    {
        this.anOnCountListService = anOnCountListService;
    }

}

