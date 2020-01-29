package dream.doc.ctg.service;

import java.util.List;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgCommonDTO;

/**
 * 문서분류 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface DocCtgListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param docCtgCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(DocCtgCommonDTO docCtgCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @param deleteRowsExt
     * @param deleteRowsExt1
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

    public String findTotalCount(DocCtgCommonDTO docCtgCommonDTO, User user);
}
