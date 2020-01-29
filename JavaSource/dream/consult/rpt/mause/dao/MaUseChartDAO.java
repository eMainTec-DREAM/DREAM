package dream.consult.rpt.mause.dao;


import java.util.List;

import common.bean.User;
import dream.consult.rpt.mause.dto.MaUseChartDTO;

/**
 * 사용현황 DAO
 * @author  kim21017
 * @version $Id: MaUseChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaUseChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaUseChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUseChartDTO
     * @return List
     */
    public List findUseList(MaUseChartDTO maUseChartDTO, User user);

    public String findTotalCount(MaUseChartDTO maUseChartDTO, User user);
}