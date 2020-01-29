package dream.work.rpt.mabmlnchart.dao;

import java.util.List;

import dream.work.rpt.mabmlnchart.dto.MaBmLnChartDTO;

/**
 * 라인고장분석 DAO
 * @author  kim21017
 * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmLnChartDAO
{
    /**
     * 라인고장분석 find grid
     * @author  kim21017
     * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmLnChartDTO
     * @return List
     */
    public List findBmList(MaBmLnChartDTO maBmLnChartDTO);
    
    /**
     * 라인고장분석 find chart
     * @author  kim21017
     * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmLnChartDTO
     * @return List
     */
    public List findBmChart(MaBmLnChartDTO maBmLnChartDTO);

    
}