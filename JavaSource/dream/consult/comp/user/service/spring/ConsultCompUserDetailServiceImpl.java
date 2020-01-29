package dream.consult.comp.user.service.spring;

import common.bean.User;
import dream.consult.comp.user.dao.ConsultCompUserDetailDAO;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUserDetailDTO;
import dream.consult.comp.user.service.ConsultCompUserDetailService;

/**
 * CompUser Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: ConsultCompUserDetailServiceImpl.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="consultCompUserDetailServiceTarget"
 * @spring.txbn id="consultCompUserDetailService"
 * @spring.property name="consultCompUserDetailDAO" ref="consultCompUserDetailDAO"
 */
public class ConsultCompUserDetailServiceImpl implements ConsultCompUserDetailService
{
private ConsultCompUserDetailDAO consultCompUserDetailDAO = null;
    
    public ConsultCompUserDetailDTO findCompUserDetail(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception
    {
        return consultCompUserDetailDAO.findCompUserDetail(consultCompUserCommonDTO, user);
    }
    
    public int insertCompUserDetail(ConsultCompUserDetailDTO consultCompUserDetailDTO, User user) throws Exception
    {
    	consultCompUserDetailDTO.setPassWord(consultCompUserDetailDTO.getUserNo().toLowerCase());
        return consultCompUserDetailDAO.insertCompUserDetail(consultCompUserDetailDTO, user);
    }
    
    public int updateCompUserDetail(ConsultCompUserDetailDTO consultCompUserDetailDTO, User user) throws Exception
    {
         return consultCompUserDetailDAO.updateCompUserDetail(consultCompUserDetailDTO, user);
    }

    public ConsultCompUserDetailDAO getConsultCompUserDetailDAO() {
        return consultCompUserDetailDAO;
    }

    public void setConsultCompUserDetailDAO(ConsultCompUserDetailDAO consultCompUserDetailDAO) {
        this.consultCompUserDetailDAO = consultCompUserDetailDAO;
    }

	@Override
	public String valUserNo(ConsultCompUserCommonDTO consultCompUserCommonDTO,
			ConsultCompUserDetailDTO consultCompUserDetailDTO) throws Exception {
		 return consultCompUserDetailDAO.valUserNo(consultCompUserCommonDTO, consultCompUserDetailDTO);
	}
}
