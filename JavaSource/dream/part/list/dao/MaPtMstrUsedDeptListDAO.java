package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptListDTO;

/**
 * 何前荤侩何辑 格废 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrUsedDeptListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param eqPartId
     * @return
     */
    public int deleteList(MaPtMstrUsedDeptListDTO maPtMstrUsedDeptListDTO, User loginUser);

	public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user);
}