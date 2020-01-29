package dream.doc.notice.dao;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;

/**
 * DocNotice Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface DocNoticeListDAO
{
    /**
     * FIND LIST
     * @param docNoticeCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
    public List findCheckList(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
    
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
     * @param docNoticeCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
    public String findCheckTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
}
