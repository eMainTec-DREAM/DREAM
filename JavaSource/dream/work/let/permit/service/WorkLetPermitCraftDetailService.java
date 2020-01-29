package dream.work.let.permit.service;

import common.bean.User;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서유형 - 작업자 상세 service
 * @author  kim210117
 * @version $Id: WorkLetPermitCraftDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface WorkLetPermitCraftDetailService
{    
    /**
     * detail find
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkLetPermitCraftDetailDTO findDetail(String woLetListId, String woLetListCraftId, User user)throws Exception;
    /**
     * detail update
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitCraftDetailDTO
     * @param workLetCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author syyang
     * @version $Id: WorkLetPermitCraftDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitCraftDetailDTO
     * @param workLetCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user) throws Exception;

}
