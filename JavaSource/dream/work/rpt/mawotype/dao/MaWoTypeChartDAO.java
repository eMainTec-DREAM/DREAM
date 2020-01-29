package dream.work.rpt.mawotype.dao;

import java.util.List;



import dream.work.rpt.mawotype.dto.MaWoTypeChartDTO;

/**
 * 작업유형별현황 DAO
 * @author  kim21017
 * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoTypeChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return List
     */
    public List findList(MaWoTypeChartDTO maWoTypeChartDTO);
    /**
     * wo cnt  find
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return List
     */
    public List findWoCntChart(MaWoTypeChartDTO maWoTypeChartDTO);
    /**
     * wo time  find
     * @author  kim21017
     * @version $Id: MaWoTypeChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoTypeChartDTO
     * @return List
     */
    public List findWoTimeChart(MaWoTypeChartDTO maWoTypeChartDTO);

}