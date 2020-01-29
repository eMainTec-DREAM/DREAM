package dream.asset.rpt.lcc.loc.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.loc.dao.AssetRptLccLocDetailDAO;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;
import dream.asset.rpt.lcc.loc.service.AssetRptLccLocDetailService;

/**
 * ����TOP(��ġ) �� ���
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptLccLocDetailServiceTarget"
 * @spring.txbn id="assetRptLccLocDetailService"
 * @spring.property name="assetRptLccLocDetailDAO" ref="assetRptLccLocDetailDAO"
 */
public class AssetRptLccLocDetailServiceImpl implements AssetRptLccLocDetailService
{
    private AssetRptLccLocDetailDAO assetRptLccLocDetailDAO = null;
    
    public AssetRptLccLocDetailDAO getAssetRptLccLocDetailDAO()
    {
        return assetRptLccLocDetailDAO;
    }
    
    public void setAssetRptLccLocDetailDAO(
            AssetRptLccLocDetailDAO assetRptLccLocDetailDAO)
    {
        this.assetRptLccLocDetailDAO = assetRptLccLocDetailDAO;
    }
    
    public List findDetail(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User loginUser)
    {
        return assetRptLccLocDetailDAO.findDetail(assetRptLccLocDetailDTO, loginUser);
        
    }

    @Override
    public List findMo(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user)
    {
        return assetRptLccLocDetailDAO.findMo(assetRptLccLocDetailDTO, user);
    }

    @Override
    public List findCa(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user)
    {
        return assetRptLccLocDetailDAO.findCa(assetRptLccLocDetailDTO, user);
    }

    @Override
    public List findRe(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user)
    {
        return assetRptLccLocDetailDAO.findRe(assetRptLccLocDetailDTO, user);
    }
	
}

