package intf.dream.cricket.woreq.service.spring;

import intf.dream.cricket.woreq.service.CricketWoReqListService;

public abstract class AbstractCricketWoReqListService implements CricketWoReqListService
{
    protected CricketWoReqListService cricketWoReqListService;
    
    public AbstractCricketWoReqListService(CricketWoReqListService cricketWoReqListService)
    {
        this.cricketWoReqListService = cricketWoReqListService;
    }

}

