package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;

/**
 * WorkPmiCInsPoint Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmiCInsPointDetailDAO
{
    /**
     * FIND DETAIL
     * @param workPmiCInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiCInsPointDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmiCInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmiCInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception;
    
    /**
     * GET ID
     * @param workPmiCInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String getId(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception;
    
}
