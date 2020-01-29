package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;

/**
 * 작업계획목록 - 작업자 상세
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanCraftDetailService
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
     * @throws Exception
     */
    public WoPlanCraftDetailDTO findDetail(String wkOrId, String woCraftId,String compNo)throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailDTO
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailDTO
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception;

    /**
     * 사원중복검사
     */
    public String validEmp(WoPlanCraftDetailDTO woPlanCraftDetailDTO, WoPlanCommonDTO woPlanCommonDTO,User loginUser);
}
