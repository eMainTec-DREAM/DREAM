package dream.doc.notice.service.spring;

import java.util.List;

import common.bean.User;
import dream.doc.notice.dao.DocNoticeDeptListDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;
import dream.doc.notice.service.DocNoticeDeptListService;

/**
 * DocNoticeDept Page - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="docNoticeDeptListServiceTarget"
 * @spring.txbn id="docNoticeDeptListService"
 * @spring.property name="docNoticeDeptListDAO" ref="docNoticeDeptListDAO"
 */
public class DocNoticeDeptListServiceImpl implements DocNoticeDeptListService
{
    private DocNoticeDeptListDAO docNoticeDeptListDAO = null;

    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception
    {      
        return docNoticeDeptListDAO.findList(docNoticeCommonDTO,docNoticeDeptListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + docNoticeDeptListDAO.deleteList(id, user);
            }
        return result;
    }

    public DocNoticeDeptListDAO getDocNoticeDeptListDAO() {
        return docNoticeDeptListDAO;
    }

    public void setDocNoticeDeptListDAO(DocNoticeDeptListDAO docNoticeDeptListDAO) {
        this.docNoticeDeptListDAO = docNoticeDeptListDAO;
    }    
    
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user)  throws Exception
    {
        return docNoticeDeptListDAO.findTotalCount(docNoticeCommonDTO, docNoticeDeptListDTO, user);
    }
}
