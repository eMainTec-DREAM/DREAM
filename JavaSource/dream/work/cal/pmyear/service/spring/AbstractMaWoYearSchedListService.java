package dream.work.cal.pmyear.service.spring;

import dream.work.cal.pmyear.service.MaWoYearSchedListService;

/**
 * 연간작업일정 - 목록 service
 * @author  nhkim8548
 * @version $Id: MaWoYearSchedListService.java,v 1.0 2018/10/26 13:21:40 nhkim8548 Exp $
 * @since   1.0
 */
public abstract class AbstractMaWoYearSchedListService implements MaWoYearSchedListService
{     
    protected MaWoYearSchedListService maWoYearSchedListService;
    
    public AbstractMaWoYearSchedListService(MaWoYearSchedListService maWoYearSchedListService) 
    {
        this.maWoYearSchedListService = maWoYearSchedListService;
    }
}
