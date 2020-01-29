package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 何前芭贰贸  格废
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrVendorListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     *  delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception;
}
