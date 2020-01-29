package dream.part.rep.service;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * ������� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRepAppListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param maPtRepAppListDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaPtRepCommonDTO maPtRepCommonDTO,MaPtRepAppListDTO maPtRepAppListDTO, User loginUser);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

}
