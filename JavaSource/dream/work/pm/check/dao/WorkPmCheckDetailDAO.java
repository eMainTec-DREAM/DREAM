package dream.work.pm.check.dao;

import common.bean.User;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckDetailDTO;

/**
 * WorkPmCheck Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkPmCheckDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmCheckDetailDAO
{
    /**
     * FIND DETAIL
     * @param workPmCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmCheckDetailDTO findDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmCheckDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmCheckDetailDTO workPmCheckDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmCheckDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmCheckDetailDTO workPmCheckDetailDTO, User user) throws Exception;
}
