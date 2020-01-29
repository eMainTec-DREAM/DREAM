package dream.mgr.mail.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.mail.dao.MaMailListDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.service.MaMailListService;

/**
 * 메일수신자설정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaMailListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMailListServiceTarget"
 * @spring.txbn id="maMailListService"
 * @spring.property name="maMailListDAO" ref="maMailListDAO"
 */
public class MaMailListServiceImpl implements MaMailListService
{
    private MaMailListDAO maMailListDAO = null;

    public MaMailListDAO getMaMailListDAO() {
		return maMailListDAO;
	}

	public void setMaMailListDAO(MaMailListDAO maMailListDAO) {
		this.maMailListDAO = maMailListDAO;
	}

	public List findMailList(MaMailCommonDTO maMailCommonDTO, User user)
    {      
        return maMailListDAO.findMailList(maMailCommonDTO,user );
    }
	
	public int deleteMail(String[] deleteRows) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maMailListDAO.deleteMail(id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaMailCommonDTO maMailCommonDTO, User user)
	{
		return maMailListDAO.findTotalCount(maMailCommonDTO, user);
	}
}

