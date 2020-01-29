package dream.asset.categ.list.service;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * 설비종류의 점검항목 탭 detail service
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailService.java,v 1.0 2015/12/04 09:08:29 euna0207 Exp $
 * @since   1.0
 */
public interface MaEqCatalogPointDetailService
{    
	/**
	 * 설비 예방작업 상세 조회
	 * @param maEqCatalogCommonDTO
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqCatalogPointDetailDTO findDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user)throws Exception;
    /**
     * 설비 예방작업 수정 
     * @param maEqCatalogCommonDTO
     * @param maEqCatalogPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception;
    /**
     * 설비 예방작업 입력
     * @param maEqCatalogCommonDTO
     * @param maEqCatalogPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user) throws Exception;
}
