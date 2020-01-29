package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;

/**
 * �۾���ȹ��� - �����۾� ��
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
     * �����۾� �ߺ��˻�
     */
    public String validWoLet(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user);
    
}
