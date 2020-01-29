package dream.edu.list.dao;

import java.util.List;

import common.bean.User;
import dream.edu.list.dto.EduCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface EduListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduCommonDTO
     * @return List
     */
    public List findList(EduCommonDTO eduCommonDTO,User user);
    
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
    public int deleteList(String compNo, String courseListId);
    
    public String findTotalCount(EduCommonDTO eduCommonDTO, User user);
}