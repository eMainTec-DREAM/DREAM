package dream.part.adj.stkmove.service;

import common.bean.User;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 재고이동 - 상세 service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface PartAdjStkMoveDetailService
{    
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveCommonDTO
     * @return
     * @throws Exception
     */
    public PartAdjStkMoveDetailDTO findDetail(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user)throws Exception;

    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;
    /**
     * detail confirm
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     * @throws Exception
     */
    public String[] moveComp(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;

    public String[] moveCancel(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception;

}
