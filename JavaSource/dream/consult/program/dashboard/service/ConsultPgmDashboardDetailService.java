package dream.consult.program.dashboard.service;

import common.bean.User;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardDetailDTO;

/**
 * ��ú��� Contents - �� service
 *
 * @author kim21017
 * @version $Id: ConsultPgmDashboardDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface ConsultPgmDashboardDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultPgmDashboardDetailDTO findDetail(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultPgmDashboardDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user) throws Exception;
}
