package intf.dream.android.online.woreq.service.spring;

import intf.dream.android.online.woreq.service.AnOnWoReqListService;

public abstract class AbstractAnOnWoReqListService implements AnOnWoReqListService
{
    protected AnOnWoReqListService anOnWoReqListService;
    
    public AbstractAnOnWoReqListService(AnOnWoReqListService anOnWoReqListService)
    {
        this.anOnWoReqListService = anOnWoReqListService;
    }

}

