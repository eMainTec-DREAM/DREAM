package dream.part.adj.stkmove.service;

import java.util.List;

import common.bean.User;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;

/**
 * 재고이동 - 목록 service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface PartAdjStkMoveListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @param partAdjStkMoveCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user);    

    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDTOList
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user) throws Exception;

}
