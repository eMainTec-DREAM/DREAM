package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrSpecDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;
import dream.asset.list.service.MaEqMstrSpecDetailService;

/**
 * 설비제원(스펙)
 * @author kim2107
 * @version $Id: MaEqMstrSpecDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrSpecDetailServiceTarget"
 * @spring.txbn id="maEqMstrSpecDetailService"
 * @spring.property name="maEqMstrSpecDetailDAO" ref="maEqMstrSpecDetailDAO"
 */
public class MaEqMstrSpecDetailServiceImpl implements MaEqMstrSpecDetailService
{
    private MaEqMstrSpecDetailDAO maEqMstrSpecDetailDAO = null;
    
    public MaEqMstrSpecDetailDAO getMaEqMstrSpecDetailDAO() {
		return maEqMstrSpecDetailDAO;
	}

	public void setMaEqMstrSpecDetailDAO(MaEqMstrSpecDetailDAO maEqMstrSpecDetailDAO) {
		this.maEqMstrSpecDetailDAO = maEqMstrSpecDetailDAO;
	}

	public MaEqMstrSpecDetailDTO findDetail(MaEqMstrSpecListDTO maEqMstrSpecListDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)throws Exception
    {
        return maEqMstrSpecDetailDAO.findDetail(maEqMstrCommonDTO, maEqMstrSpecListDTO, user);
    }
    
	public int updateDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {        
        return maEqMstrSpecDetailDAO.updateDetail(maEqMstrSpecDetailDTO, maEqMstrCommonDTO, user);
    }
	public int insertDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {        
        return maEqMstrSpecDetailDAO.insertDetail( maEqMstrSpecDetailDTO, maEqMstrCommonDTO, user);
    }
	public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception 
	{
		return maEqMstrSpecDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
	}
}
