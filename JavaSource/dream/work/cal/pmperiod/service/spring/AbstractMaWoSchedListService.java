package dream.work.cal.pmperiod.service.spring;

import dream.work.cal.pmperiod.service.MaWoSchedListService;

public abstract class AbstractMaWoSchedListService implements MaWoSchedListService
{     
    protected MaWoSchedListService maWoSchedListService;
    
    public AbstractMaWoSchedListService(MaWoSchedListService maWoSchedListService) 
    {
        this.maWoSchedListService = maWoSchedListService;
    }
}
