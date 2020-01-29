package dream.asset.categ.list.service;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * ���������� �����׸� �� detail service
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailService.java,v 1.0 2015/12/04 09:08:29 euna0207 Exp $
 * @since   1.0
 */
public interface MaEqCatalogPointDetailService
{    
	/**
	 * ���� �����۾� �� ��ȸ
	 * @param maEqCatalogCommonDTO
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqCatalogPointDetailDTO findDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user)throws Exception;
    /**
     * ���� �����۾� ���� 
     * @param maEqCatalogCommonDTO
     * @param maEqCatalogPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception;
    /**
     * ���� �����۾� �Է�
     * @param maEqCatalogCommonDTO
     * @param maEqCatalogPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception;
}
