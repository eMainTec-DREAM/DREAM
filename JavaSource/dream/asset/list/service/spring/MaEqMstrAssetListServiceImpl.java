package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrAssetListDAO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrAssetListService;

/**
 * 관련자산 목록
 * @author kim21017
 * @version $Id: MaEqMstrAssetListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrAssetListServiceTarget"
 * @spring.txbn id="maEqMstrAssetListService"
 * @spring.property name="maEqMstrAssetListDAO" ref="maEqMstrAssetListDAO"
 */
public class MaEqMstrAssetListServiceImpl implements MaEqMstrAssetListService
{
    private MaEqMstrAssetListDAO maEqMstrAssetListDAO = null;


	public List findAssetList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User loginUser )
    {      
        return maEqMstrAssetListDAO.findAssetList(maEqMstrCommonDTO, maEqMstrAssetListDTO, loginUser);
    }

	public MaEqMstrAssetListDAO getMaEqMstrAssetListDAO() {
		return maEqMstrAssetListDAO;
	}

	public void setMaEqMstrAssetListDAO(MaEqMstrAssetListDAO maEqMstrAssetListDAO) {
		this.maEqMstrAssetListDAO = maEqMstrAssetListDAO;
	}
	
	public int deleteAssetList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqMstrAssetListDAO.deleteAssetList(id, compNo);
            }
        
        return result;
    }
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO,User user)  throws Exception
    {
        return maEqMstrAssetListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrAssetListDTO, user);
    }
}

