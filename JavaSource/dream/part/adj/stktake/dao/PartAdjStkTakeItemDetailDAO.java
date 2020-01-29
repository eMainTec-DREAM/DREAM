package dream.part.adj.stktake.dao;

import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import common.bean.User;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사 item 상세 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface PartAdjStkTakeItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeListDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public PartAdjStkTakeItemDetailDTO findDetail(PartAdjStkTakeListDTO partAdjStkTakeListDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public int updateDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public int insertDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public int insertGap(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);
    
    /**
     * defaultItem insert
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @param user
     * @return
     */
    public int insertDefaultItem(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);
    
    public String validItem(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);
}