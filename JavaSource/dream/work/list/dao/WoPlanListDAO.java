package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * 작업계획목록 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @return List
     */
    public List findWoResultMstrList(WoPlanCommonDTO woPlanCommonDTO, User user);
    public int[] updateDeleteTagWoPlanMstr(List<WoPlanDetailDTO> list, User user);
    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO,User user, String woType);
}