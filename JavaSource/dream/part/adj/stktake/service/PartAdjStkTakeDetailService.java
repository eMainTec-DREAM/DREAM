package dream.part.adj.stktake.service;

import common.bean.User;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 부품실사 - 상세 service
 * 
 * @author kim21017
 * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface PartAdjStkTakeDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @return
     * @throws Exception
     */
    public PartAdjStkTakeDetailDTO findDetail(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO,User user)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     * @throws Exception
     */
    public int confirmDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) throws Exception;

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;

}
