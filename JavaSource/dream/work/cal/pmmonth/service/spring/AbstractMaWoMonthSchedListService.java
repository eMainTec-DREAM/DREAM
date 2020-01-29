package dream.work.cal.pmmonth.service.spring;

import dream.work.cal.pmmonth.service.MaWoMonthSchedListService;

public abstract class AbstractMaWoMonthSchedListService implements MaWoMonthSchedListService
{     
    protected MaWoMonthSchedListService maWoMonthSchedListService;
    
    public AbstractMaWoMonthSchedListService(MaWoMonthSchedListService maWoMonthSchedListService) 
    {
        this.maWoMonthSchedListService = maWoMonthSchedListService;
    }
}
