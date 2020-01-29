package dream.consult.rpt.mause.service;

import java.util.List;

import common.bean.User;
import dream.consult.rpt.mause.dto.MaUseChartDTO;

/**
 * �����Ȳ service
 * @author  kim21017
 * @version $Id: MaUseChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaUseChartService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaUseChartService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maUseChartDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findUseList(MaUseChartDTO maUseChartDTO, User user);

    public String findTotalCount(MaUseChartDTO maUseChartDTO, User user);    
}
