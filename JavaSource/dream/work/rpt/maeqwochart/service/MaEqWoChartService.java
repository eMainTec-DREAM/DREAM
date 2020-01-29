package dream.work.rpt.maeqwochart.service;

import java.util.List;

import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;

/**
 * 설비작업현황 service
 * @author  kim21017
 * @version $Id: MaEqWoChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqWoChartService
{     
    /**
     *  wo grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqWoChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoList(MaEqWoChartDTO maEqWoChartDTO);    
    
    /**
     *  pm grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqWoChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPmList(MaEqWoChartDTO maEqWoChartDTO);    
    /**
     *  pt grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqWoChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPtList(MaEqWoChartDTO maEqWoChartDTO);    
    /**
     *  usept grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqWoChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findUsePtList(MaEqWoChartDTO maEqWoChartDTO);    
}
