package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrAssetDetailDAO;
import dream.asset.list.dto.MaEqMstrAssetDetailDTO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrAssetDetailService;

/**
 * 관련자산
 * @author kim2107
 * @version $Id: MaEqMstrAssetDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrAssetDetailServiceTarget"
 * @spring.txbn id="maEqMstrAssetDetailService"
 * @spring.property name="maEqMstrAssetDetailDAO" ref="maEqMstrAssetDetailDAO"
 */
public class MaEqMstrAssetDetailServiceImpl implements MaEqMstrAssetDetailService
{
    private MaEqMstrAssetDetailDAO maEqMstrAssetDetailDAO = null;
    
    public MaEqMstrAssetDetailDAO getMaEqMstrAssetDetailDAO() {
		return maEqMstrAssetDetailDAO;
	}

	public void setMaEqMstrAssetDetailDAO(MaEqMstrAssetDetailDAO maEqMstrAssetDetailDAO) {
		this.maEqMstrAssetDetailDAO = maEqMstrAssetDetailDAO;
	}

	public MaEqMstrAssetDetailDTO findDetail(MaEqMstrAssetListDTO maEqMstrAssetListDTO, User user)throws Exception
    {
        return maEqMstrAssetDetailDAO.findDetail(maEqMstrAssetListDTO, user);
    }
    
	public int updateDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, User user) throws Exception
    {        
        return maEqMstrAssetDetailDAO.updateDetail(maEqMstrAssetDetailDTO, user);
    }
	public int insertDetail(MaEqMstrAssetDetailDTO maEqMstrAssetDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {        
        return maEqMstrAssetDetailDAO.insertDetail( maEqMstrAssetDetailDTO, maEqMstrCommonDTO, user);
    }

	public int copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception 
	{
		return maEqMstrAssetDetailDAO.copyDetail(oldEquipId, newEquipId, oldKeyId, newKeyId, user);
	}
}
