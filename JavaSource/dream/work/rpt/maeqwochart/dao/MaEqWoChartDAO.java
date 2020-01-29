package dream.work.rpt.maeqwochart.dao;

import java.util.List;

import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;

/**
 * 설비작업현황 DAO
 * @author  kim21017
 * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqWoChartDAO
{
    /**
     * wo grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findWoList(MaEqWoChartDTO maEqWoChartDTO);
    
    /**
     * pm grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findPmList(MaEqWoChartDTO maEqWoChartDTO);
    
    /**
     * pt grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findPtList(MaEqWoChartDTO maEqWoChartDTO);
    
    /**
     * use pt grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findUsePtList(MaEqWoChartDTO maEqWoChartDTO);

}