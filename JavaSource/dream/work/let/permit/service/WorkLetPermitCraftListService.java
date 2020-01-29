package dream.work.let.permit.service;

import java.util.List;

import common.bean.User;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서유형 - 작업자 목록 service
 * @author  syyang
 * @version $Id: WorkLetPermitCraftListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since   1.0
 */
public interface WorkLetPermitCraftListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id: WorkLetPermitCraftListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitCraftListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findCraftList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User loginUser);
    /**
     *  delete
     * @author  syyang
     * @version $Id: WorkLetPermitCraftListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteCraftList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User user) throws Exception;
    
}
