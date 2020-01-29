package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;

/**
 * �۾���ȹ��� - �۾��� �� dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanCraftDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     */
    public WoPlanCraftDetailDTO findDetail(String wkOrId, String woCraftId, String compNo);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int updateDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO);
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int insertDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO);

    /**
     * ���Ȯ��
     */
    public String validEmp(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO, User loginUser);
}