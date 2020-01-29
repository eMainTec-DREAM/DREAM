package dream.work.rpt.mabmgwchart.dao;

import java.util.List;

import dream.work.rpt.mabmgwchart.dto.MaBmGwChartDTO;

/**
 * 과별고장분석 DAO
 * @author  kim21017
 * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmGwChartDAO
{
    /**
     * 과별종합분석 find grid
     * @author  kim21017
     * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmGwChartDTO
     * @return List
     */
    public List findBmList(MaBmGwChartDTO maBmGwChartDTO);
    /**
     * 과별종합분석 find chart
     * @author  kim21017
     * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmGwChartDTO
     * @return List
     */
    public List findBmChart(MaBmGwChartDTO maBmGwChartDTO);
        
}