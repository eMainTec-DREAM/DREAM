package dream.work.let.permit.dao;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업 - 안전작업허가서 상세 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WorkLetPermitDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WorkLetPermitDetailDTO findDetail(String woLetId, String woLetListId, User user);

    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user);
    
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user);

    /**
     * detail complete
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     */
    public int completeDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user);
    
}