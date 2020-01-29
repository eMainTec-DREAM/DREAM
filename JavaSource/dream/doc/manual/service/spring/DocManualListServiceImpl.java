package dream.doc.manual.service.spring;

import java.util.List;

import common.bean.User;
import dream.doc.manual.dao.DocManualListDAO;
import dream.doc.manual.dto.DocManualCommonDTO;
import dream.doc.manual.service.DocManualListService;

/**
 * 사용자매뉴얼 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="docManualListServiceTarget"
 * @spring.txbn id="docManualListService"
 * @spring.property name="docManualListDAO" ref="docManualListDAO"
 */
public class DocManualListServiceImpl implements DocManualListService
{
    private DocManualListDAO docManualListDAO = null;

    public DocManualListDAO getDocManualListDAO() {
		return docManualListDAO;
	}

	public void setDocManualListDAO(DocManualListDAO docManualListDAO) {
		this.docManualListDAO = docManualListDAO;
	}

	public List findHelpList(DocManualCommonDTO docManualCommonDTO, User user)
    {      
        return docManualListDAO.findHelpList(docManualCommonDTO,user);
    }
	
	public int deleteHelp(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + docManualListDAO.deleteHelp(id, user);
            }
        return result;
    }

    @Override
    public String findTotalCount(DocManualCommonDTO docManualCommonDTO, User user) throws Exception
    {
        return docManualListDAO.findTotalCount(docManualCommonDTO, user);
    }
}

