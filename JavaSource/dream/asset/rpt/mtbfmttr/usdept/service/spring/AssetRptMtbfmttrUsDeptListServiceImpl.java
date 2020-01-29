package dream.asset.rpt.mtbfmttr.usdept.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dao.AssetRptMtbfmttrUsDeptListDAO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.service.AssetRptMtbfmttrUsDeptListService;

/**
 * MTBF,MTTR(사용부서) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMtbfmttrUsDeptListServiceTarget"
 * @spring.txbn id="assetRptMtbfmttrUsDeptListService"
 * @spring.property name="assetRptMtbfmttrUsDeptListDAO" ref="assetRptMtbfmttrUsDeptListDAO"
 */
public class AssetRptMtbfmttrUsDeptListServiceImpl implements AssetRptMtbfmttrUsDeptListService
{
    private AssetRptMtbfmttrUsDeptListDAO assetRptMtbfmttrUsDeptListDAO = null;
    
	public AssetRptMtbfmttrUsDeptListDAO getAssetRptMtbfmttrUsDeptListDAO()
    {
        return assetRptMtbfmttrUsDeptListDAO;
    }
	
    public void setAssetRptMtbfmttrUsDeptListDAO(
            AssetRptMtbfmttrUsDeptListDAO assetRptMtbfmttrUsDeptListDAO)
    {
        this.assetRptMtbfmttrUsDeptListDAO = assetRptMtbfmttrUsDeptListDAO;
    }
    
    public List findList(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User loginUser) throws Exception
    {
        return assetRptMtbfmttrUsDeptListDAO.findList(assetRptMtbfmttrUsDeptCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User user) throws Exception
    {
        return assetRptMtbfmttrUsDeptListDAO.findTotalCount(assetRptMtbfmttrUsDeptCommonDTO, user);
    }
	
}

