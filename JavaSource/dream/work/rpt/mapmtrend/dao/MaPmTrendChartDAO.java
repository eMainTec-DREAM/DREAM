package dream.work.rpt.mapmtrend.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dto.MaPmTrendChartDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;

/**
 * 예방점검경향분석 DAO
 * @author  kim21017
 * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmTrendChartDAO
{
    /**
     * 예방점검경향분석 find grid
     * @author  kim21017
     * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmTrendChartDTO
     * @return List
     */
    public List findPmList(MaPmTrendChartDTO maPmTrendChartDTO, User user);
    /**
     * 예방점검경향분석 find chartwfww
     * @author  kim21017
     * @version $Id: MaPmTrendChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmTrendChartDTO
     * @return List
     */
    public List findPmChart(MaPmTrendChartDTO maPmTrendChartDTO);  
    
    public int createWo(MaPmTrendChartDTO maPmTrendChartDTO, User loginUser);
    
    public String findTotalCount(MaPmTrendChartDTO maPmTrendChartDTO, User user);
}