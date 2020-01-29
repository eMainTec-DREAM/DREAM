package dream.work.let.permit.service;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업 - 안전작업허가서 상세
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WorkLetPermitDetailService
{    
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woLetId
     * @param woLetListId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkLetPermitDetailDTO findDetail(String woLetId, String woLetListId, User user)throws Exception;
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
     * @throws Exception
     */
    public int updateDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception;
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
     * @throws Exception
     */
    public int insertDetail(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception;

    /**
     * detail complete
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String completeDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception;
    
    /**
     * detail complete cancel
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @param workLetPermitDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String completeCancelDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception;
    
}
