package dream.asset.categ.list.service.spring;

import common.bean.User;
import dream.asset.categ.list.dao.MaEqCatalogPointDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;
import dream.asset.categ.list.service.MaEqCatalogPointDetailService;

/**
 * 설비종류의 점검항목 탭의 detail service impl
 * @author euna0207
 * @version $Id: MaEqCatalogPointDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCatalogPointDetailServiceTarget"
 * @spring.txbn id="maEqCatalogPointDetailService"
 * @spring.property name="maEqCatalogPointDetailDAO" ref="maEqCatalogPointDetailDAO"
 */
public class MaEqCatalogPointDetailServiceImpl implements MaEqCatalogPointDetailService
{
	private MaEqCatalogPointDetailDAO maEqCatalogPointDetailDAO = null;
    
	
	public MaEqCatalogPointDetailDAO getMaEqCatalogPointDetailDAO() {
		return maEqCatalogPointDetailDAO;
	}

	public void setMaEqCatalogPointDetailDAO(MaEqCatalogPointDetailDAO maEqCatalogPointDetailDAO) {
		this.maEqCatalogPointDetailDAO = maEqCatalogPointDetailDAO;
	}

	@Override
	public MaEqCatalogPointDetailDTO findDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO,
			MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user) throws Exception {
		return maEqCatalogPointDetailDAO.findDetail(maEqCatalogCommonDTO, maEqCatalogPointListDTO, user);
	}

	@Override
	public int updateDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO,
			MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception {
		return maEqCatalogPointDetailDAO.updateDetail(maEqCatalogCommonDTO, maEqCatalogPointDetailDTO, user);
	}

	@Override
	public int insertDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO,
			MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception {
		return maEqCatalogPointDetailDAO.insertDetail(maEqCatalogCommonDTO, maEqCatalogPointDetailDTO, user);
	}
    
}
