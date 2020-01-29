package dream.part.stk.dao;

import java.util.List;

import common.bean.User;
import dream.part.stk.dto.PartStkSerialListDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 */
public interface PartStkSerialListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partStkSerialListDTO
     * @return List
     */
    public List findSerialList(PartStkSerialListDTO partStkSerialListDTO, User user);
    
   

   
}