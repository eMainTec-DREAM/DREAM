package dream.asset.categ.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.categ.list.dao.MaEqCtgPartListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;
import dream.asset.categ.list.service.MaEqCtgPartDetailService;
import dream.asset.categ.list.service.MaEqCtgPartListService;
import dream.asset.list.service.MaEqMstrPartListService;

/**
 * 설비종류별 부품 목록
 * @author kim21017
 * @version $Id: MaEqCtgPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCtgPartListServiceTarget"
 * @spring.txbn id="maEqCtgPartListService"
 * @spring.property name="maEqCtgPartListDAO" ref="maEqCtgPartListDAO"
 * @spring.property name="maEqCtgPartDetailService" ref="maEqCtgPartDetailService"
 */
public class MaEqCtgPartListServiceImpl implements MaEqCtgPartListService
{
    private MaEqCtgPartListDAO maEqCtgPartListDAO = null;
    private MaEqCtgPartDetailService maEqCtgPartDetailService = null;

	public MaEqCtgPartDetailService getMaEqCtgPartDetailService()
    {
        return maEqCtgPartDetailService;
    }

    public void setMaEqCtgPartDetailService(
            MaEqCtgPartDetailService maEqCtgPartDetailService)
    {
        this.maEqCtgPartDetailService = maEqCtgPartDetailService;
    }

    public List findPartList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgPartListDTO maEqCtgPartListDTO, User user)
    {      
        return maEqCtgPartListDAO.findPartList(maEqCatalogCommonDTO, maEqCtgPartListDTO, user);
    }

	public MaEqCtgPartListDAO getMaEqCtgPartListDAO() {
		return maEqCtgPartListDAO;
	}

	public void setMaEqCtgPartListDAO(MaEqCtgPartListDAO maEqCtgPartListDAO) {
		this.maEqCtgPartListDAO = maEqCtgPartListDAO;
	}
	
	public int deletePartList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                //1. 관련 설비부품의 공통부품여부 N
                MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) CommonUtil.getBean("maEqMstrPartListService", compNo);
                User user = new User();
                user.setCompNo(compNo);
                result = result + maEqMstrPartListService.undoEqCtgPart(id, user);
                //2. 종류별부품 데이타 삭제
                result = result + maEqCtgPartListDAO.deletePartList(id, compNo);
            }
        
        return result;
    }
	public String findTotalCount(MaEqCtgPartListDTO maEqCtgPartListDTO, User user) throws Exception
    {      
        return maEqCtgPartListDAO.findTotalCount(maEqCtgPartListDTO,user);
    }

    @Override
    public int insertPartList(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maEqCtgPartDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maEqCtgPartDetailDTO.setEqCtgPartId(maEqCtgPartListDAO.getNextSequence("SQAEQ_CTG_PART_ID"));
            maEqCtgPartDetailDTO.setPartId(multiKey[i]);
            result = result + maEqCtgPartDetailService.insertDetail(maEqCtgPartDetailDTO, maEqCatalogCommonDTO, user);
        }
        
        return result;
    }
}

