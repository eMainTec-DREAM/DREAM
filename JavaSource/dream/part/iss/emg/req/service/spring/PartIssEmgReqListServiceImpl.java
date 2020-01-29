package dream.part.iss.emg.req.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.iss.emg.req.dao.PartIssEmgReqListDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.service.PartIssEmgReqListService;

/**
 * 부품출고 - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partIssEmgReqListServiceTarget"
 * @spring.txbn id="partIssEmgReqListService"
 * @spring.property name="partIssEmgReqListDAO" ref="partIssEmgReqListDAO"
 */
public class PartIssEmgReqListServiceImpl implements PartIssEmgReqListService
{
	private PartIssEmgReqListDAO partIssEmgReqListDAO = null;

	public List findIssReqList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception
    {      
        return partIssEmgReqListDAO.findIssReqList(partIssEmgReqCommonDTO,user);
    }

	public int deleteIssReqList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                partIssEmgReqListDAO.deleteIssReqPartsList(id, user);
                result = result + partIssEmgReqListDAO.deleteIssReqList(id, user);
            }
        return result;
    }

	public PartIssEmgReqListDAO getPartIssEmgReqListDAO() {
		return partIssEmgReqListDAO;
	}

	public void setPartIssEmgReqListDAO(PartIssEmgReqListDAO partIssEmgReqListDAO) {
		this.partIssEmgReqListDAO = partIssEmgReqListDAO;
	}
	public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user) throws Exception
    {
        return partIssEmgReqListDAO.findTotalCount(partIssEmgReqCommonDTO, user);
    }
}

