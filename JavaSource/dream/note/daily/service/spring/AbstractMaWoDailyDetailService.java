package dream.note.daily.service.spring;

import dream.note.daily.service.MaWoDailyDetailService;

public abstract class AbstractMaWoDailyDetailService implements MaWoDailyDetailService
{    
	protected MaWoDailyDetailService maWoDailyDetailService;
	
    public AbstractMaWoDailyDetailService(MaWoDailyDetailService maWoDailyDetailService)
    {
        this.maWoDailyDetailService = maWoDailyDetailService;
    }
	
	
}
