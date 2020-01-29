package dream.work.let.service;

import java.util.List;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;

/**
 * 안전작업 - 목록 service
 * @author  syyang
 * @version $Id: WorkLetListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since   1.0
 */
public interface WorkLetListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id: WorkLetListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @param workLetCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoLetList(WorkLetCommonDTO workLetCommonDTO,User user);
    
    /**
     * delete
     * @author syyang
     * @version $Id: WorkLetListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteWoLet(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(WorkLetCommonDTO workLetCommonDTO,User user, String woType);
    
}
