package dream.work.rpt.mawodaily.dao;

import java.util.List;

import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;

/**
 * 일별작업실행율 DAO
 * @author  kim21017
 * @version $Id: MaWoDailyChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoDailyChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoDailyChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyChartDTO
     * @return List
     */
    public List findWoList(MaWoDailyChartDTO maWoDailyChartDTO);
}