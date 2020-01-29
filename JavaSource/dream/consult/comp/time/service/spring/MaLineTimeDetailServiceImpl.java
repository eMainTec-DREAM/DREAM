package dream.consult.comp.time.service.spring;

import common.bean.User;
import dream.consult.comp.time.dao.MaLineTimeDetailDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;
import dream.consult.comp.time.service.MaLineTimeDetailService;

/**
 * 가동시간설정 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaLineTimeDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maLineTimeDetailServiceTarget"
 * @spring.txbn id="maLineTimeDetailService"
 * @spring.property name="maLineTimeDetailDAO" ref="maLineTimeDetailDAO"
 */
public class MaLineTimeDetailServiceImpl implements MaLineTimeDetailService
{
    private MaLineTimeDetailDAO maLineTimeDetailDAO = null;
    
    public MaLineTimeDetailDAO getMaLineTimeDetailDAO() {
		return maLineTimeDetailDAO;
	}

	public void setMaLineTimeDetailDAO(MaLineTimeDetailDAO maLineTimeDetailDAO) {
		this.maLineTimeDetailDAO = maLineTimeDetailDAO;
	}

	public MaLineTimeDetailDTO findDetail(MaLineTimeCommonDTO maLineTimeCommonDTO,User user)throws Exception
    {
        return maLineTimeDetailDAO.findDetail(maLineTimeCommonDTO,user);
    }
	
	public int insertDetail(MaLineTimeDetailDTO maLineTimeDetailDTO, User user) throws Exception
	{
	    return maLineTimeDetailDAO.insertDetail(maLineTimeDetailDTO,user);
	}
	
	public int updateDetail(MaLineTimeDetailDTO maLineTimeDetailDTO) throws Exception
    {        
        return maLineTimeDetailDAO.updateDetail(maLineTimeDetailDTO);
    }
	
}
