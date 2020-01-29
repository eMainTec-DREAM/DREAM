package dream.work.let.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;

/**
 * 안전작업 - 상세 dao
 * 
 * @author syyang
 * @version $Id$
 * @since 1.0
 */
public interface WorkLetDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @return
     */
    public WorkLetDetailDTO findDetail(WorkLetCommonDTO workLetCommonDTO,User user);
    
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     */
    public int insertDetail(WorkLetDetailDTO workLetDetailDTO, User loginUser);

    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailDTO
     * @return
     */
    public int updateDetail(WorkLetDetailDTO workLetDetailDTO, User user);
    
    public int completeDetail(WorkLetDetailDTO workLetDetailDTO, User user);
    
    public String getWoLetStatus(WorkLetDetailDTO workLetDetailDTO, User user);
    
}