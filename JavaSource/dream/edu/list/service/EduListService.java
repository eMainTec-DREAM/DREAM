package dream.edu.list.service;

import java.util.List;

import common.bean.User;
import dream.edu.list.dto.EduCommonDTO;

/**
 * �ڰ����з� - ��� service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface EduListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param eduCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(EduCommonDTO eduCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;

    /**
     * find Total Count
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduCommonDTO
     * @return
     */
    public String findTotalCount(EduCommonDTO eduCommonDTO, User user);

}
