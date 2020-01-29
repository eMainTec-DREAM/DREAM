package dream.doc.notice.dao;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;

/**
 * DocNoticeDept Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface DocNoticeDeptListDAO
{
    /**
     * FIND LIST
     * @param docNoticeDeptListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param docNoticeDeptListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception;
    
}
