package dream.work.rpt.mabmlnchart.service;

import java.util.List;

import dream.work.rpt.mabmlnchart.dto.MaBmLnChartDTO;

/**
 * ���ΰ���м� service
 * @author  kim21017
 * @version $Id: MaBmLnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaBmLnChartService
{     
    /**
     *  ���ΰ���м� grid find
     * @author  kim21017
     * @version $Id: MaBmLnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmLnChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findBmList(MaBmLnChartDTO maBmLnChartDTO);
    /**
     *  ���ΰ���м� chart find
     * @author  kim21017
     * @version $Id: MaBmLnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maBmLnChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findBmChart(MaBmLnChartDTO maBmLnChartDTO);    
}
