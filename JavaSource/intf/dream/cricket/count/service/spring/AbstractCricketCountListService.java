package intf.dream.cricket.count.service.spring;

import intf.dream.cricket.count.service.CricketCountListService;

public abstract class AbstractCricketCountListService implements CricketCountListService
{
    protected CricketCountListService cricketCountListService;
    
    public AbstractCricketCountListService(CricketCountListService cricketCountListService)
    {
        this.cricketCountListService = cricketCountListService;
    }

}

