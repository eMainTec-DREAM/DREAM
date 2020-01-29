package dream.mgr.mail.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.mail.dao.MaMailUsrListDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;
import dream.mgr.mail.service.MaMailUsrListService;

/**
 * 메일수신자설정 - 수신자 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaMailUsrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMailUsrListServiceTarget"
 * @spring.txbn id="maMailUsrListService"
 * @spring.property name="maMailUsrListDAO" ref="maMailUsrListDAO"
 */
public class MaMailUsrListServiceImpl implements MaMailUsrListService
{
    private MaMailUsrListDAO maMailUsrListDAO = null;

    public MaMailUsrListDAO getMaMailUsrListDAO() {
		return maMailUsrListDAO;
	}
	public void setMaMailUsrListDAO(MaMailUsrListDAO maMailUsrListDAO) {
		this.maMailUsrListDAO = maMailUsrListDAO;
	}
	
	public List findUsrList(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user)
    {      
        return maMailUsrListDAO.findUsrList(maMailCommonDTO, maMailUsrListDTO, user);
    }
	
	public int deleteUsrList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maMailUsrListDAO.deleteUsrList(deleteRows[i], deleteRowsExt[i] );
        }
        
        return result;
    }
	@Override
	public String findTotalCount(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user)
			throws Exception {
		return maMailUsrListDAO.findTotalCount(maMailCommonDTO, maMailUsrListDTO, user);
	}
}

