package dream.work.let.permit.service;

import java.util.List;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitListDTO;

/**
 * 안전작업 - 안전작업허가서  목록
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WorkLetPermitListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findWoLetPermitList(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User loginUser);
    /**
     *  delete
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteWoLetList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User user) throws Exception;
    
    public int inputWoLetList(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitDetailDTO workLetPermitDetailDTO, User user) throws Exception;


}
