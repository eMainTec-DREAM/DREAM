package dream.work.close.check.service;

import java.util.List;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;

/**
 * MgrWorkCloseCheck Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrWorkCloseCheckListService
{
    /**
     * FIND ASS ASSET LIST
     * @param mgrWorkCloseCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception;
    /**
     * DELETE ASS ASSET LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @return
     */
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception;
}
