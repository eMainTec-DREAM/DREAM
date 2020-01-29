package dream.doc.notice.service.spring;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dao.DocNoticeListDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.service.DocNoticeListService;

/**
 * DocNotice Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="docNoticeListServiceTarget"
 * @spring.txbn id="docNoticeListService"
 * @spring.property name="docNoticeListDAO" ref="docNoticeListDAO"
 */
public class DocNoticeListServiceImpl implements DocNoticeListService
{
    private DocNoticeListDAO docNoticeListDAO = null;

    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception
    {      
        return docNoticeListDAO.findList(docNoticeCommonDTO,user);
    }
    public List findCheckList(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception
    {      
    	return docNoticeListDAO.findCheckList(docNoticeCommonDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + docNoticeListDAO.deleteList(id, user);
            }
        return result;
    }

    public DocNoticeListDAO getDocNoticeListDAO() {
        return docNoticeListDAO;
    }

    public void setDocNoticeListDAO(DocNoticeListDAO docNoticeListDAO) {
        this.docNoticeListDAO = docNoticeListDAO;
    }    
    
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO,User user)  throws Exception
    {
        return docNoticeListDAO.findTotalCount(docNoticeCommonDTO, user);
    }
    public String findCheckTotalCount(DocNoticeCommonDTO docNoticeCommonDTO,User user)  throws Exception
    {
    	return docNoticeListDAO.findCheckTotalCount(docNoticeCommonDTO, user);
    }
}
