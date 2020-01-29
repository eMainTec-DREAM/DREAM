package dream.doc.notice.service;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * DocNoticeTarget Page - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface DocNoticeTargetListService
{
    /**
     * FIND LIST
     * @param docNoticeTargetListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception;
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
     * @param docNoticeTargetListDTO
     * @return
     */
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception;
}
