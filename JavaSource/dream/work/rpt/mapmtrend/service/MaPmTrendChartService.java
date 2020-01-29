package dream.work.rpt.mapmtrend.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dto.MaPmTrendChartDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;

/**
 * 예방점검경향분석 service
 * @author  kim21017
 * @version $Id: MaPmTrendChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmTrendChartService
{     
    /**
     *  예방점검경향분석 grid find
     * @author  kim21017
     * @version $Id: MaPmTrendChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPmTrendChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPmList(MaPmTrendChartDTO maPmTrendChartDTO,User user);
    /**
     *  예방점검경향분석 chart find
     * @author  kim21017
     * @version $Id: MaPmTrendChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPmTrendChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPmChart(MaPmTrendChartDTO maPmTrendChartDTO);    
    
    public int createWo(MaPmTrendChartDTO maPmTrendChartDTO, User loginUser);
    
    public String findTotalCount(MaPmTrendChartDTO maPmTrendChartDTO, User user);
}
