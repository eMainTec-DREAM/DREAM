package dream.asset.categ.list.dao;


import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * ���������� �����׸� ���� detail dao
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 euna0207 Exp $
 * @since   1.0
 */
public interface MaEqCatalogPointDetailDAO extends BaseJdbcDaoSupportIntf
{    
	/**
	 * �����׸� ����ȸ
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqCatalogPointDetailDTO findDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user)throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workLetCategPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workLetCategPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception;
    
    
}