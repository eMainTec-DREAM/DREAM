package dream.doc.notice.service;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;

/**
 * DocNotice Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface DocNoticeListService
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
     * @param docNoticeCommonDTO
     * @return
     */
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
    public String findCheckTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception;
}
