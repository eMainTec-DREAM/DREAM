package dream.work.rpt.mabmctgchart.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabmctgchart.dto.MaBmCtgChartDTO;

/**
 * 설비종류별고장분석 service
 * @author  kim21017
 * @version $Id: MaBmCtgChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmCtgChartService
{     
    /**
     *  설비종류별고장분석 grid find
     * @author  kim21017
     * @version $Id: MaBmCtgChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmCtgChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBmList(MaBmCtgChartDTO maBmCtgChartDTO, User user);
    /**
     *  설비종류별고장분석 chart find
     * @author  kim21017
     * @version $Id: MaBmCtgChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmCtgChartDTO
     * @param user
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBmChart(MaBmCtgChartDTO maBmCtgChartDTO, User user);    
}
