package dream.work.rpt.mabmgwchart.service;

import java.util.List;

import dream.work.rpt.mabmgwchart.dto.MaBmGwChartDTO;

/**
 * 과별고장분석 service
 * @author  kim21017
 * @version $Id: MaBmGwChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmGwChartService
{     
    /**
     *  과별고장분석 grid find
     * @author  kim21017
     * @version $Id: MaBmGwChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmGwChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBmList(MaBmGwChartDTO maBmGwChartDTO);
    /**
     *  과별고장분석 chart find
     * @author  kim21017
     * @version $Id: MaBmGwChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmGwChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBmChart(MaBmGwChartDTO maBmGwChartDTO);    
}
