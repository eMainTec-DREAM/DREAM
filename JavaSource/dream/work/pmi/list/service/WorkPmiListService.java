package dream.work.pmi.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * 점검작업 - 목록 service
 * @author  kim21017
 * @version $Id: WorkPmiListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmiListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkPmiListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workPmiCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(WorkPmiCommonDTO workPmiCommonDTO,User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkPmiListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO,User user);

}
