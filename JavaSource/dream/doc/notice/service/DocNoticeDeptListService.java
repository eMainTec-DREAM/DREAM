package dream.doc.notice.service;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;

/**
 * DocNoticeDept Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface DocNoticeDeptListService
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
     * @param docNoticeDeptListDTO
     * @return
     */
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception;
}
