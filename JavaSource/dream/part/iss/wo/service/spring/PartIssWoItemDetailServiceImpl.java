package dream.part.iss.wo.service.spring;

import common.bean.User;
import dream.part.iss.wo.dao.PartIssWoItemDetailDAO;
import dream.part.iss.wo.dto.PartIssWoItemDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;
import dream.part.iss.wo.service.PartIssWoItemDetailService;

/**
 * 자재출고확정 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partIssWoItemDetailServiceTarget"
 * @spring.txbn id="partIssWoItemDetailService"
 * @spring.property name="partIssWoItemDetailDAO" ref="partIssWoItemDetailDAO"
 * 
 */
public class PartIssWoItemDetailServiceImpl implements PartIssWoItemDetailService
{
    
    private PartIssWoItemDetailDAO partIssWoItemDetailDAO = null;
    
   public PartIssWoItemDetailDAO getPartIssWoItemDetailDAO() 
    {
		return partIssWoItemDetailDAO;
	}

	public void setPartIssWoItemDetailDAO(PartIssWoItemDetailDAO partIssWoItemDetailDAO) 
	{
		this.partIssWoItemDetailDAO = partIssWoItemDetailDAO;
	}

	public PartIssWoItemDetailDTO findDetail(PartIssWoItemListDTO partIssWoItemListDTO, User user)throws Exception
    {
        PartIssWoItemDetailDTO partIssWoItemDetailDTO = partIssWoItemDetailDAO.findDetail(partIssWoItemListDTO,user);
        
        return partIssWoItemDetailDTO;
    }
	
	public int insertDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;
	    //TAPTISSLIST 입력
	    result = result + partIssWoItemDetailDAO.insertDetail(partIssWoItemDetailDTO, loginUser);
	    result = result + partIssWoItemDetailDAO.insertRemark(partIssWoItemDetailDTO, loginUser);
        return result;
    }
	
	
	public int updateDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;
	    
	    
	    result=+ partIssWoItemDetailDAO.updateDetail(partIssWoItemDetailDTO);
	    result=+ partIssWoItemDetailDAO.insertRemark(partIssWoItemDetailDTO, loginUser);

	    return result;
    }

	public PartIssWoItemDetailDTO serialCheck(PartIssWoItemDetailDTO partIssWoItemDetailDTO) throws Exception
    {   
	    return partIssWoItemDetailDAO.serialCheck(partIssWoItemDetailDTO);
    }
   
    
}
