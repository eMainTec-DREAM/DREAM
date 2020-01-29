package dream.org.emp.service.spring;

import common.bean.User;
import dream.org.emp.dao.OrgEmpTrainDetailDAO;
import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;
import dream.org.emp.service.OrgEmpTrainDetailService;

/**
 * 구매신청item - 상세
 * @author kim2107
 * @version $Id: PartAdjStkTakeDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="orgEmpTrainDetailServiceTarget"
 * @spring.txbn id="orgEmpTrainDetailService"
 * @spring.property name="orgEmpTrainDetailDAO" ref="orgEmpTrainDetailDAO"
 */
public class OrgEmpTrainDetailServiceImpl implements OrgEmpTrainDetailService
{
    private OrgEmpTrainDetailDAO orgEmpTrainDetailDAO = null;
    
    public OrgEmpTrainDetailDAO getOrgEmpTrainDetailDAO() {
		return orgEmpTrainDetailDAO;
	}

	public void setOrgEmpTrainDetailDAO(OrgEmpTrainDetailDAO orgEmpTrainDetailDAO) {
		this.orgEmpTrainDetailDAO = orgEmpTrainDetailDAO;
	}

	public OrgEmpTrainDetailDTO findDetail(OrgEmpTrainListDTO orgEmpTrainListDTO, MaEmpCommonDTO maEmpCommonDTO, User user)throws Exception
    {
        return orgEmpTrainDetailDAO.findDetail(orgEmpTrainListDTO, maEmpCommonDTO, user);
    }
    
	public int updateDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception
    {        
		
        return orgEmpTrainDetailDAO.updateDetail(orgEmpTrainDetailDTO, maEmpCommonDTO);
    }
	public int insertDetail(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception
    {        
        return orgEmpTrainDetailDAO.insertDetail( orgEmpTrainDetailDTO, maEmpCommonDTO);
    }
}
