package dream.doc.ctg.dao;

import java.util.List;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgCommonDTO;


/**
 * 문서분류체계 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface DocCtgListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @return List
     */
    public List findList(DocCtgCommonDTO docCtgCommonDTO, User user);
    
    public String findTotalCount(DocCtgCommonDTO docCtgCommonDTO, User user);
    
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
    public int deleteList(String id, User user);
}