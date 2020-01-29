package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPartDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;
import dream.asset.list.service.MaEqMstrPartDetailService;

/**
 * 구성자재
 * @author kim2107
 * @version $Id: MaEqMstrPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPartDetailServiceTarget"
 * @spring.txbn id="maEqMstrPartDetailService"
 * @spring.property name="maEqMstrPartDetailDAO" ref="maEqMstrPartDetailDAO"
 */
public class MaEqMstrPartDetailServiceImpl implements MaEqMstrPartDetailService
{
    private MaEqMstrPartDetailDAO maEqMstrPartDetailDAO = null;
    
    public MaEqMstrPartDetailDAO getMaEqMstrPartDetailDAO() {
		return maEqMstrPartDetailDAO;
	}

	public void setMaEqMstrPartDetailDAO(MaEqMstrPartDetailDAO maEqMstrPartDetailDAO) {
		this.maEqMstrPartDetailDAO = maEqMstrPartDetailDAO;
	}

	public MaEqMstrPartDetailDTO findDetail(MaEqMstrPartListDTO maEqMstrPartListDTO, User user)throws Exception
    {
        return maEqMstrPartDetailDAO.findDetail(maEqMstrPartListDTO, user);
    }
    
	public int updateDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {        
        return maEqMstrPartDetailDAO.updateDetail(maEqMstrPartDetailDTO, maEqMstrCommonDTO, user);
    }
	public int insertDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {        
        return maEqMstrPartDetailDAO.insertDetail( maEqMstrPartDetailDTO, maEqMstrCommonDTO, user);
    }

	public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception 
	{
		return maEqMstrPartDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
	}
	
}
