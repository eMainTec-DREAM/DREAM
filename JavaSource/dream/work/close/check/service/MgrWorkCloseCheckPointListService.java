package dream.work.close.check.service;

import java.util.List;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트- 목록 service
 * @author kim21017 
 * @version $Id:  $
 * @since   1.0
 */
public interface MgrWorkCloseCheckPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param mgrWorkCloseCheckPointListDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findStdPointList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO,MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser);    
    
    /**
     * delete List
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
    
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO,MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser);
}
