package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;

/**
 * 작업계획목록 - 목록 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param woPlanCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoResultMstrList(WoPlanCommonDTO woPlanCommonDTO,User user);
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteWoResultMstr(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO,User user, String woType);

}
