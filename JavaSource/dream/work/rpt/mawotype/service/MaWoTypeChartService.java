package dream.work.rpt.mawotype.service;

import java.util.List;

import dream.work.rpt.mawotype.dto.MaWoTypeChartDTO;

/**
 * �۾���������Ȳ service
 * @author  kim21017
 * @version $Id: MaWoTypeChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoTypeChartService
{     
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoTypeChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoTypeChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaWoTypeChartDTO maWoTypeChartDTO);    
    /**
     * wo cnt chart find
     * @author  kim21017
     * @version $Id: MaWoTypeChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoTypeChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findWoCntChart(MaWoTypeChartDTO maWoTypeChartDTO);
    /**
     * wo time chart find
     * @author  kim21017
     * @version $Id: MaWoTypeChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoTypeChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findWoTimeChart(MaWoTypeChartDTO maWoTypeChartDTO);
    
}
