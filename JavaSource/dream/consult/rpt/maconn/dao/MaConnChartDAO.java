package dream.consult.rpt.maconn.dao;

import java.util.List;

import common.bean.User;
import dream.consult.rpt.maconn.dto.MaConnChartDTO;

/**
 * 접속현황 DAO
 * @author  kim21017
 * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaConnChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConnChartDTO
     * @return List
     */
    public List findConnList(MaConnChartDTO maConnChartDTO, User user);
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConnChartDTO
     * @return List
     */
    public List findUsrList(MaConnChartDTO maConnChartDTO, User user);
    /**
     * chart find
     * @author  kim21017
     * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConnChartDTO
     * @return List
     */
    public List findConnChart(MaConnChartDTO maConnChartDTO, User user);
}