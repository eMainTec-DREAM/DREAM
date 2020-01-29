package dream.work.close.check.dao;

import java.util.List;

import common.bean.User;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;

/**
 * MgrWorkCloseCheck Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrWorkCloseCheckListDAO
{
    /**
     * FIND LIST
     * @param mgrWorkCloseCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    
    /**
     * FIND TOTAL LIST
     * @param mgrWorkCloseCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception;
}
