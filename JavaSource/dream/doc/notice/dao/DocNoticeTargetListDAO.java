package dream.doc.notice.dao;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * DocNoticeTarget Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface DocNoticeTargetListDAO
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
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param docNoticeTargetListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception;
    
}
