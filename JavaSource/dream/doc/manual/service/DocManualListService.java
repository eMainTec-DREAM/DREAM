package dream.doc.manual.service;

import java.util.List;

import common.bean.User;
import dream.doc.manual.dto.DocManualCommonDTO;

/**
 * 사용자매뉴얼 - 목록 service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface DocManualListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id$
     * @param docManualCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findHelpList(DocManualCommonDTO docManualCommonDTO, User user);
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteHelp(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(DocManualCommonDTO docManualCommonDTO, User user) throws Exception;

}
