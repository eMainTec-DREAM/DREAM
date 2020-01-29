package dream.asset.std.ctctr.service.spring;

import common.bean.User;
import dream.asset.std.ctctr.dao.AssetStdCtctrDetailDAO;
import dream.asset.std.ctctr.dto.AssetStdCtctrDetailDTO;
import dream.asset.std.ctctr.service.AssetStdCtctrDetailService;

/**
 * CostCenter - »ó¼¼ serviceimpl 
 * @author  ghlee
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="assetStdCtctrDetailServiceTarget"
 * @spring.txbn id="assetStdCtctrDetailService"
 * @spring.property name="assetStdCtctrDetailDAO" ref="assetStdCtctrDetailDAO"
 */
public class AssetStdCtctrDetailServiceImpl implements AssetStdCtctrDetailService
{
    private AssetStdCtctrDetailDAO assetStdCtctrDetailDAO = null;
    
    public AssetStdCtctrDetailDAO getAssetStdCtctrDetailDAO() {
		return assetStdCtctrDetailDAO;
	}

	public void setAssetStdCtctrDetailDAO(AssetStdCtctrDetailDAO assetStdCtctrDetailDAO) {
		this.assetStdCtctrDetailDAO = assetStdCtctrDetailDAO;
	}

	public AssetStdCtctrDetailDTO findDetail(User user, String assetNo)throws Exception
    {
        return assetStdCtctrDetailDAO.findDetail(user, assetNo);
    }
    
	public int insertDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user) throws Exception
    {        
        return assetStdCtctrDetailDAO.insertDetail(assetStdCtctrDetailDTO, user);
    }
	
	public int updateDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user) throws Exception
    {        
        return assetStdCtctrDetailDAO.updateDetail(assetStdCtctrDetailDTO, user);
    }
	
	public String validCtctrNo(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user) throws Exception
    {
        return assetStdCtctrDetailDAO.validCtctrNo(assetStdCtctrDetailDTO, user);
    }
}
