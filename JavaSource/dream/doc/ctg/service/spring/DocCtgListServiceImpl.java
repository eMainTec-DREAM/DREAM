package dream.doc.ctg.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.doc.ctg.dao.DocCtgListDAO;
import dream.doc.ctg.dto.DocCtgCommonDTO;
import dream.doc.ctg.service.DocCtgDetailService;
import dream.doc.ctg.service.DocCtgListService;


/**
 * 문서분류체계 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="docCtgListServiceTarget"
 * @spring.txbn id="docCtgListService"
 * @spring.property name="docCtgListDAO" ref="docCtgListDAO"
 */
public class DocCtgListServiceImpl implements DocCtgListService
{
    private DocCtgListDAO docCtgListDAO = null;

    public DocCtgListDAO getDocCtgListDAO() 
    {
		return docCtgListDAO;
	}

	public void setDocCtgListDAO(DocCtgListDAO docCtgListDAO) 
	{
		this.docCtgListDAO = docCtgListDAO;
	}

    public List findList(DocCtgCommonDTO docCtgCommonDTO, User user)
    {      
    	return docCtgListDAO.findList(docCtgCommonDTO, user);
    }
    
    public String findTotalCount(DocCtgCommonDTO docCtgCommonDTO, User user)
    {      
    	return docCtgListDAO.findTotalCount(docCtgCommonDTO, user);
    }
    
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	result = docCtgListDAO.deleteList(id, loginUser);
            }
        
        DocCtgDetailService docCtgDetailService = (DocCtgDetailService)CommonUtil.getBean("docCtgDetailService", loginUser);
        docCtgDetailService.updateFullDesc(loginUser);
        
        return result;
    }
}

