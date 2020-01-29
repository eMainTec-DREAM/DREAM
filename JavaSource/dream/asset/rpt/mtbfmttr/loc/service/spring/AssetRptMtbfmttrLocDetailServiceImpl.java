package dream.asset.rpt.mtbfmttr.loc.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dao.AssetRptMtbfmttrLocDetailDAO;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocDetailDTO;
import dream.asset.rpt.mtbfmttr.loc.service.AssetRptMtbfmttrLocDetailService;

/**
 * MTBF,MTTR(위치) 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMtbfmttrLocDetailServiceTarget"
 * @spring.txbn id="assetRptMtbfmttrLocDetailService"
 * @spring.property name="assetRptMtbfmttrLocDetailDAO" ref="assetRptMtbfmttrLocDetailDAO"
 */
public class AssetRptMtbfmttrLocDetailServiceImpl implements AssetRptMtbfmttrLocDetailService
{
    private AssetRptMtbfmttrLocDetailDAO assetRptMtbfmttrLocDetailDAO = null;
    
    public AssetRptMtbfmttrLocDetailDAO getAssetRptMtbfmttrLocDetailDAO()
    {
        return assetRptMtbfmttrLocDetailDAO;
    }
    
    public void setAssetRptMtbfmttrLocDetailDAO(
            AssetRptMtbfmttrLocDetailDAO assetRptMtbfmttrLocDetailDAO)
    {
        this.assetRptMtbfmttrLocDetailDAO = assetRptMtbfmttrLocDetailDAO;
    }
    
    public List findDetail(AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO, User loginUser)
    {
        return assetRptMtbfmttrLocDetailDAO.findDetail(assetRptMtbfmttrLocDetailDTO, loginUser);
        
    }
	
}

