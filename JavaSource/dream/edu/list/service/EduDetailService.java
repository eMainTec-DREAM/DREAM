package dream.edu.list.service;

import common.bean.User;
import dream.edu.list.dto.EduDetailDTO;

/**
 * 자격증분류 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface EduDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public EduDetailDTO findDetail(User user, String courseListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(EduDetailDTO eduDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(EduDetailDTO eduDetailDTO) throws Exception;
    

}
