package dream.consult.rpt.maconn.service;

import java.util.List;

import common.bean.User;
import dream.consult.rpt.maconn.dto.MaConnChartDTO;

/**
 * 접속현황 service
 * @author  kim21017
 * @version $Id: MaConnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaConnChartService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaConnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maConnChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConnList(MaConnChartDTO maConnChartDTO, User user);    
    /**
     *  usr grid find
     * @author  kim21017
     * @version $Id: MaConnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maConnChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findUsrList(MaConnChartDTO maConnChartDTO, User user);    
    /**
     *  chart find
     * @author  kim21017
     * @version $Id: MaConnChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maConnChartDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConnChart(MaConnChartDTO maConnChartDTO, User user);    
}
