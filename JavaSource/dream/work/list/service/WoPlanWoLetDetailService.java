package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;

/**
 * 작업계획목록 - 안전작업 상세
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanWoLetDetailService
{    
    /**
     * detail find
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WoPlanWoLetDetailDTO findDetail(String wkOrId, String woCraftId, User user)throws Exception;
    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanWoLetDetailDTO
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanWoLetDetailDTO
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user) throws Exception;
    
    /**
     * 안전작업 중복검사
     */
    public String validWoLet(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user);
    
}
