package dream.doc.notice.service.spring;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dao.DocNoticeTargetListDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;
import dream.doc.notice.service.DocNoticeTargetListService;

/**
 * DocNoticeTarget Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="docNoticeTargetListServiceTarget"
 * @spring.txbn id="docNoticeTargetListService"
 * @spring.property name="docNoticeTargetListDAO" ref="docNoticeTargetListDAO"
 */
public class DocNoticeTargetListServiceImpl implements DocNoticeTargetListService
{
    private DocNoticeTargetListDAO docNoticeTargetListDAO = null;

    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception
    {      
        return docNoticeTargetListDAO.findList(docNoticeCommonDTO,docNoticeTargetListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + docNoticeTargetListDAO.deleteList(id, user);
            }
        return result;
    }

    public DocNoticeTargetListDAO getDocNoticeTargetListDAO() {
        return docNoticeTargetListDAO;
    }

    public void setDocNoticeTargetListDAO(DocNoticeTargetListDAO docNoticeTargetListDAO) {
        this.docNoticeTargetListDAO = docNoticeTargetListDAO;
    }    
    
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user)  throws Exception
    {
        return docNoticeTargetListDAO.findTotalCount(docNoticeCommonDTO, docNoticeTargetListDTO, user);
    }
}
