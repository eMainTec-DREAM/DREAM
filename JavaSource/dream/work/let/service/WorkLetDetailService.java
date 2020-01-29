package dream.work.let.service;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;

/**
 * 안전작업 - 상세 service
 * @author syyang
 * @version $Id$
 * @since 1.0
 */
public interface WorkLetDetailService 
{    
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @return
     * @throws Exception
     */
    public WorkLetDetailDTO findDetail(WorkLetCommonDTO workLetCommonDTO,User user)throws Exception;
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @param workLetCommonDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkLetDetailDTO workLetDetailDTO, WorkLetCommonDTO workLetCommonDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetDetailDTO workLetDetailDTO, User user) throws Exception;

    /**
     * detail complete
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     * @throws Exception
     */
    public int completeDetail(WorkLetDetailDTO workLetDetailDTO, User user) throws Exception;
    /**
     * detail complete cancel
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     * @throws Exception
     */
    public String completeCancelDetail(WorkLetDetailDTO workLetDetailDTO, User user) throws Exception;
    
}
