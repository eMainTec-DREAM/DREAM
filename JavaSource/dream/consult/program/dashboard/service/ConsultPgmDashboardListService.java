package dream.consult.program.dashboard.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;

/**
 * 대시보드 Contents - 목록 service
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface ConsultPgmDashboardListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param consultPgmDashboardCommonDTO
     * @param user
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findList(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user);
    public String findTotalCount(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultPgmDashboardListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
}
