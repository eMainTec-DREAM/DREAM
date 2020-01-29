package dream.consult.program.dashboard.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;

/**
 * ��ú��� Contents - ��� dao
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultPgmDashboardListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardCommonDTO
     * @param user
     * @return List
     */
    public List findList(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user);
    public String findTotalCount(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param user
     * @return
     */
    public int deleteList(String id, User user);
}