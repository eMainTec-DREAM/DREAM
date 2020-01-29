package dream.part.rep.dao;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRepAppListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param maPtRepAppListDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPtRepCommonDTO maPtRepCommonDTO,MaPtRepAppListDTO maPtRepAppListDTO, User loginUser);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     */
    public int deleteList(MaPtRepAppListDTO maPtRepAppListDTO, User loginUser);
}