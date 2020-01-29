package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;

/**
 * WorkPmiDInsPoint Page - Detail DAO
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointDetailDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmiDInsPointDetailDAO
{
    /**
     * FIND DETAIL
     * @param workPmiDInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiDInsPointDetailDTO findDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workPmiDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workPmiDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception;
    
    /**
     * GET ID
     * @param workPmiDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String getId(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception;
    
}
