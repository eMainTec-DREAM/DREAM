package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;

/**
 * �۾���ȹ��� - �����۾� �� dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanWoLetDetailDAO
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
     */
    public WoPlanWoLetDetailDTO findDetail(String wkOrId, String woCraftId, User user);

    /**
     * detail update
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanWoLetDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int updateDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user);
    
    /**
     * detail insert
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanWoLetDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int insertDetail(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user);

    /**
     * �����۾� �ߺ��˻�
     */
    public String validWoLet(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetDetailDTO woPlanWoLetDetailDTO, User user);
    
}