package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;

/**
 * 작업계획목록 - 투입부품 상세 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanPartDetailDAO
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
     */
    public WoPlanPartDetailDTO findDetail(String wkOrId, String woPartId, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int updateDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO);
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int insertDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO);

    /**
     * 재고확인
     */
    public String getStockQty(WoPlanPartDetailDTO woPlanPartDetailDTO, User loginUser);
}