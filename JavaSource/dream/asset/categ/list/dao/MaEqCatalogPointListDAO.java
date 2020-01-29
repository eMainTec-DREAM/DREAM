package dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * ���������� �����׸� ���� list dao
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MaEqCatalogPointListDAO
{   
	/**
	 * ���� �����׸� ��� �˻�
	 * @param maEqCatalogCommonDTO
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user) throws Exception;
    /**
     * ���� �����׸� ����
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    
    /**
	 * ���� �����׸� ��� ���� COUNT
	 * @param maEqCatalogCommonDTO
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user) throws Exception;

}