package dream.part.pur.po.dao;

import java.util.List;

import common.bean.User;
import dream.part.pur.po.dto.MaPtPoCommonDTO;

/**
 * 발주이력 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtPoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoCommonDTO
     * @return List
     */
    public List findList(MaPtPoCommonDTO maPtPoCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String poListId);

    public int insertRec(MaPtPoCommonDTO maPtPoCommonDTO, String poListId, User user);
    
    public String findTotalCount(MaPtPoCommonDTO maPtPoCommonDTO, User user);
}