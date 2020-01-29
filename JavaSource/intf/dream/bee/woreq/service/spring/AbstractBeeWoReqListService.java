package intf.dream.bee.woreq.service.spring;

import intf.dream.bee.woreq.service.BeeWoReqListService;

public abstract class AbstractBeeWoReqListService implements BeeWoReqListService
{
    protected BeeWoReqListService beeWoReqListService;
    
    public AbstractBeeWoReqListService(BeeWoReqListService beeWoReqListService)
    {
        this.beeWoReqListService = beeWoReqListService;
    }

}

