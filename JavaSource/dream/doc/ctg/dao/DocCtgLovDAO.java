package dream.doc.ctg.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgLovDTO;


/**
 * 문서분류체계 LOV - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface DocCtgLovDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @param columnMap 
     * @param conditionMap 
     * @return List
     */
    public List findList(DocCtgLovDTO docCtgLovDTO , Map<String, String> conditionMap, Map<String, String> columnMap, User user);
}