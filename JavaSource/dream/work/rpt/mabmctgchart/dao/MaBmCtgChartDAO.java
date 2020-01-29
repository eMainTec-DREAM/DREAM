package dream.work.rpt.mabmctgchart.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabmctgchart.dto.MaBmCtgChartDTO;

/**
 * 설비종류별고장분석 DAO
 * @author  kim21017
 * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmCtgChartDAO
{
    /**
     * 설비종류별고장분석 find grid
     * @author  kim21017
     * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmCtgChartDTO
     * @return List
     */
    public List findBmList(MaBmCtgChartDTO maBmCtgChartDTO, User user);
    
    /**
     * 설비종류별고장분석 find chart
     * @author  kim21017
     * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmCtgChartDTO
     * @param user
     * @return List
     */
    public List findBmChart(MaBmCtgChartDTO maBmCtgChartDTO, User user);
    

    
}