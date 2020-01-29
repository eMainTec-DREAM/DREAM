package dream.work.rpt.mawodaily.service;

import java.util.List;

import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;

/**
 * 일별작업실행율 service
 * @author  kim21017
 * @version $Id: MaWoDailyChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoDailyChartService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoDailyChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoDailyChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoList(MaWoDailyChartDTO maWoDailyChartDTO);    
}
