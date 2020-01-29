package dream.work.rpt.mawodaily.service.spring;

import java.util.List;

import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;
import dream.work.rpt.mawodaily.service.MaWoDailyChartService;

public abstract class AbstractMaWoDailyChartService implements MaWoDailyChartService {
	
	protected MaWoDailyChartService maWoDailyChartService;
	
	public AbstractMaWoDailyChartService(MaWoDailyChartService maWoDailyChartService)
	{
		this.maWoDailyChartService = maWoDailyChartService;
	}

    public abstract List findWoList(MaWoDailyChartDTO maWoDailyChartDTO);

	

}
