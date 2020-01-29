package dream.work.let.permit.dao;

import common.bean.User;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서유형 - 작업자 상세  dao
 * @author  syyang
 * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 2015/12/04 08:10:27 syyang Exp $
 * @since   1.0
 */
public interface WorkLetPermitCraftDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WorkLetPermitCraftDetailDTO findDetail(String woLetListId, String woLetListCraftId,User user);

    /**
     * detail update
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param workLetCommonDTO
     * @return
     */
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user) throws Exception;
    
    /**
     * detail insert
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailDTO
     * @param workLetCommonDTO
     * @return
     */
    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user) throws Exception;

}