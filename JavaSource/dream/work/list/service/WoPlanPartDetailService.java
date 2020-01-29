package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;

/**
 * 작업계획목록 - 투입부품 상세
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanPartDetailService
{    
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WoPlanPartDetailDTO findDetail(String wkOrId, String woPartId,User user)throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailDTO
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailDTO
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO) throws Exception;

    /**
     * 재고확인
     */
    public String getStockQty(WoPlanPartDetailDTO woPlanPartDetailDTO, User loginUser);
}
