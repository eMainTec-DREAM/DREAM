package dream.consult.program.lang.service.spring;

import common.bean.User;
import dream.consult.program.lang.dao.MaLangMngDetailDAO;
import dream.consult.program.lang.dto.MaLangMngDetailDTO;
import dream.consult.program.lang.service.MaLangMngDetailService;

/**
 * 다국어 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaLangMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maLangMngDetailServiceTarget"
 * @spring.txbn id="maLangMngDetailService"
 * @spring.property name="maLangMngDetailDAO" ref="maLangMngDetailDAO"
 */
public class MaLangMngDetailServiceImpl implements MaLangMngDetailService
{
    private MaLangMngDetailDAO maLangMngDetailDAO = null;
    
    public MaLangMngDetailDAO getMaLangMngDetailDAO() {
		return maLangMngDetailDAO;
	}

	public void setMaLangMngDetailDAO(MaLangMngDetailDAO maLangMngDetailDAO) {
		this.maLangMngDetailDAO = maLangMngDetailDAO;
	}

	public MaLangMngDetailDTO findDetail(String langId, User user)throws Exception
    {
        return maLangMngDetailDAO.findDetail(langId, user);
    }
    
	public int updateDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user) throws Exception
    {        
        return maLangMngDetailDAO.updateDetail(maLangMngDetailDTO, user);
    }
	public int insertDetail(MaLangMngDetailDTO maLangMngDetailDTO, User user) throws Exception
    {        
        return maLangMngDetailDAO.insertDetail(maLangMngDetailDTO, user);
    }
	public String validKeyId(MaLangMngDetailDTO maLangMngDetailDTO, User user) throws Exception
    {
        return maLangMngDetailDAO.validKeyId(maLangMngDetailDTO, user);
    }
}
