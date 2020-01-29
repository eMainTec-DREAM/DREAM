package dream.tool.rec.dao;

import java.util.List;

import common.bean.User;
import dream.tool.rec.dto.MaPttRecCommonDTO;

/**
 * 구매입고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPttRecListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecCommonDTO
     * @return List
     */
    public List findList(MaPttRecCommonDTO maPttRecCommonDTO,User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String prRecListId);
    
    public String findTotalCount(MaPttRecCommonDTO maPttRecCommonDTO,User user) throws Exception;
}