package dream.asset.categ.list.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.categ.list.dao.MaEqCtgAsmbListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.asset.categ.list.service.MaEqCtgAsmbListService;
import dream.asset.list.service.MaEqMstrAsmbListService;

/**
 * 설비종류별 작업부위 목록
 * @author kim21017
 * @version $Id: MaEqCtgAsmbListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqCtgAsmbListServiceTarget"
 * @spring.txbn id="maEqCtgAsmbListService"
 * @spring.property name="maEqCtgAsmbListDAO" ref="maEqCtgAsmbListDAO"
 */
public class MaEqCtgAsmbListServiceImpl implements MaEqCtgAsmbListService
{
    private MaEqCtgAsmbListDAO maEqCtgAsmbListDAO = null;


	public List findAsmbList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User loginUser)
    {         
        return maEqCtgAsmbListDAO.findAsmbList(maEqCatalogCommonDTO, maEqCtgAsmbListDTO, loginUser);
    }
	
	public MaEqCtgAsmbListDAO getMaEqCtgAsmbListDAO() {
		return maEqCtgAsmbListDAO;
	}

	public void setMaEqCtgAsmbListDAO(MaEqCtgAsmbListDAO maEqCtgAsmbListDAO) {
		this.maEqCtgAsmbListDAO = maEqCtgAsmbListDAO;
	}
	
	public int deleteAsmbList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                //1. 관련 설비부위의 공통부위여부 N
            	MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", compNo);
            	User user = new User();
            	user.setCompNo(compNo);
            	result = result + maEqMstrAsmbListService.undoEqCtgAsmb(id, user);
            	//2. 종류별 부위 데이타 삭제
            	result = result + maEqCtgAsmbListDAO.deleteAsmbList(id, compNo);
            }
        
        return result;
    }
	
	public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user) throws Exception
    {      
        return maEqCtgAsmbListDAO.findTotalCount(maEqCatalogCommonDTO,maEqCtgAsmbListDTO,user);
    }
}

