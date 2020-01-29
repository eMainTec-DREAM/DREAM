package dream.asset.categ.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dao.MaEqCatalogPointListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;
import dream.asset.categ.list.service.MaEqCatalogPointListService;

/**
 * 설비종류의 점검항목 탭의 list service impl
 * @author euna0207
 * @version $Id: MaEqCatalogPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCatalogPointListServiceTarget"
 * @spring.txbn id="maEqCatalogPointListService"
 * @spring.property name="maEqCatalogPointListDAO" ref="maEqCatalogPointListDAO"
 */
public class MaEqCatalogPointListServiceImpl implements MaEqCatalogPointListService
{
    private MaEqCatalogPointListDAO maEqCatalogPointListDAO = null;

    public MaEqCatalogPointListDAO getMaEqCatalogPointListDAO() {
		return maEqCatalogPointListDAO;
	}

	public void setMaEqCatalogPointListDAO(MaEqCatalogPointListDAO maEqCatalogPointListDAO) {
		this.maEqCatalogPointListDAO = maEqCatalogPointListDAO;
	}

	@Override
	public List findList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO,
			User user) throws Exception {
		
		return maEqCatalogPointListDAO.findList(maEqCatalogCommonDTO, maEqCatalogPointListDTO, user);
	}

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqCatalogPointListDAO.deleteList(id, user);
            }
        return result;
    }

	@Override
	public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO,
			MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user) throws Exception {
		return maEqCatalogPointListDAO.findTotalCount(maEqCatalogCommonDTO, maEqCatalogPointListDTO, user);
	}

	
    
}