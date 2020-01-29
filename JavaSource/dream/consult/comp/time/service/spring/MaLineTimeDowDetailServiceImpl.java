package dream.consult.comp.time.service.spring;

import common.bean.User;
import dream.consult.comp.time.dao.MaLineTimeDowDetailDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowDetailDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;
import dream.consult.comp.time.service.MaLineTimeDowDetailService;

/**
 * 요일별 설정
 * @author kim2107
 * @version $Id: MaLineTimeDowDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maLineTimeDowDetailServiceTarget"
 * @spring.txbn id="maLineTimeDowDetailService"
 * @spring.property name="maLineTimeDowDetailDAO" ref="maLineTimeDowDetailDAO"
 */
public class MaLineTimeDowDetailServiceImpl implements MaLineTimeDowDetailService
{
    private MaLineTimeDowDetailDAO maLineTimeDowDetailDAO = null;
    
    public MaLineTimeDowDetailDAO getMaLineTimeDowDetailDAO() {
		return maLineTimeDowDetailDAO;
	}

	public void setMaLineTimeDowDetailDAO(MaLineTimeDowDetailDAO maLineTimeDowDetailDAO) {
		this.maLineTimeDowDetailDAO = maLineTimeDowDetailDAO;
	}

	public MaLineTimeDowDetailDTO findDetail(MaLineTimeDowListDTO maLineTimeDowListDTO, User user)throws Exception
    {
        return maLineTimeDowDetailDAO.findDetail(maLineTimeDowListDTO, user);
    }
    
	public int updateDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user) throws Exception
    {        
		maLineTimeDowDetailDAO.updateDetail(maLineTimeDowDetailDTO, user);	
		maLineTimeDowDetailDAO.deleteWrkTime(maLineTimeDowDetailDTO,user);
        return maLineTimeDowDetailDAO.execRunTime(maLineTimeDowDetailDTO, user);
    }
	public int insertDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user) throws Exception
    {   
		maLineTimeDowDetailDAO.insertDetail(maLineTimeDowDetailDTO, user);     
		maLineTimeDowDetailDAO.deleteWrkTime(maLineTimeDowDetailDTO,user);
        return maLineTimeDowDetailDAO.execRunTime(maLineTimeDowDetailDTO, user);
    }
	public String validDay(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user) throws Exception
    {        
        return maLineTimeDowDetailDAO.validDay(maLineTimeDowDetailDTO, user);
    }
}
