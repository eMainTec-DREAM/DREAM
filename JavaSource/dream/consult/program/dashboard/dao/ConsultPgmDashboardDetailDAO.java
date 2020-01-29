package dream.consult.program.dashboard.dao;

import common.bean.User;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardDetailDTO;

/**
 * 대시보드 Contents - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultPgmDashboardDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardCommonDTO
     * @param user
     * @return
     */
    public ConsultPgmDashboardDetailDTO findDetail(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user);
}